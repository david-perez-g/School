/**
 * @file table_parser.h
 * @author David Pérez García
 * @brief String parsing for building binary tables.
 * @version 0.1
 * @date 2022-11-19
 */

#include <string.h>
#include <stdlib.h>

#ifndef TABLE_PARSER_H
#define TABLE_PARSER_H
#endif

#ifndef BIN_TABLE_H
#include "bin_table.h"
#endif

#define NOT_A_BIT -1
#define COLUMN_NOT_FOUND -2
#define OPERATION_SUCCESS 2
#define INVALID_NUMBER_OF_COLUMNS 3
#define INCORRECT_NUMBER_OF_ROWS 4
#define CORRUPTED_STRING 5

#define COLUMN_SEPARATOR " "
#define ROW_SEPARATOR "\n"

/**
 * @brief Returns the wanted column.
 *
 * @param line line to extract the column from
 * @param column_number number of the column to be searched
 * @return char* NULL if the column was not found, otherwise the column
 */
char *get_column(const char *line, size_t column_number)
{
    char *line_copy = strdup(line); // this variable will be free'd by strsep when
                                    // the string is fully consumed.
    size_t current_col_num = 0;

    for (
        char *token = strsep(&line_copy, COLUMN_SEPARATOR);
        token != NULL;
        token = strsep(&line_copy, COLUMN_SEPARATOR))
    {
        current_col_num++;
        if (current_col_num == column_number)
        {
            return token;
        }
    }
    return NULL;
}

/**
 * @brief Gets the specified column and converts the string to a long.
 *
 * @param line line to be parsed
 * @param column_number column number to be converted
 * @param ptr_to_result a pointer to the resulting number.
 * @return int8_t COLUMN_NOT_FOUND if the column was not found, otherwise OPERATION_SUCCESS
 */
int8_t get_number_from_column(const char *line, size_t column_number, long *ptr_to_result)
{
    char *column = get_column(line, column_number);

    if (!column)
        return COLUMN_NOT_FOUND;

    *ptr_to_result = atol(column);
    return OPERATION_SUCCESS;
}

/**
 * @brief Gets the bit from the column specified. If the column doesn't contains either
 * a 0 or 1, a 0 will be returned instead.
 *
 * @param line line to get the bit from
 * @param column_number column number
 * @param bit_ptr pointer to the resulting bit
 * @return int8_t COLUMN_NOT_FOUND if the column doesn't exists,
 *                NOT_A_BIT if the column doesn't contain either a 0 or 1,
 *                OPERATION_SUCCESS if the operation was successful.
 */
int8_t get_bit_from_column(const char *line, size_t column_number, bool *bit_ptr)
{
    long number;
    if (get_number_from_column(line, column_number, &number) == COLUMN_NOT_FOUND)
        return COLUMN_NOT_FOUND;

    if (number < 0 || number > 1)
        return NOT_A_BIT;

    *bit_ptr = number;
    return OPERATION_SUCCESS;
}

/**
 * @brief Parses the string and creates a table.
 * If a column doesn't contains either a 0 or 1, a 0 will be set instead.
 *
 *
 * @param string string to parse
 * @param dest_table destiny table
 * @return int8_t COLUMN_NOT_FOUND if there was an error while parsing the string,
 *                OPERATION_SUCCESS if the operation was successful
 */
int8_t parse_to_bin_table(const char *string, binary_table *dest_table)
{
    size_t ncols = dest_table->number_of_cols, nrows = dest_table->number_of_rows;

    char *string_copy = strdup(string);
    char *line = strsep(&string_copy, ROW_SEPARATOR);
    bool **table = malloc(nrows * sizeof(bool *));
    for (size_t row_number = 0;
         row_number < nrows;
         row_number++)
    {
        bool *row = malloc(ncols * sizeof(bool));

        for (size_t col_number = 0; col_number < ncols; col_number++)
        {
            bool *bit = malloc(sizeof(bool));
            int8_t op_result = get_bit_from_column(line, col_number + 1, bit);
            switch (op_result)
            {
            case COLUMN_NOT_FOUND:
            {
                free(string_copy);
                for (size_t i = 0; i < row_number; i++)
                    free(table[i]);
                free(table);
                return COLUMN_NOT_FOUND;
            };
            case NOT_A_BIT:
                *bit = 0;
            }
            row[col_number] = *bit;
            free(bit);
        }

        line = strsep(&string_copy, ROW_SEPARATOR);
        table[row_number] = row;
    }
    // a case where line is not null may occur,
    // in which, line can't be free'd.
    // this happens when the number of rows
    // is less than the actual rows in the string.
    if (line == NULL)
        free(line);

    free(string_copy);
    dest_table->table = table;
    return OPERATION_SUCCESS;
}