from unittest import TestCase
from unittest.mock import patch
from dungeonsanddragons import create_character
from io import StringIO


def create_ultimate_character():  # For testing purposes
    return [{'Name': 'Artorius "The King"'}, {'Race': 'human'}, {'Class': 'paladin'},
            {'HP': [1038, 1038]}, {'Strength': 500}, {'Dexterity': 500}, {'Constitution': 500}, {'Intelligence': 500},
            {'Wisdom': 500}, {'Charisma': 500}, {'Level': 100}, {'XP': 100000},
            {'Inventory': ['Sword', 'Shield', 'Bottle of water', 'Eggs']}]


def create_example_character():  # For testing purposes
    return [{'Name': 'Peasant "Henry"'}, {'Race': 'human'}, {'Class': 'bard'},
            {'HP': [10, 10]}, {'Strength': 6}, {'Dexterity': 6}, {'Constitution': 6}, {'Intelligence': 6},
            {'Wisdom': 6}, {'Charisma': 6}, {'Level': 1}, {'XP': 0},
            {'Inventory': []}]


def create_example_character_2():  # For testing purposes
    return [{'Name': 'Mikasa'}, {'Race': 'human'}, {'Class': 'rogue'},
            {'HP': [16, 16]}, {'Strength': 15}, {'Dexterity': 8}, {'Constitution': 15}, {'Intelligence': 15},
            {'Wisdom': 15}, {'Charisma': 15}, {'Level': 1}, {'XP': 0},
            {'Inventory': []}]


class TestCreateCharacter(TestCase):
    @patch('random.choice', side_effect=['j', 'a', 'v', 'i', 'e', 'r', 11, 6, 7, 8, 9, 10, 12])
    @patch('builtins.input', side_effect=["1", "8"])
    @patch('sys.stdout', new_callable=StringIO)
    def test_create_character_normal_input(self, mock_stdout, mock_input, mock_random):
        expected_character = [{"Name": "Javier"}, {"Race": "human"}, {"Class": "barbarian"}, {"HP": [12, 12]},
                               {"Strength": 11}, {"Dexterity": 6}, {"Constitution": 7},
                               {"Intelligence": 8}, {"Wisdom": 9}, {"Charisma": 10},
                               {"Level": 1}, {"XP": 0}, {"Inventory": []}]

        expected_out = "Choose a class...\n1 -> Barbarian\n2 -> Ranger\n3 -> Wizard\n4 -> Rogue\n5 -> Fighter\n" \
                       "6 -> Monk\n7 -> Druid\n8 -> Cleric\n9 -> Warlock\n10 -> Paladin\n11 -> Sorcerer\n12 -> Bard" \
                       "\n\nChoose a race...\n1 -> Dragonborn\n2 -> Dwarf\n3 -> Elf\n4 -> Gnome\n5 -> Half-Elf" \
                       "\n6 -> Halfling\n7 -> Half-Orc\n8 -> Human\n9 -> Tiefling\n\n"

        new_character = create_character(3)
        self.assertEqual(new_character, expected_character)
        self.assertEqual(mock_stdout.getvalue(), expected_out)

    @patch('random.choice', side_effect=['j', 'a', 'v', 'i', 'e', 'r', 11, 6, 7, 8, 9, 10, 12])
    @patch('builtins.input', side_effect=["1", "16", "8"])  # 16 is out of the race options
    @patch('sys.stdout', new_callable=StringIO)
    def test_create_character_wrong_input_in_race(self, mock_stdout, mock_input, mock_random):
        """
        In this test user entered '16' while selecting the race. There are only 9 races, so 16 will never be a valid
        option. In this moment, the program enters in an infinite loop and forces the user to enter a correct value.
        """
        expected_character = [{"Name": "Javier"}, {"Race": "human"}, {"Class": "barbarian"}, {"HP": [12, 12]},
                              {"Strength": 11}, {"Dexterity": 6}, {"Constitution": 7},
                              {"Intelligence": 8}, {"Wisdom": 9}, {"Charisma": 10},
                              {"Level": 1}, {"XP": 0}, {"Inventory": []}]

        expected_out = "Choose a class...\n1 -> Barbarian\n2 -> Ranger\n3 -> Wizard\n4 -> Rogue\n5 -> Fighter\n" \
                       "6 -> Monk\n7 -> Druid\n8 -> Cleric\n9 -> Warlock\n10 -> Paladin\n11 -> Sorcerer\n12 -> Bard" \
                       "\n\nChoose a race...\n1 -> Dragonborn\n2 -> Dwarf\n3 -> Elf\n4 -> Gnome\n5 -> Half-Elf" \
                       "\n6 -> Halfling\n7 -> Half-Orc\n8 -> Human\n9 -> Tiefling\n\nChoose correctly.\n" \
                       "1 -> Dragonborn\n2 -> Dwarf\n3 -> Elf\n4 -> Gnome\n5 -> Half-Elf\n6 -> Halfling\n" \
                       "7 -> Half-Orc\n8 -> Human\n9 -> Tiefling\n\n"

        new_character = create_character(3)
        self.assertEqual(new_character, expected_character)
        self.assertEqual(mock_stdout.getvalue(), expected_out)

    @patch('random.choice', side_effect=['j', 'a', 'v', 'i', 'e', 'r', 11, 6, 7, 8, 9, 10, 12])
    @patch('builtins.input', side_effect=["15", "1", "8"])  # 15 is out of the class options
    @patch('sys.stdout', new_callable=StringIO)
    def test_create_character_wrong_input_in_class(self, mock_stdout, mock_input, mock_random):
        """
        Same as above, but while selecting class.
        """
        expected_character = [{"Name": "Javier"}, {"Race": "human"}, {"Class": "barbarian"}, {"HP": [12, 12]},
                              {"Strength": 11}, {"Dexterity": 6}, {"Constitution": 7},
                              {"Intelligence": 8}, {"Wisdom": 9}, {"Charisma": 10},
                              {"Level": 1}, {"XP": 0}, {"Inventory": []}]

        expected_out = "Choose a class...\n1 -> Barbarian\n2 -> Ranger\n3 -> Wizard\n4 -> Rogue\n5 -> Fighter\n" \
                       "6 -> Monk\n7 -> Druid\n8 -> Cleric\n9 -> Warlock\n10 -> Paladin\n11 -> Sorcerer\n12 -> Bard" \
                       "\nChoose correctly.\n1 -> Barbarian\n2 -> Ranger\n3 -> Wizard\n4 -> Rogue\n5 -> Fighter\n" \
                       "6 -> Monk\n7 -> Druid\n8 -> Cleric\n9 -> Warlock\n10 -> Paladin\n11 -> Sorcerer\n12 -> Bard" \
                       "\n\nChoose a race...\n1 -> Dragonborn\n2 -> Dwarf\n3 -> Elf\n4 -> Gnome\n5 -> Half-Elf" \
                       "\n6 -> Halfling\n7 -> Half-Orc\n8 -> Human\n9 -> Tiefling\n\n"

        new_character = create_character(3)
        self.assertEqual(new_character, expected_character)
        self.assertEqual(mock_stdout.getvalue(), expected_out)
