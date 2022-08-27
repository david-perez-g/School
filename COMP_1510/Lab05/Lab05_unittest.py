import unittest
import random


class FunctionsTestCase(unittest.TestCase):
    def roll_die(self, number_of_rolls, number_of_sides):
        if number_of_rolls > 0 and number_of_sides > 2:
            max_total = (number_of_rolls * number_of_sides) + 1  # We add 1 to make the range method work correctly
            score = []
            for i in range(number_of_rolls, max_total):
                score.append(i)

            random.shuffle(score)
            return score[0]

        else:
            return 0

    def choose_inventory(self, inventory, selection):
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

    def generate_name(self, syllables):
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

    def create_character(self, length):
        character = [self.generate_name(self, length)]

        attributes = [["Strength", self.roll_die(self, 3, 6)], ["Dexterity", self.roll_die(self, 3, 6)], ["Constitution", self.roll_die(self, 3, 6)],
                      ["Intelligence", self.roll_die(self, 3, 6)], ["Wisdom", self.roll_die(self, 3, 6)], ["Charisma", self.roll_die(self, 3, 6)]]

        character += attributes

        return character

    def print_character(self, character):
        print(f"---Your character name is {character[0]}!---")
        character_name = character[0]
        character.pop(0)

        for stat, num in character:
            print(f"Your stat {stat} has {num} points.")

        character.insert(0, character_name)


if __name__ == '__main__':
    unittest.main()
