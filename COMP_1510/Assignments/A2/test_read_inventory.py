from unittest import TestCase
from unittest.mock import patch
from dungeonsanddragons import read_inventory
from test_create_character import create_example_character, create_ultimate_character
from io import StringIO


class TestReadInventory(TestCase):
    @patch('sys.stdout', new_callable=StringIO)
    def test_read_inventory_no_inventory_character(self, mock_stdout):
        character = create_example_character()
        expected_out = "Your inventory is empty.\n"
        read_inventory(character[12]['Inventory'])
        self.assertEqual(mock_stdout.getvalue(), expected_out)

    @patch('sys.stdout', new_callable=StringIO)
    def test_read_inventory_character_with_inventory(self, mock_stdout):
        character = create_ultimate_character()
        expected_out = "\nInventory:" \
                       "\n1. Sword\n" \
                       "2. Shield\n" \
                       "3. Bottle of water\n" \
                       "4. Eggs\n"
        read_inventory(character[12]['Inventory'])
        self.assertEqual(mock_stdout.getvalue(), expected_out)
