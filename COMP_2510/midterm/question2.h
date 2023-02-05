#include <stdlib.h>

int *empty_array(unsigned int size) {
    if (size == 0) {
        return NULL;
    }

    int *array = malloc(size * sizeof(int));
    for (int i = 0; i < size; i++) {
        array[i] = 0;
    }   
    return array;
}

/*
2. Write a function that takes a string and prints out the frequency of each
letter in the string.
*/
void printCharacterFrequency(char *string) {
    int *characters = empty_array(128);

    for (char ch = *string++; ch != '\0'; ch = *string++) {
        characters[ch] += 1;
    }

    for (unsigned char i = 0; i < 128; i++) {
        if (characters[i] == 0) {
            continue;
        }
        printf("%c: %d\n", i, characters[i]);
    }

    free(characters);
}