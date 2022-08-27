from unittest import TestCase


def number_translator(num):
    ABC = ["A", "B", "C"]
    DEF = ["D", "E", "F"]
    GHI = ["G", "H", "I"]
    JKL = ["J", "K", "L"]
    MNO = ["M", "N", "O"]
    PQRS = ["P", "Q", "R", "S"]
    TUV = ["T", "U", "V"]
    WXYZ = ["W", "X", "Y", "Z"]

    num_list = []

    for i in str(num):
        num_list.append(str(i))

    if len(num_list) != 10:
        print("You must insert a 10 digits number.")
        return None

    else:
        for i in num_list:
            if i in ABC:
                num_list[num_list.index(i)] = "2"

            elif i in DEF:
                num_list[num_list.index(i)] = "3"

            elif i in GHI:
                num_list[num_list.index(i)] = "4"

            elif i in JKL:
                num_list[num_list.index(i)] = "5"

            elif i in MNO:
                num_list[num_list.index(i)] = "6"

            elif i in PQRS:
                num_list[num_list.index(i)] = "7"

            elif i in TUV:
                num_list[num_list.index(i)] = "8"

            elif i in WXYZ:
                num_list[num_list.index(i)] = "9"

    return "".join(num_list)


class TestNumTranslator(TestCase):
    def test_number_translator(self):
        self.assertEqual(number_translator("CALL155NOW"), "2255155669")
