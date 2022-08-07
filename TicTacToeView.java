import java.awt.event.ActionListener;

public interface TicTacToeView {

    void setVisible(boolean b);

    /*
     * This method adds action listeners to array of buttons
     */
    void addButtonListener(ActionListener[] buttonListener);

    void updateWinner(boolean playerOnesTurn, TicTacToeModel model);

    void updateView(TicTacToeModel model);

    void addResetListener(ActionListener resetListener);

    void initialize();

    void updateDraw();

}
