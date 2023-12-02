#include <stdlib.h>
#include <stdio.h>

#include "../../data_structures/dllist.h"

int* alloc_int(int value) {
    int* copy = (int*)malloc(sizeof(int));
    *copy = value;
    return copy;
}

int* copy_int(int *item) {
    int* copy = alloc_int(*item);
    return copy;
}

dllist_t* dllist_sum(dllist_t* a, dllist_t* b) {
    int carry = 0, x, y, z, s;
    dllist_t* sum = dllist_create();
    dll_node_t *node_x = a->tail, *node_y = b->tail;

    while(node_x != NULL || node_y != NULL) {
        x = node_x == NULL ? 0 : *(int*)node_x->value;
        y = node_y == NULL ? 0 : *(int*)node_y->value;
        
        s = x + y + carry;
        if (s < 10) {
            carry = 0;
            z = s;
        } else {
            carry = 1;
            z = s - 10;
        }

        if (node_x != NULL) {
            node_x = node_x->left;
        }

        if (node_y != NULL) {
            node_y = node_y->left;
        }

        dllist_prepend(sum, copy_int(&z));
    }

    if (carry == 1) {
        dllist_prepend(sum, alloc_int(1));
    }

    return sum;
}

void print_int_dllist(dllist_t* list) {
    dll_node_t* node = list->head;

    while(node != NULL) {
        printf("%d ", *(int*)node->value);
        node = node->right;
    }

    printf("\n");
}

void append_all(dllist_t* list, int* values, int size) {
    for (int i = 0; i < size; i++) {
        dllist_append(list, &values[i]);
    }
}

int main(void){
    // Test case 1: Sum of two single digit numbers without carry
    dllist_t* a1 = dllist_create();
    dllist_t* b1 = dllist_create();
    int a1_digits[] = { 3 };
    int b1_digits[] = { 4 };

    append_all(a1, a1_digits, 1);
    append_all(b1, b1_digits, 1);
    
    dllist_t* sum1 = dllist_sum(a1, b1);
    
    printf("Expected output: 7\n");
    printf("Actual output: ");
    print_int_dllist(sum1);
    
    dllist_free(a1, false);
    dllist_free(b1, false);
    dllist_free(sum1, true);

    // Test case 2: Sum of two single digit numbers with carry
    dllist_t* a2 = dllist_create();
    dllist_t* b2 = dllist_create();
    int a2_digits[] = { 5 };
    int b2_digits[] = { 7 };
    
    append_all(a2, a2_digits, 1);
    append_all(b2, b2_digits, 1);
    
    dllist_t* sum2 = dllist_sum(a2, b2);
    
    printf("Expected output: 1 2\n");
    printf("Actual output: ");
    print_int_dllist(sum2);
    
    dllist_free(a2, false);
    dllist_free(b2, false);
    dllist_free(sum2, true);

    // Test case 3: Sum of two multi-digit numbers without carry
    dllist_t* a3 = dllist_create();
    dllist_t* b3 = dllist_create();
    int a3_digits[] = { 1, 2, 3 };
    int b3_digits[] = { 4, 5, 6 };
    
    append_all(a3, a3_digits, 3);
    append_all(b3, b3_digits, 3);
    
    dllist_t* sum3 = dllist_sum(a3, b3);
    
    printf("Expected output: 5 7 9\n");
    printf("Actual output: ");
    print_int_dllist(sum3);
    
    dllist_free(a3, false);
    dllist_free(b3, false);
    dllist_free(sum3, true);

    // Test case 4: Sum of two multi-digit numbers with carry
    dllist_t* a4 = dllist_create();
    dllist_t* b4 = dllist_create();
    int a4_digits[] = { 9, 9, 9 };
    int b4_digits[] = { 1 };

    append_all(a4, a4_digits, 3);
    append_all(b4, b4_digits, 1);
    
    dllist_t* sum4 = dllist_sum(a4, b4);

    printf("Expected output: 1 0 0 0\n");
    printf("Actual output: ");
    print_int_dllist(sum4);
    
    dllist_free(a4, false);
    dllist_free(b4, false);
    dllist_free(sum4, true);

    return EXIT_SUCCESS;
}
