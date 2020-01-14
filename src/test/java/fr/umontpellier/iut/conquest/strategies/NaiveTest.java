package fr.umontpellier.iut.conquest.strategies;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.Test;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Game;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Pawn;
import fr.umontpellier.iut.conquest.Player;

public class NaiveTest {
    private Strategy naive = new Naive();
    private Player player1 = new Player(naive, null, null, 1);
    private Pawn pawn = new Pawn(player1);

    @Test
    public void getmove_with_just_one_Pawn() {
        Pawn[][] field = { // field :
                { null, null, null, null, null, null, null }, // row 0
                { null, null, null, null, null, null, null }, // row 1
                { null, null, null, null, null, null, null }, // row 2
                { null, null, null, pawn, null, null, null }, // row 3
                { null, null, null, null, null, null, null }, // row 4
                { null, null, null, null, null, null, null }, // row 5
                { null, null, null, null, null, null, null }, // row 6
        };
        Board board = new Board(field);
        for (int i = 0; i < 100; i++) {
            Move move = naive.getMove(board, player1);
            assertTrue(board.isValid(move, player1));
        }
    }

    @Test
    public void getmove_with_Pawns_in_border() {
        Pawn[][] field = { // field :
                { pawn, null, null, pawn }, // row 0
                { null, null, null, null }, // row 1
                { null, null, null, null }, // row 2
                { pawn, null, null, pawn }, // row 3
        };
        Board board = new Board(field);
        for (int i = 0; i < 100; i++) {
            Move move = naive.getMove(board, player1);
            assertTrue(board.isValid(move, player1));
        }
    }

    @Test
    public void getmove_with_other_pawns() {
        Pawn pawnO = new Pawn(new Player(naive, null, null, 2));
        Pawn[][] field = { // field :
                { null, null, null, null }, // row 0
                { null, pawnO, pawnO, pawnO }, // row 1
                { null, pawnO, pawn, pawnO }, // row 2
                { null, pawnO, pawnO, pawnO }, // row 3
        };
        Board board = new Board(field);
        for (int i = 0; i < 100; i++) {
            Move move = naive.getMove(board, player1);
            assertTrue(board.isValid(move, player1));
        }
    }

    @Test
    public void two_naive_in_game() {
        Game game = new Game(21, naive, "dev", naive, "sys");
        assertAll(() -> game.run(1));
    }
}