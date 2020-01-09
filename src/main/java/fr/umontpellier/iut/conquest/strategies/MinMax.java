package fr.umontpellier.iut.conquest.strategies;

//TODO Oublie pas de néttoyer la class
import java.util.List;

import fr.umontpellier.iut.conquest.Player;
import fr.umontpellier.iut.conquest.board.Board;
import fr.umontpellier.iut.conquest.board.Move;
import fr.umontpellier.iut.conquest.board.memento.BoardMemento;

public class MinMax implements Strategy {

    private Speculation speculation = new Speculation();
    private int NbOfAnticipeTurn;
    private Move maxMove = null;
    private Move chosenMove;
    private int maxRateOfPawns;
    private Player player1 = null;
    private Player player2 = null;
    private Board board;

    @Override
    public Move getMove(Board board, Player player) {
        this.player1 = player;
        this.board = board;
        this.player2 = getOtherPlayer();
        findMaxMove();
        return maxMove;
    }

    private void findMaxMove() {
        BoardMemento initialBoard = board.saveToMemento();
        maxRateOfPawns = Integer.MIN_VALUE;
        for (Move move : board.getValidMoves(player1)) {
            chosenMove = move;
            board.movePawn(move);
            findAndActualiseMaxMove(1, board.saveToMemento());
            board.undoFromMemento(initialBoard);
        }
    }

    private void findAndActualiseMaxMove(int turn, BoardMemento memento) {
        if (turn == NbOfAnticipeTurn)
            recordChosenMoveIfIsMaxMove();
        else {
            if (turn % 2 == 1) {
                findMinMoveInMemento(turn, memento);
            } else {
                List<Move> validMoves = board.getValidMoves(player1);
                if (!validMoves.isEmpty()) {
                    findMaxMoveInMemento(turn, memento, validMoves);
                } else
                    recordChosenMoveIfIsMaxMove();
            }
        }
    }

    private void findMaxMoveInMemento(int turn, BoardMemento memento, List<Move> validMoves) {
        for (Move move : validMoves) {
            board.movePawn(move);
            findAndActualiseMaxMove(turn + 1, board.saveToMemento());
            board.undoFromMemento(memento);
        }
    }

    private void findMinMoveInMemento(int turn, BoardMemento memento) {
        BoardMemento speculatedMemento = speculatesMoveOtherPlayer(memento);
        findAndActualiseMaxMove(turn + 1, speculatedMemento);
        board.undoFromMemento(memento);
    }

    private BoardMemento speculatesMoveOtherPlayer(BoardMemento memento) {
        return speculation.getMemento(memento, board, player2);
    }

    private Player getOtherPlayer() {
        return new Player(null, null, null, 1 + (player1.getColor() % 2));
    }

    private void recordChosenMoveIfIsMaxMove() {
        if (nbPawnsIsMax()) {
            maxMove = chosenMove;
            maxRateOfPawns = getRateOfPawns();
        }
    }

    private int getRateOfPawns() {
        return board.getNbPawns(player1) - board.getNbPawns(player2);
    }

    private boolean nbPawnsIsMax() {
        return getRateOfPawns() > maxRateOfPawns;
    }

    /**
     * @param IAlevel Le niveau actuel de l'intelligence artificielle
     */
    public MinMax(int IAlevel) {
        this.NbOfAnticipeTurn = IAlevel;
    }

}
