#ifndef BST_H
#define BST_H

#include <stdlib.h>
#include <stdbool.h>
#include "stack.h"

// Tree node
typedef struct tnode_t {
    struct tnode_t* left;
    struct tnode_t* right;
    void* value;
} tnode_t;

typedef struct bstree_t {
    tnode_t* root;
    bool (*cmp)(void*, void*);
} bstree_t;

typedef struct bst_iterator_t {
    stack_t* stack;
} bst_iterator_t;

extern bstree_t* bst_create(bool (*cmp)(void*, void*));
extern void bst_insert(bstree_t* tree, void* value);
extern bool bst_remove(bstree_t* tree, void* value);
extern tnode_t* bst_find(const bstree_t* tree, void* value);
extern void bst_free(bstree_t* tree, bool should_free_values);

extern bst_iterator_t* bst_get_inorder_iterator(const bstree_t* tree);
extern bool bst_iter_has_next(const bst_iterator_t* it);
extern void* bst_iter_next(const bst_iterator_t* it);
extern void bst_free_iterator(bst_iterator_t* it);

#endif