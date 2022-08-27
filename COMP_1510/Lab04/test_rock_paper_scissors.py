from unittest import TestCase


def rock_paper_scissors(choice):
    """
    0 is rock
    1 is paper
    2 is scissors
    """
    computer_choices = ["0", "1", "2"]
    random.shuffle(computer_choices)

    if choice == 0 and computer_choices[0] == "0":
        return "Tie!"

    elif choice == 0 and computer_choices[0] == "1":
        return "You lost D:"

    elif choice == 0 and computer_choices[0] == "2":
        return "You won ;D"

    elif choice == 1 and computer_choices[0] == "0":
        return "You won ;)"

    elif choice == 1 and computer_choices[0] == "1":
        return "Tie!"

    elif choice == 1 and computer_choices[0] == "2":
        return "You lost ;("

    elif choice == 2 and computer_choices[0] == "0":
        return "You lost =("

    elif choice == 2 and computer_choices[0] == "1":
        return "You won! ;D"

    elif choice == 2 and computer_choices[0] == "2":
        return "Tie!"


class TestRockPaperScissors(TestCase):
    @patch("random.shuffle", side_effect=["2", "1", "0"])
    def test_rock_paper_scissors(self, mock_shuffle):
        self.assertEqual(rock_paper_scissors(1), 'You won ;)')