package fr.umontpellier.iut.conquest.strategies;

//TODO Oublie pas de néttoyer la class
import java.util.List;

import fr.umontpellier.iut.conquest.Player;
import fr.umontpellier.iut.conquest.board.Board;
import fr.umontpellier.iut.conquest.board.Move;
import fr.umontpellier.iut.conquest.board.memento.BoardMemento;

public class MinMax implements Strategy {

    private Speculation speculation = new Speculation();
    private int anticipation;
    private Move moveOptimum;
    private Move chosenMove;
    private int maxNbPawns;
    private Player player1 = null;
    private Player player2 = null;
    private Board board;
    private int finishedTurn = Integer.MAX_VALUE;

    @Override
    public Move getMove(Board board, Player player) {
        this.player1 = player;
        this.player2 = new Player(null, null, null, 1 + (player.getColor() % 2));
        this.board = board;
        this.maxNbPawns = Integer.MIN_VALUE;
        BoardMemento initialBoard = board.saveToMemento();
        for (Move move : board.getValidMoves(player)) {
            chosenMove = move;
            board.movePawn(move);
            getMove(0, board.saveToMemento());
            board.undoFromMemento(initialBoard);
        }
        return moveOptimum;
    }

    public void getMove(int turn, BoardMemento memento) {
        if (gameIsFinishedInTurn(turn)) {
            if (NbPawnsIsOptimum(turn)) {
                moveOptimum = chosenMove;
                maxNbPawns = board.getNbPawns(player1) - board.getNbPawns(player2);
            }
        } else {
            if (turn != anticipation) {
                if (NbPawnsIsOptimum(turn)) {
                    moveOptimum = chosenMove;
                    maxNbPawns = board.getNbPawns(player1) - board.getNbPawns(player2);
                }
            } else {
                BoardMemento player2Memento = speculation.getMove(memento, board, player2);
                board.undoFromMemento(player2Memento);
                List<Move> validMoves = board.getValidMoves(player1);
                if (!validMoves.isEmpty())
                    for (Move move : validMoves) {
                        board.movePawn(move);
                        if (turn < finishedTurn)
                            getMove(turn + 1, board.saveToMemento());
                        board.undoFromMemento(player2Memento);
                    }
                else {
                    if (NbPawnsIsOptimum(turn)) {
                        moveOptimum = chosenMove;
                        maxNbPawns = board.getNbPawns(player1) - board.getNbPawns(player2);
                    }
                }
            }
        }
    }

    private boolean NbPawnsIsOptimum(int turn) {
        boolean NbPawnsIsOptimum = (board.getNbPawns(player1) - board.getNbPawns(player2)) > maxNbPawns;
        return NbPawnsIsOptimum;
    }

    private boolean gameIsFinishedInTurn(int turn) {
        return (board.getNbPawns(player2) == 0) && turn <= finishedTurn;
    }

    /**
     * @param anticipation
     */
    public MinMax(int anticipation) {
        this.anticipation = anticipation;
    }

}
