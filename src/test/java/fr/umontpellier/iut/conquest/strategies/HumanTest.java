package fr.umontpellier.iut.conquest.strategies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Player;

public class HumanTest {

    private Player player1 = new Player(null, null, null, 1);
    private Player player2 = new Player(null, null, null, 2);

    private Board board;
    private Human human;

    @Before
    public void init() {
        board = new Board(5);
        board.initField(player1, player2);
        human = null;
    }

    @Test
    public void test_same_move_with_getMove() {
        Move exceptedMove = new Move(0, 0, 1, 0);

        Scanner scan = new Scanner(" 0 0 1 0 ");
        human = new Human(scan);
        Move actualMove = human.getMove(board, player1);
        assertThat(exceptedMove, Is.is(actualMove));
    }

    @Test
    public void test_same_move_with_getMove_with_invalid_move_and_valid_move() {
        Move exceptedMove = new Move(0, 0, 2, 0);

        String firstMove = "0 0 0 0 ";
        String secondMove = "0 0 2 0 ";

        Scanner scan = new Scanner(firstMove + secondMove);
        human = new Human(scan);
        Move actualMove = human.getMove(board, player1);
        assertThat(exceptedMove, Is.is(actualMove));
    }
}