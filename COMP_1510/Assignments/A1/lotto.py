import random


def number_generator():

    lottery_ticket = []

    for i in range(1, 49):
        lottery_ticket.append(str(i))

    random.shuffle(lottery_ticket)

    while len(lottery_ticket) > 6:
        lottery_ticket.pop()

    lottery_ticket.sort()

    return "".join(lottery_ticket)
