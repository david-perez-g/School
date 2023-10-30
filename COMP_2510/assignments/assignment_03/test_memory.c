#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

#include "memory.h"

int main(void) {
    memory_t* memory = memory_create(100);

    // Test allocating more bytes than available
    memory_chunk_t* chunk = memory_alloc(memory, 101);
    assert(chunk == NULL);

    // Test allocating all available bytes
    chunk = memory_alloc(memory, 100);
    assert(chunk != NULL);
    assert(chunk->process_id == 1);
    assert(chunk->base == 0);
    assert(chunk->limit == 100);
    assert(memory->available_bytes == 0);

    // Test allocating when no space is available
    chunk = memory_alloc(memory, 1);
    assert(chunk == NULL);

    // Test deallocating the first process
    memory_dealloc(memory, 1);
    assert(memory->available_bytes == 100);

    // Test allocating after deallocating
    chunk = memory_alloc(memory, 50);
    assert(chunk != NULL);
    assert(chunk->process_id == 2);
    assert(chunk->base == 0);
    assert(chunk->limit == 50);
    assert(memory->available_bytes == 50);

    // Test allocating remaining space
    chunk = memory_alloc(memory, 50);
    assert(chunk != NULL);
    assert(chunk->process_id == 3);
    assert(chunk->base == 50);
    assert(chunk->limit == 50);
    assert(memory->available_bytes == 0);
    
    printf("Tests passed\n");
    return EXIT_SUCCESS;
}
