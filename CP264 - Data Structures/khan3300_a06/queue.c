#include <stdio.h>
#include <stdlib.h>
#include "common.h"
#include "queue.h"

/* 
 * enqueue a NODE *np and maintain the length property of the Queue. 
*/
void enqueue(QUEUE *qp, NODE *np) {
    // Empty Queue:
    if (qp->length == 0) {
        qp->front = np;
        qp->rear = np;
    }

    // Insert at Rear
    else {
        qp->rear->next = np;
        qp->rear = np;
    }
    
    // Update Length
    qp->length += 1;
}  

/* 
dequeue and return the pointer of the removed NODE, maintain length property.
*/
NODE *dequeue(QUEUE *qp) {
    // New Node
    NODE *node = (NODE*)malloc(sizeof(NODE));

    // Queue Not Empty
    if (qp->length > 0){
        // Get Node and Remove from Queue
        node = qp->front;
        qp->front = qp->front->next;

        // Update Length
        qp->length -= 1;

        // Update Front and Rear if Empty Queue
        if (qp->length == 0) {
            qp->front = NULL;
            qp->rear = NULL;
        }
    }

    // Empty Queue:
    else {
        printf("Underflow\n");
    }
    return node;
}

/* 
 delete and free all nodes of the queue.  
*/
void clean_queue(QUEUE *qp) {
    // Declarations:
    NODE *temp, *node = qp->front;

    // Iterate and Free:
    while (node != NULL) {
        temp = node;
        node = node->next;
        free(temp);
    }

    // Update:
    qp->front = NULL;
    qp->rear = NULL;
    qp->length = 0;
} 