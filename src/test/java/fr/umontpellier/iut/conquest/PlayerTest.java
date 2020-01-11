package fr.umontpellier.iut.conquest;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void test_getter() {
        Game game = new Game(null, null, null, null, null);

        Player player = new Player(null, game, null, 1);

        assertThat(game, Is.is(player.getGame()));
    }


    @Test
    public void test_Equals_with_same_address() {
        Player player = new Player(null, null, null, 1);
        Player player2 = player;
        assertThat(player , Is.is(player2));
    }

    @Test
    public void test_Equals_with_same_values() {
        Player player = new Player(null,null,null,1);
        Player player2 = new Player(null,null,null,1);
        assertThat(player , Is.is(player2));
    }

    @Test
    public void test_not_Equals_with_null() {
        Player player = new Player(null,null,null,1);
        Player player2 = null;
        assertThat(player , IsNot.not(player2));
    }

    @Test
    public void test_not_Equals_with_other_class() {
        Player player = new Player(null,null,null,1);
        String player2 = "1 1";
        assertThat(player , IsNot.not(player2));
    }

    @Test
    public void test_not_Equals_with_not_same_color() {
        Player player = new Player(null,null,null,1);
        Player player2 = new Player(null,null,null,2);
        assertThat(player , IsNot.not(player2));
    }

    @Test
    public void test_same_hashcode_with_same_values() {
        int player = new Player(null,null,null,1).hashCode();
        int player2 = new Player(null,null,null,1).hashCode();
        assertEquals(player , player2);
    }
}