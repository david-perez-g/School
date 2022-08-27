from unittest import TestCase
from unittest.mock import patch
from io import StringIO
from dungeonsanddragons import roll_die, combat_round
from test_create_character import create_ultimate_character, create_example_character, create_example_character_2


class TestCombatRound(TestCase):
    @patch('random.choice', side_effect=[1, 20, 18])
    @patch('sys.stdout', new_callable=StringIO)
    def test_combat_round_one_strike_finish(self, mock_stdout, mock_random):
        expected_out = 'Combat starting between Peasant "Henry" and Artorius "The King"! Hold your arms!' \
                       '\nRolling dies...\nPeasant "Henry" got 1 and Artorius "The King" got 20.\n\n' \
                       'Artorius "The King" won and will attack!\n\nRolling dies for Artorius "The King" and if the ' \
                       'score is bigger than Peasant "Henry"´s dexterity it will inflict damage to him!' \
                       '\nArtorius "The King" won and inflicted 18 damage to Peasant "Henry"!' \
                       '\nArtorius "The King" killed Peasant "Henry" and won the battle!\n'

        ultimate_character = create_ultimate_character()
        normal_character = create_example_character()
        combat_round(normal_character, ultimate_character)
        self.assertEqual(mock_stdout.getvalue(), expected_out)
        self.assertGreater(ultimate_character[11]['XP'], 100000)  # Testing if the character's XP increased
        self.assertGreater(normal_character[11]['XP'], 0)
        self.assertEqual(normal_character[3]['HP'][0], normal_character[3]['HP'][1])  # Testing if the character was healed correctly

    @patch('random.choice', side_effect=[20, 1, 6, 20, 0, 18])
    @patch('sys.stdout', new_callable=StringIO)
    def test_combat_round_character_2_defends_then_dies(self, mock_stdout, mock_random):
        expected_out = 'Combat starting between Peasant "Henry" and Mikasa! Hold your arms!' \
                       '\nRolling dies...\nPeasant "Henry" got 20 and Mikasa got 1.\n\n' \
                       'Peasant "Henry" won and will attack!\n\nRolling dies for Peasant "Henry" and if the ' \
                       'score is bigger than Mikasa´s dexterity it will inflict damage to him!' \
                       '\nMikasa defended Peasant "Henry"´s atack!\nRolling dies...\nPeasant "Henry" got 20 and ' \
                       'Mikasa got 0.\n\nPeasant "Henry" won and will attack!\n\nRolling dies for Peasant "Henry"' \
                       ' and if the score is bigger than Mikasa´s dexterity it will inflict damage to him!' \
                       '\nPeasant "Henry" won and inflicted 18 damage to Mikasa!' \
                       '\nPeasant "Henry" killed Mikasa and won the battle!\n'
        character_1 = create_example_character()
        character_2 = create_example_character_2()
        combat_round(character_1, character_2)
        self.assertEqual(mock_stdout.getvalue(), expected_out)
        self.assertGreater(character_1[11]['XP'], 0)
        self.assertGreater(character_2[11]['XP'], 0)
        self.assertEqual(character_2[3]['HP'][0], character_2[3]['HP'][1])

    @patch('random.choice', side_effect=[20, 1, 9, 0, 20, 18])
    @patch('sys.stdout', new_callable=StringIO)
    def test_combat_round_character_1_takes_half_of_character_2_hp_then_dies(self, mock_stdout, mock_random):
        expected_out = 'Combat starting between Peasant "Henry" and Mikasa! Hold your arms!' \
                       '\nRolling dies...\nPeasant "Henry" got 20 and Mikasa got 1.\n\n' \
                       'Peasant "Henry" won and will attack!\n\nRolling dies for Peasant "Henry" and if the ' \
                       'score is bigger than Mikasa´s dexterity it will inflict damage to him!' \
                       '\nPeasant "Henry" won and inflicted 9 damage to Mikasa!\nMikasa lost 9 of HP and its currently'\
                       ' at 7 HP\nRolling dies...\nPeasant "Henry" got 0 and Mikasa got 20.\n\nMikasa won and will ' \
                       'attack!\n\nRolling dies for Mikasa and if the score is bigger than Peasant "Henry"´s ' \
                       'dexterity it will inflict damage to him!' \
                       '\nMikasa won and inflicted 18 damage to Peasant "Henry"!' \
                       '\nMikasa killed Peasant "Henry" and won the battle!\n'
        character_1 = create_example_character()
        character_2 = create_example_character_2()
        combat_round(character_1, character_2)
        self.assertEqual(mock_stdout.getvalue(), expected_out)
        self.assertGreater(character_1[11]['XP'], 0)
        self.assertGreater(character_2[11]['XP'], 0)
        self.assertEqual(character_1[3]['HP'][0], character_1[3]['HP'][1])  # Checking if both characters healed
        self.assertEqual(character_2[3]['HP'][0], character_2[3]['HP'][1])  # correctly

    @patch('random.choice', side_effect=[10, 10, 10, 10, 6, 8, 18])
    @patch('sys.stdout', new_callable=StringIO)
    def test_combat_round_die_tie(self, mock_stdout, mock_random):
        """
        In this test, we have a tie between the two first die scores, to prevent this, the function enters in a loop
        where the same problems happens again, the loop will never end if these two dies have different values.
        """
        expected_out = 'Combat starting between Peasant "Henry" and Mikasa! Hold your arms!' \
                       '\nRolling dies...\nPeasant "Henry" got 10 and Mikasa got 10.\n\nTie! Rolling again...\n\n' \
                       'Peasant "Henry" got 10 and Mikasa got 10.\n\nTie! Rolling again...\n\nPeasant "Henry" got 6' \
                       ' and Mikasa got 8.\n\nMikasa won and will attack!\n\nRolling dies for Mikasa and if ' \
                       'the score is bigger than Peasant "Henry"´s dexterity it will inflict damage to him!' \
                       '\nMikasa won and inflicted 18 damage to Peasant "Henry"!\n' \
                       'Mikasa killed Peasant "Henry" and won the battle!\n'
        character_1 = create_example_character()
        character_2 = create_example_character_2()
        combat_round(character_1, character_2)
        self.assertEqual(mock_stdout.getvalue(), expected_out)
        self.assertGreater(character_1[11]['XP'], 0)
        self.assertGreater(character_2[11]['XP'], 0)
        self.assertEqual(character_1[3]['HP'][0], character_1[3]['HP'][1])  # Checking if both characters healed
        self.assertEqual(character_2[3]['HP'][0], character_2[3]['HP'][1])  # correctly

    @patch('random.choice', side_effect=[12, 10, 8, 20, 1, 18])
    @patch('sys.stdout', new_callable=StringIO)
    def test_combat_round_die_score_equal_to_defending_character_dexterity(self, mock_stdout, mock_random):
        expected_out = 'Combat starting between Peasant "Henry" and Mikasa! Hold your arms!' \
                       '\nRolling dies...\nPeasant "Henry" got 12 and Mikasa got 10.\n\nPeasant "Henry" won and will' \
                       ' attack!\n\nRolling dies for Peasant "Henry" and if the score is bigger than Mikasa´s'\
                       ' dexterity it will inflict damage to him!\nWe got a tie! Rolling dices again and the winner' \
                       ' will have the chance to attack!...\nRolling dies...\nPeasant "Henry" got 20 and Mikasa got ' \
                       '1.\n\nPeasant "Henry" won and will attack!\n\nRolling dies for Peasant "Henry" and if the ' \
                       'score is bigger than Mikasa´s dexterity it will inflict damage to him!\nPeasant "Henry" won ' \
                       'and inflicted 18 damage to Mikasa!\nPeasant "Henry" killed Mikasa and won the battle!\n'
        character_1 = create_example_character()
        character_2 = create_example_character_2()
        combat_round(character_1, character_2)
        self.assertEqual(mock_stdout.getvalue(), expected_out)
        self.assertGreater(character_1[11]['XP'], 0)
        self.assertGreater(character_2[11]['XP'], 0)
        self.assertEqual(character_2[3]['HP'][0], character_2[3]['HP'][1])
