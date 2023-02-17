let board = [
    ['', '', ''],
    ['', '', ''],
    ['', '', '']
];

let currentPlayer = 'X';
let isGameOver = false;

function printBoard() {
    console.log(board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
    console.log(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
    console.log(board[2][0] + " | " + board[2][1] + " | " + board[2][2]);
}

function hasWon(player) {
    // Check rows for a win
    for (let i = 0; i < 3; i++) {
        if (board[i][0] === player && board[i][1] === player && board[i][2] === player) {
            return true;
        }
    }
    // Check columns for a win
    for (let j = 0; j < 3; j++) {
        if (board[0][j] === player && board[1][j] === player && board[2][j] === player) {
            return true;
        }
    }
    // Check diagonals for a win
    if (board[0][0] === player && board[1][1] === player && board[2][2] === player) {
        return true;
    }
    if (board[0][2] === player && board[1][1] === player && board[2][0] === player) {
        return true;
    }
    return false;
}

function getMove() {
    let row, col;
    while (true) {
        row = prompt("Enter row number (1-3): ") - 1;
        col = prompt("Enter column number (1-3): ") - 1;
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            console.log("Invalid input. Try again.");
        } else if (board[row][col] !== '') {
            console.log("That space is already occupied. Try again.");
        } else {
            board[row][col] = currentPlayer;
            break;
        }
    }
}

console.log("Welcome to Tic-Tac-Toe!");
printBoard();

while (!isGameOver) {
    getMove();
    printBoard();
    if (hasWon(currentPlayer)) {
        console.log("Player " + currentPlayer + " wins!");
        isGameOver = true;
    } else if (board.every(row => row.every(cell => cell !== ''))) {
        console.log("It's a tie!");
        isGameOver = true;
    } else {
        currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
    }
}
