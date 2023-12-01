#include <assert.h>
#include <stdio.h>

#include "bst.h"

bool int_cmp(void* a, void* b) {
    int int_a = *(int*)a;
    int int_b = *(int*)b;

    return int_a >= int_b;
}

int main() {
    bstree_t* tree = bst_create(int_cmp);

    int values[] = {5, 3, 7, 2, 4, 6, 8};
    for (int i = 0; i < 7; i++) {
        printf("Hola 0\n");
        bst_insert(tree, &values[i]);
    }

    for (int i = 0; i < 7; i++) {
        printf("Hola 1\n");
        assert(bst_find(tree, &values[i]) != NULL);
    }

    int not_in_tree = 9;
    printf("Hola 2\n");
    assert(bst_find(tree, &not_in_tree) == NULL);

    printf("All tests passed!\n");

    bst_free(tree);

    return 0;
}

