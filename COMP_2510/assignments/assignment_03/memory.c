#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "memory.h"

#define UNUSED_CHUNK 0

static memory_chunk_t* create_memory_chunk(uint base, uint limit, memory_chunk_t* next, uint id) {
    memory_chunk_t* chunk = (memory_chunk_t*)malloc(sizeof(memory_chunk_t));
    chunk->base = base;
    chunk->limit = limit;
    chunk->next = next;
    chunk->process_id = id;
    return chunk;
}

memory_t* memory_create(uint size) {
    memory_chunk_t* initial_chunk = create_memory_chunk(
            0, size, NULL, UNUSED_CHUNK
        );
    memory_t* memory = (memory_t*)malloc(sizeof(memory_t));
    memory->total_bytes = size;
    memory->available_bytes = size;
    memory->start = initial_chunk;
    memory->unused = initial_chunk;
    memory->last_added_process_id = 0;
    return memory;
}

static bool is_unused(const memory_chunk_t* chunk) {
    if (chunk == NULL) {
        return false;
    }

    return chunk->process_id == UNUSED_CHUNK;
}

/**
 * Joins two contiguous memory chunks.
 * The other chunk will be free'd after joining.
 */
static void join(memory_chunk_t* chunk, memory_chunk_t* other) {
    chunk->limit = chunk->limit + other->limit;
    chunk->next = other->next;
    free(other);
}

/**
 * Joins all contiguous unused memory chunks.
 */
static void merge_free_blocks(memory_t* memory) {
    memory_chunk_t* chunk = memory->start;
    
    while(chunk != NULL) {
        if (is_unused(chunk) && is_unused(chunk->next)) {
            // the other chunk is free'd here
            join(chunk, chunk->next);
            continue;
        }
        chunk = chunk->next;
    }
}

/**
 * Moves all used chunks to the beginning of the memory, followed by all unused chunks.
 * After moving all chunks, it calls the merge_free_blocks function to merge
 * adjacent free blocks.
 */
static void compact(memory_t* memory) {
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
    merge_free_blocks(memory);
}

static void split_chunk(memory_t* memory, memory_chunk_t* chunk, uint bytes) {
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
static bool can_allocate(const memory_chunk_t* chunk, uint bytes) {
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
static memory_chunk_t* find_available_chunk(const memory_t* memory, uint bytes) {
    memory_chunk_t* chunk = memory->unused;

    while(chunk != NULL) {
        if (can_allocate(chunk, bytes)) {
            return chunk;
        }
        chunk = chunk->next;
    }

    return NULL;
}

memory_chunk_t* memory_alloc(memory_t* memory, uint bytes) {
    if (bytes > memory->available_bytes) {
        return NULL;
    }

    memory_chunk_t* chunk = find_available_chunk(memory, bytes);

    // If it can't be allocated at any unused chunk of memory and 
    // there are enough bytes available, the compaction operation 
    // is needed and will be performed.
    if (chunk == NULL) {
        compact(memory);
        // After this operation chunk shouldn't be NULL
        chunk = find_available_chunk(memory, bytes);

        if (chunk == NULL) {
            perror("internal error while allocating bytes");
            return NULL;
        }
    }

    // Will resize the chunk to hold only the requested bytes,
    // the other unused bytes will be a part of the next chunk.
    split_chunk(memory, chunk, bytes);
    memory->available_bytes -= bytes;
    memory->unused = chunk->next;
    return chunk;
}

void memory_dealloc(memory_t* memory, uint process_id) {
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

void memory_chunk_dealloc(memory_t* memory, memory_chunk_t* chunk) {
    memory_dealloc(memory, chunk->process_id);
}


void memory_print(const memory_t* memory) {
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