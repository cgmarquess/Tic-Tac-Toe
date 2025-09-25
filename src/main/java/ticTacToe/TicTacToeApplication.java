package ticTacToe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TicTacToeApplication extends Application {

    private String currentPlayer = "X";
    private Button[][] board = new Button[3][3];
    private Label turn;

    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();

        turn = new Label("Turn: " + currentPlayer);
        turn.setFont(Font.font("Monospaced", 20));
        layout.setTop(turn);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button btn = new Button(" ");
                btn.setFont(Font.font("Monospaced", 40));
                btn.setMinSize(60, 60);
                board[row][col] = btn;

                btn.setOnAction((event) -> {
                    if (!btn.getText().equals(" ") || isGameOver(turn)) {
                        return;
                    }

                    btn.setText(currentPlayer);

                    if (checkWin(currentPlayer)) {
                        turn.setText("The end!");
                        disableBoard();
                        return;
                    } else if (isBoardFull()) {
                        turn.setText("Draw!");
                        return;
                    }

                    currentPlayer = currentPlayer.equals("X") ? "O" : "X";
                    turn.setText("Turn: " + currentPlayer);
                });

                gridPane.add(btn, col, row);
            }
        }

        layout.setCenter(gridPane);

        Button restartButton = new Button("Restart Game");
        restartButton.setOnAction(e -> resetGame());
        HBox bottomBox = new HBox(restartButton);
        bottomBox.setSpacing(10);
        layout.setBottom(bottomBox);

        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }

    private boolean checkWin(String player) {
        for (int row = 0; row < 3; row++) {
            if (board[row][0].getText().equals(player) &&
                    board[row][1].getText().equals(player) &&
                    board[row][2].getText().equals(player)) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col].getText().equals(player) &&
                    board[1][col].getText().equals(player) &&
                    board[2][col].getText().equals(player)) {
                return true;
            }
        }

        if (board[0][0].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][2].getText().equals(player)) {
            return true;
        }

        if (board[0][2].getText().equals(player) &&
                board[1][1].getText().equals(player) &&
                board[2][0].getText().equals(player)) {
            return true;
        }

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setDisable(true);
            }
        }
    }

    private boolean isGameOver(Label turn) {
        return turn.getText().equals("The end!") || turn.getText().equals("Draw!");
    }

    private void resetGame() {
        currentPlayer = "X";
        turn.setText("Turn: " + currentPlayer);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setText(" ");
                board[row][col].setDisable(false);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
