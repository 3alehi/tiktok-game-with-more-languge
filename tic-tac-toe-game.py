# Tic-Tac-Toe game in Python

board = [
    ['-', '-', '-'],
    ['-', '-', '-'],
    ['-', '-', '-']
]

# Function to print the Tic-Tac-Toe board
def print_board():
    for i in range(3):
        for j in range(3):
            print(board[i][j], end=' ')
        print()

# Function to check if the game has ended
def game_ended():
    # Check rows for a win
    for i in range(3):
        if board[i][0] == board[i][1] == board[i][2] and board[i][0] != '-':
            return True
    # Check columns for a win
    for j in range(3):
        if board[0][j] == board[1][j] == board[2][j] and board[0][j] != '-':
            return True
    # Check diagonals for a win
    if board[0][0] == board[1][1] == board[2][2] and board[0][0] != '-':
        return True
    if board[0][2] == board[1][1] == board[2][0] and board[0][2] != '-':
        return True
    # Check if the board is full (a tie)
    for i in range(3):
        for j in range(3):
            if board[i][j] == '-':
                return False
    return True

# Function to get the user's move
def get_move(player):
    print(f"Player {player}'s turn.")
    while True:
        row = int(input("Enter row number (1-3): ")) - 1
        col = int(input("Enter column number (1-3): ")) - 1
        if row < 0 or row > 2 or col < 0 or col > 2:
            print("Invalid input. Try again.")
        elif board[row][col] != '-':
            print("That space is already occupied. Try again.")
        else:
            board[row][col] = player
            break

# Main game loop
print("Welcome to Tic-Tac-Toe!")
print_board()
player = 'X'
while not game_ended():
    get_move(player)
    print_board()
    if player == 'X':
        player = 'O'
    else:
        player = 'X'
winner = 'X' if player == 'O' else 'O'
print(f"Player {winner} has won the game!")
