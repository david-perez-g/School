from unittest import TestCase


def number_generator():
    lottery_ticket = []

    for i in range(1, 49):
        lottery_ticket.append(str(i))

    random.shuffle(lottery_ticket)

    while len(lottery_ticket) > 6:
        lottery_ticket.pop()

    lottery_ticket.sort()

    return "".join(lottery_ticket)


class TestNumberGenerator(TestCase):
    @patch("random.shuffle", side_effect=[1, 2, 3, 4, 5, 6])
    def test_number_generator(self, mock_shuffle):
        self.assertEqual(number_generator(), "123456")