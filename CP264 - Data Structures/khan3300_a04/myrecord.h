#ifndef myrecord_H
#define myrecord_H
typedef struct {
  char name[20];
  float marks;
} RECORD;

typedef struct {
  int count;
  float mean; 
  float median;
  float stddev; 
} STATS;

char letter_grade(float score);
int import_data(RECORD dataset[], char *infilename);
STATS report_data(RECORD dataset[], int count, char *outfilename);

#endif