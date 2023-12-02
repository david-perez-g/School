#include <stdio.h>
#include <stdlib.h>

#include <assert.h>

#include "../../data_structures/bst.h"

int int_cmp(void *a, void *b) {
    int int_a = *(int *)a;
    int int_b = *(int *)b;

    if (int_a == int_b) {
        return 0;
    }

    if (int_a > int_b) {
        return 1;
    }

    return -1;
}

// Tree Comparer Functions -------------------------------------------------------------------

bool are_equal_nodes(const tnode_t* a, const tnode_t* b, int (*cmp)(void *, void *)) {
    if (a == NULL || b == NULL) {
        return false;
    }

    return cmp(a->value, b->value) == 0;
}

bool cmp_node_heirarchy(const tnode_t* a, const tnode_t* b, int (*cmp)(void *, void *)) {
    if (a == NULL && b == NULL) {
        return true;
    }
    
    if (a == NULL || b == NULL) {
        return false;
    }

    if (!are_equal_nodes(a, b, cmp)) {
        return false;
    }

    return cmp_node_heirarchy(a->left, b->left, cmp) && cmp_node_heirarchy(a->right, b->right, cmp);
}

bool are_equal_trees(const bstree_t* a, const bstree_t* b) {
    return cmp_node_heirarchy(a->root, b->root, a->cmp);
}

// Contains Path With Sum Functions ----------------------------------------------------------

bool is_leaf(const tnode_t* node) {
    return node->left == NULL && node->right == NULL;
}

bool lookup_sum(const tnode_t* node, int current_sum, int target_sum) {
    if (node == NULL) {
        return false;
    }

    int new_sum = current_sum + *(int*)node->value;
    
    if (new_sum == target_sum && is_leaf(node)) {
        return true;
    }

    return lookup_sum(node->left, new_sum, target_sum) || lookup_sum(node->right, new_sum, target_sum);
}

bool contains_path_with_sum(const bstree_t* tree, int sum) {
    if (tree->root == NULL) {
        return false;
    }

    return lookup_sum(tree->root, 0, sum);
}

// Tree Mirroring Functions ------------------------------------------------------------------

void switch_childs(tnode_t* parent) {
    tnode_t* temp = parent->left;
    parent->left = parent->right;
    parent->right = temp;
}

void switch_hierarchy(tnode_t* parent) {
    if (parent == NULL) {
        return;
    }

    switch_childs(parent);
    switch_hierarchy(parent->left);
    switch_hierarchy(parent->right);
}

void mirror_tree(const bstree_t* tree) {
    switch_hierarchy(tree->root);
}

void test_are_equal_trees();
void test_contains_path_with_sum();
void test_mirror_tree();

int main() {
    test_are_equal_trees();
    test_contains_path_with_sum();
    test_mirror_tree();

    return EXIT_SUCCESS;
}

void test_are_equal_trees() {
    printf("Testing: are_equal_trees\n");
    
    bstree_t* tree1 = bst_create(int_cmp);
    bstree_t* tree2 = bst_create(int_cmp);

    int values[] = {5, 3, 7, 2, 4, 6, 8};

    for (int i = 0; i < 7; i++) {
        bst_insert(tree1, &values[i]);
        bst_insert(tree2, &values[i]);
    }

    // Test that the trees are equal
    assert(are_equal_trees(tree1, tree2));

    // Insert an additional value into the first tree
    int extra_value = 9;
    bst_insert(tree1, &extra_value);

    assert(!are_equal_trees(tree1, tree2));

    bst_free(tree1, false);
    bst_free(tree2, false);

    bstree_t* tree3 = bst_create(int_cmp);
    bstree_t* tree4 = bst_create(int_cmp);

    // Test that two empty trees are equal
    assert(are_equal_trees(tree3, tree4));

    // Insert a value into the first tree
    int value1 = 5;
    bst_insert(tree3, &value1);

    assert(!are_equal_trees(tree3, tree4));

    int value2 = 5;
    bst_insert(tree4, &value2);

    // Test that the trees are equal
    assert(are_equal_trees(tree3, tree4));

    int value3 = 3;
    int value4 = 4;
    bst_insert(tree3, &value3);
    bst_insert(tree4, &value4);

    assert(!are_equal_trees(tree3, tree4));

    bst_free(tree3, false);
    bst_free(tree4, false);

    printf("Test successful\n");
}

