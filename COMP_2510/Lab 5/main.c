/**
 * @file main.c
 * @author David Pérez García
 * @version 0.1
 * @date 2022-11-17
 */

#include <stdlib.h>
#include <stdio.h>

#include "constants.h"
#include "utils.h"
#include "bin_table.h"
#include "table_parser.h"
#include "bit_manipulation.h"

/**
 * @brief Processes the file and performs the program's main operations.
 *
 * @param file file to be processed.
 * @return int8_t COLUMN_NOT_FOUND if an error occurred while searching
 *                for the number of rows or columns,
 *                INVALID_NUMBER_OF_COLUMNS if the number of columns was
 *                out of this range (0, MAX_COLUMN_SIZE],
 *                CORRUPTED_STRING if there was an error while parsing
 *                the file,
 *                OPERATION_SUCCESS if everything worked as expected
 */
int8_t process_file(FILE *file)
{
    char *first_line = joinnlines(file, 1, MAX_LINE_SIZE);

    size_t nrows, ncols;
    if (get_number_from_column(first_line, 1, &nrows) == COLUMN_NOT_FOUND ||
        get_number_from_column(first_line, 2, &ncols) == COLUMN_NOT_FOUND)
        return COLUMN_NOT_FOUND;

    free(first_line);

    if (ncols > MAX_COLUMN_SIZE || !ncols)
        return INVALID_NUMBER_OF_COLUMNS;

    binary_table *table = new_binary_table(nrows, ncols, NULL);
    char *table_string = joinnlines(file, nrows, 2 * ncols + 1);

    if (parse_to_bin_table(table_string, table) == COLUMN_NOT_FOUND)
    {
        free(table_string);
        return CORRUPTED_STRING;
    }
    free(table_string);

    for (size_t row = 0; row < table->number_of_rows; row++)
    {
        long converted = binary_arr_to_unsigned_number(
            table->table[row], table->number_of_cols);

        printf("%d\n", converted);
    }

    free_table(table);
    return OPERATION_SUCCESS;
}

int main(int argc, const char *argv[])
{
    if (argc < 2)
    {
        printf("Please provide the name of the file to be read.\n");
        return EXIT_FAILURE;
    }

    const char *file_path = argv[1];

    if (!file_exists(file_path))
    {
        printf("The provided file (%s) does not exist.\n", file_path);
        return EXIT_FAILURE;
    }

    FILE *file = fopen(file_path, "r");
    int8_t operation_result = process_file(file);
    fclose(file);

    switch (operation_result)
    {
    case COLUMN_NOT_FOUND:
    {
        printf("Couldn't fetch the number of columns or rows from the file.\n");
        return EXIT_FAILURE;
    };
    case INVALID_NUMBER_OF_COLUMNS:
    {
        printf("This number of columns is invalid.\n");
        return EXIT_FAILURE;
    };
    case CORRUPTED_STRING:
    {
        printf("An error occurred while parsing the file.\n");
        return EXIT_FAILURE;
    };
    case OPERATION_SUCCESS:
        return EXIT_SUCCESS;
    }
}
