package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Player;
import fr.umontpellier.iut.conquest.board.Board;
import fr.umontpellier.iut.conquest.board.Move;
import fr.umontpellier.iut.conquest.board.memento.BoardMemento;

public class MinMax implements Strategy {

    private int anticipation;
    private Move moveOptimum;
    private Move chosenMove;
    private int maxNbPawns;
    private Player player1 = null;
    private Player player2 = null;
    private Board board;

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
            getMove(anticipation, board.saveToMemento());
            board.undoFromMemento(initialBoard);
        }
        return moveOptimum;
    }

    public void getMove(int anticipation, BoardMemento memento) {
        if (anticipation == 0) {
            if ((board.getNbPawns(player1) - board.getNbPawns(player2)) > maxNbPawns) {
                moveOptimum = chosenMove;
                maxNbPawns = board.getNbPawns(player1);
            }
        } else {
            BoardMemento player2Memento = getMoveOtherPlayer(memento);
            if (player2Memento != memento) {
                board.undoFromMemento(player2Memento);
                for (Move move : board.getValidMoves(player1)) {
                    board.movePawn(move);
                    getMove(anticipation - 1, board.saveToMemento());
                    board.undoFromMemento(player2Memento);
                }
            } else {
                if ((board.getNbPawns(player1) - board.getNbPawns(player2)) > maxNbPawns) {
                    moveOptimum = chosenMove;
                    maxNbPawns = board.getNbPawns(player1);
                }
            }
        }
    }

    private BoardMemento getMoveOtherPlayer(BoardMemento memento) {
        int maxNbPawns2 = 0;
        BoardMemento optimumMemento = memento;

        board.undoFromMemento(memento);
        for (Move move : board.getValidMoves(player2)) {
            board.movePawn(move);
            if (board.getNbPawns(player2) > maxNbPawns2) {
                optimumMemento = board.saveToMemento();
                maxNbPawns2 = board.getNbPawns(player2);
            }
            board.undoFromMemento(memento);
        }
        return optimumMemento;
    }

    /**
     * @param anticipation
     */
    public MinMax(int anticipation) {
        this.anticipation = anticipation;
    }

}
