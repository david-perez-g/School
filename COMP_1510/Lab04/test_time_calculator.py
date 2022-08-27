from unittest import TestCase


def time_calculator(seconds):
    minutes = seconds // 60
    hours = seconds // 3600
    days = seconds // 86400

    return f"Minutes: {minutes} Hours: {hours} Days: {days}"


class TestTimeCalculator(TestCase):
    def test_time_calculator(self):
        self.assertEqual(time_calculator(87000), "Minutes: 1450 Hours: 24 Days: 1")
