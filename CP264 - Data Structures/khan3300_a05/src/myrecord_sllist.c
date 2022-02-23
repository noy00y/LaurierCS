#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h> 
#include "myrecord_sllist.h"

void display(SLLIST *sllistp) {
    NODE *np = sllistp->start;
    printf("length:%d\n", sllistp->length);
    while (np != NULL) {
        printf("%s,%.1f\n", np->data.name, np->data.score);
        np = np->next;
    }
}

NODE *search(SLLIST *sllistp, char *name) {

  // Declarations:
  NODE *node = sllistp->start;

  // Traverse:
  while (node != NULL) {
    
    // string comparision:
    if (strcmp(node->data.name, name) == 0) {
      return node; // returns the node found
    }
    
    // Iteration:
    node = node->next;
  }
  return node; //returns the last node in the list
}

NODE *prev_node(SLLIST *sllistp, char *name) {
  
  // Declarations:
  NODE *node = sllistp->start;
  NODE *prev = NULL; 

  // Traverse:
  while (node != NULL) {
    
    // string comparision:
    if (strcmp(node->data.name, name) == 0) {
      return prev; // returns the node prev
    }
    
    // Iteration:
    prev = node;
    node = node->next;
  }

  return prev; // returns the nodes
}

void insert(SLLIST *sllistp, char *name, float score) {

  // Insertion Node Declarations:
  NODE *node = (NODE*)malloc(sizeof(NODE));
  strcpy(node->data.name, name);
  node->data.score = score;
  node->next = NULL;

  // Insertion Case: Empty Linked List
  if (sllistp->length == 0) {
    sllistp->start = node; // start set to node
    sllistp->length += 1; // increase length
  }

  // Insertion Case: Smallest Value
  else if (strcmp(sllistp->start->data.name, name) >= 0) {
    node->next = sllistp->start;
    sllistp->start = node;
    sllistp->length += 1; // increase length
  }

  // Insertion Cases: Middle or end insertion
  else {

    // Linked List Traversing Declarations:
    NODE *curr = sllistp->start;
    NODE *prev = NULL;

    // Find Point of Insertion:
    while (curr != NULL) {

      // Insert in Middle:
      if (strcmp(curr->data.name, name) >= 0) {
        prev->next = node;
        node->next = curr;
        sllistp->length += 1; // increase length
        break;
      }

      // Insert at End
      if (curr->next == NULL) {
        curr->next = node;
        sllistp->length += 1; // increase length  
        break;
      }

      // Iteration: prev is set to curr, curr is set to next
      prev = curr;
      curr = curr->next;
    }
    
  }
}

int delete(SLLIST *sllistp, char *name) {

  // Case 1: Empty List -> can't delete
  if (sllistp->length == 0) {
    return 0;
  }

  // List Not Empty --> Delete
  else {

    // Get Nodes
    NODE *node = search(sllistp, name);
    NODE *prev = prev_node(sllistp, name);

    // Case: Node Found --> Delete Node
    if (strcmp(node->data.name, name) == 0) {

      // printf("node: %s, prev: %s\n", node->data.name, prev->data.name); // Debug

      // Delete Front:
      if (sllistp->start == node) {
        sllistp->start->next = node->next;
      }

      // Delete End:
      else if (node->next == NULL) {
        prev->next = NULL;
      }

      // Delete Mid:
      else {
        prev->next = node->next;
      }

      // Update Length, Free Node and Return:
      free(node);
      sllistp->length -= 1;
      return 1;
    }

    // Case: Node not Found --> cant delete
    else {
      return -1;
    }

  }
}

void clean(SLLIST *sllistp) {
  
  // Node Declarations:
  NODE *temp,  *node = sllistp->start;
  
  while (node != NULL) {
    temp = node;

    // Next Iteration
    node = node->next;
    free(temp);
  }

  // your implementation to clean the singly linked list
  sllistp->start = NULL;
  sllistp->length = 0;
}

char letter_grade(float s){
  char r = 'F';
  if (s >= 90) r = 'S';
  else if (s >= 80) r = 'A';
  else if (s >= 70) r = 'B';
  else if (s >= 60) r = 'C';
  else if (s >= 50) r = 'D';  
  else r = 'F';
  return r;
}

void select_sort(float a[], int left, int right) 
{
  int i, j, k;
  float temp;
  for (i = left; i <= right; ++i) {
    k = i;
    for(j = i+1; j <= right; ++j) { 
      if (a[j] < a[k]) {
        k = j;
      }
    }
    if (i != k) {
      temp = a[k];
      a[k] = a[i];
      a[i] = temp;     
    }
  } 
}

float median(SLLIST *sllistp)
{
  if (sllistp->start == NULL) return 0;
  NODE *np = sllistp->start;
  int i=0, n = sllistp->length;
  float a[n];   // create array for score float values for sorting
  while (np) {  // get score values from sllist
    a[i] = np->data.score;
    np = np->next;
    i++;
  }
  select_sort(a, 0, n-1);
  return (n % 2 == 1)? a[n/2] : (a[n/2-1] + a[n/2])/2 ;   
} 
 
STATS get_stats(SLLIST *sllistp) {
  int count = 0;
  float mean = 0;
  float stddev = 0;
  NODE *np = sllistp->start;   
  while (np != NULL) {
    count++;
    mean += np->data.score;
    stddev += np->data.score * np->data.score ;
    np = np->next;
  }
  mean /= count;
  stddev = sqrt(stddev/count - mean*mean);
  
  STATS stats = {};
  stats.count = count;
  stats.mean = mean;
  stats.stddev = stddev;
  stats.median = median(sllistp);
  return stats; 
}
 
int import_data(SLLIST *sllistp, char *filename) {
  char line[40], name[40];
  char *valuestr = NULL;
  char delimiters[] = ",";
  float score = 0;

  FILE *fp = fopen(filename, "r");  
  if (fp == NULL) {
    perror("Error while opening the file.\n");
    return 0;
  }
  while (fgets(line, sizeof(line), fp) != NULL) {
    valuestr = strtok(line, delimiters);
    strcpy(name, valuestr);
    valuestr = strtok(NULL, delimiters);
    score = atof(valuestr);
        if (strlen(name)>0 && score >0 ) // validate data
      insert(sllistp, name, score);
  }
  fclose(fp);
  return sllistp->length;
}

STATS report_data(SLLIST *sllistp, char *filename) {
  NODE *np = sllistp->start;
  FILE *fp = fopen(filename, "w");
  while (np != NULL) {
    fprintf(fp, "%s,%c\n", np->data.name, letter_grade(np->data.score));
    np = np->next;
  }
  fclose(fp);  
  
  return get_stats(sllistp);
}

