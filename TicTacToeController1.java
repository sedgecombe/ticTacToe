import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeController1 implements TicTacToeController {

    private TicTacToeView view;
    private TicTacToeModel model;

    TicTacToeController1(TicTacToeView view, TicTacToeModel model) {
        this.view = view;
        this.model = model;

        // add action listeners
        ButtonListener listener[] = new ButtonListener[9];
        view.addResetListener(new ResetListener());

        for (int i = 0; i < 9; i++) {
            listener[i] = new ButtonListener(i);
        }

        view.addButtonListener(listener);
    }

    // inner class ButtonListener
    class ButtonListener implements ActionListener {

        // private variable to label button pressed
        private int buttonID;

        ButtonListener(int button) {
            this.buttonID = button;
        }

        public int getButtonID() {
            return this.buttonID;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToeController1.this.model.playSquare(this.buttonID);
            if (TicTacToeController1.this.model.victoryAchieved()) {
                TicTacToeController1.this.view.updateWinner(
                        TicTacToeController1.this.model.isPlayerOnesTurn(),
                        TicTacToeController1.this.model);
            } else if (TicTacToeController1.this.model.draw()) {
                TicTacToeController1.this.view.updateDraw();
            } else {

                TicTacToeController1.this.model.flipTurn();
            }

            TicTacToeController1.this.view
                    .updateView(TicTacToeController1.this.model);

            if (!TicTacToeController1.this.model.victoryAchieved()
                    && !TicTacToeController1.this.model.draw()) {

                TicTacToeController1.this.model
                        .playSquare(TicTacToeController1.this.model.makeMove());

                if (TicTacToeController1.this.model.victoryAchieved()) {
                    TicTacToeController1.this.view.updateWinner(
                            TicTacToeController1.this.model.isPlayerOnesTurn(),
                            TicTacToeController1.this.model);
                } else if (TicTacToeController1.this.model.draw()) {
                    TicTacToeController1.this.view.updateDraw();
                } else {

                    TicTacToeController1.this.model.flipTurn();
                }

                TicTacToeController1.this.view
                        .updateView(TicTacToeController1.this.model);
            }
        }
    }

    // inner class reset listener
    class ResetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            TicTacToeController1.this.view.initialize();
            TicTacToeController1.this.model.initialize();

        }

    }

}
