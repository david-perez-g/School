/**
 * @file bit_manipulation.h
 * @author David Pérez García
 * @brief Bit operations.
 * @version 0.1
 * @date 2022-11-19
 */

#include <stdbool.h>
#include <math.h>

#ifndef CONSTANTS_H
#include "constants.h"
#endif

#ifndef BITS_H
#define BITS_H
#endif

/**
 * Returns the mask for the position, the position must be >= 1
 */
long get_mask(u_int8_t bit_pos_from_right)
{
    return ;
}

/**
 * @brief Sets the bit at the provided position to 1.
 */
void turn_on(long *number, u_int8_t bit_position_from_right)
{
    *number |= get_mask(bit_position_from_right);
}

/**
 * @brief Sets the bit at the provided position to 0.
 */
void turn_off(long *number, u_int8_t bit_position_from_right)
{
    *number &= ~get_mask(bit_position_from_right);
}

/**
 * @brief Sets the bit at the provided position to the specified value.
 */
void set_bit_to(bool value, long *number, u_int8_t bit_position_from_right)
{
    if (!bit_position_from_right)
    {
        perror("The bit position must be >= 1");
        return;
    }

    value ? turn_on(number, bit_position_from_right) : turn_off(number, bit_position_from_right);
}

/**
 * @brief Converts the binary array into a unsigned long.
 *
 * @param arr a binary array
 * @param nbits size of the array
 * @return long the number converted to an unsigned long
 */
ulong binary_arr_to_unsigned_number(const bool *arr, size_t nbits)
{
    if (nbits == 0)
        return 0;
    if (nbits > MAX_COLUMN_SIZE)
        nbits = MAX_COLUMN_SIZE;

    long result = 0;

    for (long i = nbits - 1; i >= 0; i--)
    {
        bool bit = arr[i];
        u_int8_t pos_from_right = nbits - i;
        set_bit_to(bit, &result, pos_from_right);
    }

    return result;
}