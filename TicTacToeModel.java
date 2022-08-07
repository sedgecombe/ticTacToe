import java.util.ArrayList;

public interface TicTacToeModel {

    void playSquare(int buttonID);

    boolean victoryAchieved();

    boolean isPlayerOnesTurn();

    void flipTurn();

    int getSquareValue(int i);

    ArrayList<Integer> winningRow(boolean playerOnesTurn);

    void initialize();

    boolean draw();

    int makeMove();

    int isGoodMove(int i, boolean myTurn);

    void update(TicTacToeModel model);

    void unPlaySquare(int i);

    void setPlayerOnesTurn(boolean b);

}
