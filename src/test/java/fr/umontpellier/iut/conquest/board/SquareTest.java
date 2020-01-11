package fr.umontpellier.iut.conquest.board;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsNot;
import org.junit.Test;

public class SquareTest {

    @Test
    public void test_getters() {
        int row = 1;
        int column = 2;

        Square square = new Square(row,column);

        assertEquals(row, square.getRow());
        assertEquals(column, square.getColumn());
    }


    @Test
    public void test_Equals_with_same_address() {
        Square square = new Square(1,1);
        Square square2 = square;
        assertThat(square , Is.is(square2));
    }

    @Test
    public void test_Equals_with_same_values() {
        Square square = new Square(1,1);
        Square square2 = new Square(1,1);
        assertThat(square , Is.is(square2));
    }

    @Test
    public void test_not_Equals_with_null() {
        Square square = new Square(1,1);
        Square square2 = null;
        assertThat(square , IsNot.not(square2));
    }

    @Test
    public void test_not_Equals_with_other_class() {
        Square square = new Square(1,1);
        String square2 = "1 1";
        assertThat(square , IsNot.not(square2));
    }

    
    @Test
    public void test_not_Equals_with_not_same_column() {
        Square square = new Square(1,1);
        Square square2 = new Square(1,2);
        assertThat(square , IsNot.not(square2));
    }

    @Test
    public void test_not_Equals_with_not_same_row() {
        Square square = new Square(1,1);
        Square square2 = new Square(2,1);
        assertThat(square , IsNot.not(square2));
    }

    @Test
    public void test_same_hashcode_with_same_values() {
        int square = new Square(1,1).hashCode();
        int square2 = new Square(1,1).hashCode();
        assertEquals(square , square2);
    }
}