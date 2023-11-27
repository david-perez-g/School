#include <stdlib.h>

#include "stack.h"

static node_t* create_node(void* value, node_t* next) {
	node_t* node = (node_t*)malloc(sizeof(node_t));

	if (node == NULL) {
		perror("Insufficient memory\n");
		return NULL;
	}

	node->value = value;
	node->next = next;

	return node;
}

stack_t* stack_create() {
	stack_t* stack = (stack_t*)malloc(sizeof(stack_t));
	stack->top = NULL;
	return stack;
}

void stack_push(stack_t* stack, void* value) {
	node_t* node = create_node(value, stack->top);
	stack->top = node;
}

node_t* stack_pop(stack_t* stack) {
	node_t* top = stack->top;

	if (top == NULL) {
		return NULL;
	}

	stack->top = top->next;
	return top;
}

void stack_free(stack_t* stack) {
	if (stack == NULL) {
		return;
	}

	node_t* node = stack->top;
	node_t* prev;
	
	while (node != NULL) {
		prev = node;
		node = node->next;
		free(prev);
	}
}