def colour_mixter(first_color, second_color):
    primary_colours = ["BLUE", "YELLOW", "RED"]

    first_color = first_color.upper()
    second_color = second_color.upper()

    if first_color not in primary_colours or second_color not in second_color:
        print("You must insert two primary colours!")
        return None

    elif first_color == second_color:
        print("You must insert two different primary colours!")
        return None

    else:
        if first_color == "BLUE" and second_color == "RED":
            return "purple"

        elif first_color == "RED" and second_color == "BLUE":
            return "purple"

        elif first_color == "BLUE" and second_color == "YELLOW":
            return "green"

        elif first_color == "YELLOW" and second_color == "BLUE":
            return "green"

        elif first_color == "RED" and second_color == "YELLOW":
            return "orange"

        elif first_color == "YELLOW" and second_color == "RED":
            return "orange"
