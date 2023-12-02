#ifndef DLLIST_H
#define DLLIST_H

#include <stdlib.h>
#include <stdbool.h>

// A node from a Doubly linked list 
typedef struct dll_node_t {
    struct dll_node_t* left;
    struct dll_node_t* right;
    void* value;
} dll_node_t;

typedef struct dllist_t {
    dll_node_t* head;
    dll_node_t* tail;
    unsigned int size;
} dllist_t;

extern dllist_t* dllist_create();
extern void dllist_append(dllist_t* list, void* value);
extern void dllist_prepend(dllist_t* list, void* value);
extern void dllist_free(dllist_t* list, bool should_free_values);

#endif