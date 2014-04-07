package game;

import org.junit.Test;

import game.Game.Direction;


public class GameTest {

    @Test
    public void test() {
        Game game = new Game(4,4);
        game = game.placeRandom();
        game = game.placeRandom();
        game = game.placeRandom();
        game = game.placeRandom();
        System.out.println(game);
        game = game.move(Direction.RIGHT);
        System.out.println(game);
        game = game.move(Direction.UP);
        System.out.println(game);
    }

}
