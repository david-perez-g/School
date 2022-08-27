import Lab05


def main():
    user_character = Lab05.create_character(3)
    user_inventory = []
    inventory_request = ""
    user_choice = ""
    die_score = 0
    options = ["1", "2", "3", "4", "5"]
    store = ["Abacus", "Barrel", "Blanket", "Bucket", "Candle", "Chest", "Clothes (common)", "Clothes (fine)", "Hammer",
             "Ink", "Lantern", "Lamp", "Lock", "Mess Kit", "Paper", "Pot", "Sack", "Scale, Merchant´s", "Shovel"]

    print(".:Welcome to D&D!:.")
    print("We made a character for you!")
    Lab05.print_character(user_character)

    while True:
        print("\n---MENU---")
        print("For taking random elements from the shop, press 1.")
        print("For checking your inventory press 2.")
        print("For checking your character stats press 3.")
        print("For rolling a die press 4.")
        print("Want to go out? Press 5.\n")
        user_choice = input("->")

        while user_choice not in options:
            user_choice = input("Please insert a correct option -> ")

        if user_choice == "1":
            inventory_request = int(input("Please insert how many random items you want to get ->"))
            user_inventory += Lab05.choose_inventory(store, inventory_request)
            print("Done!")

        elif user_choice == "2":
            if len(user_inventory) == 0:
                print("Your inventory is empty.")
            else:
                print(user_inventory)

        elif user_choice == "3":
            Lab05.print_character(user_character)

        elif user_choice == "4":
            die_score = Lab05.roll_die(1, 6)
            print(f"Your score is {die_score}!")

        elif user_choice == "5":
            print("Thanks for playing!")
            break


if __name__ == '__main__':
    main()
