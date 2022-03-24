#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "bst.h"

TNODE *search(TNODE *root, char *name) {
    if (root==NULL) {
        return NULL;
    } else if (strcmp(name,root->data.name)==0){
        return root;
    } else if (strcmp(name,root->data.name)<0){
        return search(root->left,name);
    } else {
        return search(root->right,name);
    }
}

TNODE *node_insert(char *name, float score) {
  // Declarations:
  TNODE *node = (TNODE*)malloc(sizeof(TNODE));
  if (node == NULL) return NULL;

  // Set Values:
  strcpy(node->data.name, name);
  node->data.score = score;
  node->right = NULL;
  node->left = NULL;

  return node;
}

void insert(TNODE **rootp, char *name, float score) {
    // Base Case: Insert
    if (*rootp == NULL) { 
        TNODE *node = node_insert(name,score); 
        if (node==NULL) return; 
        *rootp = node; 
    } 
    
    // Else: Find Insertion Pt and Insert:
    else {
        // Declarations:
        int compare = strcmp(name,(*rootp)->data.name);
        
        // Case 1: Val Already in BST
        if (compare==0){ 
            return; 
        } 

        // Recurse and Insert Left
        else if (compare<0){
            return insert(&(*rootp)->left,name,score);
        } 

        // Recurse and Insert Right
        else {
            return insert(&(*rootp)->right,name,score);
        }
    }
}

void delete(TNODE **rootp, char *name) {
  TNODE *node = *rootp, *temp; // Declarations:
  if (*rootp == NULL) {
    return; // If NULL --> Return
  } 
  // Else if --> node found --> remove it
  else if (strcmp(name, node->data.name) == 0) {
    // Case 1: Leaf Node --> Just Remove it
    if (node->left == NULL && node->right == NULL) {
      free(node);
    }

    // Case 2: 1 Child --> change link
    else if (node->right!=NULL && node->left==NULL){
      temp = node->right;
      free(node);
      *rootp = temp;
    }
    else if (node->left != NULL && node->right == NULL) {
      temp = node->left;
      free(node);
      *rootp = temp;
    }

    // Case 3: 2 Child --> Reassign
    else if (node->left!=NULL && node->right!=NULL){
      temp = extract_smallest_node(&node->right);
      temp->left = node->left;
      temp->right = node->right;
      *rootp = temp;
      free(node);
    }
    return;
  }

  // Node Not Found --> Recurse
  else {
    if (strcmp(name, node->data.name) < 0) {
      return delete(&node->left, name);
    }
    else {
      return delete(&node->right, name);
    }
  }
}


/* auxiliary function for delete */
TNODE *extract_smallest_node(TNODE **rootp) {
  TNODE *p = *rootp, *parent = NULL;
  if (p) {
    while (p->left) {
      parent = p;
      p = p->left;
    }
    
    if (parent == NULL)
      *rootp = p->right;
    else
      parent->left = p->right;
    
    p->left = NULL;
    p->right = NULL;
  }
  
  return p;
}


// the following functions are given for testing, need to add to your program.
void clean_tree(TNODE **rootp) {
    TNODE *root = *rootp;
    if (root) {
        if (root->left)
            clean_tree(&root->left);
        if (root->right)
            clean_tree(&root->right);
        free(root);
    }
    *rootp = NULL;
}