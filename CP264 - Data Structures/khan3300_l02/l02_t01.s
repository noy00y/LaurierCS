/*
-------------------------------------------------------
l02_t01.s
Assign to and add contents of registers.
-------------------------------------------------------
Author:  Ozair Khan	
ID:      20014300
Email:   khan3300@wlu.ca
Date:    2022-01-22
-------------------------------------------------------
*/
.org    0x1000    // Start at memory location 1000
.text  // Code section
.global _start
_start:

// Copy data from memory to registers
LDR    R3, =A; // register needs "="
LDR    R0, [R3]
LDR    R3, =B; // register needs "="
LDR    R1, [R3]
ADD    R2, R1, R0 // can't add a memory
// Copy data to memory
LDR    R3, =Result    // Assign address of Result to R3
STR    R2, [R3]    // Store contents of R2 to address in R3
// End program
_stop:
B _stop

.data         // Initialized data section
A:
.word    4
B:
.word    8
.bss        // Uninitialized data section
Result:
.space 4    // Set aside 4 bytes for result

.end