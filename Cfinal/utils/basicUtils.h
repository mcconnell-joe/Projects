/**
 * @file myUtils.h
 * The myUtils.h defines the basic menu and strip functions that will be
 * required during the course of the quarter.
 */

#ifndef MYUTILS_H
#define MYUTILS_H

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 100


/**
 * @brief Prompts the user for a menu choice.
 *
 * Prompts the user for a menu choice and ensures the value
 * entered by the user is within range.
 *
 * @note The carriage return is removed from the input buffer
 *
 * @return int - Representing the menu choice
 */
int menu();


/**
 * readIndex reads an index from the keyboard and ensures it
 * is between 0 and the total - 1.
 *
 * @param int Representing the total size
 * @return int - Representing the index
 *
 * @warning ensures the carriage return is removed from stdin
 */
int readIndex(int total);

#endif
