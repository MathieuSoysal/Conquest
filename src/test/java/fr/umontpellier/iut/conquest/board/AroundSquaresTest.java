package fr.umontpellier.iut.conquest.board;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import fr.umontpellier.iut.conquest.board.AroundSquares;
import fr.umontpellier.iut.conquest.board.Square;

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
    public void test_around_squares_with_the_border_top_left_of_board() {
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

    @Test
    public void test_around_squares_with_the_border_bottom_right_of_board() {
        Queue<Square> aroundTrue = new LinkedList<Square>();

        aroundTrue.add(new Square(2,2));
        aroundTrue.add(new Square(2,3));
        aroundTrue.add(new Square(3,2));
        aroundTrue.add(new Square(3,3));
        
        int i =0;

        for (AroundSquares aS = new AroundSquares(new Square(3, 3), 1, 3); aS.haseNext(); aS.next()) {
            assertTrue(String.valueOf(i++),aS.getAroundSquare().equals(aroundTrue.poll()));
        }
    }

}