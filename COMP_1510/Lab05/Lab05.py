import random


def roll_die(number_of_rolls, number_of_sides):
    """Returns a random total depending on the number of rolls and sides.

    :param number_of_rolls: number of times the die is going to roll
    :param number_of_sides: number of sides of the dice (must be bigger than 2)
    :precondition: both number must be positive integers and number_of_sides must be bigger than 2
    :postcondition: returns a random total depending on the number of rolls and sides
    :return:  a random total score
    """
    if number_of_rolls > 0 and number_of_sides > 2:
        max_total = (number_of_rolls * number_of_sides) + 1  # We add 1 to make the range method work correctly
        score = []
        for i in range(number_of_rolls, max_total):
            score.append(i)

        random.shuffle(score)
        return score[0]

    else:
        return 0


def choose_inventory(inventory, selection):
    """Returns x random elements requested by the argument (selection) from a list (inventory).

    :param inventory: list where function is going to extract x random elements
    :param selection: positive integer which is the number of random elements
    function is going to take from inventory
    :precondition: selection must be an positive integer and inventory must not be empty
    :postcondition: correctly copy x random elements from the list to a new list
    :return:  a list with x random elements from another list
    """
    from_inventory = []

    if selection == 0 or len(inventory) == 0:
        return from_inventory

    elif selection < 0:
        print("ERROR: You must insert a positive integer.")

    elif selection > len(inventory):
        print("The size of your selection is bigger than the list.")
        return sorted(inventory)

    elif selection == len(inventory):
        return sorted(inventory)

    else:
        random.shuffle(inventory)

        for item in range(0, selection):
            from_inventory.append(inventory[item])

        return from_inventory


def generate_name(syllables):
    """Generates a name with the the number of syllables especified in the argument

    :param syllables:  number of syllables of the new name
    :precondition:  syllables must be integers bigger than 0
    :postcondition:  correctly creates a new name with each consonant followed by a vowel
    :return:  a new name with the number of syllabes especified i the argument
    """
    if syllables <= 0:
        return "ERROR"

    else:
        new_name = ""

        def generate_vowel():
            vowels = ["a", "e", "i", "o", "u", "y"]
            random.shuffle(vowels)
            return vowels[0]

        def generate_consonant():
            consonants = ["b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p",
                          "q", "r", "s", "t", "v", "w", "x", "y", "z"]
            random.shuffle(consonants)
            return consonants[0]

        def generate_syllables():
            new_syllabe = ""
            new_syllabe += generate_consonant()
            new_syllabe += generate_vowel()
            return new_syllabe

        for i in range(0, syllables):
            new_name += generate_syllables()

        return new_name.capitalize()


def create_character(length):
    """Creates a character.

    :param length:  especifies the number of syllabes of the name of the future character
    :precondition:  a positive integer as argument
    :postcondition:  correctly creates a list with the character stats
    :return:  a list with the new character stats
    """
    character = [generate_name(length)]

    attributes = [["Strength", roll_die(3, 6)], ["Dexterity", roll_die(3, 6)], ["Constitution", roll_die(3, 6)],
                  ["Intelligence", roll_die(3, 6)], ["Wisdom", roll_die(3, 6)], ["Charisma", roll_die(3, 6)]]

    character += attributes

    return character


def print_character(character):
    """Prints character info.

    :param character:  list made by generate_character function
    :precondition:  the list with the character info made by generate_character function
    :postcondition:
    :return:  info of the character
    """
    print(f"---Your character name is {character[0]}!---")
    character_name = character[0]
    character.pop(0)

    for stat, num in character:
        print(f"Your stat {stat} has {num} points.")

    character.insert(0, character_name)
