#include <math.h>

int helper(int number, unsigned int call_number) {
    if (number == 0) {
        return 0;
    }

    char first_digit = number - 10 * floor(number / 10);
    int without_first_digit = (number - first_digit) / 10;
    
    if (call_number % 2 == 0) {
        return helper(without_first_digit, call_number + 1);
    }

    return first_digit * pow(10, (call_number - 1) / 2) + 
        helper(without_first_digit, call_number + 1);
    
}

/*
1. Write a recursive function that takes an integer as the input and returns
   a new number which consists of the digits of the original number which
   are in odd positions. Assuming rightmost digit is in position 1, the second
   rightmost digit is in position 2 and so on.
   Here are some examples:
    • 89201 → 821
    • −123 → −13
    • 20 → 0
    • 87654321 → 7531
*/
int getOddPositionedDigits(int number) {
    return helper(number, 1);
}
