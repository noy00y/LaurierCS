#include<stdio.h>
#include "matrix.h"

void display_vector(int *v, int n) {
    int *p = v, i, j;
    for (i = 0; i < n; i++) {
       printf("%-4d", *p++); 
    }
    printf("\n");
}

void display_matrix(int *m, int n) {
    int *p = m, i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) printf("%4d", *p++);
        printf("\n");
    }
}

// Sum of Vector 
int vsum(int *v, int n) {
    int *p = v, i, sum = 0;

    for (i = 0; i < n; i++) {
        sum = sum + *p++;
    }
    return sum;
}

// Sum of Matrix
int msum(int *m, int n) {
    int *p = m, i, j, sum = 0;

    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            sum = sum + *p++;
        }
    }
    return sum;
}

// Transpose Matrix
void transpose_matrix(int *m1, int *m2, int n) {
    int *p1 = m1, *p2 = m2, i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            *p2++ = *(p1 + n * j + i);
        }
    }
}

// Matrix Multi
void multiply_matrix(int *m1, int *m2, int *m3, int n) {
    int *p1 = m1, *p2 = m2, *p3 = m3, x = 0, i, j, k;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            x = 0;
            for (k = 0; k < n; k++) {
                x += *(p1 + n * i + k) * *(p2 + n * k + j);
                }
                *p3++ = x;
            }
    }
}

// Vector Multi
void multiply_vector(int *m, int *vin, int *vout, int n) {

}