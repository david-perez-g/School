#include <stdlib.h>

#include "dllist.h"

dllist_t* dllist_create() {
    dllist_t* dllist = (dllist_t*)malloc(sizeof(dllist_t));
    dllist->head = NULL;
    dllist->tail = NULL;
    dllist->size = 0;
    return dllist;
}

static dll_node_t* create_node(void* value, dll_node_t* left, dll_node_t* right) {
    dll_node_t* node = (dll_node_t*)malloc(sizeof(dll_node_t));
    node->value = value;
    node->left = left;
    node->right = right;
    return node;
}

void dllist_append(dllist_t* list, void* value) {
    dll_node_t* node;

    if (list->size == 0) {
        node = create_node(value, NULL, NULL);
        list->size++;
        list->head = node;
        list->tail = node;
        return;
    }

    node = create_node(value, list->tail, NULL);
    list->tail->right = node;
    list->tail = node;
    list->size++;
}

void dllist_free(dllist_t* list, bool should_free_values) {
    dll_node_t* node = list->head;
    dll_node_t* prev;

    while(node != NULL) {
        if (should_free_values) {
            free(node->value);
        }

        prev = node;
        node = node->right;
        free(prev);        
    }
}