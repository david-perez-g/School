#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_LINE_SIZE 100
#define MAX_NUMBER_OF_STUDENTS 1000

typedef struct student
{
    char *name;
    double gpa;
} Student;

Student *get_student_from_line(char *line)
{
    Student *s = malloc(sizeof(Student));
    s->name = strdup(strtok(line, " "));
    s->gpa = atof(strtok(NULL, " "));
    return s;
}

void sort_students(Student *students[], size_t size)
{
    for (size_t i = 0; i < size; i++)
    {
        Student *max = students[i];
        for (size_t j = i; j < size; j++)
        {
            if (max->gpa < students[j]->gpa)
            {
                max = students[j];
                students[j] = students[i];
                students[i] = max;
            }
        }
    }
}

void print_students(Student *students[], size_t size)
{
    for (size_t i = 0; i < size; i++)
    {
        printf("%s %f\n", students[i]->name, students[i]->gpa);
    }
}

// Looks for all the students in the file,
// selects the ones with a gpa higher than 3.9
// and prints them in a descending order.
// The students are in the format:
// <name> <gpa>
void read_file(FILE *file)
{
    Student *students[MAX_NUMBER_OF_STUDENTS];
    short n = 0; // number of valid students found in the file
    char *buff = malloc(MAX_LINE_SIZE);
    while (fgets(buff, MAX_LINE_SIZE, file) != NULL &&
           n < MAX_NUMBER_OF_STUDENTS)
    {
        Student *s = get_student_from_line(buff);
        if (s->gpa <= 3.9)
        {
            continue;
        }

        students[n] = s;
        n++;
    }

    sort_students(students, n);
    print_students(students, n);
    free(buff);
}

int main(int argc, char *argv[])
{
    if (argc < 2)
    {
        perror("Please provide the path to the file");
        return EXIT_FAILURE;
    }

    char *name = argv[1];
    FILE *file = fopen(name, "r");

    if (file == NULL)
    {
        perror("Couldn't read the file with the provided name");
        return EXIT_FAILURE;
    }

    read_file(file);
    fclose(file);

    return EXIT_SUCCESS;
}