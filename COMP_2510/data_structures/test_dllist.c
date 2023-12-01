#include <stdio.h>
#include <assert.h>

#include "dllist.h"

void test_dllist_create() {
    printf("Running test_dllist_create...\n");
    dllist_t* list = dllist_create();
    assert(list != NULL);
    assert(list->head == NULL);
    assert(list->tail == NULL);
    assert(list->size == 0);
    printf("test_dllist_create passed\n");
    dllist_free(list, false);
}

void test_dllist_append() {
    printf("Running test_dllist_append...\n");
    dllist_t* list = dllist_create();
    int values[] = {5, 10, 15};

    for (int i = 0; i < 3; i++) {
        dllist_append(list, &values[i]);
    }

    assert(list->size == 3);
    assert(list->head != NULL);
    assert(list->tail != NULL);
    assert(list->head->value == &values[0]);
    assert(list->tail->value == &values[2]);

    dll_node_t* node = list->head;

    for (int i = 0; i < 3; i++) {
        assert(node != NULL);
        assert(node->value == &values[i]);
        node = node->right;
    }

    printf("test_dllist_append passed\n");
    dllist_free(list, false);
}

int main() {
    test_dllist_create();
    test_dllist_append();
    return 0;
}
