package ant.g2048.game;

import org.junit.Test;

import ant.g2048.game.Game;
import ant.g2048.game.Player;



public class GameTest {

    @Test
    public void test() {
        Game game = new Game(new Player());
        game.play();
    }

}
