def format_name(first, last):
    print(f"{first} {last}")


def tripler(arg):
    print(arg * 3)


def this_year():
    this_year = 2**10 + 30**2 + 9**2 + 2**2 + 3**2 + 1
    print(this_year)


if __name__ == "__main__":
    def main_function():
        format_name(first=str(input()), last=str(input()))

        tripler(arg=input())

        this_year()

    main_function()
