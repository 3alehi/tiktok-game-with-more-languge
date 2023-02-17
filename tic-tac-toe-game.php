<!DOCTYPE html>
<html>
  <head>
    <title>Tic-Tac-Toe</title>
    <style>
      .board {
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        grid-template-rows: repeat(3, 1fr);
        grid-gap: 10px;
      }
      .cell {
        width: 100px;
        height: 100px;
        background-color: #ccc;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 48px;
      }
    </style>
  </head>
  <body>
    <h1>Tic-Tac-Toe</h1>
    <div class="board">
      <?php
        $board = array_fill(0, 9, '');
        if (isset($_POST['board'])) {
          $board = str_split($_POST['board']);
        }
        $game_over = false;
        $winning_moves = [
          [0,1,2], [3,4,5], [6,7,8], // horizontal
          [0,3,6], [1,4,7], [2,5,8], // vertical
          [0,4,8], [2,4,6], // diagonal
        ];
        foreach (range(0, 8) as $i) {
          $cell = $board[$i];
          $disabled = $game_over || $cell !== '';
          printf('<button class="cell" name="move" value="%d" %s>%s</button>', $i, $disabled ? 'disabled' : '', $cell);
        }
        if (!$game_over && isset($_POST['move'])) {
          $move = (int) $_POST['move'];
          $board[$move] = 'X';
          foreach ($winning_moves as $moves) {
            if ($board[$moves[0]] === 'X' && $board[$moves[1]] === 'X' && $board[$moves[2]] === 'X') {
              $game_over = true;
              echo '<p>X wins!</p>';
              break;
            }
          }
          if (!$game_over) {
            $open_cells = array_keys($board, '');
            $ai_move = $open_cells[array_rand($open_cells)];
            $board[$ai_move] = 'O';
            foreach ($winning_moves as $moves) {
              if ($board[$moves[0]] === 'O' && $board[$moves[1]] === 'O' && $board[$moves[2]] === 'O') {
                $game_over = true;
                echo '<p>O wins!</p>';
                break;
              }
            }
          }
        }
      ?>
    </div>
    <?php if (!$game_over) { ?>
      <form method="post">
        <input type="hidden" name="board" value="<?php echo implode('', $board); ?>">
        <button type="submit">Play</button>
      </form>
    <?php } ?>
  </body>
</html>
