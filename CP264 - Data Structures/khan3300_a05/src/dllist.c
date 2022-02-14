
#include <stdio.h>
#include <stdlib.h> 
#include "dllist.h"

NODE *new_node(char data) {
  // Node Declaration:
  NODE *node = (NODE*)malloc(sizeof(NODE));
  if (node == NULL) {
    printf("malloc failed"); 
    return NULL;
  }

  // Node Initializations:
  // strcpy(node->data, data);
  node->data = data;
  node->prev = NULL;
  node->next = NULL;
  
  return node;
}

void insert_start(DLLIST *dllistp, NODE *np) {
  // Insertion: Empty List:
  if (dllistp->length == 0) {
    dllistp->start = np;
    dllistp->end = np;
  }

  // Else:
  else {
    np->next = dllistp->start;
    dllistp->start->prev = np;
    dllistp->start = np;
  }

  // Increase Counts:
  dllistp->length += 1;
}

void insert_end(DLLIST *dllistp, NODE *np) {
  // Insertion: Empty List
  if (dllistp->length == 0) {
    dllistp->start = np;
    dllistp->end = np;
  }

  // Else: 
  else {
    dllistp->end->next = np;
    np->prev = dllistp->end;
    dllistp->end = np;
  }

  // Increase Counts:
  dllistp->length += 1;
}

void delete_start(DLLIST *dllistp) {
  // If Empty --> cant delete
  if (dllistp->length == 0) {
    // printf("cannot delete from empty list\n");
    return;
  }

  // Delete
  else {
    NODE *node = dllistp->start; // get node to delete
    dllistp->start = dllistp->start->next; // shave starting node from the list
    free(node); // delete the Node
  }

  // Update Counts:
  dllistp->length -= 1;
}

void delete_end(DLLIST *dllistp) {
  //If Empty --> cant delete
  if (dllistp->length == 0) {
    // printf("cannot delete from empty list\n");
    return;
  }

  // Delete:
  else {
    NODE *node = dllistp->end;
    dllistp->end = dllistp->end->prev;
    free(node);
  }

  // Update Contents:
  dllistp->length -= 1;
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