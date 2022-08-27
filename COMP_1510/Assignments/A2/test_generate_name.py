from unittest import TestCase
from unittest.mock import patch
from dungeonsanddragons import generate_name


class TestGenerateName(TestCase):
    @patch('random.choice', side_effect=["m", "i", "k", "a", "s", "a"])
    def test_generate_name_3_syllables(self, mock_random):
        self.assertEqual(generate_name(3), "Mikasa")

    @patch('random.choice', side_effect=["y", "u"])
    def test_generate_name_1_syllable(self, mock_random):
        self.assertEqual(generate_name(1), "Yu")

    @patch('random.choice', side_effect=["l", "u", "k", "a", "n", "o", "r", "i"])
    def test_generate_name_4_syllables(self, mock_random):
        self.assertEqual(generate_name(4), "Lukanori")
