/*
--------------------------------------------------
Project: cp264-a2q2
File:    polynomial_main.c  public test driver 
Author:  HBF
Version: 2022-01-10
Compile: gcc polynomial.h polynomial_main.c
--------------------------------------------------
*/
#include<stdio.h>
#include<stdlib.h>
#include<math.h>
#include "polynomial.h"
 
int main()
{  
  int n = 4;
  float p[] = {1, 2, 3, 4};
  
  int m = 3;
  float x[] = {0,1,10};
  
  // test display and horner functions
  int i;
  for (i=0; i<m; i++) {
    printf("P(%.2f)=", x[i]);
    display_polynomial(p, n, x[i]);
    printf("=");  
    printf("%.2f\n", horner(p, n, x[i]));
  }
  
float horner(float a[], int n, float x) { //calculate sum using horner method

    int rounded;

    if (x > 0) //add .5 to round
        rounded = (int) (x * 100 + 0.5);

    else
        rounded = (int) (x * 100 - 0.5); //subtract .5 to round

    x = (float) rounded / 100;

    float value = a[0]; //initializer

    for (int i = 1; i < n; i++) //calculate polynomial
        value = value * x + a[i];

    return value;
}

  // test bisection function
  float a=-2, b=2;
  float pa = horner(p, n, a); 
  printf("P(%.2f)=%.2f\n", a, pa);      
  float pb = horner(p, n, b);
  printf("P(%.2f)=%.2f\n", b, pb);

  if (pa * pb <= 0) {
    float root = bisection(p, n, a, b);   
    printf("root=%.2f\n", root);
    printf("P(%.2f)=%.2f\n", root, horner(p, n, root)); 
  }
  else {
    printf("have the same sign on both sides\n");
  }  
  return 0;
}
