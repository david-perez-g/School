from unittest import TestCase, main as unittest_main
from functions import make_move, make_board, make_character


class TestMakeMove(TestCase):
    def test_make_move_down(self):
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
        goal_position = {"x": 4, "y": 4}
        board = make_board(character, goal_position)
        move = 'DOWN'
        make_move(move, character, board)
        expected_board_result = [
            [0, 0, 0, 0, 0],
            [1, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, -1],
        ]

        expected_character_result = {"x": 0, "y": 1}
        self.assertEqual(character, expected_character_result)
        self.assertEqual(board, expected_board_result)

    def test_make_move_up(self):
        character = {"x": 3, "y": 2}
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
        2   |      |      |      |  X   |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        3   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        4   |      |      |      |      | ~||~ |
            |______|______|______|______|______|

        """
        goal_position = {"x": 4, "y": 4}
        board = make_board(character, goal_position)
        move = 'UP'
        make_move(move, character, board)
        expected_board_result = [
            [0, 0, 0, 0, 0],
            [0, 0, 0, 1, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, -1],
        ]
        expected_character_result = {"x": 3, "y": 1}
        self.assertEqual(character, expected_character_result)
        self.assertEqual(board, expected_board_result)

    def test_make_move_right(self):
        character = {"x": 3, "y": 3}
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
        3   |      |      |      |  X   |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        4   |      |      |      |      | ~||~ |
            |______|______|______|______|______|

        """
        goal_position = {"x": 4, "y": 4}
        board = make_board(character, goal_position)
        move = 'RIGHT'
        make_move(move, character, board)
        expected_board_result = [
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 1],
            [0, 0, 0, 0, -1],
        ]
        expected_character_result = {"x": 4, "y": 3}
        self.assertEqual(character, expected_character_result)
        self.assertEqual(board, expected_board_result)

    def test_make_move_left(self):
        character = {"x": 2, "y": 2}
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
        2   |      |      |  X   |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        3   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        4   |      |      |      |      | ~||~ |
            |______|______|______|______|______|

        """
        goal_position = {"x": 4, "y": 4}
        board = make_board(character, goal_position)
        move = 'LEFT'
        make_move(move, character, board)
        expected_board_result = [
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 1, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, -1],
        ]
        expected_character_result = {"x": 1, "y": 2}
        self.assertEqual(character, expected_character_result)
        self.assertEqual(board, expected_board_result)


if __name__ == '__main__':
    unittest_main()
