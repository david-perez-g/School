from functions import request_move, get_possible_moves
from unittest import TestCase, main as unittest_main
from unittest.mock import patch
from io import StringIO


class TestRequestMove(TestCase):
    @patch('sys.stdout', new_callable=StringIO)
    @patch('builtins.input', side_effect=['1'])
    def test_request_down_starting_position(self, mock_input, mock_stdout: StringIO):
        character = {"x": 0, "y": 0}
        """
        Character Position

               0      1      2      3      4  
            .______.______.______.______.______.
            |      |      |      |      |      |
        0   |  X   |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        1   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        2   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        3   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        4   |      |      |      |      | ~||~ |
            |______|______|______|______|______|

        """
        possible_moves = get_possible_moves(character)
        move = request_move(possible_moves)
        expected_output = 'You can move to:\n' \
                          '\n' \
                          '1. DOWN\n' \
                          '2. RIGHT\n\n'

        expected_requested_move = "DOWN"

        self.assertEqual(mock_stdout.getvalue(), expected_output)
        self.assertEqual(move, expected_requested_move)

    @patch('sys.stdout', new_callable=StringIO)
    @patch('builtins.input', side_effect=['2'])
    def test_request_right_starting_position(self, mock_input, mock_stdout: StringIO):
        character = {"x": 0, "y": 0}
        """
        Character Position

               0      1      2      3      4  
            .______.______.______.______.______.
            |      |      |      |      |      |
        0   |  X   |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        1   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        2   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        3   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        4   |      |      |      |      | ~||~ |
            |______|______|______|______|______|

        """
        possible_moves = get_possible_moves(character)
        move = request_move(possible_moves)
        expected_output = 'You can move to:\n' \
                          '\n' \
                          '1. DOWN\n' \
                          '2. RIGHT\n\n'

        expected_requested_move = "RIGHT"

        self.assertEqual(mock_stdout.getvalue(), expected_output)
        self.assertEqual(move, expected_requested_move)

    @patch('sys.stdout', new_callable=StringIO)
    @patch('builtins.input', side_effect=['3'])
    def test_request_left_alternative_position(self, mock_input, mock_stdout: StringIO):
        character = {"x": 4, "y": 2}
        """
        Character Position

               0      1      2      3      4  
            .______.______.______.______.______.
            |      |      |      |      |      |
        0   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        1   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        2   |      |      |      |      |  X   |
            |______|______|______|______|______|
            |      |      |      |      |      |
        3   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        4   |      |      |      |      | ~||~ |
            |______|______|______|______|______|

        """
        possible_moves = get_possible_moves(character)
        move = request_move(possible_moves)
        expected_output = 'You can move to:\n' \
                          '\n' \
                          '1. UP\n' \
                          '2. DOWN\n' \
                          '3. LEFT\n\n'

        expected_requested_move = "LEFT"

        self.assertEqual(mock_stdout.getvalue(), expected_output)
        self.assertEqual(move, expected_requested_move)

    @patch('sys.stdout', new_callable=StringIO)
    @patch('builtins.input', side_effect=['1'])
    def test_request_up_alternative_position(self, mock_input, mock_stdout: StringIO):
        character = {"x": 2, "y": 4}
        """
        Character Position

               0      1      2      3      4  
            .______.______.______.______.______.
            |      |      |      |      |      |
        0   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        1   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        2   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        3   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        4   |      |      |  X   |      | ~||~ |
            |______|______|______|______|______|

        """
        possible_moves = get_possible_moves(character)
        move = request_move(possible_moves)
        expected_output = 'You can move to:\n' \
                          '\n' \
                          '1. UP\n' \
                          '2. LEFT\n' \
                          '3. RIGHT\n\n'

        expected_requested_move = "UP"

        self.assertEqual(mock_stdout.getvalue(), expected_output)
        self.assertEqual(move, expected_requested_move)


if __name__ == '__main__':
    unittest_main()


