// program signature
#include <stdio.h>
#include <math.h>  // need this library for maths functions fabs() and sqrt()

#define EPSILON 0.000001
// or #define EPSILON 1e-6

int main() {
	// setbuf(stdout, NULL); // uncomment this line for using Eclipse console

	float a, b, c, d, x1, x2;
	int inn;
	char temp;

	do { // do-while for new input problem

		do { // do-while loop to get correct input of three floating numbers

			printf("Please enter the coefficients a,b,c\n");
			inn = scanf("%f,%f,%f", &a, &b, &c);

			if (inn != 3) {
				printf("input:Invalid input\n");
			} else
				break;

			do {  // flush the input buffer
				scanf("%c", &temp);
				if (temp == '\n')
					break;
			} while (1);

		} while (1);

		if (fabs(a) < EPSILON && fabs(b) < EPSILON && fabs(c) < EPSILON) {
			printf("input:quit\n");
			break;

		} else if (fabs(a) < EPSILON) {
			printf("input:not a quadratic equation\n");

		} else {

			d = b * b - 4 * a * c;  // compute the discriminant

			// Case 1: D is 0
			if (d == 0) {
				x1 = (-1 * b) / (2 * a);
				printf("real: %f", x1);
				printf("\n");
			}

			// Case 2: D > 0
			else if (d > 0) {
				x1 = ((-1 * b) + sqrt(b * b - 4 * a * c)) / (2 * a);
				x2 = ((-1 * b) - sqrt(b * b - 4 * a * c)) / (2 * a);
				printf("x1: %f", x1);
				printf("\n");
				printf("x2: %f", x2);
				printf("\n");
			}

			// Case 3: D < 0
			else if (d < 0) {
				x1 = (-1 * b) / (2 * a);
				printf("imaginary: %f", x1);
				printf("\n");
			}

		}
	} while (1);
	return 0;
}
