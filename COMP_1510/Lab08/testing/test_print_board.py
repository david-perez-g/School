from functions import make_board, print_board
from unittest import TestCase, main as unittest_main
from unittest.mock import patch
from io import StringIO


class TestPrintBoard(TestCase):
    @patch(target='sys.stdout', new_callable=StringIO)
    def test_print_starting_point_board(self, mock_stdout: StringIO):
        character_position = {"x": 0, "y": 0}
        goal_position = {"x": 4, "y": 4}
        board = make_board(character_position, goal_position)
        print_board(board, character_position)
        expected_output = """
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

    
Your current position is
X: 0
Y: 0

"""
        self.assertEqual(mock_stdout.getvalue(), expected_output)

    @patch(target='sys.stdout', new_callable=StringIO)
    def test_print_alternative_goal_position_board(self, mock_stdout: StringIO):
        character_position = {"x": 2, "y": 2}
        goal_position = {"x": 4, "y": 0}
        board = make_board(character_position, goal_position)

        print_board(board, character_position)
        expected_output = """
           0      1      2      3      4  
        .______.______.______.______.______.
        |      |      |      |      |      |
    0   |      |      |      |      | ~||~ |
        |______|______|______|______|______|
        |      |      |      |      |      |
    1   |      |      |      |      |      |
        |______|______|______|______|______|
        |      |      |      |      |      |
    2   |      |      |  X   |      |      |
        |______|______|______|______|______|
        |      |      |      |      |      |
    3   |      |      |      |      |      |
        |______|______|______|______|______|
        |      |      |      |      |      |
    4   |      |      |      |      |      |
        |______|______|______|______|______|

    
Your current position is
X: 2
Y: 2

"""
        self.assertEqual(mock_stdout.getvalue(), expected_output)


if __name__ == '__main__':
    unittest_main()
