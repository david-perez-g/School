#include <stdio.h>
#include "../../data_structures/bst.h"

bool int_cmp(void *a, void *b) {
    return *(int *)a >= *(int *)b;
}

int main(void){
    bstree_t* tree = bst_create(int_cmp);
    int values[] = { 5, 3, 2, 7, 9 };

    for (int i = 0; i < 5; i++) {
        bst_insert(tree, &values[i]);
    }

    bst_iterator_t* it = bst_get_inorder_iterator(tree);
    while(bst_iter_has_next(it)) {
        printf("%d\n", *(int*)bst_iter_next(it));
    }

    bst_free_iterator(it);
    bst_free(tree, false);
}
