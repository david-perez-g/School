#ifndef STACK_H
#define STACK_H

#include "linked_list.h"

typedef struct stack_t {
	node_t* top;
} stack_t;

extern stack_t* stack_create();
extern void stack_push(stack_t* stack, void* value);
extern node_t* stack_pop(stack_t* stack);
extern void stack_free(stack_t* stack);

#endif