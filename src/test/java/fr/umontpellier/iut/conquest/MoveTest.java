package fr.umontpellier.iut.conquest;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Test;

public class MoveTest {


    @Test
    public void test_Equals_with_same_address() {
        Move move = new Move(1,1,1,1);
        Move move2 = move;
        assertThat(move , Is.is(move2));
    }

    @Test
    public void test_Equals_with_same_values() {
        Move move = new Move(1,1,1,1);
        Move move2 = new Move(1,1,1,1);
        assertThat(move , Is.is(move2));
    }

    @Test
    public void test_not_Equals_with_null() {
        Move move = new Move(1,1,1,1);
        Move move2 = null;
        assertThat(move , IsNot.not(move2));
    }

    @Test
    public void test_not_Equals_with_other_class() {
        Move move = new Move(1,1,1,1);
        String move2 = "1 1 1 1";
        assertThat(move , IsNot.not(move2));
    }

    
    @Test
    public void test_not_Equals_with_not_same_arrival_column() {
        Move move = new Move(1,1,1,1);
        Move move2 = new Move(1,1,1,2);
        assertThat(move , IsNot.not(move2));
    }

    @Test
    public void test_not_Equals_with_not_same_arrival_row() {
        Move move = new Move(1,1,1,1);
        Move move2 = new Move(1,1,3,1);
        assertThat(move , IsNot.not(move2));
    }

    @Test
    public void test_not_Equals_with_not_same_starting_row() {
        Move move = new Move(1,1,1,1);
        Move move2 = new Move(2,1,1,1);
        assertThat(move , IsNot.not(move2));
    }

    @Test
    public void test_not_Equals_with_not_same_starting_column() {
        Move move = new Move(1,1,1,1);
        Move move2 = new Move(1,2,1,1);
        assertThat(move , IsNot.not(move2));
    }

    @Test
    public void test_same_hashcode_with_same_values() {
        int move = new Move(1,1,1,1).hashCode();
        int move2 = new Move(1,1,1,1).hashCode();
        assertEquals(move , move2);
    }
}