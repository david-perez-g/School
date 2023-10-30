/**
 * @file utils.h
 * @author David Pérez García
 * @brief Utils for the program.
 * @version 0.1
 * @date 2022-11-18
 */

#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#ifndef UTILS_H
#define UTILS_H
#endif

/**
 * @brief Checks whether the passed file path exists.
 *
 * @param file_path path to the file to be checked
 * @return true if the file path exists
 * @return false if the file path does not exist
 */
bool file_exists(const char *file_path)
{
    FILE *f = fopen(file_path, "r");
    if (f == NULL)
    {
        return false;
    }
    fclose(f);
    return true;
}

/**
 * @brief Joins the specified number of lines of the file into a single string.
 *
 * @param file file to get the lines from
 * @param nlines number of lines to join
 * @param line_size maximum size of each line
 * @return char* all the read lines joined in a single string
 */
char *joinnlines(FILE *file, size_t nlines, size_t line_size)
{
    char *result = malloc(nlines * line_size * sizeof(char) + 1);
    for (size_t i = 0; i < nlines; i++)
    {
        char *line = malloc(line_size * sizeof(char));
        if (fgets(line, line_size, file) == NULL)
        {
            free(line);
            result[i] = '\0';
            break;
        }
        strncat(result, line, line_size);
        free(line);
    }
    return result;
}