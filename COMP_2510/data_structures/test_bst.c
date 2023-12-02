#include <assert.h>
#include <stdio.h>

#include "bst.h"

int cmp(void* a, void* b) {
    int int_a = *((int*) a);
    int int_b = *((int*) b);
    if (int_a == int_b) return 0;
    if (int_a < int_b) return -1;
    return 1;
}

int main() {
    // Test 1: Inserting and finding values
    bstree_t* tree = bst_create(cmp);
    int value1 = 5;
    int value2 = 10;
    int value3 = 15;
    bst_insert(tree, &value1);
    bst_insert(tree, &value2);
    bst_insert(tree, &value3);
    assert(bst_find(tree, &value1)->value == &value1);
    assert(bst_find(tree, &value2)->value == &value2);
    assert(bst_find(tree, &value3)->value == &value3);
    printf("Test 1 passed.\n");

    // Test 2: In-order iterator
    bst_iterator_t* it = bst_get_inorder_iterator(tree);
    assert(bst_iter_has_next(it));
    assert(bst_iter_next(it) == &value1);
    assert(bst_iter_has_next(it));
    assert(bst_iter_next(it) == &value2);
    assert(bst_iter_has_next(it));
    assert(bst_iter_next(it) == &value3);
    assert(!bst_iter_has_next(it));
    bst_free_iterator(it);
    printf("Test 2 passed.\n");

    bst_free(tree, false);
    return 0;
}
