package fr.umontpellier.iut.conquest.strategies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Ignore;
import org.junit.Test;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Pawn;
import fr.umontpellier.iut.conquest.Player;
import fr.umontpellier.iut.conquest.board.memento.BoardMemento;

public class SpeculationTest {
    private Speculation speculation = new Speculation();

    @Test
    public void test_init() {
        Board board = new Board(5);
        Player player1 = new Player(null, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        board.initField(player1, player2);
        Move move = speculation.getMove(board, player1);

        assertTrue(board.isValid(move, player1));
    }

    @Test
    public void test_good_move() {
        Player player1 = new Player(null, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Board board = new Board(3);

        board.initField(player1, player2);
        Move move = speculation.getMove(board, player1);
        board.movePawn(move);
        assertNotNull(board.getField()[1][1]);
    }

    @Test
    public void getmove_with_just_one_Pawn_and_two_pawns_other_player() {
        Speculation myRobot = new Speculation();
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { null, null, null, null, null, null, null }, // row 0
                { null, null, null, null, null, null, null }, // row 1
                { null, null, null, null, null, pawn2, null }, // row 2
                { null, null, null, pawn, null, null, null }, // row 3
                { null, null, null, null, null, pawn2, null }, // row 4
                { null, null, null, null, null, null, null }, // row 5
                { null, null, null, null, null, null, null }, // row 6
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 4, board.getNbPawns(player1));
    }

    @Test
    public void getmove_with_just_one_Pawn_and_two_pawns_other_player_when_player_can_kill_all() {
        Speculation myRobot = new Speculation();
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { null, null, null, null, null, null, null }, // row 0
                { null, null, null, null, null, null, null }, // row 1
                { null, null, null, null, null, pawn2, null }, // row 2
                { null, null, null, pawn, null, null, pawn2 }, // row 3
                { null, null, null, null, null, null, null }, // row 4
                { null, null, null, null, null, null, null }, // row 5
                { null, null, null, null, null, null, null }, // row 6
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 3, board.getNbPawns(player1));
        assertEquals(board.toString(), 0, board.getNbPawns(player2));
    }

    @Test
    public void getmove_should_is_optimum_with_five_Pawns_and_three_pawns_other_player() {
        Speculation myRobot = new Speculation();
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn, null, null, null, null }, // row 0
                { null, null, pawn2, pawn2, null }, // row 1
                { null, null, pawn2, null, null }, // row 2
                { null, pawn, null, null, null }, // row 3
                { pawn, pawn, null, null, pawn }, // row 4
        };
        Board board = new Board(field);
        BoardMemento memento = myRobot.getMemento(board.saveToMemento(), board, player1);
        board.undoFromMemento(memento);
        assertEquals(board.toString(), 8, board.getNbPawns(player1));
        assertEquals(board.toString(), 0, board.getNbPawns(player2));
    }

    @Test
    public void getmove_should_is_optimum_with_five_Pawns_and_three_pawns_other_player_with_IAlevel4() {
        Speculation myRobot = new Speculation();
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { null, null, pawn, null, null }, // row 0
                { null, null, pawn, pawn, null }, // row 1
                { null, null, pawn2, null, null }, // row 2
                { null, pawn, null, null, null }, // row 3
                { pawn, pawn, null, null, pawn }, // row 4
        };
        Board board = new Board(field);
        BoardMemento memento = myRobot.getMemento(board.saveToMemento(), board, player2);
        board.undoFromMemento(memento);
        assertEquals(board.toString(), 4, board.getNbPawns(player1));
        assertEquals(board.toString(), 4, board.getNbPawns(player2));
    }
}