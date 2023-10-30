#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define SIZE_OF_ARRAY 5

void swap(void *first, void *second, size_t byte_size)
{
    char temp[byte_size];
    memcpy(temp, first, byte_size);
    memcpy(first, second, byte_size);
    memcpy(second, temp, byte_size);
}

int intcmp(const void *a, const void *b)
{
    int x = *(int *)a;
    int y = *(int *)b;

    if (x == y)
    {
        return 0;
    }

    if (x < y)
    {
        return -1;
    }

    return 1;
}

void bubble_sort(void *array, size_t size, size_t byte_size,
                 int (*cmp)(const void *, const void *))
{
    while (true)
    {
        bool swapped = false;
        for (int i = 1; i < size; i++)
        {
            void *a = array + (i - 1) * byte_size;
            void *b = array + i * byte_size;
            if (cmp(a, b) > 0)
            {
                swapped = true;
                swap(a, b, byte_size);
            }
        }
        if (!swapped)
        {
            return;
        }
    }
}

int main()
{
    int size = 5;
    int array[SIZE_OF_ARRAY] = {10, 1, -2, 5, 7};
    bubble_sort(array, size, sizeof(int), intcmp);
    for (int i = 0; i < size; i++)
    {
        printf("A[%d] = %d\n", i, array[i]);
    }
    return 0;
}
