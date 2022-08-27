def convert_to_roman(positive_int):
    ROMAN = {
        1000: "M",
        500: "D",
        50: "L",
        10: "X",
        5: "V",
        1: "I"
    }

    result = ""

    while positive_int>0:
        for number, roman in ROMAN.items():
            while positive_int >= number:
                if positive_int == 900:
                    result += "CM"
                    positive_int -= 900

                elif positive_int == 400:
                    result += "CD"
                    positive_int -= 400

                elif positive_int == 9:
                    result += "IX"
                    positive_int -= 9

                elif positive_int == 4:
                    result += "IV"
                    positive_int -= 4

                else:
                    result += roman
                    positive_int -= number

    return result


print(convert_to_roman(2021))
