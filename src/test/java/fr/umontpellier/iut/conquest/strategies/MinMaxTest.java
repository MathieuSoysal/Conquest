package fr.umontpellier.iut.conquest.strategies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.umontpellier.iut.conquest.Pawn;
import fr.umontpellier.iut.conquest.Player;
import fr.umontpellier.iut.conquest.board.Board;
import fr.umontpellier.iut.conquest.board.Move;

public class MinMaxTest {

    @Test
    public void getmove_with_just_one_Pawn_with_IAlevel_1() {
        MinMax myRobot = new MinMax(1);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn, null, pawn2 }, // row 0
                { null, null, null }, // row 1
                { pawn2, null, pawn } // row 2
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 5, board.getNbPawns(player1));
    }

    @Test
    public void getmove_with_just_one_Pawn_with_IAlevel_2() {
        MinMax myRobot = new MinMax(2);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn, null, pawn2 }, // row 0
                { null, null, null }, // row 1
                { pawn2, null, pawn } // row 2
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 5, board.getNbPawns(player1));
    }

    @Test
    public void getmove_with_just_one_Pawn_with_IAlevel_3() {
        MinMax myRobot = new MinMax(3);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn, null, pawn2 }, // row 0
                { null, null, null }, // row 1
                { pawn2, null, pawn } // row 2
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 5, board.getNbPawns(player1));
    }

    @Test
    public void getmove_with_just_one_Pawn_with_IAlevel_4() {
        MinMax myRobot = new MinMax(3);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn, null, pawn2 }, // row 0
                { null, null, null }, // row 1
                { pawn2, null, pawn } // row 2
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 5, board.getNbPawns(player1));
    }
}