package fr.umontpellier.iut.conquest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board b;
    private Player player1 = new Player(null, null, null, 1);
    private Player player2 = new Player(null, null, null, 2);

    @BeforeEach
    void disableConsole() {
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int arg0) {

            }
        }));

    }

    @BeforeEach
    void create_board() {
        b = new Board(5);
        b.initField(player1, player2);
    }

    @Test
    void at_the_start_top_left_pawn_should_belong_to_player1() {
        assertEquals(1, b.getField()[0][0].getPlayer().getColor());
    }

    @Test
    void at_the_start_bottom_right_pawn_should_belong_to_player1() {
        assertEquals(1, b.getField()[b.getSize() - 1][b.getSize() - 1].getPlayer().getColor());
    }

    @Test
    void at_the_start_top_right_pawn_should_belong_to_player2() {
        assertEquals(2, b.getField()[0][b.getSize() - 1].getPlayer().getColor());
    }

    @Test
    void at_the_start_bottom_right_pawn_should_belong_to_player2() {
        assertEquals(2, b.getField()[b.getSize() - 1][0].getPlayer().getColor());
    }

    @Test
    void at_the_start_every_pawn_must_be_free_except_for_corner_pawns() {
        Pawn[][] tab = b.getField();
        for (int i = 0; i < b.getSize(); i++) {
            for (int j = 0; j < b.getSize(); j++) {
                if (!(i == 0 && (j == 0 || j == b.getSize() - 1))
                        && !(i == b.getSize() - 1 && (j == 0 || j == b.getSize() - 1))) {
                    assertNull(tab[i][j]);
                }
            }
        }
    }

    @Test
    void a_move_that_starts_from_a_negative_row_should_be_invalid() {
        assertFalse(b.isValid(new Move(-1, 0, 0, 1), player1));
    }

    @Test
    void a_move_that_starts_from_a_negative_column_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, -1, 0, 1), player1));
    }

    @Test
    void a_move_that_starts_from_too_large_row_should_be_invalid() {
        assertFalse(b.isValid(new Move(b.getSize(), 0, 0, 1), player1));
    }

    @Test
    void a_move_that_starts_from_too_large_column_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, b.getSize(), 0, 1), player1));
    }

    @Test
    void a_move_that_ends_in_a_negative_row_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, 0, -1, 1), player1));
    }

    @Test
    void a_move_that_ends_in_a_negative_column_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, 0, 0, -1), player1));
    }

    @Test
    void a_move_that_ends_in_a_too_large_row_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, 0, b.getSize(), 1), player1));
    }

    @Test
    void a_move_that_ends_in_a_too_large_column_should_be_invalid() {
        assertFalse(b.isValid(new Move(0, 0, 0, b.getSize()), player1));
    }

    @Test
    void player1_should_not_be_able_to_move_a_pawn_that_does_not_exist() {
        assertFalse(b.isValid(new Move(0, 1, 0, 2), player1));
    }

    @Test
    void player2_should_not_be_able_to_move_a_pawn_that_does_not_exist() {
        assertFalse(b.isValid(new Move(0, 1, 0, 2), player2));
    }

    @Test
    void player2_should_not_be_able_to_move_a_pawn_from_player1() {
        assertFalse(b.isValid(new Move(0, 0, 0, 1), player2));
    }

    @Test
    void player1_should_not_be_able_to_move_a_pawn_from_player2() {
        assertFalse(b.isValid(new Move(0, b.getSize() - 1, 0, b.getSize() - 2), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_to_a_free_cell() {
        assertTrue(b.isValid(new Move(0, 0, 0, 1), player1));
    }

    @Test
    void a_pawn_should_not_be_able_to_move_to_an_occupied_cell() {
        b.movePawn(new Move(0, 0, 0, 1));
        assertFalse(b.isValid(new Move(0, 0, 0, 1), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_one_cell_right() {
        assertTrue(b.isValid(new Move(0, 0, 0, 1), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_one_cell_left() {
        assertTrue(b.isValid(new Move(0, b.getSize() - 1, 0, b.getSize() - 2), player2));
    }

    @Test
    void a_pawn_should_be_able_to_move_one_cell_up() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 1), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_one_cell_down() {
        assertTrue(b.isValid(new Move(0, 0, 1, 0), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_one_cell_up_left() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 2), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_one_cell_up_right() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, 0, b.getSize() - 2, 1), player2));
    }

    @Test
    void a_pawn_should_be_able_to_move_one_cell_down_left() {
        assertTrue(b.isValid(new Move(0, b.getSize() - 1, 1, b.getSize() - 2), player2));
    }

    @Test
    void a_pawn_should_be_able_to_move_one_cell_down_right() {
        assertTrue(b.isValid(new Move(0, 0, 1, 1), player1));
    }

    @Test
    void a_pawn_that_moved_one_cell_right_should_clone_itself() {
        b.movePawn(new Move(0, 0, 0, 1));
        assertEquals(1, b.getField()[0][0].getPlayer().getColor());
    }

    @Test
    void a_pawn_that_moved_one_cell_left_should_clone_itself() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 1, b.getSize() - 2));
        assertEquals(1, b.getField()[b.getSize() - 1][b.getSize() - 1].getPlayer().getColor());
    }

    @Test
    void a_pawn_that_moved_one_cell_up_should_clone_itself() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 1));
        assertEquals(1, b.getField()[b.getSize() - 1][b.getSize() - 1].getPlayer().getColor());
    }

    @Test
    void a_pawn_that_moved_one_cell_down_should_clone_itself() {
        b.movePawn(new Move(0, 0, 1, 0));
        assertEquals(1, b.getField()[0][0].getPlayer().getColor());
    }

    @Test
    void a_pawn_that_moved_one_cell_up_right_should_clone_itself() {
        b.movePawn(new Move(b.getSize() - 1, 0, b.getSize() - 2, 1));
        assertEquals(2, b.getField()[b.getSize() - 1][0].getPlayer().getColor());
    }

    @Test
    void a_pawn_that_moved_one_cell_up_left_should_clone_itself() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 2));
        assertEquals(1, b.getField()[b.getSize() - 1][b.getSize() - 1].getPlayer().getColor());
    }

    @Test
    void a_pawn_that_moved_one_cell_down_right_should_clone_itself() {
        b.movePawn(new Move(0, 0, 1, 1));
        assertEquals(1, b.getField()[0][0].getPlayer().getColor());
    }

    @Test
    void a_pawn_that_moved_one_cell_down_left_should_clone_itself() {
        b.movePawn(new Move(0, b.getSize() - 1, 1, b.getSize() - 2));
        assertEquals(2, b.getField()[0][b.getSize() - 1].getPlayer().getColor());
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_right() {
        assertTrue(b.isValid(new Move(0, 0, 0, 2), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_left() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 1, b.getSize() - 3), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_up() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 1), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_down() {
        assertTrue(b.isValid(new Move(0, 0, 2, 0), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_up_left() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 3), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_up_right() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, 0, b.getSize() - 3, 2), player2));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_down_right() {
        assertTrue(b.isValid(new Move(0, 0, 2, 2), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_down_left() {
        assertTrue(b.isValid(new Move(0, b.getSize() - 1, 2, b.getSize() - 3), player2));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_up_then_one_cell_left() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 2), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_up_then_one_cell_right() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, 0, b.getSize() - 3, 1), player2));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_down_then_one_cell_right() {
        assertTrue(b.isValid(new Move(0, 0, 2, 1), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_down_then_one_cell_left() {
        assertTrue(b.isValid(new Move(0, b.getSize() - 1, 2, b.getSize() - 2), player2));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_right_then_one_cell_down() {
        assertTrue(b.isValid(new Move(0, 0, 1, 2), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_right_then_one_cell_up() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, 0, b.getSize() - 2, 2), player2));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_left_then_one_cell_up() {
        assertTrue(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 3), player1));
    }

    @Test
    void a_pawn_should_be_able_to_move_two_cells_left_then_one_cell_down() {
        assertTrue(b.isValid(new Move(0, b.getSize() - 1, 1, b.getSize() - 3), player2));
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_right_should_be_free() {
        b.movePawn(new Move(0, 0, 0, 2));
        assertNull(b.getField()[0][0]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_left_should_be_free() {
        b.movePawn(new Move(0, b.getSize() - 1, 0, b.getSize() - 3));
        assertNull(b.getField()[0][b.getSize() - 1]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_down_should_be_free() {
        b.movePawn(new Move(0, 0, 2, 0));
        assertNull(b.getField()[0][0]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_up_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 1));
        assertNull(b.getField()[b.getSize() - 1][b.getSize() - 1]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_up_left_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 3));
        assertNull(b.getField()[b.getSize() - 1][b.getSize() - 1]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_up_right_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, 0, b.getSize() - 3, 2));
        assertNull(b.getField()[b.getSize() - 1][0]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_down_left_should_be_free() {
        b.movePawn(new Move(0, b.getSize() - 1, 2, b.getSize() - 3));
        assertNull(b.getField()[0][b.getSize() - 1]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_down_right_should_be_free() {
        b.movePawn(new Move(0, 0, 2, 2));
        assertNull(b.getField()[0][0]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_left_then_one_cell_down_should_be_free() {
        b.movePawn(new Move(0, b.getSize() - 1, 1, b.getSize() - 3));
        assertNull(b.getField()[0][b.getSize() - 1]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_left_then_one_cell_up_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 2, b.getSize() - 3));
        assertNull(b.getField()[b.getSize() - 1][b.getSize() - 1]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_right_then_one_cell_down_should_be_free() {
        b.movePawn(new Move(0, 0, 1, 2));
        assertNull(b.getField()[0][0]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_right_then_one_cell_up_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, 0, b.getSize() - 2, 2));
        assertNull(b.getField()[b.getSize() - 1][0]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_up_then_one_cell_left_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 3, b.getSize() - 2));
        assertNull(b.getField()[b.getSize() - 1][b.getSize() - 1]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_up_then_one_cell_right_should_be_free() {
        b.movePawn(new Move(b.getSize() - 1, 0, b.getSize() - 3, 1));
        assertNull(b.getField()[b.getSize() - 1][0]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_down_then_one_cell_left_should_be_free() {
        b.movePawn(new Move(0, b.getSize() - 1, 2, b.getSize() - 2));
        assertNull(b.getField()[0][b.getSize() - 1]);
    }

    @Test
    void the_starting_cell_of_a_pawn_that_moved_two_cells_down_then_one_cell_right_should_be_free() {
        b.movePawn(new Move(0, 0, 2, 1));
        assertNull(b.getField()[0][0]);
    }

    @Test
    void a_pawn_should_not_be_able_to_move_too_far_right() {
        assertFalse(b.isValid(new Move(0, 0, 0, 3), player1));
    }

    @Test
    void a_pawn_should_not_be_able_to_move_too_far_left() {
        assertFalse(b.isValid(new Move(0, b.getSize() - 1, 0, b.getSize() - 4), player2));
    }

    @Test
    void a_pawn_should_not_be_able_to_move_too_far_up() {
        assertFalse(b.isValid(new Move(b.getSize() - 1, b.getSize() - 1, b.getSize() - 4, b.getSize() - 1), player2));
    }

    @Test
    void a_pawn_should_not_be_able_to_move_too_far_down() {
        assertFalse(b.isValid(new Move(0, 0, 3, 0), player1));
    }

    @Test
    void after_a_move_an_adjacent_opposing_pawn_should_change_color() {
        b.movePawn(new Move(0, 0, 0, 2));
        b.movePawn(new Move(0, b.getSize() - 1, 0, b.getSize() - 2));
        assertEquals(2, b.getField()[0][2].getPlayer().getColor());
    }

    @Test
    void after_a_move_a_non_adjacent_opposing_pawn_should_not_change_color() {
        b.movePawn(new Move(0, 0, 0, 2));
        assertEquals(2, b.getField()[0][4].getPlayer().getColor());
    }

    @Test
    void after_a_move_an_adjacent_free_cell_should_stay_free() {
        b.movePawn(new Move(0, 0, 0, 2));
        assertNull(b.getField()[0][1]);
    }

    @Test
    void from_starting_position_on_a_board_of_size_3_player1_should_be_able_to_move_top_left_pawn_and_bottom_right_to_every_free_cell() {
        Board board = new Board(3);
        board.initField(player1, player2);
        List<Move> validMoves = board.getValidMoves(player1);
        System.out.println(validMoves.size());
        assertTrue("1 test : fails", validMoves.contains(new Move(0, 0, 0, 1)));
        assertTrue("2 test : fails", validMoves.contains(new Move(0, 0, 1, 0)));
        assertTrue("3 test : fails", validMoves.contains(new Move(0, 0, 1, 1)));
        assertTrue("4 test : fails", validMoves.contains(new Move(0, 0, 2, 1)));
        assertTrue("5 test : fails", validMoves.contains(new Move(0, 0, 1, 2)));
        assertTrue("6 test : fails", validMoves.contains(new Move(2, 2, 0, 1)));
        assertTrue("7 test : fails", validMoves.contains(new Move(2, 2, 1, 0)));
        assertTrue("8 test : fails", validMoves.contains(new Move(2, 2, 1, 1)));
        assertTrue("9 test : fails", validMoves.contains(new Move(2, 2, 2, 1)));
        assertTrue("10 test : fails", validMoves.contains(new Move(2, 2, 1, 2)));

        assertEquals("Size test : fails", validMoves.size(), 10);
    }

    @Test
    void when_the_pawns_of_the_player1_is_surrounded_by_pawns_of_player2() {
        b.movePawn(new Move(0, 4, 1, 4));
        b.movePawn(new Move(0, 4, 0, 3));
        b.movePawn(new Move(0, 4, 1, 3));
        b.movePawn(new Move(1, 3, 1, 2));
        b.movePawn(new Move(1, 3, 2, 3));
        b.movePawn(new Move(1, 3, 2, 2));
        b.movePawn(new Move(0, 0, 1, 0));
        b.movePawn(new Move(1, 0, 3, 0));
        b.movePawn(new Move(0, 0, 0, 2));
        b.movePawn(new Move(4, 4, 2, 4));
        b.movePawn(new Move(3, 0, 2, 1));

        // __0_1_2_3_4
        // 0|_ _ X X O
        // 1|_ _ X X X
        // 2|_ X X X X
        // 3|X _ _ _ _
        // 4|X _ _ _ _

        List<Move> validMoves = b.getValidMoves(player2);
        assertTrue(validMoves.isEmpty());
    }

    @Test
    void test_range_of_getValidesMoves_When_pawn_is_in_center() {
        Pawn pawn = new Pawn(player1);
        Pawn[][] field = { // field :
                { null, null, null, null, null, null, null }, // row 0
                { null, null, null, null, null, null, null }, // row 1
                { null, null, pawn, null, null, null, null }, // row 2
                { null, null, null, null, null, null, null }, // row 3
                { null, null, null, null, null, null, null }, // row 4
                { null, null, null, null, null, null, null }, // row 5
                { null, null, null, null, null, null, null }, // row 6
        };
        Board board = new Board(field);

        List<Move> validMoves = board.getValidMoves(player1);

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {

                boolean isSquareOfActualPawn = i == 2 && j == 2;
                boolean isInRange = i <= 4 && j <= 4;

                if (!isSquareOfActualPawn && isInRange)
                    assertTrue(" in range " + localization(i, j), validMoves.contains(new Move(2, 2, i, j)));
                else
                    assertFalse(" over range " + localization(i, j), validMoves.contains(new Move(2, 2, i, j)));
            }
        }

    }

    @Test
    void test_getValidesMoves_whith_just_1_case_empty() {
        Pawn pawn = new Pawn(player1);
        Pawn[][] field = { // field :
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 0
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 1
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 2
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 3
                { pawn, pawn, pawn, null, pawn, pawn, pawn }, // row 4
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 5
                { pawn, pawn, pawn, pawn, pawn, pawn, pawn }, // row 6
        };
        Board board = new Board(field);

        List<Move> validMoves = board.getValidMoves(player1);

        // Parcours toute les cases
        for (int startingRow = 0; startingRow < field.length; startingRow++) {
            for (int startingColulmn = 0; startingColulmn < field.length; startingColulmn++) {

                // Parcours toutes les cases
                for (int arrivalRow = 0; arrivalRow < field.length; arrivalRow++) {
                    for (int arrivalColumn = 0; arrivalColumn < field.length; arrivalColumn++) {

                        boolean isEmptyArrivalCase = (arrivalRow == 4 && arrivalColumn == 3);
                        boolean isNotEmptyStartingCase = (startingRow != 4 || startingColulmn != 3);
                        boolean isInRange = Math.abs(startingRow - 4) <= 2 && Math.abs(startingColulmn - 3) <= 2;

                        Move move = new Move(startingRow, startingColulmn, arrivalRow, arrivalColumn);
                        String localizations = localizations(startingRow, startingColulmn, arrivalRow, arrivalColumn);

                        if (isEmptyArrivalCase && isNotEmptyStartingCase && isInRange)
                            assertTrue(" excepted : in range ; but was : over range : \n" + localizations, validMoves.contains(move));
                        else {
                            assertFalse(" excepted : in over range ; but was : in range : \n" + localizations,
                                    validMoves.contains(move));
                        }
                    }
                }
            }
        }

    }

    private String localizations(int startingRow, int startingColulmn, int arrivalRow, int arrivalColumn) {
        String arrivalSquare = localization(arrivalRow, arrivalColumn);
        String startingSquare = localization(startingRow, startingColulmn);

        return String.format(" Starting square %s \n Arrival square %s", startingSquare, arrivalSquare);
    }

    private String localization(int row, int column) {
        return String.format(" localized in : Row %s Column %s ", row, column);
    }

    // @Disabled
    @Test
    void from_starting_position_after_player1_does_one_distance_1_move_and_one_distance_2_move_and_taking_an_opponent_pawn_player1_should_have_four_pawns_and_player2_should_have_1_pawn() {
        b.movePawn(new Move(0, 0, 0, 1));
        b.movePawn(new Move(0, 1, 0, 3));
        assertEquals(4, b.getNbPawns(player1));
        assertEquals(1, b.getNbPawns(player2));
    }

    @Test
    void run_around_the_board_with_a_pawn_player1_should_have_16_pawns() {
        b.movePawn(new Move(0, 0, 0, 1));
        b.movePawn(new Move(0, 1, 0, 2));
        b.movePawn(new Move(0, 2, 0, 3));
        b.movePawn(new Move(0, 3, 0, 4));

        b.movePawn(new Move(0, 4, 1, 4));
        b.movePawn(new Move(1, 4, 2, 4));
        b.movePawn(new Move(2, 4, 3, 4));
        b.movePawn(new Move(3, 4, 4, 4));

        b.movePawn(new Move(4, 4, 4, 3));
        b.movePawn(new Move(4, 3, 4, 2));
        b.movePawn(new Move(4, 2, 4, 1));
        b.movePawn(new Move(4, 1, 4, 0));

        b.movePawn(new Move(4, 0, 3, 0));
        b.movePawn(new Move(3, 0, 2, 0));
        b.movePawn(new Move(2, 0, 1, 0));

        assertEquals(16, b.getNbPawns(player1));
    }

    @Test
    void from_the_starting_position_after_player_1_has_made_a_distance_movement_1_and_a_distance_movement_2_and_taking_an_opponent_pawn_from_the_top_right_corner_and_performs_the_same_operation_towards_the_opponent_pawn_from_below_on_the_left_the_opponent_player2_should_have_0_pawn_and_player1_should_have_six_pawns() {
        b.movePawn(new Move(0, 0, 0, 1));
        b.movePawn(new Move(0, 1, 0, 3));
        b.movePawn(new Move(0, 0, 1, 0));
        b.movePawn(new Move(1, 0, 3, 0));
        assertEquals(6, b.getNbPawns(player1));
        assertEquals(0, b.getNbPawns(player2));
    }

    @Test
    void in_starting_player1_should_have_two_pawns_and_player2_should_have_two_pawns() {
        assertEquals(2, b.getNbPawns(player1));
        assertEquals(2, b.getNbPawns(player2));
    }

    @Test
    void the_payer1_forms_a_square_with_his_pawns_the_player2_places_one_in_the_center_of_the_square_all_the_pawns_of_the_square_must_belong_to_the_player2() {
        b.movePawn(new Move(0, 0, 0, 1));
        b.movePawn(new Move(0, 1, 0, 2));

        b.movePawn(new Move(0, 2, 1, 2));
        b.movePawn(new Move(1, 2, 2, 2));

        b.movePawn(new Move(0, 0, 1, 0));
        b.movePawn(new Move(1, 0, 2, 0));

        b.movePawn(new Move(2, 0, 2, 1));
        b.movePawn(new Move(2, 1, 2, 2));

        b.movePawn(new Move(2, 1, 3, 2));

        // premier plateau :
        // __0_1_2_3_4
        // 0|X X X _ O
        // 1|X _ X _ _
        // 2|X X X _ _
        // 3|_ _ X _ _
        // 4|O _ _ _ X
        assertEquals("First board test (player1) : fails", 10, b.getNbPawns(player1));
        assertEquals("First board test (player2) : fails", 2, b.getNbPawns(player2));

        b.movePawn(new Move(4, 0, 4, 2));

        // second plateau :
        // __0_1_2_3_4
        // 0|X X X _ O
        // 1|X _ X _ _
        // 2|X X X _ _
        // 3|_ _ O _ _
        // 4|_ _ O _ X

        assertEquals("Second board test (player1) : fails", 9, b.getNbPawns(player1));
        assertEquals("Second board test (player2) : fails", 3, b.getNbPawns(player2));

        b.movePawn(new Move(3, 2, 1, 1));

        // plateau résultante :
        // __0_1_2_3_4
        // 0|O O O _ O
        // 1|O O O _ _
        // 2|O O O _ _
        // 3|_ _ _ _ _
        // 4|_ _ O _ X

        assertEquals("final board test (player1) : fails", 11, b.getNbPawns(player2));
        assertEquals("final board test (player2) : fails", 1, b.getNbPawns(player1));
    }
}
