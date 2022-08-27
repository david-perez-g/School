import dungeonsanddragons as dnd
from time import sleep


def main():
    inventory_request = ""
    user_choice = ""
    options = ["1", "2", "3", "4", "5", "6", "7"]
    store = ["Abacus", "Barrel", "Blanket", "Bucket", "Candle", "Chest", "Clothes (common)", "Clothes (fine)", "Hammer",
             "Ink", "Lantern", "Lamp", "Lock", "Mess Kit", "Paper", "Pot", "Sack", "Scale, Merchant´s", "Shovel"]

    print(".:Welcome to D&D!:.")
    print("Let us make a character for you!")
    sleep(2)
    user_character = dnd.create_character(3)
    print("And now let´s make a character for your rival!")
    sleep(1.5)
    rival_character = dnd.create_character(2)
    print("These are your character stats...")
    dnd.print_character(user_character)
    sleep(6)
    print("And these are from your rival!")
    dnd.print_character(rival_character)
    sleep(6)
    while True:
        sleep(1)
        print("\n-------------------MENU-------------------")
        print("For buying items from the shop, press 1.")
        print("For checking your inventory press 2.")
        print("For checking your character stats press 3.")
        print("For checking your rival stats press 4.")
        print("For rolling a die press 5.")
        print("Want to battle against your rival! Press 6.")
        print("Want to go out? Press 7.")
        print("------------------------------------------\n")
        user_choice = input("->")

        while user_choice not in options:
            user_choice = input("Please insert a correct option -> ")

        if user_choice == "1":
            user_character[12]['Inventory'] += dnd.choose_inventory(store)
            print("Done!")

        elif user_choice == "2":
            dnd.read_inventory(user_character[12]['Inventory'])

        elif user_choice == "3":
            dnd.print_character(user_character)

        elif user_choice == "4":
            dnd.print_character(rival_character)

        elif user_choice == "5":
            print(f"Your score is {dnd.roll_die(1, 6)}!")

        elif user_choice == "6":
            dnd.combat_round(user_character, rival_character)

        else:
            print("Thanks for playing!")
            break


if __name__ == '__main__':
    main()
