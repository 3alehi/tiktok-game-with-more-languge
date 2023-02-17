fun main() {
    val board = Array(3) { arrayOf(" ", " ", " ") }
    var player = "X"

    fun printBoard() {
        for (i in board.indices) {
            println("-------------")
            for (j in board[i].indices) {
                print("| ${board[i][j]} ")
            }
            println("|")
        }
        println("-------------")
    }

    fun checkWin(row: Int, col: Int, player: String): Boolean {
        var win = true

        // Check row
        for (i in board.indices) {
            if (board[row][i] != player) {
                win = false
                break
            }
        }
        if (win) return true

        // Check column
        win = true
        for (i in board.indices) {
            if (board[i][col] != player) {
                win = false
                break
            }
        }
        if (win) return true

        // Check diagonal 1
        win = true
        for (i in board.indices) {
            if (board[i][i] != player) {
                win = false
                break
            }
        }
        if (win) return true

        // Check diagonal 2
        win = true
        for (i in board.indices) {
            if (board[i][2 - i] != player) {
                win = false
                break
            }
        }
        return win
    }

    println("Welcome to Tic-Tac-Toe")
    println("Player 1: X")
    println("Player 2: O\n")

    while (true) {
        printBoard()

        print("Player $player's turn. Enter row,column: ")
        val input = readLine() ?: ""
        val (row, col) = input.split(",")
        if (row.isBlank() || col.isBlank()) {
            continue
        }

        val rowIndex = row.trim().toInt()
        val colIndex = col.trim().toInt()

        if (board[rowIndex][colIndex] == " ") {
            board[rowIndex][colIndex] = player

            if (checkWin(rowIndex, colIndex, player)) {
                printBoard()
                println("Player $player wins!")
                break
            }

            player = if (player == "X") "O" else "X"
        } else {
            println("That square is already occupied. Try again.")
        }

    }
}
