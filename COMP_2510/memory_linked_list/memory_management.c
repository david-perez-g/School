#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#ifndef MEMORY_MANAGEMENT
#define MEMORY_MANAGEMENT
#endif

#define UNUSED_CHUNK 0

typedef unsigned int uint;

/**
 * Information about a memory of memory within a memory_block.
*/
typedef struct memory_chunk_t {
    /**
     * The ID of the process that's currently using this memory memory.
     * If there's no process using this memory, it will be UNUSED_CHUNK instead.
    */
    uint process_id;
    
    /**
     * Starting index in memory of the memory.
    */
    uint base;

    /**
     * Size of the memory in bytes.
    */
    uint limit; 

    /**
     * Chunk of memory following this chunk.
     * Could be NULL if this is the last chunk.
    */
    struct memory_chunk_t* next;
} memory_chunk_t;

memory_chunk_t* create_memory_chunk(uint base, uint limit, memory_chunk_t* next, uint id) {
    memory_chunk_t* chunk = (memory_chunk_t*)malloc(sizeof(memory_chunk_t));
    chunk->base = base;
    chunk->limit = limit;
    chunk->next = next;
    chunk->process_id = id;
    return chunk;
}

/**
 * 
*/
typedef struct memory_t {
    /**
     * Total size in bytes of the memory.
    */
    uint total_bytes;

    /**
     * Amount of bytes available in the memory.
    */
    uint available_bytes;

    /**
     * Initial memory chunk.
    */
    memory_chunk_t* start;

    /**
     * Starting chunk of unused memory. 
     * The unused memory usually comes after the used chunks.
     * However, after certain deallocations this chunk may not
     * refer to the actual first unused chunk of memory.
     * In those scenarios a compaction operation will be needed.
    */
    memory_chunk_t* unused;

    /**
     * The ID of the last added process to the memory.
    */
    uint last_added_process_id;
} memory_t;

memory_t* create_memory(uint size) {
    memory_chunk_t* initial = create_memory_chunk(
            0, size, NULL, UNUSED_CHUNK
        );
    memory_t* memory = (memory_t*)malloc(sizeof(memory_t));
    memory->total_bytes = size;
    memory->available_bytes = size;
    memory->start = initial;
    memory->unused = initial;
    memory->last_added_process_id = 0;
    return memory;
}

bool __is_unused(const memory_chunk_t* chunk) {
    if (chunk == NULL) {
        return false;
    }

    return chunk->process_id == UNUSED_CHUNK;
}

/**
 * Joins two contiguous memory chunks.
 * The other chunk will be free'd after joining.
 */
void __join(memory_chunk_t* chunk, memory_chunk_t* other) {
    chunk->limit = chunk->limit + other->limit;
    chunk->next = other->next;
    free(other);
}

/**
 * Joins all contiguous unused memory chunks.
 */
void __merge_free_blocks(memory_t* memory) {
    memory_chunk_t* chunk = memory->start;
    
    while(chunk != NULL) {
        if (__is_unused(chunk) && __is_unused(chunk->next)) {
            // the other chunk is free'd here
            __join(chunk, chunk->next);
            continue;
        }
        chunk = chunk->next;
    }
}

/**
 * Moves all used chunks to the beginning of the memory, followed by all unused chunks.
 * After moving all chunks, it calls the __merge_free_blocks function to merge
 * adjacent free blocks.
 */
void __compaction(memory_t* memory) {
    memory_chunk_t* chunk = memory->start;
    memory_chunk_t* last_unused_chunk = NULL;
    memory_chunk_t* last_used_chunk = NULL;

    while(chunk != NULL) {
        if (chunk->process_id == UNUSED_CHUNK) {
            if (last_unused_chunk == NULL) {
                memory->unused = chunk;
                last_unused_chunk = chunk;
                chunk = chunk->next;
                continue;
            }

            last_unused_chunk->next = chunk;
            chunk = chunk->next;
            continue;
        }

        if (last_used_chunk == NULL) {
            memory->start = chunk;
            last_used_chunk = chunk;
            chunk = chunk->next;
            continue;
        }

        last_used_chunk->next = chunk;
        chunk = chunk->next;
    }

    if (last_used_chunk != NULL) {
        last_used_chunk->next = memory->unused;
    }
    __merge_free_blocks(memory);
}

