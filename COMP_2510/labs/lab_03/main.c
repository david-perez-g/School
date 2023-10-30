#include <stdio.h>
#include <stdbool.h>

void printTokens(char *inputString)
{
    bool word = false;

    while (*inputString)
    {
        if (*inputString == ' ')
        {
            if (word)
            {
                // the word ended
                printf("\n");
                word = false;
            }

            inputString++;
            continue;
        }
        word = true;
        printf("%c", *inputString++);
    }

    printf("\n");
}

int main(void)
{
    printTokens("  Hello  this  is a test  ");
    return 0;
}