package main

import (
    "fmt")

var (
    board [3][3]string
    player = "X"
)

func main() {
    clearBoard()
    fmt.Println("Welcome to Tic-Tac-Toe!")
    printBoard()
    for !isGameOver() {
        makeMove()
        printBoard()
    }
}

func clearBoard() {
    for i := 0; i < 3; i++ {
        for j := 0; j < 3; j++ {
            board[i][j] = "-"
        }
    }
}

func printBoard() {
    fmt.Println("  1 2 3")
    for i := 0; i < 3; i++ {
        fmt.Printf("%d %s\n", i+1, board[i])
    }
}

func makeMove() {
    var row, col int
    fmt.Printf("Player %s, enter row and column (e.g. 1 2): ", player)
    fmt.Scanln(&row, &col)
    if row < 1 || row > 3 || col < 1 || col > 3 {
        fmt.Println("Invalid input, please try again")
        makeMove()
    } else if board[row-1][col-1] != "-" {
        fmt.Println("Position already taken, please try again")
        makeMove()
    } else {
        board[row-1][col-1] = player
        if player == "X" {
            player = "O"
        } else {
            player = "X"
        }
    }
}

func isGameOver() bool {
    // check rows
    for i := 0; i < 3; i++ {
        if board[i][0] != "-" && board[i][0] == board[i][1] && board[i][1] == board[i][2] {
            fmt.Printf("Player %s wins!\n", board[i][0])
            return true
        }
    }
    // check columns
    for j := 0; j < 3; j++ {
        if board[0][j] != "-" && board[0][j] == board[1][j] && board[1][j] == board[2][j] {
            fmt.Printf("Player %s wins!\n", board[0][j])
            return true
        }
    }
    // check diagonals
    if board[0][0] != "-" && board[0][0] == board[1][1] && board[1][1] == board[2][2] {
        fmt.Printf("Player %s wins!\n", board[0][0])
        return true
    }
    if board[0][2] != "-" && board[0][2] == board[1][1] && board[1][1] == board[2][0] {
        fmt.Printf("Player %s wins!\n", board[0][2])
        return true
    }
    // check tie
    for i := 0; i < 3; i++ {
        for j := 0; j < 3; j++ {
            if board[i][j] == "-" {
                return false
            }
        }
    }
    fmt.Println("It's a tie!")
    return true
}
