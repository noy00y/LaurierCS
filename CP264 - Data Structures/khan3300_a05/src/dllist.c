
#include <stdio.h>
#include <stdlib.h> 
#include "dllist.h"

NODE *new_node(char data) {
// your implementation
}

void insert_start(DLLIST *dllistp, NODE *np) {
// your implementation
}

void insert_end(DLLIST *dllistp, NODE *np) {
// your implementation
}

void delete_start(DLLIST *dllistp) {
// your implementation
}

void delete_end(DLLIST *dllistp) {
// your implementation
}

void display_forward(DLLIST *dllistp) {
  NODE *np = dllistp->start;
  while ( np != NULL) {
    printf("%c", np->data);
    np = np->next;
  }
}

void display_backward(DLLIST *dllistp) {
  NODE *np = dllistp->end;
  while ( np != NULL) {
    printf("%c", np->data);
    np = np->prev;
  }
}

void clean(DLLIST *dllistp) {
  NODE *temp, *np = dllistp->start;
  while (np != NULL) {
    temp = np;
    np = np->next;
      free(temp);
  }
  dllistp->start = NULL;
  dllistp->end = NULL;
    dllistp->length = 0;
}