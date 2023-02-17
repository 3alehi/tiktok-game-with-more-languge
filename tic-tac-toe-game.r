use std::io;

#[derive(PartialEq)]
enum Mark {
    X,
    O,
    Empty,
}

struct Board {
    squares: [Mark; 9],
    current_player: Mark,
}

impl Board {
    fn new() -> Board {
        Board {
            squares: [Mark::Empty; 9],
            current_player: Mark::X,
        }
    }

    fn print(&self) {
        println!("{}|{}|{}", self.squares[0], self.squares[1], self.squares[2]);
        println!("-----");
        println!("{}|{}|{}", self.squares[3], self.squares[4], self.squares[5]);
        println!("-----");
        println!("{}|{}|{}", self.squares[6], self.squares[7], self.squares[8]);
    }

    fn make_move(&mut self, square: usize) -> Result<(), &str> {
        if square >= self.squares.len() {
            return Err("Invalid square number.");
        }

        if self.squares[square] != Mark::Empty {
            return Err("Square is already taken.");
        }

        self.squares[square] = self.current_player;
        self.current_player = match self.current_player {
            Mark::X => Mark::O,
            Mark::O => Mark::X,
            Mark::Empty => Mark::Empty,
        };

        Ok(())
    }

    fn is_game_over(&self) -> bool {
        self.get_winner().is_some() || self.squares.iter().all(|&mark| mark != Mark::Empty)
    }

    fn get_winner(&self) -> Option<Mark> {
        let winning_positions = [
            (0, 1, 2),
            (3, 4, 5),
            (6, 7, 8),
            (0, 3, 6),
            (1, 4, 7),
            (2, 5, 8),
            (0, 4, 8),
            (2, 4, 6),
        ];

        for &(a, b, c) in winning_positions.iter() {
            if self.squares[a] != Mark::Empty && self.squares[a] == self.squares[b] && self.squares[a] == self.squares[c] {
                return Some(self.squares[a]);
            }
        }

        None
    }
}

fn main() {
    let mut board = Board::new();

    loop {
        board.print();

        if board.is_game_over() {
            match board.get_winner() {
                Some(Mark::X) => println!("X wins!"),
                Some(Mark::O) => println!("O wins!"),
                None => println!("It's a tie!"),
            }
            return;
        }

        let player = if board.current_player == Mark::X {
            "X"
        } else {
            "O"
        };

        println!("Player {}, make your move (0-8):", player);

        let mut input = String::new();
        io::stdin()
            .read_line(&mut input)
            .expect("Failed to read input.");
        let square = input.trim().parse().unwrap();

        match board.make_move(square) {
            Ok(()) => {}
            Err(e) => {
                println!("{}", e);
                continue;
            }
        };
    }
}
