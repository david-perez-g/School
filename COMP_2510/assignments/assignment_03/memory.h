#ifndef MEMORY_LLIST_H
#define MEMORY_LLIST_H

typedef unsigned int uint;

/**
 * A piece of memory.
*/
typedef struct memory_chunk_t {
    /**
     * The ID of the process that's currently using this chunk.
     * If there's no process using this chunk, it will be UNUSED_CHUNK instead.
    */
    uint process_id;
    
    /**
     * Starting index in memory of the chunk.
    */
    uint base;

    /**
     * Size of the chunk in bytes.
    */
    uint limit; 

    /**
     * Chunk of memory following this chunk.
     * Could be NULL if this is the last chunk.
    */
    struct memory_chunk_t* next;
} memory_chunk_t;

/** 
 * The memory of the system.
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

/**
 * Create a memory_t structure for the system with the given bytes.
 */
extern memory_t* memory_create(uint size);

/**
 * Allocate N bytes into the given memory memory.
 * If there's not enough space available the function 
 * will return NULL.
*/
extern memory_chunk_t* memory_alloc(memory_t* memory, uint bytes);

/**
 * Deallocates the chunk of memory used by the
 * given process.
 */
extern void memory_dealloc(memory_t* memory, uint process_id);

/**
 * Deallocates the given chunk of memory.
 */
extern void memory_chunk_dealloc(memory_t* memory, memory_chunk_t* chunk);

extern void memory_print(const memory_t* memory);

#endif
