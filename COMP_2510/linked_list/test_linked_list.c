#include <assert.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include "linked_list.c"

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

void test_create_linked_list() {
    linked_list_t* list = create_llist();
    assert(list != NULL);
    assert(list->head == NULL);
    assert(list->tail == NULL);
    assert(list->size == 0);
    free_llist(list);
}

void test_append() {
    linked_list_t* list = create_llist();
    int* value1 = malloc(sizeof(int));
    *value1 = 1;
    llist_append(list, value1);
    assert(list->size == 1);
    assert(*(int*)list->head->value == 1);
    assert(*(int*)list->tail->value == 1);

    int* value2 = malloc(sizeof(int));
    *value2 = 2;
    llist_append(list, value2);
    assert(list->size == 2);
    assert(*(int*)list->head->value == 1);
    assert(*(int*)list->tail->value == 2);

    free_llist(list);
}

void test_insert() {
    linked_list_t* list = create_llist();
    
    int* value1 = malloc(sizeof(int));
    *value1 = 1;
    llist_insert(list, value1, 0);

    int* value2 = malloc(sizeof(int));
    *value2 = 2;
    llist_insert(list, value2, 0);

	int* value3 = malloc(sizeof(int));
	*value3 = 3;
	llist_insert(list, value3, 1);

	assert(list->size == 3);
	assert(*(int*)list->head->value == 2);
	assert(*(int*)list->head->next->value == 3);
	assert(*(int*)list->tail->value == 1);


	free_llist(list);
}

void test_index_of() {
	linked_list_t* list = create_llist();

	int* value1 = malloc(sizeof(int));	
	*value1 = 1;

	llist_append(list, value1);	

	int* value2 = malloc(sizeof(int));
	*value2 = 2;
	llist_append(list, value2);


	int index = llist_index_of(list, value2, cmp_int);
	assert(index == 1);

	free_llist(list);
}

void test_remove() {
	linked_list_t* list = create_llist();

	int* value1 = malloc(sizeof(int));
	*value1 = 1;
	llist_append(list, value1);

	int* value2 = malloc(sizeof(int));
	*value2 = 2;
	llist_append(list, value2);

	bool removed = llist_remove(list, value2, cmp_int);
	assert(removed == true);

	assert(list->size == 1);
	assert(*(int*)list->head->value == 1);

	free_llist(list);
}

int main(void) {
	test_create_linked_list();
	printf("Passed: create_linked_list\n");
	test_append();
	printf("Passed: append\n");
	test_index_of();
	printf("Passed: llist_index_of\n");
	test_insert();
	printf("Passed: llist_insert\n");
	test_remove();
	printf("Passed: remove\n");
	return EXIT_SUCCESS;
}