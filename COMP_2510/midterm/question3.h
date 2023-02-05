#include <math.h>

int get_mask(u_int8_t bit_pos_from_right)
{
    return pow(2, bit_pos_from_right - 1);
}

void turn_on(int *number, u_int8_t bit_position_from_right)
{
    *number |= get_mask(bit_position_from_right);
}

void turn_off(int *number, u_int8_t bit_position_from_right)
{
    *number &= ~get_mask(bit_position_from_right);
}

/*
3. Write a function that takes a non-negative number and turns on the second 
   and third binary digits (from right) for this number. The function
   should also turn off the fourth digit (from right). Here are some examples:
    • 9 = (1001)2 → 7 = (0111)2
    • 0 = (0)2 → 6 = (110)2
    • 17 = (10001)2 → 23 = (10111)2
    • 31 = (11111)2 → 23 = (10111)2
*/
int bitFun(unsigned int number) {
    turn_on(&number, 2);
    turn_on(&number, 3);
    turn_off(&number, 4);
    return number;
}