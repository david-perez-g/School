from unittest import TestCase
from unittest.mock import patch
from io import StringIO
from dungeonsanddragons import choose_inventory
from test_create_character import create_example_character


class TestChooseInventory(TestCase):
    @patch("builtins.input", side_effect=['-1'])
    @patch("sys.stdout", new_callable=StringIO)
    def test_show_inventory(self, mock_stdout, mock_input):
        inventory = ["Abacus", "Barrel", "Blanket", "Bucket", "Candle"]
        expected_out = "Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n" \
                       "\n1. Abacus\n" \
                       "2. Barrel\n" \
                       "3. Blanket\n" \
                       "4. Bucket\n" \
                       "5. Candle\n" \
                       "What would you like to buy? (Press -1 to finish)\n\n"

        character = create_example_character()
        character[12]['Inventory'] += choose_inventory(inventory)
        self.assertEqual(mock_stdout.getvalue(), expected_out)

    @patch("builtins.input", side_effect=['1', '-1'])
    @patch("sys.stdout", new_callable=StringIO)
    def test_take_first_element_from_inventory(self, mock_stdout, mock_input):
        inventory = ["Abacus", "Barrel", "Blanket", "Bucket", "Candle"]
        expected_out = "Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n" \
                       "\n1. Abacus\n2. Barrel\n3. Blanket\n4. Bucket\n5. Candle\n" \
                       "What would you like to buy? (Press -1 to finish)\n" \
                       "\n\nDone!\n\n" \
                       "Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n" \
                       "\n1. Abacus\n2. Barrel\n3. Blanket\n4. Bucket\n5. Candle\n" \
                       "What would you like to buy? (Press -1 to finish)\n\n"

        character = create_example_character()
        character[12]['Inventory'] += choose_inventory(inventory)
        self.assertIn("Abacus", character[12]['Inventory'])  # Checking if the selected element was added correctly
        self.assertEqual(mock_stdout.getvalue(), expected_out)

    @patch("builtins.input", side_effect=['1', '3', '5', '-1'])
    @patch("sys.stdout", new_callable=StringIO)
    def test_take_many_elements_from_inventory(self, mock_stdout, mock_input):
        inventory = ["Abacus", "Barrel", "Blanket", "Bucket", "Candle"]
        expected_out = "Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n" \
                       "\n1. Abacus\n2. Barrel\n3. Blanket\n4. Bucket\n5. Candle\n" \
                       "What would you like to buy? (Press -1 to finish)\n" \
                       "\n\nDone!\n\n" \
                       "Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n" \
                       "\n1. Abacus\n2. Barrel\n3. Blanket\n4. Bucket\n5. Candle\n" \
                       "What would you like to buy? (Press -1 to finish)\n" \
                       "\n\nDone!\n\n" \
                       "Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n" \
                       "\n1. Abacus\n2. Barrel\n3. Blanket\n4. Bucket\n5. Candle\n" \
                       "What would you like to buy? (Press -1 to finish)\n" \
                       "\n\nDone!\n\n" \
                       "Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n" \
                       "\n1. Abacus\n2. Barrel\n3. Blanket\n4. Bucket\n5. Candle\n" \
                       "What would you like to buy? (Press -1 to finish)\n\n"

        character = create_example_character()
        character[12]['Inventory'] += choose_inventory(inventory)
        self.assertIn("Abacus", character[12]['Inventory'])  # Checking if the selected elements were added correctly
        self.assertIn("Blanket", character[12]['Inventory'])
        self.assertIn("Candle", character[12]['Inventory'])
        self.assertEqual(mock_stdout.getvalue(), expected_out)

    @patch("builtins.input", side_effect=['6', '-1'])
    @patch("sys.stdout", new_callable=StringIO)
    def test_element_is_out_of_range(self, mock_stdout, mock_input):
        """
        In this test user entered in an infinite loop where the only way out is pressing a valid option or '-1' for
        ending the function.
        """
        inventory = ["Abacus", "Barrel", "Blanket", "Bucket", "Candle"]
        expected_out = "Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n" \
                       "\n1. Abacus\n" \
                       "2. Barrel\n" \
                       "3. Blanket\n" \
                       "4. Bucket\n" \
                       "5. Candle\n" \
                       "What would you like to buy? (Press -1 to finish)\n\n" \
                       "Choose a number...\n\n"

        character = create_example_character()
        character[12]['Inventory'] += choose_inventory(inventory)
        self.assertEqual(mock_stdout.getvalue(), expected_out)

    @patch("builtins.input", side_effect=['6', '2', '4', '-1'])
    @patch("sys.stdout", new_callable=StringIO)
    def test_element_is_out_of_range_and_user_keeps_choosing(self, mock_stdout, mock_input):
        inventory = ["Abacus", "Barrel", "Blanket", "Bucket", "Candle"]
        expected_out = "Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n" \
                       "\n1. Abacus\n" \
                       "2. Barrel\n" \
                       "3. Blanket\n" \
                       "4. Bucket\n" \
                       "5. Candle\n" \
                       "What would you like to buy? (Press -1 to finish)\n\n" \
                       "Choose a number...\n\n" \
                       "\nDone!\n\n" \
                       "Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n" \
                       "\n1. Abacus\n2. Barrel\n3. Blanket\n4. Bucket\n5. Candle\n" \
                       "What would you like to buy? (Press -1 to finish)\n" \
                       "\n\nDone!\n\n" \
                       "Welcome to the Olde Tyme Merchant!\n\nHere is what we have for sale:\n" \
                       "\n1. Abacus\n2. Barrel\n3. Blanket\n4. Bucket\n5. Candle\n" \
                       "What would you like to buy? (Press -1 to finish)\n\n"

        character = create_example_character()
        character[12]['Inventory'] += choose_inventory(inventory)
        self.assertIn("Barrel", character[12]['Inventory'])
        self.assertIn("Bucket", character[12]['Inventory'])
        self.assertEqual(mock_stdout.getvalue(), expected_out)
