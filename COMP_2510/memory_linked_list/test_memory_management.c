#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include "memory_management.c"

#include <assert.h>

void test_allocate() {
    memory_t* memory = create_memory(100);

    // Test allocating more bytes than available
    memory_chunk_t* chunk = allocate(memory, 101);
    assert(chunk == NULL);

    // Test allocating all available bytes
    chunk = allocate(memory, 100);
    assert(chunk != NULL);
    assert(chunk->process_id == 1);
    assert(chunk->base == 0);
    assert(chunk->limit == 100);
    assert(memory->available_bytes == 0);

    // Test allocating when no space is available
    chunk = allocate(memory, 1);
    assert(chunk == NULL);

    // Test deallocating
    deallocate(memory, 1);
    assert(memory->available_bytes == 100);

    // Test allocating after deallocating
    chunk = allocate(memory, 50);
    assert(chunk != NULL);
    assert(chunk->process_id == 2);
    assert(chunk->base == 0);
    assert(chunk->limit == 50);
    assert(memory->available_bytes == 50);

    // Test allocating remaining space
    chunk = allocate(memory, 50);
    assert(chunk != NULL);
    assert(chunk->process_id == 3);
    assert(chunk->base == 50);
    assert(chunk->limit == 50);
    assert(memory->available_bytes == 0);

}

void test_merge_free_blocks() {
    memory_t* memory = create_memory(100);

    // Test merging free blocks
    allocate(memory, 25);
    allocate(memory, 25);
    deallocate(memory, 1);
    deallocate(memory, 2);
    __merge_free_blocks(memory);
    assert(memory->start->process_id == UNUSED_CHUNK);
    assert(memory->start->limit == 100);
    assert(memory->start->next == NULL);

}

void test_compaction() {
    memory_t* memory = create_memory(100);

    // Test compaction
    allocate(memory, 25);
    allocate(memory, 25);
    deallocate(memory, 1);
    __compaction(memory);
    assert(memory->start->process_id == 2);
    assert(memory->start->limit == 25);
    assert(memory->start->next->process_id == UNUSED_CHUNK);
    assert(memory->start->next->limit == 75);

}

int main() {
    test_allocate();
    test_merge_free_blocks();
    test_compaction();
    printf("Tests passed\n");
    return EXIT_SUCCESS;
}
