package ant.g2048.game;

import ant.g2048.game.Board.Direction;

public class Player {

    private Direction lastMove = Direction.LEFT;

    public Board makeMove(Board board) {
        Direction nextMove = Direction.values()[(lastMove.ordinal() + 1) % Direction.values().length];
        System.out.println(nextMove);
        lastMove = nextMove;
        return board.move(nextMove);
    }

}
