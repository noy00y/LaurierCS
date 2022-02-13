#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>
#include "myrecord.h"

#define MAX_REC 100
int main(int argc, char *args[]) { 
  char infilename[40] = "marks.txt";   
  char outfilename[40] = "report.txt"; 
  if (argc > 1) {

    if (argc >= 2) strcpy(infilename, args[1]);

    if (argc >= 3) strcpy(outfilename, args[2]);

  }  

  RECORD dataset[MAX_REC]; 

  int record_count = import_data(dataset, infilename); 

  if (record_count >=1) { 

    STATS stats = report_data(dataset, record_count, outfilename);

printf("count:%d\n", stats.count);

printf("mean:%.1f\n", stats.mean);

printf("stddev:%.1f\n", stats.stddev);

printf("median:%.1f\n", stats.median);

  } else 

    printf("no data is found");

  return 0;

}