
package ant.g2048.game;

public class Game {

    private Board board;

    private final Player player;

    public Game(Player player) {
        this.player = player;
        board = new Board(4, 4);
    }

    public void play() {
        while (!board.isFull()) {
            board = board.placeRandom();
            System.out.println(board);
            board = player.makeMove(board);
        }
    }

}
