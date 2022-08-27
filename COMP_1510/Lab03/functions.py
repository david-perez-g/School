import random


def roll_die(number_of_rolls, number_of_sides):
    while number_of_rolls > 0:
        max_total = (number_of_rolls * number_of_sides)+1  # We add 1 to make the range method work
        score = []
        for i in range(number_of_rolls, max_total):
            score.append(i)

        random.shuffle(score)
        return score[0]


def create_name(length):
    if length <= 0:
        return None
    else:
        new_name = ""
        letters = ["a", "e", "i", "o", "u", "b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", 'r',
                   "s", "t", "v", "w", "x", "y", "z"]
        for i in range(0, length):
            random.shuffle(letters)
            new_name += letters[0]

        return new_name.capitalize()


print(roll_die(1, 6))
print(create_name(5))
