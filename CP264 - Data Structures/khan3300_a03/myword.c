#include <stdio.h>
#include <string.h>
#include "mystring.h"
#include "myword.h"


int process_word(char *infilename, WORDINFO *wip)
{
  const char delimiters[] = " .,;:!()&?-\n\t\r"'"; 
  char line[MAX_LINE_LEN]; 
  char *word_token = NULL; 
  int j; 
  FILE fp = fopen(infilename,"r"); 
  while (fgets(line,MAX_LINE_LEN,fp) != NULL){
    wip -> line_count ++;
    lower_case(line); 
    trim(line); 
    word_token = (char)strtok(line,delimiters); 
    while (word_token != NULL){
        int exists = 0; 
        for (int i=0;i<MAX_WORDS;i++){  
            if (strcmp(wip -> word_array[i].word,word_token) == 0){ 
                wip -> word_array[i].frequency++; 
                exists = 1; 
            }
        }
        if (exists!=1){
            WORD test = {word_token,1}; 
            for (int i=0;i<MAX_WORDS;i++){
                if (str_length(wip -> word_array[i].word) == 0){ 
                    strcpy(wip -> word_array[i].word,word_token); 
                    wip -> word_array[i].frequency = 1;
                    wip -> word_count += 1;
                    wip -> distinct_word_count += 1;
                }
            }
        } 
    }
    word_token = (char)strtok(NULL,delimiters);
  }
  fclose(fp);
}

int save_to_file(char *outfilename, WORDINFO *wip)
{
// your implementation
}