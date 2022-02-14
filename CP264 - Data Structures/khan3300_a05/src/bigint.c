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
  BIGINT sum = bigint(NULL);
  
  // your implementation of addition algorithm

  return sum;
}

BIGINT Fibonacci(int n) {
  if (n <= 2 ) {
    return bigint("1");
  } else {
    BIGINT temp = bigint(NULL);  
    BIGINT f1 = bigint("1");
    BIGINT f2 = bigint("1");
    
   // your implementation of iterative algorithm for Fibonacci number

   return f2;
  }
}