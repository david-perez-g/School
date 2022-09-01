#include <stdio.h>

#define MAX_UNSIGNED_INT 276447232

size_t strlen(const char *string)
{
    size_t len = 0;

    while (*string++ && ++len)
        ;

    return len;
}

void findMinDistance(char *inputString)
{
    char repeatedChar = ' ';
    unsigned int minDistance = MAX_UNSIGNED_INT;
    const size_t length = strlen(inputString);

    for (size_t i = 0; i < length - 1; i++)
    {
        char currentChar = inputString[i];
        for (size_t j = i + 1; j < length; j++)
        {
            if (currentChar == inputString[j])
            {
                const unsigned int distance = j - i - 1;

                if (distance < minDistance)
                {
                    minDistance = distance;
                    repeatedChar = currentChar;
                }
            }
        }
    }
    // no repetitions found
    if (minDistance == MAX_UNSIGNED_INT)
    {
        printf("No repetitions found\n");
        return;
    }

    printf("Repeated char: %c, min distance: %d\n", repeatedChar, minDistance);
}

int main(void)
{
    findMinDistance("a abb");
}
