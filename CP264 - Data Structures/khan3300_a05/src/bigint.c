#include <stdio.h>
#include <stdlib.h>
#include "bigint.h"

BIGINT bigint(char *p) {
  BIGINT bn = {0};
  if (p == NULL) 
    return bn;
  else if (!(*p >= '0' && *p <= '9')) {// not begin with digits 
    return bn;
  }
  else if (*p == '0' && *(p+1) == '\0') {// just "0"
    insert_end(&bn, new_node(*p -'0'));
    return bn;
  }  
  else { 
    while (*p) {
      if (*p >= '0' && *p <= '9' ){
        insert_end(&bn, new_node(*p -'0'));
      } else {
        clean(&bn);   
        break;
      }
      p++;
    }
    return bn;
  }
}

void display_bigint(BIGINT bignumber) {
  NODE *ptr = bignumber.start;
  while ( ptr != NULL) {
    printf("%d", ptr->data);
    ptr = ptr->next;
  }
}

BIGINT add(BIGINT op1, BIGINT op2) {
  // Declarations
  BIGINT sum = bigint(NULL);
  NODE *p1 = op1.end;
  NODE *p2 = op2.end;
  int c = 0, a, b, s;

  while (p1 || p2) {
    a = 0; b = 0;
    if (p1) {a = p1->data; p1 = p1->prev;}
    if (p2) {b = p2->data; p2 = p2->prev;} 

    // Sum of the Digit:
    s = a + b + c;
    if (s >= 10) {
      
      // Pass Sum to Array:
      insert_start(&sum,new_node((s-10)+'0'));
      c = 1;
    }

    else {
      insert_start(&sum, new_node((s) + '0'));
      c= 0;
    }
  }

  // If Carry --> insert 1 at the start
  if (c==1) {
    insert_start(&sum,new_node(1+'0'));
  }

  return sum;
}

BIGINT Fibonacci(int n) {
  if (n <= 2 ) {
    return bigint("1");
  } 
  else {
    BIGINT temp = bigint(NULL);  
    BIGINT f1 = bigint("1");
    BIGINT f2 = bigint("1");
    
    // your implementation of iterative algorithm for Fibonacci number
    int f1 = 1, f2 = 1, temp, i;

    for (i = 3; i <= n; i++) {
      
    }

   return f2;
  }
}