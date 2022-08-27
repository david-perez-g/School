from functions import get_possible_moves
from unittest import TestCase, main as unittest_main


class TestGetPossibleMoves(TestCase):
    def test_get_possible_moves_position_1(self):
        character_position = {"x": 0, "y": 0}

        """
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
        4   |      |      |      |      |      |
            |______|______|______|______|______|
            
        """

        expected_output = {"UP": False, "DOWN": True, "RIGHT": True, "LEFT": False}
        self.assertEqual(get_possible_moves(character_position), expected_output)

    def test_get_possible_moves_position_1(self):
        character_position = {"x": 0, "y": 0}

        """
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
        4   |      |      |      |      |      |
            |______|______|______|______|______|

        """

        expected_output = {"UP": False, "DOWN": True, "RIGHT": True, "LEFT": False}
        self.assertEqual(get_possible_moves(character_position), expected_output)

    def test_get_possible_moves_position_2(self):
        character_position = {"x": 1, "y": 1}

        """
             0      1      2      3      4  
            .______.______.______.______.______.
            |      |      |      |      |      |
        0   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        1   |      |  X   |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        2   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        3   |      |      |      |      |      |
            |______|______|______|______|______|
            |      |      |      |      |      |
        4   |      |      |      |      |      |
            |______|______|______|______|______|

        """

        expected_output = {"UP": True, "DOWN": True, "RIGHT": True, "LEFT": True}
        self.assertEqual(get_possible_moves(character_position), expected_output)

    def test_get_possible_moves_position_3(self):
        character_position = {"x": 1, "y": 4}

        """
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
        4   |      |  X   |      |      |      |
            |______|______|______|______|______|

        """

        expected_output = {"UP": True, "DOWN": False, "RIGHT": True, "LEFT": True}
        self.assertEqual(get_possible_moves(character_position), expected_output)

    def test_get_possible_moves_position_4(self):
        character_position = {"x": 4, "y": 4}

        """
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
        4   |      |      |      |      |  X   |
            |______|______|______|______|______|

        """

        expected_output = {"UP": True, "DOWN": False, "RIGHT": False, "LEFT": True}
        self.assertEqual(get_possible_moves(character_position), expected_output)


if __name__ == '__main__':
    unittest_main()
