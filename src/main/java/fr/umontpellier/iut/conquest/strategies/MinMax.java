package fr.umontpellier.iut.conquest.strategies;

//TODO Oublie pas de néttoyer la class
import java.util.List;

import fr.umontpellier.iut.conquest.Player;
import fr.umontpellier.iut.conquest.board.Board;
import fr.umontpellier.iut.conquest.board.Move;
import fr.umontpellier.iut.conquest.board.memento.BoardMemento;

public class MinMax implements Strategy {

    private Speculation speculation = new Speculation();
    private int anticipatedTurn;
    private Move maxMove = null;
    private Move chosenMove;
    private int maxRateOfPawns;
    private int nbTurnForMax;
    private Player player1;
    private Player player2;
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
        nbTurnForMax = Integer.MAX_VALUE;
        for (Move move : board.getValidMoves(player1)) {
            chosenMove = move;
            board.movePawn(move);
            findAndActualiseMaxMove(1, board.saveToMemento());
            board.undoFromMemento(initialBoard);
        }
    }

    private void findAndActualiseMaxMove(int turn, BoardMemento memento) {
        if (turn == anticipatedTurn)
            recordChosenMoveIfIsMaxMove(turn);
        else {
            if (turn % 2 == 1) {
                findMinMoveInMementoAndContinuefindAndActualiseMaxMove(turn, memento);
            } else {
                List<Move> validMoves = board.getValidMoves(player1);
                if (!validMoves.isEmpty()) {
                    findMaxMoveInMementoAndContinuefindAndActualiseMaxMove(turn, memento, validMoves);
                } else
                    recordChosenMoveIfIsMaxMove(turn);
            }
        }
    }

    private void findMaxMoveInMementoAndContinuefindAndActualiseMaxMove(int turn, BoardMemento memento,
            List<Move> validMoves) {
        for (Move move : validMoves) {
            board.movePawn(move);
            findAndActualiseMaxMove(turn + 1, board.saveToMemento());
            board.undoFromMemento(memento);
        }
    }

    private void findMinMoveInMementoAndContinuefindAndActualiseMaxMove(int turn, BoardMemento memento) {
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

    private void recordChosenMoveIfIsMaxMove(int turn) {
        if (nbPawnsIsMax() || nbTurnIsMax(turn)) {
            maxMove = chosenMove;
            nbTurnForMax = turn;
            maxRateOfPawns = getRateOfPawns();
        }
    }

    private boolean nbTurnIsMax(int turn) {
        return (getRateOfPawns() == maxRateOfPawns) && (turn < nbTurnForMax);
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
        this.anticipatedTurn = IAlevel;
    }

}
