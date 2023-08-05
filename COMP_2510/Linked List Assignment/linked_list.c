#include<stdlib.h>
#include<stdbool.h>
#include<stdio.h>
#include<string.h>

#ifndef _LINKED_LIST
#define _LINKED_LIST
#endif

typedef struct node_t {
	void* value;
	struct node_t* next;
} node_t;

typedef struct linked_list_t {
	node_t* head;
	node_t* tail;
	unsigned int size;
} linked_list_t;

node_t* create_node(void* value) {
	node_t* node = (node_t*)malloc(sizeof(node_t));
	
	if (node == NULL) {
		perror("Unable to allocate node, returning NULL");
		return NULL;
	}

	node->value = value;
	node->next = NULL;
	return node;
}

void free_nodes(node_t* node) {
	if (node == NULL) {
		return;
	}

	free(node->value);
	free_nodes(node->next);
	free(node);
}

void set_next(node_t* node, node_t* next) {
	node->next = next;
}

linked_list_t* create_linked_list() {
	linked_list_t* list = (linked_list_t*)malloc(sizeof(linked_list_t));
	
	if (list == NULL) {
		perror("Unable to allocate linked list, returning NULL");
		return NULL;
	}

	list->head = NULL;
	list->tail = NULL;
	list->size = 0;
	return list;
}

void free_linked_list(linked_list_t* list) {
	// free nodes will recursively delete the other nodes
	free_nodes(list->head);
	free(list);
}

void append(linked_list_t* list, void* value) {
	node_t* node = create_node(value);
	list->size++;

	if (list->head != NULL) {
		set_next(list->tail, node);
		list->tail = node;
		return;
	}

	// the list is empty
	list->head = node;
	list->tail = node;
}

void insert(linked_list_t* list, void* value, int index) {
    if (index < 0 || index > list->size) {
        perror("Index out of bounds\n");
        return;
    }

    node_t* new_node = create_node(value);

    list->size++;

    if (index == 0) {
        new_node->next = list->head;
        list->head = new_node;
		
		if (list->tail == NULL) {
			list->tail = new_node;
		}

		return;
	}

	node_t* current = list->head;

	for (int i = 0; i < index - 1; i++) {
		current = current->next;
	}

	new_node->next = current->next;
	current->next = new_node;   

	if (new_node->next == NULL) {
		list->tail = new_node;
	}
}

int index_of(linked_list_t* list, void* value, bool (*cmp)(void*, void*)) {
	node_t* current = list->head;
	int index = 0;

    while (current != NULL) {
		if (!cmp(current->value, value)) {
			current = current->next;
			index++;
			continue;
		}
		return index;
	}

	return -1;
}

bool remove_value(linked_list_t* list, void* value, bool (*cmp)(void*, void*)) {
    node_t* current = list->head;
    node_t* previous = NULL;

    while (current != NULL) {
		if (!cmp(current->value, value)) {
			previous = current;
			current = current->next;
			continue;
		}

		if (previous != NULL) {
			previous->next = current->next;
		} else {
			list->head = current->next;
			if (list->tail == NULL) {
				list->tail = list->head;
			}
		}
		free(current->value);
		free(current);
		list->size--; 
		return true;       
    }
	return false;
}

void print_linked_list(linked_list_t* list, char* (*to_string)(void*)) {
    node_t* current = list->head;

    while (current != NULL) {
        char* value_str = to_string(current->value);
        printf("%s ", value_str);
        free(value_str);
        current = current->next;
    }

    printf("\n");
}
