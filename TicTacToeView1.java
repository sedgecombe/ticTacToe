import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TicTacToeView1 extends JFrame implements TicTacToeView {

    // Private variables
    private JButton[] squares = new JButton[9];
    private JLabel message = new JLabel("Player 1's turn",
            SwingConstants.CENTER);
    private JPanel gameScreen = new JPanel();
    private JPanel messageScreen = new JPanel();
    private JButton reset = new JButton("Reset");

    TicTacToeView1() {

        // add components to frame
        this.setLayout(new GridLayout(2, 1));
        this.gameScreen.setLayout(new GridLayout(3, 3));
        this.messageScreen.setLayout(new GridLayout(2, 1));

        for (int i = 0; i < 9; i++) {
            this.squares[i] = new JButton();
        }

        for (int i = 0; i < 9; i++) {
            this.gameScreen.add(this.squares[i]);
        }

        this.messageScreen.add(this.reset);
        this.messageScreen.add(this.message);

        this.add(this.messageScreen);
        this.add(this.gameScreen);
        this.pack();

        this.setTitle("Tic Tac Toe");
    }

    // add the action listeners

    @Override
    public void addButtonListener(ActionListener[] buttonListener) {
        for (int i = 0; i < 9; i++) {
            this.squares[i].addActionListener(buttonListener[i]);
        }

    }

    @Override
    public void addResetListener(ActionListener resetListener) {
        this.reset.addActionListener(resetListener);
    }

    @Override
    public void initialize() {
        for (int i = 0; i < 9; i++) {
            this.squares[i].setText("");
            this.squares[i].setForeground(Color.BLACK);
            this.squares[i].setOpaque(false);
            this.squares[i].setBackground(Color.BLACK);
            this.squares[i].setEnabled(true);
        }

        this.message.setText("Player One's Turn");
        this.reset.setText("Reset");
    }

    @Override
    public void updateWinner(boolean playerOnesTurn, TicTacToeModel model) {

        String winningText;

        if (playerOnesTurn) {
            winningText = "Player One Wins!";
        } else {
            winningText = "Player Two Wins!";
        }

        this.message.setText(winningText);

        ArrayList<Integer> winningRow = model.winningRow(playerOnesTurn);
        for (int index : winningRow) {
            this.squares[index].setBackground(Color.GREEN);
            this.squares[index].setOpaque(true);
        }

        this.reset.setText("Play Again");
        for (int i = 0; i < 9; i++) {
            this.squares[i].setEnabled(false);
        }

    }

    @Override
    public void updateView(TicTacToeModel model) {
        for (int i = 0; i < 9; i++) {
            int squareValue = model.getSquareValue(i);

            if (squareValue > 0) {
                this.squares[i].setText("x");
                this.squares[i].setForeground(Color.RED);
            } else if (squareValue < 0) {
                this.squares[i].setText("o");
            }
        }

        if (!model.victoryAchieved() && !model.draw()) {
            if (model.isPlayerOnesTurn()) {
                this.message.setText("Player One's Turn");
            } else {
                this.message.setText("Player Two's Turn");
            }
        }
    }

    @Override
    public void updateDraw() {
        for (int i = 0; i < 9; i++) {
            this.squares[i].setEnabled(false);
        }

        this.reset.setText("Play Again");
        this.message.setText("It's a draw");

    }

}
