#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#include "../../data_structures/linked_list.h"

linked_list_t* copy_llist(const linked_list_t* list) {
    linked_list_t* new_list = linked_list_create();
    
    for (node_t* node = list->head; node != NULL; node = node->next) {
        linked_list_append(new_list, node->value);
    }

    return new_list;
}

void reverse_llist(linked_list_t* list) {
    if (list->size <= 1) {
        return;
    }

    node_t* node = list->head;
    node_t* next = node->next;
    node_t* prev;
    node->next = NULL;
    list->tail = node;

    while (next != NULL) {
        prev = node;
        node = next;
        next = node->next;
        node->next = prev;
        list->head = node;
    }
}

char* str_to_str(void *value) {
    int len = strlen(value);
    char *copy = malloc(len + 1);
    if (copy == NULL) {
        perror("Insufficient memory");
        return NULL;
    }
    strncpy(copy, value, len + 1);
    return copy;
}

bool str_cmp(const void* a, const void* b) {
    return strcmp(a, b) == 0;
}

int main(void) {
    linked_list_t* initial_list = linked_list_create();
    
    linked_list_append(initial_list, "David");
    linked_list_append(initial_list, "John");
    linked_list_append(initial_list, "James");
    linked_list_append(initial_list, "Merry");

    linked_list_t* copy = copy_llist(initial_list);
    
    printf("Original list: ");
    linked_list_print(initial_list, str_to_str);
    printf("Copy: ");
    linked_list_print(copy, str_to_str);

    printf("\nAfter removing John from the original\n");
    linked_list_remove(initial_list, "John", str_cmp);

    printf("Original: ");
    linked_list_print(initial_list, str_to_str);
    printf("Copy: ");
    linked_list_print(copy, str_to_str);

    reverse_llist(initial_list);
    reverse_llist(copy);
    printf("\nAfter reversing both lists\n");
    linked_list_print(initial_list, str_to_str);
    linked_list_print(copy, str_to_str);

    linked_list_free(initial_list);
    linked_list_free(copy);

    return EXIT_SUCCESS;
}
