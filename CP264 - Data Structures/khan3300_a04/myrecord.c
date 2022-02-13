#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include "myrecord.h"
#define MAX_LINE 100

char letter_grade(float score){
if(score >= 90.0 && score<=100.0){
    return 'S';
  }
  else if(score >= 80.0 && score<90.0){
    return 'A';
  }
  else if(score >= 70.0 && score<80.0){
    return 'B';
  }
  else if(score >= 60.0 && score<70.0){
    return 'C';
  }
  else if(score >= 50.0 && score<60.0){
    return 'D';
  }
  else if(score >= 0.0 && score<50.0){
    return 'F';
  }
  return '@';
}

int import_data(RECORD dataset[], char *filename) {
    int cnt=0;
    FILE *fp = fopen(filename, "r");
    if (fp == NULL)
    {
        printf("Error: could not open file %s", filename);
        return 1;
    }
    const unsigned MAX_LENGTH = 256;
    char buffer[MAX_LENGTH];

    while (fgets(buffer, MAX_LENGTH, fp))
       {
        char * token = strtok(buffer, ","); 
        memcpy(dataset[cnt].name , token , 20);
        token = strtok(NULL, ",");
        dataset[cnt].marks = atoi(token);
        ++cnt;
       }
    fclose(fp);
    return cnt;
}

STATS report_data(RECORD dataset[], int n, char *filename) {
  STATS report = {};
  report.count = n;
  if (n < 1) return report;
  float  variance , sum = 0, sum1 = 0;
  int i;
  for (i = 0; i < n; i++)
    {
        sum = sum + dataset[i].marks;
    }
  report.mean = sum / (float)n;
  for (i = 0; i < n; i++)
    {
        sum1 = sum1 + pow((dataset[i].marks - report.mean), 2);
    }
  variance = sum1 / (float)n;
  report.stddev = sqrt(variance);
  RECORD temp;
  for (i = 0; i < n - 1; i++)
    for (int j = i; j < n; j++) {
      if (dataset[i].marks > dataset[j].marks) {
        temp = dataset[i];
        dataset[i] = dataset[j];
        dataset[j] = temp;
      }
    }
  if ((n + 1) % 2 == 0) {
          report.median = dataset[((n + 1) / 2) - 1].marks;
  } else {
          report.median = (dataset[((n + 1) / 2) - 1].marks + dataset[((n + 2) / 2) - 1].marks) / 2;
  }
  FILE *fp;
  fp = fopen(filename, "w");
  if(fp == NULL) {
        printf("file can't be opened\n");
        exit(1);
    }
  for(int i=0;i<n;++i){
    fprintf(fp , "%s,%c\n" , dataset[i].name , letter_grade(dataset[i].marks));
  }
  return report;
}