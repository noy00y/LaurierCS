// your program signature


#include "mysort.h"

BOOLEAN is_sorted(int *a, int left, int right)
{
	// Declarations
	int prev, curr, n = right;

	for (int i = 0; i < n; i++) {

		// Iteration:
		curr = *(a+i);

		if (i > 0 && prev > curr) {
			return false;
		}

		else {
			prev = curr;
		}
	}
	return true;
}

void select_swap() {

}

void select_sort(int *a, int left, int right)
{
	// Declarations:
	int i, j, minIndex, n = right;

	for (i = 0; i < n; i++) {

		// Setting the Min Index:
		minIndex = i;

		// unsorted sub array
		for (j = i + 1; j < n; i++) {

			// if j val smaller then minVal:
			if (*(a + j) < *(a + minIndex)) {

			}
		}
	}
}

void quick_sort(int *a, int left, int right)
{
// your implementation
}
