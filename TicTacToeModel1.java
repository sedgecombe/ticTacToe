import java.util.ArrayList;

public class TicTacToeModel1 implements TicTacToeModel {
    // variables
    private Integer[] squares = new Integer[9];
    private boolean playerOnesTurn;

    TicTacToeModel1() {
        this.initialize();

    }

    // methods needed
    @Override
    public void initialize() {
        for (int i = 0; i < 9; i++) {
            this.squares[i] = 0;
        }

        this.playerOnesTurn = true;
    }

    @Override
    public void flipTurn() {
        this.playerOnesTurn = !this.playerOnesTurn;
    }

    @Override
    public int getSquareValue(int i) {
        return this.squares[i];
    }

    @Override
    public void playSquare(int i) {
        if (this.playerOnesTurn) {
            this.squares[i] = 1;
        } else {
            this.squares[i] = -1;
        }
    }

    @Override
    public boolean isPlayerOnesTurn() {
        return this.playerOnesTurn;
    }

    @Override
    public boolean victoryAchieved() {
        boolean returnValue = false;

        // check horizantal and vertical victory
        for (int i = 0; i < 3; i++) {

            if (this.squares[i] != 0) {

                if (this.squares[i] == this.squares[i + 3]
                        && this.squares[i] == this.squares[i + 6]) {
                    returnValue = true;
                }
            }

            if (this.squares[3 * i] != 0) {
                if (this.squares[3 * i] == this.squares[3 * i + 1]
                        && this.squares[3 * i] == this.squares[3 * i + 2]) {
                    returnValue = true;
                }
            }
        }

        // check diagonals
        if (this.squares[4] != 0) {
            if (this.squares[0] == this.squares[4]
                    && this.squares[0] == this.squares[8]) {
                returnValue = true;
            } else if (this.squares[2] == this.squares[4]
                    && this.squares[2] == this.squares[6]) {
                returnValue = true;
            }
        }

        return returnValue;
    }

    @Override
    public ArrayList<Integer> winningRow(boolean playerOnesTurn) {
        // array of all xs or os, whichever won
        ArrayList<Integer> spotsOfInterest = new ArrayList<>();
        // array of winning row
        ArrayList<Integer> winningRow = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (playerOnesTurn) {
                if (this.squares[i] > 0) {
                    spotsOfInterest.add(i);
                }
            } else {
                if (this.squares[i] < 0) {
                    spotsOfInterest.add(i);
                }
            }

        }

        // check horizantal and vertical
        for (int i = 0; i < 3; i++) {
            if (spotsOfInterest.contains(i) && spotsOfInterest.contains(i + 3)
                    && spotsOfInterest.contains(i + 6)) {
                winningRow.add(i);
                winningRow.add(i + 3);
                winningRow.add(i + 6);
            }

            if (spotsOfInterest.contains(3 * i)
                    && spotsOfInterest.contains(3 * i + 1)
                    && spotsOfInterest.contains(3 * i + 2)) {
                winningRow.add(3 * i);
                winningRow.add(3 * i + 1);
                winningRow.add(3 * i + 2);
            }
        }

        // check diagonals
        if (spotsOfInterest.contains(0) && spotsOfInterest.contains(4)
                && spotsOfInterest.contains(8)) {
            winningRow.add(0);
            winningRow.add(4);
            winningRow.add(8);
        }

        if (spotsOfInterest.contains(2) && spotsOfInterest.contains(4)
                && spotsOfInterest.contains(6)) {
            winningRow.add(2);
            winningRow.add(4);
            winningRow.add(6);
        }

        return winningRow;
    }

    @Override
    public boolean draw() {

        boolean returnValue = true;

        for (int i = 0; i < 9; i++) {
            if (this.squares[i] == 0) {
                returnValue = false;
            }
        }

        return returnValue;
    }

    @Override
    public int makeMove() {
        int returnValue = -1;
        int bestResult = -2;

        for (int i = 0; i < 9; i++) {
            if (this.squares[i] == 0) {
                int result = this.isGoodMove(i, true);
                if (result > bestResult) {
                    returnValue = i;
                    bestResult = result;
                }
                this.unPlaySquare(i);
                this.flipTurn();

            }
        }

        return returnValue;
    }

    @Override
    public int isGoodMove(int move, boolean myTurn) {
        this.playSquare(move);
        this.flipTurn();
        if (this.victoryAchieved()) {
            if (myTurn) {
                return 1;
            } else {
                return -1;
            }
        }
        if (this.draw()) {
            return 0;
        }

        int nextMove = this.makeMove();
        int returnValue = this.isGoodMove(nextMove, !myTurn);

        this.flipTurn();

        this.unPlaySquare(nextMove);
        return returnValue;

    }

    @Override
    public void update(TicTacToeModel model) {
        for (int i = 0; i < 9; i++) {
            this.squares[i] = model.getSquareValue(i);
        }

        this.playerOnesTurn = model.isPlayerOnesTurn();
    }

    @Override
    public void unPlaySquare(int i) {
        this.squares[i] = 0;

    }

    @Override
    public void setPlayerOnesTurn(boolean b) {
        this.playerOnesTurn = b;

    }

}
