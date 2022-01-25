#include <stdio.h>
#include <time.h>

int *ha = 0;
int *la = 0;

// Iterative Implementation
int iterative_fibonacci(int n) {
    
    // 0 Case
    if (&n < la)
        la = &n;

    // Declarations:
    int x = 0;
    int y = 1;
    int a;

    // Iterate
    for (int i = 1; i < n; i++) {
        a = x + y;
        x = y;
        y = a;
    }

    return y;
} 

// Recursive Implementation
int recursive_fibonacci(int n) {
    
    if (&n < la)
        la = &n;

    if (n == 0)
        return 0;

    if (n == 1)
        return 1;
    else
        return recursive_fibonacci(n - 1) + recursive_fibonacci(n - 2);
}