void test_contains_path_with_sum() {
    printf("Testing: contains_path_with_sum\n");

    bstree_t* tree = bst_create(int_cmp);

    // Testing an empty tree
    assert(!contains_path_with_sum(tree, 0));

    // Insert values into the tree
    int values[] = {5, 3, 7, 2, 4, 6, 8};
    for (int i = 0; i < 7; i++) {
        bst_insert(tree, &values[i]);
    }

    /*
                5
            3       7
         2    4   6   8
    */

    assert(!contains_path_with_sum(tree, 1));

    assert(!contains_path_with_sum(tree, 0));

    assert(!contains_path_with_sum(tree, 100));

    // Test that the tree contains a path with sum 10 (5 + 3 + 2)
    assert(contains_path_with_sum(tree, 10));

    // Test that the tree contains a path with sum 12 (5 + 3 + 4)
    assert(contains_path_with_sum(tree, 12));

    // Test that the tree contains a path with sum 18 (5 + 7 + 6)
    assert(contains_path_with_sum(tree, 18));

    // Test that the tree contains a path with sum 20 (5 + 7 + 8)
    assert(contains_path_with_sum(tree, 20));

    // Free the tree
    bst_free(tree, false);

    bstree_t* tree2 = bst_create(int_cmp);

    // A tree with only negative values
    int values2[] = {-5, -3, -7, -2, -4, -6, -8};
    for (int i = 0; i < 7; i++) {
        bst_insert(tree2, &values2[i]);
    }

    /*
                  -5
            -7          -3
         -8    -6    -4     -2
    */

    // Test that the tree contains a path with sum -10 (-5 -3 -2)
    assert(contains_path_with_sum(tree2, -10));

    // Test that the tree does not contain a path with sum -12 (-5 -3 -4)
    assert(contains_path_with_sum(tree2, -12));

    // Test that the tree contains a path with sum -18 (-5 -7 -6)
    assert(contains_path_with_sum(tree2, -18));

    // Test that the tree contains a path with sum -20 (-5 -7 -8)
    assert(contains_path_with_sum(tree2, -20));

    // Test that the tree does not contain a path with sum -100
    assert(!contains_path_with_sum(tree2, -100));

    bst_free(tree2, false);

    printf("Test successful\n");
}

void test_mirror_tree() {
    printf("Test: mirror_tree\n");

    // Test 1: Empty tree
    bstree_t* tree1 = bst_create(int_cmp);
    mirror_tree(tree1);
    assert(tree1->root == NULL);

    // Test 2: Single node tree
    int value1 = 5;
    bst_insert(tree1, &value1);
    mirror_tree(tree1);
    assert(*(int*)tree1->root->value == 5);

    // Test 3: Two node tree (left child)
    int value2 = 3;
    bst_insert(tree1, &value2);
    mirror_tree(tree1);
    assert(*(int*)tree1->root->value == 5);
    assert(tree1->root->left == NULL);
    assert(tree1->root->right != NULL);
    assert(*(int*)tree1->root->right->value == 3);

    // Test 4: Two node tree (right child)
    bstree_t* tree2 = bst_create(int_cmp);
    int value3 = 5;
    int value4 = 7;
    bst_insert(tree2, &value3);
    bst_insert(tree2, &value4);
    mirror_tree(tree2);
    assert(*(int*)tree2->root->value == 5);
    assert(tree2->root->right == NULL);
    assert(tree2->root->left != NULL);
    assert(*(int*)tree2->root->left->value == 7);

    // Test 5: Three node tree
    bstree_t* tree3 = bst_create(int_cmp);
    int value5 = 5;
    int value6 = 3;
    int value7 = 7;
    bst_insert(tree3, &value5);
    bst_insert(tree3, &value6);
    bst_insert(tree3, &value7);
    mirror_tree(tree3);
    assert(*(int*)tree3->root->value == 5);
    assert(tree3->root->left != NULL);
    assert(tree3->root->right != NULL);
    assert(*(int*)tree3->root->left->value == 7);
    assert(*(int*)tree3->root->right->value == 3);

    // Free the trees
    bst_free(tree1, false);
    bst_free(tree2, false);
    bst_free(tree3, false);

    printf("Test successful\n");
}