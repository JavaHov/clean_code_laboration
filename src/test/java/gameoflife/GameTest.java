package gameoflife;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    @Test
    public void oneLivingNeighbour() {
        Game game = new Game();
        int numNeighbours = game.getLivingNeighbours();
        assertThat(numNeighbours).isEqualTo(1);


    }

}
