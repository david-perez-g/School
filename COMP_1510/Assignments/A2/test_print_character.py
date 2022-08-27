from unittest import TestCase
from unittest.mock import patch
from dungeonsanddragons import print_character
from test_create_character import create_ultimate_character, create_example_character
from io import StringIO


class TestPrintCharacter(TestCase):
    @patch('sys.stdout', new_callable=StringIO)
    def test_print_character_1(self, mock_stdout):
        character = create_ultimate_character()
        expected_out = '\n-----Character Stats-----' \
            '\nName -> Artorius "The King"\n' \
            'Race -> human\n' \
            'Class -> paladin\n' \
            'HP -> [1038, 1038]\n' \
            'Strength -> 500\n' \
            'Dexterity -> 500\n' \
            'Constitution -> 500\n' \
            'Intelligence -> 500\n' \
            'Wisdom -> 500\n' \
            'Charisma -> 500\n' \
            'Level -> 100\n' \
            'XP -> 100000\n' \
            "Inventory -> ['Sword', 'Shield', 'Bottle of water', 'Eggs']\n\n"
        print_character(character)
        self.assertEqual(mock_stdout.getvalue(), expected_out)

    @patch('sys.stdout', new_callable=StringIO)
    def test_print_character_2(self, mock_stdout):
        character = create_example_character()
        expected_out = '\n-----Character Stats-----' \
                       '\nName -> Peasant "Henry"\n' \
                       'Race -> human\n' \
                       'Class -> bard\n' \
                       'HP -> [10, 10]\n' \
                       'Strength -> 6\n' \
                       'Dexterity -> 6\n' \
                       'Constitution -> 6\n' \
                       'Intelligence -> 6\n' \
                       'Wisdom -> 6\n' \
                       'Charisma -> 6\n' \
                       'Level -> 1\n' \
                       'XP -> 0\n' \
                       "Inventory -> []\n\n"
        print_character(character)
        self.assertEqual(mock_stdout.getvalue(), expected_out)
