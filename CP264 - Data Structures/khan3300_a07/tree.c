#include <stdio.h>
#include <stdlib.h>
#include "queue_stack.h"
#include "tree.h"

TNODE *new_node(int value) {
  TNODE *np = (TNODE *) malloc(sizeof(TNODE));
  if (np == NULL) return NULL;
  np->data = value;
  np->left = NULL;
  np->right = NULL;
  return np;
}

// Helper Funcs for GetProps
int node_order(TNODE *root) {
  if (root == NULL) {
    return 0;
  }
  return 1 + node_order(root->left) + node_order(root->right);
}

int tree_height(TNODE *root) {
  if (root == NULL) {
    return -1;
  }

  else {
    int lDepth = tree_height(root->left);
    int rDepth = tree_height(root->right);
  
    if (lDepth > rDepth) {
      return (lDepth + 1);
    }

    else {
      return (rDepth + 1);
    }
  }
}

TPROPS get_props(TNODE *root) {
  TPROPS r = {0};

  if (root == NULL) {
    return r;
  }

  else {
    TPROPS lp = get_props(root->left);
    TPROPS rp = get_props(root->right);

    // Get Node Counts:
    r.order = node_order(root);

    // Get Node Height:
    r.height = tree_height(root) + 1;
    
    return r;
  }
}

void display_preorder(TNODE *root) {
  if (root) {
    printf("%c ", root->data);
    display_preorder(root->left);
    display_preorder(root->right);
  }
}

void display_inorder(TNODE *root) {
  if (root) {
    display_inorder(root->left);
    printf("%c ", root->data);
    display_inorder(root->right);
  }
}

void display_postorder(TNODE *root) {
  if (root) {
    display_postorder(root->left);
    display_postorder(root->right);
    printf("%c ", root->data);
  }
}

/* use auxiliary queue data structure for the algorithm  */
void display_bforder(TNODE *root) {
  if (root == NULL) return;

  // Declarations:
  QUEUE q = {0};
  TNODE *temp = NULL;
  enqueue(&q, root);

  // Iterate Through Queue
  while (q.front) {
    temp = dequeue(&q);
    printf("%c ", temp->data);

    if (temp->left) {
     enqueue(&q, temp->left); 
    }
    if (temp->right) {
      enqueue(&q, temp->right);
    }
  }

  // Clean Queue:
  clean_queue(&q);
}

/* use auxiliary queue data structure for the algorithm  */
TNODE *iterative_bfs(TNODE *root, int val) {
  // Declarations:
  QUEUE q = {0};
  TNODE *temp = NULL, *r = NULL;
  enqueue(&q, root);

  // Iterate Through Q:
  if (root != NULL) {
    while (q.front) {
      temp = dequeue(&q);

      // Compare --> get var and break from loop
      if (temp->data == val) {
        r = temp;
        break;
      }

      // Traverse:
      if (temp->left) {
      enqueue(&q, temp->left); 
      }
      if (temp->right) {
        enqueue(&q, temp->right);
      }
    }
  }

  // Clear Queue and Return Var:
  clean_queue(&q);
  return r;
}

TNODE *iterative_dfs(TNODE *root, int val) {
  // Declarations:
  STACK stack = {0};
  TNODE *temp = NULL, *r = NULL;

  // Traverse Stack:
  if (root != NULL) {
    push(&stack, root);
    while (stack.top) {
      temp = (TNODE *) (stack.top)->data; pop(&stack); 

      // Compare --> get var and break from loop
      if (temp->data == val) {
        r = temp;
        break;
      }

      if (temp->right) push(&stack, temp->right);
      if (temp->left) push(&stack, temp->left);
    }
  }

  // Clear Stack and return:
  clean_stack(&stack);
  return r;
}


// the following functions are given, need to add to your program.

void clean_tree(TNODE **rootp) {
  TNODE *p = *rootp;
  if (p) {
    if (p->left)
      clean_tree(&p->left);
    if (p->right)
      clean_tree(&p->right);
    free(p);
  }
  *rootp = NULL;
}

void insert_complete(TNODE **rootp, int val){
  if( *rootp == NULL) { 
    *rootp = new_node(val);
  } else {
    QUEUE queue = {0};
    TNODE *p;
    enqueue(&queue, *rootp);
    while(queue.front){
      p = dequeue(&queue);
      if(p->left == NULL){
        p->left = new_node(val);
        break;
      } else {
        enqueue(&queue, p->left);
      }
      
      if(p->right == NULL){
        p->right = new_node(val);
        break;
      } else  {
        enqueue(&queue, p->right);
      }
    }
  }
}   