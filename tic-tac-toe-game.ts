type Player = 'X' | 'O';

type Board = Player[][];

function createBoard(): Board {
  return Array(3).fill(null).map(() => Array(3).fill(null));
}

function printBoard(board: Board): void {
  console.log('-------------');
  board.forEach(row => {
    console.log(`| ${row[0] || ' '} | ${row[1] || ' '} | ${row[2] || ' '} |`);
    console.log('-------------');
  });
}

function getMove(player: Player): [number, number] {
  const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
  });

  return new Promise(resolve => {
    readline.question(`Player ${player}, enter row (1-3) and column (1-3): `, input => {
      readline.close();
      const [row, col] = input.split(' ').map(coord => parseInt(coord) - 1);
      resolve([row, col]);
    });
  });
}

function checkWin(board: Board, player: Player): boolean {
  // Check rows
  for (let row = 0; row < 3; row++) {
    if (board[row][0] === player && board[row][1] === player && board[row][2] === player) {
      return true;
    }
  }

  // Check columns
  for (let col = 0; col < 3; col++) {
    if (board[0][col] === player && board[1][col] === player && board[2][col] === player) {
      return true;
    }
  }

  // Check diagonals
  if (board[0][0] === player && board[1][1] === player && board[2][2] === player) {
    return true;
  }
  if (board[0][2] === player && board[1][1] === player && board[2][0] === player) {
    return true;
  }

  return false;
}

async function main(): Promise<void> {
  let board: Board = createBoard();
  let currentPlayer: Player = 'X';

  while (true) {
    printBoard(board);
    const [row, col] = await getMove(currentPlayer);

    if (row < 0 || row > 2 || col < 0 || col > 2) {
      console.log('Invalid move. Try again.');
    } else if (board[row][col] !== null) {
      console.log('That square is already occupied. Try again.');
    } else {
      board[row][col] = currentPlayer;

      if (checkWin(board, currentPlayer)) {
        printBoard(board);
        console.log(`Player ${currentPlayer} wins!`);
        break;
      }

      const hasEmptySquare = board.some(row => row.includes(null));
      if (!hasEmptySquare) {
        printBoard(board);
        console.log('The game is a tie.');
        break;
      }

      currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
    }
  }
}

main();
