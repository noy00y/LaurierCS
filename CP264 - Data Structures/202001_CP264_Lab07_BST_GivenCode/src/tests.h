/*
 -------------------------------------
 File:    tests.h
 Project: data_structures_linked
 file description
 -------------------------------------
 Author:  David Brown
 ID:      999999999
 Email:   dbrown@wlu.ca
 Version: 2019-06-22
 -------------------------------------
 */

#ifndef TESTS_H_
#define TESTS_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "data.h"
#include "int_data.h"
#include "bst.h"

#define SEP "-------------------------------\n"
#define BAD_PRINT(a) (a == NULL) ? "string too small" : a

void bst_test();

#endif /* TESTS_H_ */