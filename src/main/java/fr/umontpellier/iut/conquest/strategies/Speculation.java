package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Player;
import fr.umontpellier.iut.conquest.board.Board;
import fr.umontpellier.iut.conquest.board.Move;
import fr.umontpellier.iut.conquest.board.memento.BoardMemento;

class Speculation implements Strategy {

    private Board board;

    private Player player1;
    private Player player2;

    private int maxNbPawns;

    @Override
    public Move getMove(Board board, Player player) {
        init(board, player);
        return getMaxMoveIntoMemento(board.saveToMemento());
    }

    BoardMemento getMemento(BoardMemento memento, Board board, Player player) {
        BoardMemento optimimumMemento = memento;
        init(board, player);
        Move maxMove = getMaxMoveIntoMemento(memento);
        if (maxMove != null) {
            board.movePawn(maxMove);
            optimimumMemento = board.saveToMemento();
        }
        return optimimumMemento;
    }

    private Move getMaxMoveIntoMemento(BoardMemento memento) {
        Move maxMove = null;
        maxNbPawns = Integer.MIN_VALUE;
        for (Move move : board.getValidMoves(player1)) {
            board.movePawn(move);
            if (isMax()) {
                maxMove = move;
                maxNbPawns = board.getNbPawns(player1) - board.getNbPawns(player2);
            }
            board.undoFromMemento(memento);
        }
        return maxMove;
    }

    private Player getOtherPlayer() {
        return new Player(null, null, null, 1 + (player1.getColor() % 2));
    }

    private boolean isMax() {
        return (board.getNbPawns(player1) - board.getNbPawns(player2)) > maxNbPawns;
    }

    private void init(Board board, Player player) {
        this.player1 = player;
        this.player2 = getOtherPlayer();
        this.board = board;
    }

}