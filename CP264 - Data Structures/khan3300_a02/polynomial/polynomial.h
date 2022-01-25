#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#define EPSILON 0.01

// Display Polynomial
void display_polynomial(float p[], int n, float x) {
    int i;

    for (i = 0; i < n; i++) {
        if (n < 0) {
            break;
        }
        
        // Printing
        if (n > 0)
            printf(" + ");
        else if (p[i] < 0)
            printf(" - ");
        else
            printf(" ");
        printf("%.2f*%.2f  %d", p[i], x, --n);
    }
}

// Horner Method
float horner(float p[], int n, float x) {
    // Declarations
    int rounded; // rounding down input

    // Roudning
    if (x > 0)
        rounded = (int) (x * 100 + 0.5);

    else
        rounded = (int) (x * 100 - 0.5);
    
    x = (float) rounded / 100;

    float val = p[0];

    // Iteration for Calculuation
    for (int i = 1; i < n; i++)
        val = val * x + p[i];

    return val;
}

// Bisection Method
float bisection(float p[], int n, float a, float b) {
    // Declarations:
    float x = a;
    float fa, fc; // creating floats for eval

    // Iterating
    while ((b - a) > EPSILON) {
        
        x = (a + b) / 2.0;

        // Eval
        fc = x * x * x * x + 2.0 * x * x * x + 3.0 * x * x + 4.0 * x;
        fa = a * a * a * a + 2.0 * a * a * a + 3.0 * a * a + 4.0 * a;

        // Recalc b
        if (fc < 0.000001) {
            b = x;
        } else {
            a = x;
        }
    }
    return x;
}