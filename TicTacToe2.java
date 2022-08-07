/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class TicTacToe2 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private TicTacToe2() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {

        TicTacToeModel model = new TicTacToeModel1();
        TicTacToeView view = new TicTacToeView1();
        TicTacToeController controller = new TicTacToeController1(view, model);

        view.setVisible(true);
    }
}
