/**
 * @file bin_table.h
 * @author David Pérez García
 * @brief Utils for manipulating binary tables.
 * @version 0.1
 * @date 2022-11-19
 */

#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#ifndef BIN_TABLE_H
#define BIN_TABLE_H
#endif

#ifndef UTILS_H
#include <utils.h>
#endif

#ifndef CONSTANTS_H
#include "constants.h"
#endif

typedef struct binary_table
{
    size_t number_of_rows;
    size_t number_of_cols;
    bool **table;
} binary_table;

binary_table *new_binary_table(size_t rows, size_t cols, bool **table)
{
    binary_table *new_table = malloc(sizeof(binary_table));
    new_table->number_of_rows = rows;
    new_table->number_of_cols = cols;
    new_table->table = table;
    return new_table;
}

/**
 * @brief Frees the table and its rows.
 */
void free_table(binary_table *table)
{
    for (size_t i = 0; i < table->number_of_rows; i++)
    {
        free(table->table[i]);
    }
    free(table);
}

/**
 * @brief Returns the bit at the wanted position.
 *
 * @param table table to extract the bit from
 * @param row row number of the bit
 * @param col column of the bit
 * @return bool the bit
 */
bool get_bit(binary_table *table, size_t row, size_t col)
{
    return table->table[row - 1][col - 1];
}

void print_table(binary_table *table)
{
    for (size_t row = 1; row <= table->number_of_rows; row++)
    {
        for (size_t col = 1; col <= table->number_of_cols; col++)
        {
            bool bit = get_bit(table, row, col);
            printf("%2d", get_bit(table, row, col));
        }
        printf("\n");
    }
}
