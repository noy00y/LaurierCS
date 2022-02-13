#include "mystring.h"
#define NULL 0

int str_length(char *s)
{
if (!s)
return -1;

int n = 0;
for (; *s; ++s)
{

++n;
}

return n;
}

int is_white_space(char c) //helper function
{
if (c == ' ' || c == '\t' || c == '\n')
return 1;
return 0;
}

int word_count(char *s)
{
// char *s = string;
int inWord = 0;
int w_count = 0;

while (*s)
{
if (is_white_space(*s))
{
inWord = 0;
while (is_white_space(*s))
s++; //move to next character
}
else
{
if (!inWord)
{
inWord = 1;
w_count++;
}
s++;
}
}

return w_count;
}

void lower_case(char *s)
{
if (!s)
return;

for (; *s; ++s)
{
int ascii = *s;
if (ascii >= 65 && ascii <= 90)
{
ascii += 32;
*s = ascii;
}
}
}

void trim(char *s)
{
char *org = s;
int size = str_length(s);
char trimmed_s[size];
int b = 0;
if (is_white_space(*s))
{

while (is_white_space(*s))
s++; //move to next character
}
while (*s != '\0')
{
if (!(is_white_space(*s) == 1 && ((is_white_space(*(s + 1)) == 1) || *(s + 1)== '\0')))
{
trimmed_s[b] = *s;
b++;
}
s++;
}
trimmed_s[b] = '\0';
s = org;
for (int i = 0; i < size; i++)
{
*s = trimmed_s[i];
s++;
}
}