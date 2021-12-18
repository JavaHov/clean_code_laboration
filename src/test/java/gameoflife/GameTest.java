package gameoflife;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {


    private Game game = new Game(8, 8);




    @Test
    public void zeroLivingNeighbours() {
        int numNeighbours = game.getLivingNeighbours(2, 4);
        assertThat(numNeighbours).isEqualTo(0);

    }

    @Test
    public void zeroLivingNeighboursAdjacent() {

        game.setLivingCell(0,0);
        game.setLivingCell(0,4);
        game.setLivingCell(4,0);
        game.setLivingCell(4,4);
        int numNeighbours = game.getLivingNeighbours(2,2);
        assertThat(numNeighbours).isEqualTo(0);
    }

    @Test
    public void oneLivingNeighbour() {

        game.setLivingCell(3, 4);
        int numNeighbours = game.getLivingNeighbours(3, 5);
        assertThat(numNeighbours).isEqualTo(1);
    }

    @Test
    public void twoLivingNeighbours() {

        game.setLivingCell(1,4);
        game.setLivingCell(1,5);
        int numNeighbours = game.getLivingNeighbours(0,4);
        assertThat(numNeighbours).isEqualTo(2);
    }

    @Test
    public void eightLivingNeighbours() {

        game.setLivingCell(2,2);
        game.setLivingCell(2,3);
        game.setLivingCell(2,4);
        game.setLivingCell(3,2);
        game.setLivingCell(3,4);
        game.setLivingCell(4,2);
        game.setLivingCell(4,3);
        game.setLivingCell(4,4);
        int numNeighbours = game.getLivingNeighbours(3,3);
        assertThat(numNeighbours).isEqualTo(8);
    }

    @Test
    public void aCellWithLessThanTwoLivingNeighboursDies() {

        game.setLivingCell(0,0);
        game.setLivingCell(0,1);

        game.buildNextGenMatrix();

        assertThat(game.isDead(0,0)).isTrue();
    }

    @Test
    public void aCellWithTwoLivingNeighboursLives() {

        game.setLivingCell(0,0);
        game.setLivingCell(0,1);
        game.setLivingCell(1,0);

        game.buildNextGenMatrix();

        assertThat(game.isAlive(1,0)).isTrue();
    }

}
