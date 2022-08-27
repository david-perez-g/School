CHARACTER_START_POINT = {
    'x': 0,
    'y': 0
}

GOAL_POSITION = {
    'x': 4,
    'y': 4
}


def make_character() -> dict:
    """Create a character with the starting x and y positions"""

    return CHARACTER_START_POINT


def make_board() -> list:
    """Creates a board.

    The board consists in a list that contains 5 lists of 5 numbers, each list representing a row of the board
    and each number a column of the row.
    The numbers can be
    1 -> Meaning the character position
    0 -> Meaning a blank space in the board
    -1 -> Meaning the goal position

    :precondition: the character starting position (x and y) and the goal position (x and y)
    :postcondition: correctly creates a board 5x5 with the character and the goal correctly positioned
    :return: a list
    """

    start_x, start_y = CHARACTER_START_POINT['x'], CHARACTER_START_POINT['y']
    end_x, end_y = GOAL_POSITION['x'], GOAL_POSITION['y']

    board = [[0 for i in range(5)] for j in range(5)]
    board[start_y][start_x] = 1  # Setting character starting point
    board[end_y][end_x] = -1  # Setting the goal position

    return board


def print_board(board: list, character=None) -> None:
    """Prints the board to the console.

    :param board: the game board
    :param character: if it isn't None, the function will print the character position (that will be inside character)
    :precondition: the board, and the character position (x and y) in case it is required
    :postcondition: correctly print the board and the character position in case it is required
    :return: None
    """

    board_ = """
           0      1      2      3      4  
        .______.______.______.______.______.
        |      |      |      |      |      |
    0   | (00) | (10) | (20) | (30) | (40) |
        |______|______|______|______|______|
        |      |      |      |      |      |
    1   | (01) | (11) | (21) | (31) | (41) |
        |______|______|______|______|______|
        |      |      |      |      |      |
    2   | (02) | (12) | (22) | (32) | (42) |
        |______|______|______|______|______|
        |      |      |      |      |      |
    3   | (03) | (13) | (23) | (33) | (43) |
        |______|______|______|______|______|
        |      |      |      |      |      |
    4   | (04) | (14) | (24) | (34) | (44) |
        |______|______|______|______|______|
    
    """

    replacements = {
        1: ' X  ',
        0: '    ',
        -1: '~||~'
    }

    for x in range(5):
        for y in range(5):
            board_ = board_.replace(f'({x}{y})', replacements[board[y][x]])

    print(board_)

    if character:
        print('Your current position is\nX: {}\nY: {}\n'.format(character['x'], character['y']))


def get_possible_moves(character: dict) -> dict:
    """Get the possible moves from the current character position.

    :param character: character position (x and y)
    :precondition: the character position
    :postcondition: correctly return the available moves
    :return: a dictionary containing the 4 possible movements as keys, and their corresponding validity as a bool

    >>> get_possible_moves({"x": 0, "y": 0})
    {'UP': False, 'DOWN': True, 'LEFT': False, 'RIGHT': True}

    >>> get_possible_moves({"x": 1, "y": 1})
    {'UP': True, 'DOWN': True, 'LEFT': True, 'RIGHT': True}

    >>> get_possible_moves({"x": 0, "y": 4})
    {'UP': True, 'DOWN': False, 'LEFT': False, 'RIGHT': True}

    >>> get_possible_moves({"x": 4, "y": 4})
    {'UP': True, 'DOWN': False, 'LEFT': True, 'RIGHT': False}
    """

    x, y = character['x'], character['y']

    possible_moves = {
        'UP': True if y > 0 else False,
        'DOWN': True if y < 4 else False,
        'LEFT': True if x > 0 else False,
        'RIGHT': True if x < 4 else False
    }

    return possible_moves


def request_move(possible_moves: dict) -> str:
    """Request a move to the user

    :param possible_moves: possible moves in the current character position (result of get_possible_moves function)
    :precondition: the possible moves of the current character position
    :postcondition: correctly return a move of the user choice
    :return: the requested move
    """

    counter = 0
    possible_answers = dict()

    print('You can move to:\n')

    for direction, valid in possible_moves.items():
        if valid:
            counter += 1
            possible_answers[str(counter)] = direction
            print(f'{counter}. {direction}')

    print()  # A blank space

    while True:
        answer = input('->')

        if answer not in possible_answers.keys():
            print('Invalid input.')
            continue

        return possible_answers[answer]


def make_move(move: str, character: dict, board: list) -> None:
    """Make a move.

    :param move: the move requested by the user, can be UP, DOWN, LEFT, RIGHT
    :param character: the character
    :param board: the game board
    :precondition: a move, a character and the game board
    :postcondition: correctly increase/decrease the character's x or y, and make the changes in the board
    :return: None
    """

    if move == 'UP':
        board[character['y']][character['x']] = 0  # deleting previous position
        character['y'] -= 1
        board[character['y']][character['x']] = 1  # updating position in board

    elif move == 'DOWN':
        board[character['y']][character['x']] = 0
        character['y'] += 1
        board[character['y']][character['x']] = 1

    elif move == 'RIGHT':
        board[character['y']][character['x']] = 0
        character['x'] += 1
        board[character['y']][character['x']] = 1

    else:
        board[character['y']][character['x']] = 0
        character['x'] -= 1
        board[character['y']][character['x']] = 1


def is_game_over(character: dict) -> bool:
    """Check if the character reached the goal.

    :param character: a dict containing the character position (x and y)
    :precondition: the character position and the goal position
    :postcondition: correctly return true in case the character reached the goal position, otherwise false
    :return: a boolean value
    """

    return character == GOAL_POSITION


def game() -> None:
    """Game main method."""

    character = make_character()
    board = make_board()
    game_finished = False

    while not game_finished:
        print_board(board, character)
        possible_moves = get_possible_moves(character)
        move = request_move(possible_moves)
        make_move(move, character, board)

        if is_game_over(character):
            print_board(board,  character=None)
            print('You reached the goal!')
            game_finished = True


if __name__ == '__main__':
    game()
