package fr.umontpellier.iut.conquest;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

public class AroundSquaresTest {

    @Test
    public void test_around_squares() {
        Queue<Square> aroundTrue = new LinkedList<Square>();

        aroundTrue.add(new Square(0,0));
        aroundTrue.add(new Square(0,1));
        aroundTrue.add(new Square(0,2));
        aroundTrue.add(new Square(1,0));
        aroundTrue.add(new Square(1,1));
        aroundTrue.add(new Square(1,2));
        aroundTrue.add(new Square(2,0));
        aroundTrue.add(new Square(2,1));
        aroundTrue.add(new Square(2,2));

        int i =0;

        for (AroundSquares aS = new AroundSquares(new Square(1, 1), 1, 3); aS.haseNext(); aS.next()) {
            assertTrue(String.valueOf(i++),aS.getAroundSquare().equals(aroundTrue.poll()));
        }
    }

    @Test
    public void test_around_squares_with_the_border_of_board() {
        Queue<Square> aroundTrue = new LinkedList<Square>();

        aroundTrue.add(new Square(0,0));
        aroundTrue.add(new Square(0,1));
        aroundTrue.add(new Square(1,0));
        aroundTrue.add(new Square(1,1));
        
        int i =0;

        for (AroundSquares aS = new AroundSquares(new Square(0, 0), 1, 3); aS.haseNext(); aS.next()) {
            assertTrue(String.valueOf(i++),aS.getAroundSquare().equals(aroundTrue.poll()));
        }
    }

}