package fr.umontpellier.iut.conquest.strategies;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.Ignore;
import org.junit.Test;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Game;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Pawn;
import fr.umontpellier.iut.conquest.Player;

public class MinMaxTest {

    @Test
    public void getmove_with_board_size_3_with_IAlevel_1() {
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
    public void getmove_with_board_size_3_with_IAlevel_2() {
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
    public void getmove_with_board_size_3_with_IAlevel_3() {
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
    public void getmove_with_board_size_3_with_IAlevel_4() {
        MinMax myRobot = new MinMax(4);
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
    public void getmove_should_is_optimum_with_one_Pawn_and_two_pawns_other_player_with_IAlevel1() {
        MinMax myRobot = new MinMax(1);
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
    public void getmove_should_is_optimum_with_one_Pawn_and_two_pawns_other_player_with_IAlevel2() {
        MinMax myRobot = new MinMax(2);
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
    public void getmove_should_is_optimum_with_one_Pawn_and_two_pawns_other_player_with_IAlevel3() {
        MinMax myRobot = new MinMax(3);
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
    public void getmove_should_is_optimum_with_one_Pawn_and_two_pawns_other_player_with_IAlevel4() {
        MinMax myRobot = new MinMax(4);
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
    public void getmove_should_is_optimum_with_one_Pawn_and_three_pawns_other_player_with_IAlevel4() {
        MinMax myRobot = new MinMax(4);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { null, null, null, null, null, null, null }, // row 0
                { null, null, null, null, null, null, null }, // row 1
                { null, null, pawn, null, null, null, null }, // row 2
                { null, null, null, null, null, null, pawn2 }, // row 3
                { null, null, null, null, pawn2, pawn2, null }, // row 4
                { null, null, null, null, null, null, null }, // row 5
                { null, null, null, null, null, null, null }, // row 6
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertNull(board.toString(), board.getField()[3][4]);
    }

    // @Ignore
    @Test
    public void getmove_should_is_optimum_with_one_Pawn_and_three_pawns_other_player_with_IAlevel1() {
        MinMax myRobot = new MinMax(1);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { null, null, null, null, null, null, null }, // row 0
                { null, null, null, null, null, null, null }, // row 1
                { null, null, pawn, null, null, null, null }, // row 2
                { null, null, null, null, null, null, pawn2 }, // row 3
                { null, null, null, null, pawn2, pawn2, null }, // row 4
                { null, null, null, null, null, null, null }, // row 5
                { null, null, null, null, null, null, null }, // row 6
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertNotNull(board.toString(), board.getField()[3][4]);
    }

    @Test
    public void getmove_in_context_game_with_IAlevel_2() {
        MinMax myRobot = new MinMax(2);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn, pawn, pawn2, pawn2, pawn2 }, // row 0
                { pawn, pawn, pawn, pawn2, null }, // row 1
                { pawn, pawn, pawn2, pawn2, pawn2 }, // row 2
                { pawn2, pawn2, pawn2, pawn2, pawn2 }, // row 3
                { pawn2, pawn2, pawn2, pawn2, pawn2 } // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertNotEquals(board.toString(), null, board.getField()[1][4]);
    }

    @Test
    public void getmove_should_is_optimum_with_five_Pawns_and_three_pawns_other_player_with_IAlevel4() {
        MinMax myRobot = new MinMax(4);
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
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 8, board.getNbPawns(player1));
        assertEquals(board.toString(), 0, board.getNbPawns(player2));
    }

    @Test
    public void getmove_with_one_Pawn_and_two_pawns_other_player_when_player_can_kill_all() {
        MinMax myRobot = new MinMax(4);
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
    public void getmove_is_optimum_move_with_just_three_Pawsn_and_three_pawns_other_player_with_IAlevel1() {
        MinMax myRobot = new MinMax(1);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn, null, null, null, pawn2 }, // row 0
                { null, null, null, null, null }, // row 1
                { null, null, null, null, null }, // row 2
                { pawn2, null, null, pawn, null }, // row 3
                { pawn2, null, null, null, pawn }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 5, board.getNbPawns(player1));
        assertEquals(board.toString(), 1, board.getNbPawns(player2));
    }

    @Test
    public void getmove_is_optimum_move_with_just_three_Pawsn_and_three_pawns_other_player_with_IAlevel2() {
        MinMax myRobot = new MinMax(2);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn, null, null, null, pawn2 }, // row 0
                { null, null, null, null, null }, // row 1
                { null, null, null, null, null }, // row 2
                { pawn2, null, null, pawn, null }, // row 3
                { pawn2, null, null, null, pawn }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 5, board.getNbPawns(player1));
        assertEquals(board.toString(), 1, board.getNbPawns(player2));
    }

    @Test
    public void getmove_is_optimum_move_with_just_three_Pawsn_and_three_pawns_other_player_with_IAlevel3() {
        MinMax myRobot = new MinMax(3);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn, null, null, null, pawn2 }, // row 0
                { null, null, null, null, null }, // row 1
                { null, null, null, null, null }, // row 2
                { pawn2, null, null, pawn, null }, // row 3
                { pawn2, null, null, null, pawn }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 5, board.getNbPawns(player1));
        assertEquals(board.toString(), 1, board.getNbPawns(player2));
    }

    @Test
    public void getmove_is_optimum_move_with_just_three_Pawsn_and_three_pawns_other_player_with_IAlevel4() {
        MinMax myRobot = new MinMax(4);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn, null, null, null, pawn2 }, // row 0
                { null, null, null, null, null }, // row 1
                { null, null, null, null, null }, // row 2
                { pawn2, null, null, pawn, null }, // row 3
                { pawn2, null, null, null, pawn }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 5, board.getNbPawns(player1));
        assertEquals(board.toString(), 1, board.getNbPawns(player2));
    }

    @Test
    public void getmove_is_optimum_move_with_six_Pawsn_and_three_pawns_other_player_with_IAlevel4() {
        MinMax myRobot = new MinMax(4);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { null, pawn2, null, pawn2, null }, // row 0
                { null, null, null, null, null }, // row 1
                { pawn, pawn, null, null, null }, // row 2
                { pawn2, null, pawn, null, null }, // row 3
                { pawn, pawn, pawn, null, null }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 8, board.getNbPawns(player1));
        assertEquals(board.toString(), 2, board.getNbPawns(player2));
    }

    @Test
    public void getmove_is_optimum_move_with_five_Pawns_and_three_pawns_other_player_with_IAlevel2() {
        MinMax myRobot = new MinMax(2);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { null, pawn, null, null, null }, // row 0
                { pawn2, null, pawn, pawn, pawn }, // row 1
                { pawn2, null, pawn, null, null }, // row 2
                { null, null, null, null, pawn2 }, // row 3
                { null, null, null, null, null }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 6, board.getNbPawns(player1));
        assertEquals(board.toString(), 2, board.getNbPawns(player2));
    }

    @Test
    public void getmove_is_optimum_move_with_Ten_Pawns_and_fiveteen_pawns_other_player_with_IAlevel3_should_pawns_equals_20() {
        MinMax myRobot = new MinMax(3);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn2, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 0
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 1
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 2
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 3
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 4
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 5
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 6
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 7
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 8
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, pawn, null }, // row 9
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 99, board.getNbPawns(player1));
        assertEquals(board.toString(), 1, board.getNbPawns(player2));
    } 

    @Test
    public void getmove_is_optimum_move_with_Ten_Pawns_and_fiveteen_pawns_other_player_with_IAlevel4_should_pawns_equals_23() {
        MinMax myRobot = new MinMax(4);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn2, pawn2, pawn, pawn, pawn, pawn, pawn }, // row 0
                { pawn2, pawn2, pawn, pawn, pawn, pawn, pawn }, // row 1
                { pawn2, pawn2, pawn, pawn, pawn, pawn, pawn }, // row 2
                { pawn2, pawn2, pawn, pawn, pawn, pawn, pawn }, // row 3
                { pawn2, pawn2, pawn, pawn, pawn, pawn, pawn }, // row 4
                { pawn2, pawn2, pawn, pawn, pawn, pawn, pawn }, // row 4
                { pawn2, pawn2, pawn, pawn, pawn, null, pawn }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 34, board.getNbPawns(player1));
        assertEquals(board.toString(), 14, board.getNbPawns(player2));
    } 

    @Test
    public void getmove_is_optimum_move_with_Ten_Pawns_and_fiveteen_pawns_other_player_with_IAlevel4_should_pawns_equals_14() {
        MinMax myRobot = new MinMax(4);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn2, pawn2, pawn, pawn, pawn }, // row 0
                { pawn2, pawn2, pawn, pawn, pawn }, // row 1
                { pawn2, pawn2, pawn, pawn, null }, // row 2
                { pawn2, pawn2, pawn, pawn, pawn }, // row 3
                { pawn2, pawn2, pawn, pawn, pawn }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 14, board.getNbPawns(player1));
        assertEquals(board.toString(), 10, board.getNbPawns(player2));
    }

    @Test
    public void getmove_is_optimum_move_with_Ten_Pawns_and_fiveteen_pawns_other_player_with_IAlevel3_should_pawns_equals_14() {
        MinMax myRobot = new MinMax(3);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn2, pawn2, pawn, pawn, pawn }, // row 0
                { pawn2, pawn2, pawn, pawn, pawn }, // row 1
                { pawn2, pawn2, pawn, pawn, null }, // row 2
                { pawn2, pawn2, pawn, pawn, pawn }, // row 3
                { pawn2, pawn2, pawn, pawn, pawn }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 14, board.getNbPawns(player1));
        assertEquals(board.toString(), 10, board.getNbPawns(player2));
    }

    @Test
    public void getmove_is_optimum_move_with_Ten_Pawns_and_fiveteen_pawns_other_player_with_IAlevel2_should_pawns_equals_15() {
        MinMax myRobot = new MinMax(2);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn2, pawn2, pawn, pawn, pawn }, // row 0
                { pawn2, pawn2, pawn, pawn, pawn }, // row 1
                { pawn2, pawn2, pawn, pawn, null }, // row 2
                { pawn2, pawn2, pawn, pawn, pawn }, // row 3
                { pawn2, pawn2, pawn, pawn, pawn }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 15, board.getNbPawns(player1));
        assertEquals(board.toString(), 10, board.getNbPawns(player2));
    }

    @Test
    public void getmove_is_optimum_move_with_Ten_Pawns_and_fiveteen_pawns_other_player_with_IAlevel1_should_pawns_equals_15() {
        MinMax myRobot = new MinMax(1);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn2, pawn2, pawn, pawn, pawn }, // row 0
                { pawn2, pawn2, pawn, pawn, pawn }, // row 1
                { pawn2, pawn2, pawn, pawn, null }, // row 2
                { pawn2, pawn2, pawn, pawn, pawn }, // row 3
                { pawn2, pawn2, pawn, pawn, pawn }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 15, board.getNbPawns(player1));
        assertEquals(board.toString(), 10, board.getNbPawns(player2));
    }

    @Test
    public void getmove_is_optimum_move_with_six_Pawsn_and_three_pawns_other_player_with_IAlevel2() {
        MinMax myRobot = new MinMax(2);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { null, pawn2, null, pawn2, null }, // row 0
                { null, null, null, null, null }, // row 1
                { pawn, pawn, null, null, null }, // row 2
                { pawn2, null, pawn, null, null }, // row 3
                { pawn, pawn, pawn, null, null }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        board.movePawn(move);
        assertEquals(board.toString(), 8, board.getNbPawns(player1));
        assertEquals(board.toString(), 2, board.getNbPawns(player2));
    }

    @Test
    public void getmove_does_not_throw_exception_with_two_Pawns_and_three_pawns_other_player_with_IAlevel4() {
        MinMax myRobot = new MinMax(4);
        Player player1 = new Player(myRobot, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn pawn = new Pawn(player1);
        Pawn pawn2 = new Pawn(player2);

        Pawn[][] field = { // field :
                { pawn2, null, null, null, null }, // row 0
                { null, pawn, null, null, null }, // row 1
                { null, null, pawn2, pawn2, null }, // row 2
                { null, null, null, null, null }, // row 3
                { pawn, null, null, null, null }, // row 4
        };
        Board board = new Board(field);
        Move move = myRobot.getMove(board, player1);
        assertTrue("move non valide :", board.isValid(move, player1));
        assertAll(() -> {
            board.movePawn(move);
        });
    }

    @Test(timeout = 30_000)
    public void test_MinMax_IAlevel4_30s() {
        Game game = new Game(5, new MinMax(4), null, new MinMax(4), null);
        game.run(1);
    }
}