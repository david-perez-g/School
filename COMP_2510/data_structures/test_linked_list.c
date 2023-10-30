#include <assert.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include "linked_list.h"

bool cmp_int(void* a, void* b) {
    return *(int*)a == *(int*)b;
}

char* int_to_string(void* value) {
    int val = *(int*)value;
    int length = snprintf(NULL, 0, "%d", val);
    char* str = malloc(length + 1);
    snprintf(str, length + 1, "%d", val);
    return str;
}

void test_llist_create() {
    linked_list_t* list = linked_list_create();
    assert(list != NULL);
    assert(list->head == NULL);
    assert(list->tail == NULL);
    assert(list->size == 0);
    linked_list_free(list);
}

void test_llist_append() {
    linked_list_t* list = linked_list_create();
    int* value1 = (int*)malloc(sizeof(int));
    *value1 = 1;
    linked_list_append(list, value1);
    assert(list->size == 1);
    assert(*(int*)list->head->value == 1);
    assert(*(int*)list->tail->value == 1);

    int* value2 = (int*)malloc(sizeof(int));
    *value2 = 2;
    linked_list_append(list, value2);
    assert(list->size == 2);
    assert(*(int*)list->head->value == 1);
    assert(*(int*)list->tail->value == 2);

    linked_list_free(list);
}

void test_llist_insert() {
    linked_list_t* list = linked_list_create();
    
    int* value1 = (int*)malloc(sizeof(int));
    *value1 = 1;
    linked_list_insert(list, value1, 0);

    int* value2 = (int*)malloc(sizeof(int));
    *value2 = 2;
    linked_list_insert(list, value2, 0);

	int* value3 = (int*)malloc(sizeof(int));
	*value3 = 3;
	linked_list_insert(list, value3, 1);

	assert(list->size == 3);
	assert(*(int*)list->head->value == 2);
	assert(*(int*)list->head->next->value == 3);
	assert(*(int*)list->tail->value == 1);

	linked_list_free(list);
}

void test_llist_index_of() {
	linked_list_t* list = linked_list_create();

	int* value1 = (int*)malloc(sizeof(int));	
	*value1 = 1;

	linked_list_append(list, value1);	

	int* value2 = (int*)malloc(sizeof(int));
	*value2 = 2;
	linked_list_append(list, value2);


	int index = linked_list_index_of(list, value2, cmp_int);
	assert(index == 1);

	linked_list_free(list);
}

void test_llist_remove() {
	linked_list_t* list = linked_list_create();

	int* value1 = (int*)malloc(sizeof(int));
	*value1 = 1;
	linked_list_append(list, value1);

	int* value2 = (int*)malloc(sizeof(int));
	*value2 = 2;
	linked_list_append(list, value2);

	bool removed = linked_list_remove(list, value2, cmp_int);
	assert(removed == true);

	assert(list->size == 1);
	assert(*(int*)list->head->value == 1);

	linked_list_free(list);
}

int main(void) {
	test_llist_create();
	printf("Passed: linked_list_create\n");
	test_llist_append();
	printf("Passed: linked_list_append\n");
	test_llist_index_of();
	printf("Passed: linked_list_index_of\n");
	test_llist_insert();
	printf("Passed: linked_list_insert\n");
	test_llist_remove();
	printf("Passed: linked_list_remove\n");
	return EXIT_SUCCESS;
}