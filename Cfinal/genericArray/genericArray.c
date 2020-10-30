#include "genericArray.h"
#include "../utils/basicUtils.h"

/**
 * The fillArray method, fills the array by reading a record from the file.
 *
 * @param in Representing the input stream
 * @param length The total number of elements in the array
 * @param function pointer for building the type
 * @return GenericArray * Representing the filled array
 */

GenericArray * fillArray(FILE * in, int length, void * (*buildType)(FILE * input))
{
	int x;

	GenericArray * array = (GenericArray *)calloc(length, sizeof(GenericArray));

   for(x = 0; x < length; ++x)
   {
      array[x].data = buildType(in);
   }

	return array;
}

/**
 * The printArray method.  The point of this method is to:<br>
 * The method loops thru each element of the array and simply calls the appropriate print method and prints the contents to the stream
 *
 * @param array Representing the GenericArray as a 1D pointer
 * @param length A pointer back to main for the length of the array
 * @param function pointer for print the contents of the void pointer -- note the function pointer is passed the void * data
 */

void printArray(GenericArray * array, int length, void (*printType)(void *))
{
	int x;

	printf("\n");

   for(x = 0; x < length; x++)
		printType(array[x].data);

	printf("\n");
}

/**
 * The cleanArray method.  The point of this method is to:<br>
 * The method loops thru each element of the array and simply calls the appropriate clean method and frees any dynamically allocated memory<br>
 * Each element of the appropriate type should be set to NULL if it is a pointer<br>
 * Once the for loop completes the array itself is freed.
 *
 * @param array Representing the GenericArray as a 1D pointer
 * @param length A pointer back to main for the length of the array
 * @param function pointer for cleaning the contents of the void pointer -- note the function pointer is passed the void * data
 */

void cleanArray(GenericArray * array, int length, void (*cleanType)(void *))
{
   int x;

   for(x = 0; x < length; ++x)
   {
      cleanType(array[x].data);
   }

   free(array);
   array = NULL;
}

/**
 * The sortArray method uses the selectionSort to sort the array
 *
 * @param array Representing the GenericArray as a 1D pointer
 * @param length A pointer back to main for the length of the array
 * @param function pointer for comparing the data from the line if(compar(array[search], array[min]) < 0)
 */

void sortArray(GenericArray * array, int length, int (*compar)(const void * v1, const void * v2))
{
	int start, search, min;
	GenericArray temp;

	for(start = 0; start < length - 1; start++)
	{
		min = start;

		for(search = start + 1; search < length; search ++)
			if(compar(array[search].data, array[min].data) < 0)
				min = search;

		temp = array[min];
		array[min] = array[start];
		array[start] = temp;

	}// for start

}// end sortArray


/**
 * The addItem method, adds an item into the array.  It first creates a new array of size length + 1
 * then it copies over all the elements.  After the copy it prompts the user to enter the new item
 * from the command line which it stores in the last element of the array. The old array is freed and
 * the length is updated before the new array is returned.
 *
 * @param array The original array to be copied
 * @param length The total number of elements in the array as a pointer
 * @param function pointer for building the type from the keyboard
 * @return GenericArray * Representing the new array
 */
GenericArray * addItem(GenericArray * array, int *length, void * (*buildType_prompt)())
{
   int total = *length, x;
   GenericArray *copy = (GenericArray *)calloc(total + 1, sizeof(GenericArray));


   for(x = 0; x < total; ++x)
   {
      copy[x].data = array[x].data;
   }
   copy[x].data = buildType_prompt();
   *length = total + 1;
   free(array);

   return copy;

}// end addItem



/**
 * The removeItemByValue method, removes an item from the array.  It first prompts the user to enter a type
 * via the function pointer buildType_ propmt.  It then check to see if that value is in the array.
 * If not, then an "Item Not Found! No Changes\n" message is displayed and the original array is returned.
 * If the item is in the array, then item is removed using the function
 * pointer cleanType. A new array is made one smaller and the elements except the one being removed
 * are copied.  the local item is freed via cleanType, and the old array is freed and the length is updated.
 * NOTE: What happens if the array is length 0?
 *
 * @param array The original array to be changed
 * @param length The total number of elements in the array as a pointer
 * @param function pointer for creating the type from the keyboard
 * @param function pointer for removing the type
 * @param function pointer for comparing the type
 * @return GenericArray * Representing the new array
 */
GenericArray * removeItemByValue(GenericArray * array, int *length, void * (*buildType_prompt)(), void (*cleanType)(void * ptr), int (*compar)(const void * v1, const void * v2))
{
   void * type = buildType_prompt();
   int x, res, y;
   if(*length == 0)
   {
       return array;
   }

   for(x = 0; x < *length && res != 0; x++)
   {
      res = compar(type, array[x].data);
   }
   x = x - 1;
   if(res == 0)
   {
       cleanType(array[x].data);
       GenericArray * copy = NULL;
       if(*length > 1)
       {
           copy = (GenericArray *)calloc(*length - 1, sizeof(GenericArray));
           for(y = 0; y < x; y++)
           {
              copy[y].data = array[y].data;
           }
           for(int z = x + 1; z < *length; z++)
           {
           copy[y].data = array[z].data;
           y++;
           }
       }

       free(array);
       array = NULL;

       *length = *length - 1;
       cleanType(type);

       return copy;
   }
   printf("Item not found! No changes\n");
   cleanType(type);
   return array;

}//end removeItemByValue



/**
 * The removeItemByIndex method, removes an item from the array.
 * The method first prompts for an index -- look in myUtils
 * Then item is removed using the function pointer cleanType.
 * A new array is made one smaller and the elements except the one being removed
 * are copied.  The old array is freed and the length is updated.
 * NOTE: What happens if the array is length 0? Can you actually get here if the length is 0?
 *
 * @param array The original array to be changed
 * @param length The total number of elements in the array as a pointer
 * @param function pointer for removing the type
 * @return GenericArray * Representing the new array
 */
GenericArray * removeItemByIndex(GenericArray * array, int *length, void (*cleanType)(void * ptr))
{

   if(*length == 0)
   {
      return array;
   }

   int index = readIndex(*length), x, y;
   GenericArray * copy = NULL;

   cleanType(array[index].data);

   if(*length > 1)
   {
      copy = (GenericArray *)calloc(*length - 1, sizeof(GenericArray));
      for(x = 0; x < index; x++)
      {
         copy[x].data = array[x].data;
      }
      for(y = index + 1; y < *length; y++)
      {
         copy[x].data = array[y].data;
         ++x;
      }
   }

    free(array);
    array = NULL;

   *length = *length - 1;

   return copy;

}//end actuallyRemoveItemByIndex





