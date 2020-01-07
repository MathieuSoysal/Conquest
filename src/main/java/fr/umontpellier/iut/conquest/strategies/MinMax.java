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
    private Move moveOptimum = null;
    private Move chosenMove;
    private int maxNbPawns;
    private Player player1 = null;
    private Player player2 = null;
    private Board board;
    private int finishedTurn = Integer.MAX_VALUE;

    @Override
    public Move getMove(Board board, Player player) {
        this.player1 = player;
        this.board = board;

        initFields();
        findOptimumMove();

        return moveOptimum;
    }

    private void findOptimumMove() {
        BoardMemento initialBoard = board.saveToMemento();
        for (Move move : board.getValidMoves(player1)) {
            chosenMove = move;
            board.movePawn(move);
            findAndActualiseOptimumMove(1, board.saveToMemento());
            board.undoFromMemento(initialBoard);
        }
    }

    private void initFields() {
        this.finishedTurn = Integer.MAX_VALUE;
        this.player2 = new Player(null, null, null, 1 + (player1.getColor() % 2));
        this.maxNbPawns = Integer.MIN_VALUE;
    }

    private void findAndActualiseOptimumMove(int turn, BoardMemento memento) {
        if (turn < finishedTurn) {
            if (moveFinishedGameInThisTurn(turn))
                recordMoveWasFinishedGame(turn);
            else {
                if (turn == anticipation)
                    recordIfIsOptimumMove();
                else {
                    if (turn % 2 == 1) {
                        BoardMemento speculatedMemento = speculatesMoveOtherPlayer(memento);
                        findAndActualiseOptimumMove(turn + 1, speculatedMemento);
                        board.undoFromMemento(memento);
                    } else {
                        List<Move> validMoves = board.getValidMoves(player1);
                        if (!validMoves.isEmpty()) {
                            for (Move move : validMoves) {
                                board.movePawn(move);
                                findAndActualiseOptimumMove(turn + 1, board.saveToMemento());
                                board.undoFromMemento(memento);
                            }
                        } else
                            recordIfIsOptimumMove();
                    }
                }
            }
        }
    }

    private void recordMoveWasFinishedGame(int turn) {
        if (finishedTurn != turn) {
            finishedTurn = turn;
            moveOptimum = chosenMove;
            maxNbPawns = board.getNbPawns(player1);
        } else {
            recordIfIsOptimumMove();
        }
    }

    private BoardMemento speculatesMoveOtherPlayer(BoardMemento memento) {
        return speculation.getMove(memento, board, player2);
    }

    private void recordIfIsOptimumMove() {
        if (finishedTurn > 4 && nbPawnsIsOptimum()) {
            moveOptimum = chosenMove;
            maxNbPawns = board.getNbPawns(player1) - board.getNbPawns(player2);
        }
    }

    private boolean nbPawnsIsOptimum() {
        return (board.getNbPawns(player1) - board.getNbPawns(player2)) > maxNbPawns;
    }

    private boolean moveFinishedGameInThisTurn(int turn) {
        return (board.getNbPawns(player2) == 0) && turn <= finishedTurn;
    }

    /**
     * @param IAlevel Le niveau actuel de l'intelligence artificielle
     */
    public MinMax(int IAlevel) {
        this.anticipation = IAlevel;
    }

}
