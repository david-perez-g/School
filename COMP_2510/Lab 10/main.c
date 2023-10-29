#include "../linked_list/linked_list.c"

linked_list_t* copy_llist(const linked_list_t* list) {
    linked_list_t* new_list = create_llist();
    
    for (node_t* node = list->head; node != NULL; node = node->next) {
        llist_append(new_list, node->value);
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

char* str_to_str(const void *value) {
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
    linked_list_t* initial_list = create_llist();
    
    llist_append(initial_list, "David");
    llist_append(initial_list, "John");
    llist_append(initial_list, "James");
    llist_append(initial_list, "Merry");

    linked_list_t* copy = copy_llist(initial_list);
    
    printf("Original list: ");
    print_llist(initial_list, str_to_str);
    printf("Copy: ");
    print_llist(copy, str_to_str);

    printf("\nAfter removing John from the original\n");
    llist_remove(initial_list, "John", str_cmp);

    printf("Original: ");
    print_llist(initial_list, str_to_str);
    printf("Copy: ");
    print_llist(copy, str_to_str);

    reverse_llist(initial_list);
    reverse_llist(copy);
    printf("\nAfter reversing both lists\n");
    print_llist(initial_list, str_to_str);
    print_llist(copy, str_to_str);

    free_llist(initial_list);
    free_llist(copy);

    return EXIT_SUCCESS;
}
