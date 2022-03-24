// your program signature

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "avl.h"

// help functions
TNODE *extract_smallest_node(TNODE **rootp);
int max(int a, int b);

// this is a help function for balance factor.
int height(TNODE *np)
{
  // Base Case:
  if (np == NULL) {
    return 0;
  }

  else {
    // Recurse:
    int lheight = height(np->left);
    int rheight = height(np->right);

    // Find and Return Max Height  
    if (lheight > rheight)
      return (lheight + 1);
    else
      return (rheight + 1);
  }
}

int balance_factor(TNODE* np) {
  return height(np->left) - height(np->right);
}

int is_avl(TNODE *root) {
  if (root == NULL) return 1;
  else {
    int bf = balance_factor(root);
    if (bf > 1 || bf < -1) {
      return 0;
    }
    if (is_avl(root->left) == 0) {
      return 0;
    }
    if (is_avl(root->right) == 0) {
      return 0;
    }
    return 1;
  }
}

TNODE *rotate_right(TNODE *y)
{
  TNODE *x = y->left;
  TNODE *T2 = x->right;
  // perform rotation
  x->right = y;
  y->left = T2;
  // update heights
  y->height = max(height(y->left), height(y->right))+1;
  x->height = max(height(x->left), height(x->right))+1;
  return x;
}

TNODE *rotate_left(TNODE *x)
{
  TNODE *y = x->right;
  TNODE *T2 = y->left;
  // perform rotation
  y->left = x;
  x->right = T2;
  // Update heights
  x->height = max(height(x->left), height(x->right))+1;
  y->height = max(height(y->left), height(y->right))+1;
  return y;
}

void insert(TNODE **rootp, char *name, float score)
{
  TNODE *np = (TNODE *) malloc(sizeof(TNODE));
  if (np == NULL) return;
  strcpy(np->data.name, name);
  np->data.score = score;
  np->height = 1;
  np->left = NULL;
  np->right = NULL;
  
  // 1. Perform the normal BST insertion
  if (*rootp == NULL) {
    *rootp = np;
    return;
  }
  
  TNODE *root = *rootp;
  if (strcmp(name, root->data.name) < 0 )
    insert(&root->left, name, score);
  else if (strcmp(name, root->data.name) > 0 )
    insert(&root->right, name, score);
  else return ;

  // 2. update height of this root node
  root->height = 1 + max(height(root->left), height(root->right));
 
  // 3. Get the balance factor of this ancestor node to check whether this node became unbalanced
  int bf = balance_factor(root);

  // 4. re-balance if not balanced
  if (bf > 1) {
    if (balance_factor(root->left) >=0 ) *rootp = rotate_right(root);
    else {
      root->left = rotate_left(root->left);
      *rootp = rotate_right(root);
    }
  }
  else if (bf < -1) {
  if (balance_factor(root->right) <= 0 ) *rootp = rotate_left(root);
    else {
      root->right = rotate_right(root->right);
      *rootp = rotate_left(root);
    }
  }
}

void delete(TNODE **rootp, char *name)
{
  TNODE *root = *rootp;
  TNODE* np;

  if (root == NULL) return;

  if (strcmp(name, root->data.name) == 0) {
    if (root->left == NULL && root->right == NULL) {
      free(root);
      *rootp = NULL;
    } else if (root->left != NULL && root->right == NULL) {
      np = root->left;
      free(root);
      *rootp = np;
    } else if (root->left == NULL && root->right != NULL) {
      np = root->right;
      free(root);
      *rootp = np;
    } else if (root->left != NULL && root->right != NULL) {
      np = extract_smallest_node(&root->right);
      np->left = root->left;
      np->right = root->right;
      free(root);
      *rootp = np;
    }
  } else {
    if (strcmp(name, root->data.name) < 0) {
      delete(&root->left, name);
    } else {
      delete(&root->right, name);
    }
  }

    // If the tree had only one node then return
  if (*rootp == NULL) return;

  root = *rootp;
  
  // 2. update height of this root node
  root->height = 1 + max(height(root->left), height(root->right));
 
  // 3. Get the balance factor of this ancestor node to check whether this node became unbalanced
  int bf = balance_factor(root);

  // 4. re-balance if not balanced
  if (bf > 1) {
    if (balance_factor(root->left) >=0 ) *rootp = rotate_right(root);
    else {
      root->left = rotate_left(root->left);
      *rootp = rotate_right(root);
    }
  }
  else if (bf < -1) {
  if (balance_factor(root->right) <= 0 ) *rootp = rotate_left(root);
    else {
      root->right = rotate_right(root->right);
      *rootp = rotate_left(root);
    }
  }
}


// You are allowed to use the following functions
int max(int a, int b)
{
  return (a > b)? a : b;
}

TNODE *extract_smallest_node(TNODE **rootp) {
  TNODE *tnp = *rootp;
  TNODE *parent = NULL;
  if (tnp == NULL) {
    return NULL;
  } else {
    while (tnp->left) {
      parent = tnp;
      tnp = tnp->left;
    }
    if (parent == NULL)
      *rootp = tnp->right;
    else
      parent->left = tnp->right;
    tnp->left = NULL;
    tnp->right = NULL;
    return tnp;
  }
}

// the following functions are from A7
TNODE *search(TNODE *root, char *name) {
  TNODE *tp = root;
  while (tp) {
    if (strcmp(name, tp->data.name) == 0) {
       return tp;
    } else if (strcmp(name, tp->data.name) < 0) 
      tp = tp->left;
     else 
      tp = tp->right;
  }    
  return NULL;
}

void clean_tree(TNODE **rootp) {
  if (*rootp) {
    TNODE *np = *rootp;
    if (np->left)
      clean_tree(&np->left);
    if (np->right)
      clean_tree(&np->right);
    free(np);
  }
  *rootp = NULL; ;
}