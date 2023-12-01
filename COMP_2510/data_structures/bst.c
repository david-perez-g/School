#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#include "bst.h"
#include "stack.h"

static tnode_t* create_tnode(void* value, tnode_t* left, tnode_t* right) {
    tnode_t* node = (tnode_t*)malloc(sizeof(tnode_t));
    node->value = value;
    node->left = left;
    node->right = right;
    return node;
}

bstree_t* bst_create(bool (*cmp)(void*, void*)) {
    bstree_t* bst = (bstree_t*)malloc(sizeof(bstree_t));
    bst->root = NULL;
    bst->cmp = cmp;
    return bst;
}

void bst_insert(bstree_t* tree, void* value) {
    tnode_t* node = create_tnode(value, NULL, NULL);

    if (node == NULL) {
        perror("Couldn't create node");
        return;
    }

    if (tree->root == NULL) {
        tree->root = node;
        return;
    }

    tnode_t* current = tree->root;
    tnode_t* parent = NULL;

    while (current != NULL) {
        parent = current;
        if (tree->cmp(value, current->value)) {
            current = current->left;
        } else {
            current = current->right;
        }
    }

    if (tree->cmp(value, parent->value)) {
        parent->left = node;
    } else {
        parent->right = node;
    }
}

static void free_tnode(tnode_t* node, bool should_free_values) {
    if (node == NULL) {
        return;
    }

    if (should_free_values) {
        free(node->value);
    }

    free_tnode(node->left, should_free_values);
    free_tnode(node->right, should_free_values);
    free(node);
}

void bst_free(bstree_t* tree, bool should_free_values) {
    free_tnode(tree->root, should_free_values);
}


static void construct_inorder_iterator(const tnode_t* node, stack_t* stack) {
    if (node == NULL) {
        return;
    }

    construct_inorder_iterator(node->left, stack);
    stack_push(stack, node->value);
    construct_inorder_iterator(node->right, stack);
}

bst_iterator_t* bst_get_inorder_iterator(const bstree_t* tree) {
    stack_t* stack = stack_create();
    construct_inorder_iterator(tree->root, stack);
    bst_iterator_t* it = (bst_iterator_t*)malloc(sizeof(bst_iterator_t));

    if (it == NULL) {
        perror("Insufficient memory.\n");
        return NULL;
    }

    it->stack = stack;
    return it;
}

bool bst_iter_has_next(const bst_iterator_t* it) {
    return it->stack->top != NULL;
}

void* bst_iter_next(const bst_iterator_t* it) {
    node_t* node = stack_pop(it->stack);

    if (node == NULL) {
        return NULL;
    }

    return node->value;
}

void bst_free_iterator(bst_iterator_t* it) {
    node_t* node = it->stack->top;
    node_t* prev;

    while (node != NULL) {
        prev = node;
        node = node->next;
        // Only free the node
        // If node->value is free'd 
        // The original BST will be affected
        free(prev);
    }

    free(it->stack);
    free(it);
}