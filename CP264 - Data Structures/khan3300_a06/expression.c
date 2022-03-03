#include <stdio.h>
#include <stdlib.h>
#include "common.h"
#include "queue.h"
#include "stack.h"
#include "expression.h"

/*
 * auxiliary function 
*/
int get_priority(char op) {
  if (op == '/' || op == '*' || op == '%') 
    return 1;
  else if (op == '+' || op == '-')
    return 0;
  else 
    return -1;    
}

/*
 * auxiliary function 
*/
int type(char c) {
  if (c >= '0' &&  c <= '9' )
     return 0;
  else if (c == '/' || c == '*' || c == '%' || c == '+' || c == '-')
    return 1;
  else if (c == '(')
    return 2;
  else if ( c == ')')
    return 3;  
  else 
    return 4;
}

QUEUE infix_to_postfix(char *infixstr) {
  
  // Declarations:
  char *p = infixstr;
  QUEUE queue = {0};
  STACK stack = {0};
  int sign = 1, num = 0;

  // Traverse the infix string
  while (*p) {

    // Negative Integer (-)
    if ( *p == '-' && (p == infixstr || *(p-1) == '(') ) {
      sign = -1;
    }

    // Number
    else if (*p >= '0' && *p <= '9') { 
      num = *p-'0'; 
      while ((*(p+1) >= '0' && *(p+1) <= '9')) { 
        num = num*10 + *(p+1)-'0'; p++; 
      }
      enqueue(&queue, new_node(sign*num, 0));
      sign = 1; 
    }

    // Open Bracket
    else if (*p == '(') {
      push(&stack, new_node(*p, type(*p)));
    }

    // Close Bracket
    else if (*p == ')') {
      while (stack.top) {
        if (stack.top->type == 2) {
          free(pop(&stack));
          break;
        }
        enqueue(&queue, pop(&stack));
      }
    }

    // Push 
    else if (type(*p) == 1) {
      while (stack.top && stack.top->type == 1
        && get_priority(stack.top->data) >= get_priority(*p)) {
        enqueue(&queue, pop(&stack));
      }
      push(&stack, new_node(*p, 1));
    }

    // Iterate:
    p++;
  }

  // Return:
  while(stack.top) { 
    enqueue(&queue, pop(&stack) ); 
  }

  return queue;
}

// Eval with postfix notation
int evaluate_postfix(QUEUE queue) {

  // Declarations:
  STACK stack = {0};
  NODE *temp = queue.front;
  int var = 0;
  int value = 0;

  // Iterate through Queue
  while (temp) {
    var = temp->type; // var type
    
    // If Operand --> push to stack
    if (var == 0) {
      push(&stack, new_node(temp->data, 0));
    } 

    // If Operator --> pop a and b --> eval and push c to stack
    else if (var == 1) {
      NODE *a = pop(&stack);
      NODE *b = pop(&stack);
      NODE *c = new_node(0, 0);

      // Evalute based on operator
      switch (temp->data) {
        case '+':
          c->data = b->data + a->data; 
        break;
        case '-':
          c->data = b->data - a->data; 
        break;
        case '*':
          c->data = b->data * a->data; 
        break;
        case '/':
          c->data = b->data / a->data; 
        break;
      } 

      // Push result to stack
      push(&stack, c);
    }
    // Iterate to next
    temp = temp->next;
  } 
  if (stack.top != NULL) {
    value = stack.top->data;
  }
  return value;
}

// Eval with Infix
int evaluate_infix(char *infixstr) {
  QUEUE queue = infix_to_postfix(infixstr);
  int value = evaluate_postfix(queue);
  return value;
}