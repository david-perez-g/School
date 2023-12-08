#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>
#include <errno.h>
#include <string.h>

#define MAX_NUMBER_OF_LINES 20
#define MAX_NUMBER_OF_ELEMENTS_ON_EACH_LINE 50
#define MAX_NUMBER 100

void swap(int *a, int *b) {
    int t = *a;
    *a = *b;
    *b = t;
}

int partition (int arr[], int low, int high) {
    int pivot = arr[high];
    int i = (low - 1);

    for (int j = low; j <= high - 1; j++) {
        if (arr[j] < pivot) {
            i++;
            swap(&arr[i], &arr[j]);
        }
    }
    swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}

void quick_sort(int arr[], int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);
        quick_sort(arr, low, pi - 1);
        quick_sort(arr, pi + 1, high);
    }
}

bool are_equal(int *first, int first_size, int *second, int second_size) {
    if (first_size != second_size) {
        return false;
    }

    for (int i = 0; i < first_size; i++) {
        if (first[i] != second[i]) {
            return false;
        }
    }

    return true;
}

bool are_anagrams(int *first, int first_size, int *second, int second_size) {
    if (first_size != second_size) {
        return false;
    }

    quick_sort(first, 0, first_size - 1);
    quick_sort(second, 0, second_size - 1);

    return are_equal(first, first_size, second, second_size);
}


void test_are_anagrams() {
    int first1[] = {1, 2, 3, 4, 5};
    int second1[] = {5, 4, 3, 2, 1};
    int size1 = 5;

    bool result1 = are_anagrams(first1, size1, second1, size1);
    assert(result1);

    int first2[] = {1, 2, 3, 4, 5};
    int second2[] = {1, 2, 3, 4, 6};
    int size2 = 5;

    bool result2 = are_anagrams(first2, size2, second2, size2);
    assert(result2 == false);

    int first3[] = {1, 1, 2, 2, 3, 3};
    int second3[] = {3, 3, 2, 2, 1, 1};
    int size3 = 6;

    bool result3 = are_anagrams(first3, size3, second3, size3);
    assert(result3);
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <filename>\n", argv[0]);
        return EXIT_FAILURE;
    }


    char *filename = argv[1];
    FILE *file;

    errno_t err = fopen_s(&file, filename, "r");

    if (err != 0) {
        char *error_msg = (char *)malloc(sizeof(char) * 50);
        strerror_s(error_msg, 50, err);
        fprintf(stderr, "Cannot open file '%s': %s\n", filename, error_msg);
        return EXIT_FAILURE;
    }

    int arr[MAX_NUMBER_OF_LINES][MAX_NUMBER_OF_ELEMENTS_ON_EACH_LINE];
    int rows, columns;

    fscanf_s(file, "%d %d", &rows, &columns);
    for (int i = 0; i < rows; i++) {
        for (int j = 0; j < columns; j++) {
            fscanf_s(file, "%d", &arr[i][j]);
        }
    }

    fclose(file);

    for (int i = 0; i < rows; i++) {
        quick_sort(arr[i], 0, columns - 1);
    }

    for (int i = 0; i < rows; i++) {
        for (int j = i + 1; j < rows; j++) {
            if (are_equal(arr[i], columns, arr[j], columns)) {
                printf("Found: lines %d and %d\n", i + 2, j + 2);
                return EXIT_SUCCESS;
            }
        }
    }

    printf("Not found\n");
    return EXIT_SUCCESS;
}