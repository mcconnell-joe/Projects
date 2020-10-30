#include "fileUtils.h"


int countRecords(FILE * fin, int linesPer)
{
	if(fin == NULL || linesPer < 1)
	{
		perror("Bad params to countRecords\n");
		exit(-99);
	}
	int count = 0;
	char temp[MAX];

	fgets(temp, 100, fin);

	while(!feof(fin))
	{
		count ++;
		fgets(temp, 100, fin);
	}

	if(count == 0 || count % linesPer != 0)
	{
		perror("Bad count in countRecords\n");
		exit(-99);
	}
	
	rewind(fin);

	return count/linesPer;
}


FILE * openInputFile_Prompt()
{
	FILE * fin = NULL;
	char fileName[MAX];
	do
	{
		printf("Please enter the name of the input file ");
		fgets(fileName, MAX, stdin);
		strip(fileName);

		fin = fopen(fileName, "r");

	}while(fin == NULL);

	return fin;
}


void strip(char *array)
{
	if(array == NULL)
	{
		perror("array is null");
		exit(-99);
	}// end if

	int len = strlen(array), x = 0;
   
	while(array[x] != '\0' && x < len)
	{
	  if(array[x] == '\r')
		 array[x] = '\0';

	  else if(array[x] == '\n')
		 array[x] = '\0';

	  x++;

	}// end while
   
}// end strip

