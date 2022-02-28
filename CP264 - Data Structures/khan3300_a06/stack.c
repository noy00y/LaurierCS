#include <stdio.h>
#include <stdlib.h>
#include "stack.h"

void push(STACK *sp, NODE *np) {
    // Empty Stack
    if (sp->height == 0) {
        sp->top = np;
    }

    // Update Top
    else {
        np->next = sp->top;
        sp->top = np;
    }

    // Update Height:
    sp->height += 1;
}

NODE *pop(STACK *sp) {
    // Create Node:
    NODE *node = (NODE*)malloc(sizeof(NODE));

    // Pop if Not Empty:
    if (sp->height > 0) {
        // Get Node and Remove from Stack
        node = sp->top;
        sp->top = sp->top->next;

        // Update Height
        sp->height -= 1;
    }

    else{
        printf("Underflow\n");
    }

    // Return:
    return node;
}

void clean_stack(STACK *sp) {
    // Declarations:
    NODE *temp, *node = sp->top;

    // Iterate and Free:
    while (node != NULL) {
        temp = node;
        node = node->next;
        free(temp);
    }

    // Update:
    sp->top = NULL;
    sp->height = 0;
}