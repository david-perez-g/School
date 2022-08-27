from functions import make_board
from unittest import TestCase, main as unittest_main


class TestMakeBoard(TestCase):
    def test_make_board_default_positions(self):
        start_position = {"x": 0, "y": 0}
        goal_position = {"x": 4, "y": 4}

        expected_result = [
            [1, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, -1],
        ]

        self.assertEqual(make_board(start_position, goal_position), expected_result)

    def test_make_board_alternative_positions(self):
        start_position = {"x": 1, "y": 0}
        goal_position = {"x": 4, "y": 0}

        expected_result = [
            [0, 1, 0, 0, -1],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
        ]

        self.assertEqual(make_board(start_position, goal_position), expected_result)

    def test_make_board_start_pos_is_equal_to_goal(self):
        start_position = {"x": 0, "y": 0}
        goal_position = {"x": 0, "y": 0}

        expected_result = [
            [-1, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0],
        ]

        self.assertEqual(make_board(start_position, goal_position), expected_result)


if __name__ == '__main__':
    unittest_main()