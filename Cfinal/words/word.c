#include "word.h"
#include "../utils/fileUtils.h"


/**
 * @brief Prints the specific word.
 * Format is word - length
 * @param passedIn - The void * passed in representing a specific word
 */
void printTypeWord(void * passedIn)
{
   Word * temp = (Word *)passedIn;
   printf("%s: %d\n",temp->ltrs, temp->len);

}// end printType

/**
 * @brief Builds and returns a single word.
 * Reads the initial data from the file and then builds
 * an object of type word.
 * @note The carriage return will be removed when reading from the file
 * @param fin - The FILE * representing the open file
 * @return void * - Representing a word object
 */
void * buildTypeWord(FILE * fin)
{
    char temp[MAX];
    fgets(temp, MAX, fin);
    strip(temp);

    Word * aWord = calloc(1, sizeof(Word));
    aWord->ltrs = (char *) calloc(strlen(temp) + 1, sizeof(char));
    aWord->len = strlen(temp);

    strcpy(aWord->ltrs, temp);

    return (void *)aWord;
}// end buildType


/**
 * @brief Builds and returns a single word.
 * Reads the initial data from the keyboard by prompting the user.
 * A word object is constructed.
 * @note The carriage return will be removed when reading from the keyboard
 * @return void * - Representing a word object
 */
void * buildTypeWord_Prompt()
{

    char temp[MAX];
    printf("Please enter the word:");
    fgets(temp, MAX, stdin);
    strip(temp);

    Word * aWord = calloc(1, sizeof(Word));
    aWord->ltrs = (char*)calloc(strlen(temp) + 1, sizeof(char));

    strcpy(aWord->ltrs, temp);
    aWord->len = strlen(temp);

    return (void *)aWord;
}// end buildType_Prompt


/**
 * @brief Compares two objects of type word.
 * Words are compared based on the letters (word)
 * @note The passed in items will need to be cast to the appropriate word type.
 * @param p1 - The void * representing an object of type word
 * @param p2 - The void * representing an object of type word
 * @return int - Representing order < 0 indicates p1 is less than p2,
 * > 0 indicates p1 is greater than p2 and == 0 indicates p1 == p2 for contents
 */
int compareWords(const void * p1, const void * p2)
{
   Word *left = (Word *)p1;
   Word *right = (Word *)p2;

   return strcmp(left->ltrs, right->ltrs);
}// end compare


/**
 * @brief Cleans up all dynamically allocated memory for the word
 * Cleans up and frees all the dynamically allocated memory
 * In this case ltrs
 * Each pointer in the specific data type is set to NULL after it has been freed.
 * @param passedIn - The void * passed in representing the specific word
 */
void cleanTypeWord(void * ptr)
{
   Word * temp = (Word *) ptr;
   free(temp->ltrs);
   temp->ltrs = NULL;

   free(temp);
   temp = NULL;

}// end cleanType
