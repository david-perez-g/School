import random


def roll_die(number_of_rolls, number_of_sides):
    """Returns a random total depending on the number of rolls and sides.

    :param number_of_rolls: number of times the die is going to roll
    :param number_of_sides: number of sides of the dice (must be bigger than 3)
    :precondition: both number must be positive integers and number_of_sides must be bigger than 3
    :postcondition: returns a random total depending on the number of rolls and sides
    :return:  a random total score
    """
    if number_of_rolls > 0 and number_of_sides > 3:
        max_total = (number_of_rolls * number_of_sides) + 1  # We add 1 to make the range method work correctly
        score = []
        for i in range(number_of_rolls, max_total):
            score.append(i)

        return random.choice(score)
    else:
        return 0


def choose_inventory(inventory):
    """Returns a selected element from a list and adds it to the character's inventory.

    :param inventory: list where function is going to extract the selected element
    :precondition: inventory must not be empty
    :postcondition: correctly add a selected element from the parameter list to a another list
    :return: correctly return a selected element from inventory and add it to character's inventory
    """
    from_inventory = []
    counter = 0
    choices = []

    while True:
        print("Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n")
        for item in inventory:
            counter += 1
            print(f"{counter}. {item}")

        for i in range(1, len(inventory) + 1):
            choices.append(str(i))

        choices.insert(0, "-1")

        print("What would you like to buy? (Press -1 to finish)\n")
        user_choice = input()

        while user_choice not in choices:
            print("Choose a number...\n")
            user_choice = input()

        if user_choice == "-1":
            break

        else:
            index = int(user_choice) - 1

            from_inventory.append(inventory[index])
            print("\nDone!\n")
            counter = 0

    return from_inventory


def read_inventory(inventory):
    """Reads character´s inventory.

    :param inventory: character´s inventory.
    :precondition:  character´s list made by create_character
    :postcondition:  correctly shows inventory items
    :return:
    """
    if len(inventory) == 0:
        print("Your inventory is empty.")
    else:
        counter = 0
        print("\nInventory:")
        for item in inventory:
            counter += 1
            print(f"{counter}. {item}")


def generate_name(syllables):
    """Generates a name with the the number of syllables especified in the argument

    :param syllables:  number of syllables of the new name
    :precondition:  syllables must be integers bigger than 0
    :postcondition:  correctly creates a new name with each consonant followed by a vowel
    :return:  a new name with the number of syllabes especified i the argument
    """
    new_name = ""

    def generate_vowel():
        vowels = ["a", "e", "i", "o", "u", "y"]
        return random.choice(vowels)

    def generate_consonant():
        consonants = ["b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p",
                      "q", "r", "s", "t", "v", "w", "x", "y", "z"]
        return random.choice(consonants)

    def generate_syllables():
        new_syllabe = ""
        new_syllabe += generate_consonant()
        new_syllabe += generate_vowel()
        return new_syllabe

    for i in range(0, syllables):
        new_name += generate_syllables()

    return new_name.capitalize()


