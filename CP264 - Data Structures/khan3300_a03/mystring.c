#include "mystring.h"
#define NULL 0

int str_length(char *a)
{
	if (!a)
		return -1;

	int n = 0;
	for (; *a; ++a)
	{
		++n;
	}

	return n;
}

int white_space(char a) 
{
	if (a == ' ' || a == '\t' || a == '\n')
		return 1;
	
	return 0;
}

int word_count(char *a)
{
	int inWord = 0;
	int w_count = 0;

	while (*a)
	{
		if (white_space(*a))
		{
			inWord = 0;
			while (white_space(*a))
			a++; 
		}
		else
		{
			if (!inWord)
		{
			inWord = 1;
			w_count++;
		}
			a++;
		}
	}
	return w_count;
}

void lower_case(char *a)
{
	if (!a)
		return;

	for (; *a; ++a)
	{
		int ascii = *a;
		if (ascii >= 65 && ascii <= 90)
		{
			ascii += 32;
			*a = ascii;
		}
	}
}

void trim(char *a)
{
	char *org = a;
	int size = str_length(a);
	char trimmed_s[size];
	int b = 0;
	if (white_space(*a))
	{
		while (white_space(*a))
		a++; 
	}
	while (*a != '\0')
	{
		if (!(white_space(*a) == 1 && ((white_space(*(a + 1)) == 1) || *(a + 1)== '\0')))
		{
		trimmed_s[b] = *a;
		b++;
		}
		a++;
	}
	trimmed_s[b] = '\0';
	a = org;
	for (int i = 0; i < size; i++)
	{
		*a = trimmed_s[i];
		a++;
	}
}