#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#include "bst.h"

static tnode_t* create_tnode(void* value, tnode_t* left, tnode_t* right) {
    tnode_t* node = (tnode_t*)malloc(sizeof(tnode_t));
    node->value = value;
    node->left = left;
    node->right = right;
    return node;
}

bstree_t* bst_create(int (*cmp)(void*, void*)) {
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

        // current->value >= value
        if (tree->cmp(current->value, value) >= 0) {
            current = current->left;
        } else {
            current = current->right;
        }
    }

    if (tree->cmp(parent->value, value) >= 0) {
        parent->left = node;
    } else {
        parent->right = node;
    }
}

tnode_t* bst_find(const bstree_t* tree, void* value) {
    tnode_t* node = tree->root;

    while (node != NULL) {
        int cmp = tree->cmp(node->value, value);

        if (cmp == 0) {
            return node;
        } else if (cmp > 0) {
            node = node->left;
        } else {
            node = node->right;
        }
    }

    return NULL;
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


static void construct_inorder_iterator(const tnode_t* node, linked_list_t* list) {
    if (node == NULL) {
        return;
    }

    construct_inorder_iterator(node->left, list);
    linked_list_append(list, node->value);
    construct_inorder_iterator(node->right, list);
}

bst_iterator_t* bst_get_inorder_iterator(const bstree_t* tree) {
    linked_list_t* list = linked_list_create();
    construct_inorder_iterator(tree->root, list);
    bst_iterator_t* it = (bst_iterator_t*)malloc(sizeof(bst_iterator_t));

    if (it == NULL) {
        perror("Insufficient memory.\n");
        return NULL;
    }

    it->list = list;
    return it;
}

bool bst_iter_has_next(const bst_iterator_t* it) {
    return it->list->size > 0;
}

void* bst_iter_next(const bst_iterator_t* it) {
    node_t* node = linked_list_pop_front(it->list);

    if (node == NULL) {
        return NULL;
    }

    return node->value;
}

void bst_free_iterator(bst_iterator_t* it) {
    node_t* node = it->list->head;
    node_t* prev;

    while (node != NULL) {
        prev = node;
        node = node->next;
        // Only free the node
        // If node->value is free'd 
        // The original BST will be affected
        free(prev);
    }

    free(it->list);
    free(it);
}