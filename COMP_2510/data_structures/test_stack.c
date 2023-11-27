#include <assert.h>
#include "stack.h"

int main(void) {
    // Create a stack
    stack_t* stack = stack_create();
    assert(stack != NULL);

    // Push elements onto the stack
    int a = 1, b = 2, c = 3;
    stack_push(stack, &a);
    stack_push(stack, &b);
    stack_push(stack, &c);

    // Pop elements from the stack and check their values
    assert(*(int*)stack_pop(stack)->value == 3);
    assert(*(int*)stack_pop(stack)->value == 2);
    assert(*(int*)stack_pop(stack)->value == 1);

    // Check that the stack is empty
    assert(stack_pop(stack) == NULL);

    // Free the stack
    stack_free(stack);

    return 0;
}