void __split_chunk(memory_t* memory, memory_chunk_t* chunk, uint bytes) {
    memory->last_added_process_id += 1;
    chunk->process_id = memory->last_added_process_id;

    memory_chunk_t* next_chunk = NULL;

    // checking if the resulting chunk after the split has a size bigger than 0
    if ((int)(chunk->limit) - (int)bytes > 0) {
        next_chunk = create_memory_chunk(
            chunk->base + bytes, // starting index
            chunk->limit - bytes, // size
            chunk->next, // next chunk
            UNUSED_CHUNK // id
        );
    }

    chunk->limit = bytes;
    chunk->next = next_chunk;
}


/**
 * Check whether is possible to allocate the given amount of bytes
 * in the memory chunk.
*/
bool __can_allocate(const memory_chunk_t* chunk, uint bytes) {
    // you can't allocate memory on an already used chunk
    if (chunk->process_id != UNUSED_CHUNK) {
        return false;
    }

    return chunk->limit >= bytes;
}

/**
 * Will search starting from memory->unused an unused chunk that can
 * hold the given number of bytes. 
 * Will return NULL if none.
 */
memory_chunk_t* __find_available_chunk(const memory_t* memory, uint bytes) {
    memory_chunk_t* chunk = memory->unused;

    while(chunk != NULL) {
        if (__can_allocate(chunk, bytes)) {
            return chunk;
        }
        chunk = chunk->next;
    }

    return NULL;
}

/**
 * Allocate N bytes into the given memory memory.
 * If there's not enough space available the function 
 * will return NULL.
*/
memory_chunk_t* allocate(memory_t* memory, uint bytes) {
    if (bytes > memory->available_bytes) {
        return NULL;
    }

    memory_chunk_t* chunk = __find_available_chunk(memory, bytes);

    // If it can't be allocated at any unused
    // chunk of memory and there are
    // enough bytes available, the compaction
    // operation is needed and will be performed.
    if (chunk == NULL) {
        __compaction(memory);
        // After this operation chunk shouldn't be NULL
        chunk = __find_available_chunk(memory, bytes);
        if (chunk == NULL) {
            perror("internal error while allocating bytes");
            return NULL;
        }
    }

    // Will resize the chunk to hold only the requested bytes,
    // the other unused bytes will be a part of the next chunk.
    __split_chunk(memory, chunk, bytes);
    memory->available_bytes -= bytes;
    memory->unused = chunk->next;
    return chunk;
}

/**
 * Deallocates the chunk of memory used by the
 * given process.
 */
void deallocate(memory_t* memory, uint process_id) {
    memory_chunk_t* chunk = memory->start;
    
    while (chunk != NULL) {
        if (chunk->process_id == process_id) {
            chunk->process_id = UNUSED_CHUNK;
            memory->available_bytes += chunk->limit;
            return;
        }
        chunk = chunk->next;
    }
}

void print_memory(const memory_t* memory) {
    if (memory == NULL || memory->start == NULL) {
        printf("Memory is empty.\n");
        return;
    }

    memory_chunk_t* chunk = memory->start;
    
    while(chunk != NULL) {
        if (chunk->process_id == UNUSED_CHUNK) {
            printf("Node Process: none, Base: %d, Limit: %d\n",
                chunk->base, chunk->limit
            );
        } else {
            printf("Node Process: %d, Base: %d, Limit: %d\n",
                chunk->process_id, chunk->base, chunk->limit
            );
        }

        chunk = chunk->next;
    }
}