def create_character(length):  # Original function (works with input)
    """Creates a character.

    :param length:  especifies the number of syllabes of the name of the future character
    :precondition:  a positive integer as argument
    :postcondition:  correctly creates a list with the character stats
    :return:  a list with the new character stats
    """

    character = [{"Name": generate_name(length)}, {"Strength": roll_die(3, 6)}, {"Dexterity": roll_die(3, 6)},
                 {"Constitution": roll_die(3, 6)}, {"Intelligence": roll_die(3, 6)}, {"Wisdom": roll_die(3, 6)},
                 {"Charisma": roll_die(3, 6)}, {"Level": 1}, {"XP": 0}, {"Inventory": []}]

    def select_class():
        chara_class = {"Class": ""}

        choices = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"]

        print("Choose a class...\n1 -> Barbarian\n2 -> Ranger\n3 -> Wizard\n4 -> Rogue\n5 -> Fighter\n6 -> Monk"
              "\n7 -> Druid\n8 -> Cleric\n9 -> Warlock\n10 -> Paladin\n11 -> Sorcerer\n12 -> Bard")

        user_choice = input()

        while user_choice not in choices:
            print("Choose correctly.\n1 -> Barbarian\n2 -> Ranger\n3 -> Wizard\n4 -> Rogue\n5 -> Fighter\n6 -> Monk"
                  "\n7 -> Druid\n8 -> Cleric\n9 -> Warlock\n10 -> Paladin\n11 -> Sorcerer\n12 -> Bard")
            user_choice = input()

        if user_choice == "1":
            chara_class["Class"] = "barbarian"

        elif user_choice == "2":
            chara_class["Class"] = "ranger"

        elif user_choice == "3":
            chara_class["Class"] = "wizard"

        elif user_choice == "4":
            chara_class["Class"] = "rogue"

        elif user_choice == "5":
            chara_class["Class"] = "fighter"

        elif user_choice == "6":
            chara_class["Class"] = "monk"

        elif user_choice == "7":
            chara_class["Class"] = "druid"

        elif user_choice == "8":
            chara_class["Class"] = "cleric"

        elif user_choice == "9":
            chara_class["Class"] = "warlock"

        elif user_choice == "10":
            chara_class["Class"] = "paladin"

        elif user_choice == "11":
            chara_class["Class"] = "sorcerer"

        elif user_choice == "12":
            chara_class["Class"] = "bard"

        character.insert(1, chara_class)

        def add_hp():
            low_life_classes = ["sorcerer", "wizard", "monk", "cleric"]
            medium_life_classes = ["ranger", "bard", "druid", "rogue"]
            high_life_classes = ["warlock", "barbarian", "paladin", "fighter"]

            if chara_class["Class"] in low_life_classes:
                hp_die = roll_die(3, 4)
                hp = {"HP": [hp_die, hp_die]}

            elif chara_class["Class"] in medium_life_classes:
                hp_die = roll_die(3, 6)
                hp = {"HP": [hp_die, hp_die]}

            elif chara_class["Class"] in high_life_classes:
                hp_die = roll_die(3, 8)
                hp = {"HP": [hp_die, hp_die]}

            character.insert(2, hp)

        add_hp()

    def select_race():
        chara_race = {"Race": ""}

        choices = ["1", "2", "3", "4", "5", "6", "7", "8", "9"]

        print("\nChoose a race...\n1 -> Dragonborn\n2 -> Dwarf\n3 -> Elf\n4 -> Gnome\n5 -> Half-Elf"
              "\n6 -> Halfling\n7 -> Half-Orc\n8 -> Human\n9 -> Tiefling")
        user_choice = input()
        print("")

        while user_choice not in choices:
            print("Choose correctly.\n1 -> Dragonborn\n2 -> Dwarf\n3 -> Elf\n4 -> Gnome\n5 -> Half-Elf\n"
                  "6 -> Halfling\n7 -> Half-Orc\n8 -> Human\n9 -> Tiefling")
            user_choice = input()
            print("")

        if user_choice == "1":
            chara_race["Race"] = "dragonborn"

        elif user_choice == "2":
            chara_race["Race"] = "dwarf"

        elif user_choice == "3":
            chara_race["Race"] = "elf"

        elif user_choice == "4":
            chara_race["Race"] = "gnome"

        elif user_choice == "5":
            chara_race["Race"] = "half-elf"

        elif user_choice == "6":
            chara_race["Race"] = "halfling"

        elif user_choice == "7":
            chara_race["Race"] = "half-orc"

        elif user_choice == "8":
            chara_race["Race"] = "human"

        elif user_choice == "9":
            chara_race["Race"] = "tiefling"

        character.insert(1, chara_race)

    select_class()
    select_race()

    return character


def print_character(character):
    """Prints character info.

    :param character:  list made by generate_character function
    :precondition:  the list with the character info made by generate_character function
    :postcondition: correctly shows character important info
    :return:  info of the character
    """
    print("\n-----Character Stats-----")
    for i in range(0, len(character)):
        for key, value in character[i].items():
            print(f"{key} -> {value}")
    print("")


