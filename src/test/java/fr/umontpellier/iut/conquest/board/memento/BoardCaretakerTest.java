package fr.umontpellier.iut.conquest.board.memento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import fr.umontpellier.iut.conquest.Pawn;
import fr.umontpellier.iut.conquest.Player;
import fr.umontpellier.iut.conquest.board.Board;
import fr.umontpellier.iut.conquest.board.Move;

public class BoardCaretakerTest {
    private BoardCaretaker caretaker = new BoardCaretaker();
    private Board board = new Board(5);
    private Player player1 = new Player(null, null, null, 1);
    private Player player2 = new Player(null, null, null, 2);

    private Pawn pawnP1 = new Pawn(player1);
    private Pawn pawnP2 = new Pawn(player2);

    public static void main(String[] args) {
        // Déclaration :
        BoardCaretaker caretaker = new BoardCaretaker();
        Board board = new Board(5);
        Player player1 = new Player(null, null, null, 1);
        Player player2 = new Player(null, null, null, 2);

        board.initField(player1, player2);

        // On garde en souvenir l'état actuel de board
        BoardMemento memento = board.saveToMemento();
        caretaker.addMemento(memento);

        // On récupére le souvenir
        memento = caretaker.getMemento();

        // On modifie board
        board.movePawn(new Move(0, 0, 1, 0));

        // On crée une nouvelle board avec le souvenir
        Board boardInMemento = new Board(memento.getField());

        System.out.println(boardInMemento.toString());
    }

    @Before
    public void init(){
        caretaker = new BoardCaretaker();
        board.initField(player1, player2);
    }

    @Test
    public void init_test(){
        caretaker.addMemento(board.saveToMemento());
        Pawn [][] field = {//field :
                { pawnP1, null, null, null, pawnP2 }, // row 1
                { null, null, null, null, null }, // row 2
                { null, null, null, null, null }, // row 3
                { null, null, null, null, null }, // row 4
                { pawnP2, null, null, null, pawnP1 }// row 5
        };
        board.undoFromMemento(caretaker.getMemento());
        assertEquals(board.toString(), new Board(field).toString());
    }

    @Test
    public void one_undo_test(){
        BoardMemento memento = board.saveToMemento();
        caretaker.addMemento(memento);
        Pawn [][] field = {//field :
                { pawnP1, null, null, null, pawnP2 }, // row 1
                { null, null, null, null, null }, // row 2
                { null, null, null, null, null }, // row 3
                { null, null, null, null, null }, // row 4
                { pawnP2, null, null, null, pawnP1 }// row 5
        };
        board.movePawn(new Move(0,0,1,0));
        memento = caretaker.getMemento();
        board.undoFromMemento(memento);
        assertEquals(board.toString(), new Board(field).toString());
    }

    @Test
    public void undo_test_to_back_first_move_steps_by_steps() {
        BoardMemento memento = board.saveToMemento();
        caretaker.addMemento(memento);
        Pawn[][] fieldStep1 = { // field :
                { pawnP1, null, null, null, pawnP2 }, // row 0
                { null, null, null, null, null }, // row 1
                { null, null, null, null, null }, // row 2
                { null, null, null, null, null }, // row 3
                { pawnP2, null, null, null, pawnP1 }// row 4
        };

        board.movePawn(new Move(0, 0, 1, 0));
        memento = board.saveToMemento();
        caretaker.addMemento(memento);

        Pawn[][] fieldStep2 = { // field :
                { pawnP1, null, null, null, pawnP2 }, // row 0
                { pawnP1, null, null, null, null }, // row 1
                { null, null, null, null, null }, // row 2
                { null, null, null, null, null }, // row 3
                { pawnP2, null, null, null, pawnP1 }// row 4
        };

        board.movePawn(new Move(4, 4, 3, 4));
        memento = board.saveToMemento();
        caretaker.addMemento(memento);

        Pawn[][] fieldStep3 = { // field :
                { pawnP1, null, null, null, pawnP2 }, // row 0
                { pawnP1, null, null, null, null }, // row 1
                { null, null, null, null, null }, // row 2
                { null, null, null, null, pawnP1 }, // row 3
                { pawnP2, null, null, null, pawnP1 }// row 4
        };

        board.movePawn(new Move(4, 0, 3, 0));

        Pawn[][] fieldStep4 = { // field :
                { pawnP1, null, null, null, pawnP2 }, // row 0
                { pawnP1, null, null, null, null }, // row 1
                { null, null, null, null, null }, // row 2
                { pawnP2, null, null, null, pawnP1 }, // row 3
                { pawnP2, null, null, null, pawnP1 }// row 4
        };

        assertEquals(board.toString(), new Board(fieldStep4).toString());

        board.undoFromMemento(caretaker.getMemento());
        assertEquals("\n"+board.toString(),"\n"+ new Board(fieldStep3).toString());

        board.undoFromMemento(caretaker.getMemento());
        assertEquals("\n"+board.toString(),"\n"+ new Board(fieldStep2).toString());

        board.undoFromMemento(caretaker.getMemento());
        assertEquals("\n"+board.toString(),"\n"+ new Board(fieldStep1).toString());
    }
}