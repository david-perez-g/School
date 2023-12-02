#ifndef LINKED_LIST_H
#define LINKED_LIST_H

#include <stdbool.h>

typedef struct node_t {
	void* value;
	struct node_t* next;
} node_t;

typedef struct linked_list_t {
	node_t* head;
	node_t* tail;
	unsigned int size;
} linked_list_t;

extern linked_list_t* linked_list_create(void);
extern void linked_list_free(linked_list_t* list);
extern void linked_list_append(linked_list_t* list, void* value);
extern void linked_list_insert(linked_list_t* list, void* value, unsigned int index);
extern node_t* linked_list_pop_front(linked_list_t* list);
extern unsigned int linked_list_index_of(const linked_list_t* list, void* value, bool (*cmp)(void*, void*));
extern bool linked_list_remove(linked_list_t* list, void* value, bool (*cmp)(void*, void*));
extern void linked_list_print(const linked_list_t* list, char* (*to_string)(void*));

#endif