from unittest import TestCase
from unittest.mock import patch
from dungeonsanddragons import roll_die


class TestRollDie(TestCase):
    @patch('random.choice', return_value=12)
    def test_roll_die_example(self, mock_patch):
        self.assertEqual(roll_die(3, 6), 12)

    @patch('random.choice', return_value=3)
    def test_roll_die_example_number_2(self, mock_patch):
        self.assertEqual(roll_die(3, 8), 3)

    def test_roll_die_number_of_rolls_is_0(self):
        self.assertEqual(roll_die(0, 6), 0)

    def test_roll_die_number_of_sides_less_than_4(self):
        self.assertEqual(roll_die(3, 3), 0)
