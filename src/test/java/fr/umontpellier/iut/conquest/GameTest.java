package fr.umontpellier.iut.conquest;

import fr.umontpellier.iut.conquest.board.Board;
import fr.umontpellier.iut.conquest.strategies.Human;
import fr.umontpellier.iut.conquest.strategies.MinMax;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @BeforeEach
    void disableConsole() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int arg0) {

            }
        }));

    }

    void set_input(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Game.initInput(inputStream);
    }

    @Test
    void if_player2_has_no_pawn_left_then_the_game_should_be_finished_and_player1_should_win() {
        // Create input
        String input = "";
        /*
         * __0_1_2
         * 0|X _ O
         * 1|_ _ _
         * 2|O _ X
         */

        // Set player1 first move to (0,0) -> (1,1)
        input = input + "0 0 ";
        input = input + "1 1 ";
        /*
         * __0_1_2
         * 0|X _ X
         * 1|_ X _
         * 2|X _ X
         */

        // Set System.in to input
        set_input(input);

        // Create game and players
        Game game = new Game(3, new Human(Game.getScan()), null, null, null);
        Player player1 = game.getPlayers()[0];

        // Play in pvp hardcore mode
        game.run(1);

        // The game is finished and the winner is player1
        assertTrue(game.isFinished());
        assertEquals(player1, game.getWinner());
    }

    @Test
    void if_player1_has_no_pawn_left_then_the_game_should_be_finished_and_player2_should_win() {
        // Create input
        String input = "";
        /*
         * __0_1_2
         * 0|X _ O
         * 1|_ _ _
         * 2|O _ X
         */

        // Set player1 first move to (0,0) -> (1,1)
        input = input + "0 0 ";
        input = input + "0 1 ";
        /*
         * __0_1_2
         * 0|X X X
         * 1|_ _ _
         * 2|O _ X
         */

         // Set player2 first move to (2,0) -> (1,1)
        input = input + "2 0 ";
        input = input + "1 1 ";
        /*
         * __0_1_2
         * 0|O O O
         * 1|_ O _
         * 2|O _ O
         */

        // Set System.in to input
        set_input(input);

        // Create game and players
        Game game = new Game(3, new Human(Game.getScan()), null, new Human(Game.getScan()), null);
        Player player2 = game.getPlayers()[1];

        // Play in pvp hardcore mode
        game.run(1);

        // The game is finished and the winner is player1
        assertTrue(game.isFinished());
        assertEquals(player2, game.getWinner());
        assertEquals(6, game.getBoard().getNbPawns(player2));
    }

    @Test
    void if_the_board_is_filled_and_player2_has_more_pawns_then_the_game_should_be_finished_and_player2_should_win() {
        // Create predefined game
        Pawn[][] field = new Pawn[3][3];
        Board board = new Board(field);
        Game game = new Game(board, null, null, null, null);
        Player player1 = game.getPlayers()[0];
        Player player2 = game.getPlayers()[1];
        field[0][0] = new Pawn(player1);
        field[0][1] = new Pawn(player1);
        field[0][2] = new Pawn(player1);
        field[1][0] = new Pawn(player1);
        field[1][1] = new Pawn(player2);
        field[1][2] = new Pawn(player2);
        field[2][0] = new Pawn(player2);
        field[2][1] = new Pawn(player2);
        field[2][2] = new Pawn(player2);
        /*
         * __0_1_2
         * 0|X X X
         * 1|X O O
         * 2|O O O
         */

        // The game is finished and the winner is player2
        assertTrue(game.isFinished());
        assertEquals(player2, game.getWinner());
    }

    @Test
    void test_undo_one_move() {
        // Create input
        String input = "";
        /*
         * __0_1_2
         * 0|X _ O
         * 1|_ _ _
         * 2|O _ X
         */

        // Set player1 first move to (0,0) -> (1,1)
        input = input + "0 0 ";
        input = input + "0 1 ";
        /*
         * __0_1_2
         * 0|X X X
         * 1|_ _ _
         * 2|O _ X
         */

        // Undo player1 first move
        input = input + "1 ";
        /*
         * __0_1_2
         * 0|X _ O
         * 1|_ _ _
         * 2|O _ X
         */

        // Set player1 first move to (0,0) -> (0,1)
        input = input + "0 0 ";
        input = input + "0 1 ";
        /*
         * __0_1_2
         * 0|X X X
         * 1|_ _ _
         * 2|O _ X
         */

        // Valid player1 first move
        input = input + "0 ";

        // Set player2 first move to (2,0) -> (1,2)
        input = input + "2 0 ";
        input = input + "1 2 ";
        /*
         * __0_1_2
         * 0|X O O
         * 1|_ _ O
         * 2|_ _ O
         */

        // Undo player2 first move
        input = input + "1 ";
        /*
         * __0_1_2
         * 0|X X X
         * 1|_ _ _
         * 2|O _ X
         */

        // Not undoing player1 first move
        input = input + "0 ";

        // Set player2 first move to (2,0) -> (1,0)
        input = input + "2 0 ";
        input = input + "1 2 ";
        /*
         * __0_1_2
         * 0|X O O
         * 1|_ _ O
         * 2|_ _ O
         */

        // Valid player2 first move
        input = input + "0 ";

        // Set player1 second move to (0,0) -> (1,1)
        input = input + "0 0 ";
        input = input + "1 1 ";
        /*
         * __0_1_2
         * 0|X X X
         * 1|_ X X
         * 2|_ _ X
         */

        // Valid player1 second move
        input = input + "0 ";

        // Set System.in to input
        set_input(input);

        // Create game
        Game game = new Game(3, new Human(Game.getScan()), null, new Human(Game.getScan()), null);

        // Play in pvp non-hardcore mode
        game.run(0);

        // Test if the board state is correct
        Pawn[][] field = game.getBoard().getField();
        // Top left pawn should belong to player1
        assertEquals(1, field[0][0].getPlayer().getColor());
        // Top center pawn should belong to player1
        assertEquals(1, field[0][1].getPlayer().getColor());
        // Top right pawn should belong to player1
        assertEquals(1, field[0][2].getPlayer().getColor());
        // Middle left pawn should not exist
        assertNull(field[1][0]);
        // Middle center pawn should belong to player1
        assertEquals(1, field[1][1].getPlayer().getColor());
        // Middle right pawn should belong to player1
        assertEquals(1, field[1][2].getPlayer().getColor());
        // Bottom left pawn should not exist
        assertNull(field[2][0]);
        // Bottom center pawn should not exist
        assertNull(field[2][1]);
        // Bottom right pawn should belong to player1
        assertEquals(1, field[2][2].getPlayer().getColor());

    }

    @Test
    void test_undo_two_move() {
        // Create input
        String input = "";
        /*
         * __0_1_2
         * 0|X _ O
         * 1|_ _ _
         * 2|O _ X
         */

        // Set player1 first move to (0,0) -> (0,1)
        input = input + "0 0 ";
        input = input + "0 1 ";

        /*
         * __0_1_2
         * 0|X X X
         * 1|_ _ _
         * 2|O _ X
         */

        // Valid player1 first move
        input = input + "0 ";

        // Set player2 first move to (2,0) -> (1,2)
        input = input + "2 0 ";
        input = input + "1 2 ";
        /*
         * __0_1_2
         * 0|X O O
         * 1|_ _ O
         * 2|_ _ O
         */

        // Undo player2 first move
        input = input + "1 ";
        /*
         * __0_1_2
         * 0|X X X
         * 1|_ _ _
         * 2|O _ X
         */

        // Undo player1 first move
        input = input + "1 ";
        /*
         * __0_1_2
         * 0|X _ O
         * 1|_ _ _
         * 2|O _ X
         */


        // Set player1 second move to (2,2) -> (1,0)
        input = input + "2 2 ";
        input = input + "1 0 ";
        /*
         * __0_1_2
         * 0|X _ O
         * 1|X _ _
         * 2|X _ _
         */

        // Valid player1 first second
        input = input + "0 ";

        // Set player2 second move to (0,0) -> (1,1)
        input = input + "0 2 ";
        input = input + "1 1 ";
        /*
         * __0_1_2
         * 0|O _ O
         * 1|O O _
         * 2|O _ _
         */

        // Valid player2 second move
        input = input + "0 ";

        // Set System.in to input
        set_input(input);

        // Create game
        Game game = new Game(3, new Human(Game.getScan()), null, new Human(Game.getScan()), null);

        // Play in pvp non-hardcore mode
        game.run(0);

        // Play is finished
        assertTrue(game.isFinished());

        // Test if the board state is correct
        Pawn pawnP2 = new Pawn(game.getPlayers()[1]);
        Pawn[][] expectedField = { // field :
                { pawnP2, null, pawnP2 }, // row 0
                { pawnP2, pawnP2, null }, // row 1
                { pawnP2, null, null } // row 2
        };
        Board expectedBoard = new Board(expectedField);

        assertEquals("\n"+expectedBoard.toString(), "\n"+game.getBoard().toString());
    }

    @Test
    void test_undo_move_when_player_win() {
        // Create input
        String input = "";
        /*
         * __0_1_2
         * 0|X _ O
         * 1|_ _ _
         * 2|O _ X
         */

        // Set player1 first move to (0,0) -> (1,1)
        input = input + "0 0 ";
        input = input + "1 1 ";

        /*
         * __0_1_2
         * 0|X _ X
         * 1|_ X _
         * 2|X _ X
         */

        // Undo player1 first move
        input = input + "1 ";

        /*
         * __0_1_2
         * 0|X _ O
         * 1|_ _ _
         * 2|O _ X
         */

        // Set player1 first move to (0,0) -> (1,0)
        input = input + "0 0 ";
        input = input + "1 0 ";

        /*
         * __0_1_2
         * 0|X _ O
         * 1|X _ _
         * 2|X _ X
         */

        // Valid player1 first move
        input = input + "0 ";

        // Set player2 first move to (0,2) -> (1,1)
        input = input + "0 2 ";
        input = input + "1 1 ";

        /*
         * __0_1_2
         * 0|O _ O
         * 1|O O _
         * 2|O _ O
         */

        // Undo player2 first move
        input = input + "1 ";

        /*
         * __0_1_2
         * 0|X _ O
         * 1|X _ _
         * 2|X _ X
         */

        // Stop Undo Move
        input = input + "0 ";

        // Set player2 first move to (0,2) -> (0,1)
        input = input + "0 2 ";
        input = input + "0 1 ";

        /*
         * __0_1_2
         * 0|O O O
         * 1|O _ _
         * 2|X _ X
         */

        // Valid player2 first move
        input = input + "0 ";

        // Set player1 second move to (2,0) -> (1,1)
        input = input + "2 0 ";
        input = input + "1 1 ";

        /*
         * __0_1_2
         * 0|X X X
         * 1|X X _
         * 2|X _ X
         */

        // Valid player2 second move
        input = input + "0 ";

        // Set System.in to input
        set_input(input);

        // Create game
        Game game = new Game(3, new Human(Game.getScan()), null, new Human(Game.getScan()), null);

        // Play in pvp non-hardcore mode
        game.run(0);

        // Play is finished
        assertTrue(game.isFinished());

        // Test if the board state is correct
        Pawn pawnP1 = new Pawn(game.getPlayers()[0]);
        Pawn[][] expectedField = { // field :
                { pawnP1, pawnP1, pawnP1 }, // row 0
                { pawnP1, pawnP1, null }, // row 1
                { pawnP1, null, pawnP1 } // row 2
        };
        Board expectedBoard = new Board(expectedField);

        assertEquals("\n"+expectedBoard.toString(), "\n"+game.getBoard().toString());
    }

    @Test
    public void test_MinMax() {
        String input = "";

        /* init board
         * __0_1_2_3_4
         * 0|X _ _ _ O
         * 1|_ _ _ _ _
         * 2|_ _ _ _ _
         * 3|_ _ _ _ _
         * 4|O _ _ _ X
         */

         input += "0 0 1 1 ";

        /* player turn
         * __0_1_2_3_4
         * 0|X _ _ _ O
         * 1|_ X _ _ _
         * 2|_ _ _ _ _
         * 3|_ _ _ _ _
         * 4|O _ _ _ X
         */

        /* trun of MinMax
         * __0_1_2_3_4
         * 0|X _ _ _ _
         * 1|_ O _ _ _
         * 2|_ _ O _ _
         * 3|_ _ _ _ _
         * 4|O _ _ _ X
         */
        input += "4 4 2 3 ";

        /* player turn
         * __0_1_2_3_4
         * 0|X _ _ _ _
         * 1|_ O _ _ _
         * 2|_ _ X X _
         * 3|_ _ _ _ _
         * 4|O _ _ _ _
         */

        set_input(input);

        // Create game
        Game game = new Game(5, new Human(Game.getScan()), null, new MinMax(4), null);

        assertThrows( NoSuchElementException.class ,() -> {
            game.run(1);
        });
        // Play in pve hardcore mode
    }
}