def combat_round(opponent1, opponent2):
    """Automatize a battle between two previously made characters using if/else and loops.

    :param opponent1: first character info
    :param opponent2: second character info
    :precondition: two previously lists made by create_character with character´s stats
    :postcondition: correctly display the result of a battle
    :return:
    """

    def combat_die_helper(p1_die, p2_die):
        """Returns the winner between two dice´s score.
        Helper function
        :param p1_die: player 1 die score
        :param p2_die: player 2 die score
        :return: winner
        """
        if p1_die > p2_die:
            return "P1"

        elif p1_die == p2_die:
            return "Tie"

        else:
            return "P2"

    print(f"Combat starting between {opponent1[0]['Name']} and {opponent2[0]['Name']}! Hold your arms!")

    while True:
        print("Rolling dies...")
        player1_die = roll_die(1, 20)
        player2_die = roll_die(1, 20)
        print(f"{opponent1[0]['Name']} got {player1_die} and {opponent2[0]['Name']} got {player2_die}.\n")
        while player1_die == player2_die:
            print("Tie! Rolling again...\n")
            player1_die = roll_die(1, 20)
            player2_die = roll_die(1, 20)
            print(f"{opponent1[0]['Name']} got {player1_die} and {opponent2[0]['Name']} got {player2_die}.\n")

        higher_die = combat_die_helper(player1_die, player2_die)

        if higher_die == "P1":
            print(f"{opponent1[0]['Name']} won and will attack!\n")
            print(
                f"Rolling dies for {opponent1[0]['Name']} and if the score is bigger than {opponent2[0]['Name']}´s "
                f"dexterity it will inflict damage to him!")
            player1_die = roll_die(3, 6)

            higher_die = combat_die_helper(player1_die, opponent2[5]['Dexterity'])

            if higher_die == "P1":
                print(f"{opponent1[0]['Name']} won and inflicted {player1_die} damage to {opponent2[0]['Name']}!")
                opponent2[3]['HP'][1] -= player1_die
                if opponent2[3]['HP'][1] <= 0:
                    print(f"{opponent1[0]['Name']} killed {opponent2[0]['Name']} and won the battle!")
                    opponent1[11]['XP'] += 10  # Adding XP to characters
                    opponent2[11]['XP'] += 5
                    opponent2[3]['HP'][1] = opponent2[3]['HP'][0]  # Healing characters
                    opponent1[3]['HP'][1] = opponent1[3]['HP'][0]
                    break
                else:
                    print(
                        f"{opponent2[0]['Name']} lost {player1_die} of HP and its currently at {opponent2[3]['HP'][1]}"
                        " HP")
            elif higher_die == "Tie":
                print("We got a tie! Rolling dices again and the winner will have the chance to attack!...")
            else:
                print(f"{opponent2[0]['Name']} defended {opponent1[0]['Name']}´s atack!")
        else:
            print(f"{opponent2[0]['Name']} won and will attack!\n")
            print(
                f"Rolling dies for {opponent2[0]['Name']} and if the score is bigger than {opponent1[0]['Name']}´s "
                f"dexterity it will inflict damage to him!")
            player2_die = roll_die(3, 6)

            higher_die = combat_die_helper(opponent1[5]['Dexterity'], player2_die)

            if higher_die == "P2":
                print(f"{opponent2[0]['Name']} won and inflicted {player2_die} damage to {opponent1[0]['Name']}!")
                opponent1[3]['HP'][1] -= player2_die
                if opponent1[3]['HP'][1] <= 0:
                    print(f"{opponent2[0]['Name']} killed {opponent1[0]['Name']} and won the battle!")
                    opponent2[11]['XP'] += 10  # Adding XP
                    opponent1[11]['XP'] += 5
                    opponent2[3]['HP'][1] = opponent2[3]['HP'][0]  # Healing Characters
                    opponent1[3]['HP'][1] = opponent1[3]['HP'][0]
                    break
                else:
                    print(
                        f"{opponent1[0]['Name']} lost {player2_die} of HP and its currently at {opponent2[3]['HP'][1]}"
                        f" HP")
            elif higher_die == "Tie":
                print("We got a tie! Rolling dices again...\n")

            else:
                print(f"{opponent1[0]['Name']} defended {opponent2[0]['Name']}´s attack!")
