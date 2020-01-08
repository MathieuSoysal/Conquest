package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Player;
import fr.umontpellier.iut.conquest.board.Board;
import fr.umontpellier.iut.conquest.board.Move;
import fr.umontpellier.iut.conquest.board.memento.BoardMemento;

class Speculation implements Strategy {
    private BoardMemento initialMemento;

    @Override
    public Move getMove(Board board, Player player) {
        initialMemento = board.saveToMemento();
        Player player2 = new Player(null, null, null, 1 + (player.getColor() % 2));
        int maxNbPawns = 0;
        Move optimumMove = null;

        for (Move move : board.getValidMoves(player)) {
            board.movePawn(move);
            if ((board.getNbPawns(player) - board.getNbPawns(player2)) > maxNbPawns) {
                optimumMove = move;
                maxNbPawns = board.getNbPawns(player);
            }
            board.undoFromMemento(initialMemento);
        }
        return optimumMove;
    }

    BoardMemento getMove(BoardMemento memento, Board board, Player player) {
        BoardMemento optimimumMemento = memento;
        int maxNbPawns = 0;
        Move optimumMove = null;

        for (Move move : board.getValidMoves(player)) {
            board.movePawn(move);
            if (board.getNbPawns(player) > maxNbPawns) {
                optimumMove = move;
                maxNbPawns = board.getNbPawns(player);
            }
            board.undoFromMemento(memento);
        }

        if (optimumMove != null) {
            board.movePawn(optimumMove);
            optimimumMemento = board.saveToMemento();
        }

        return optimimumMemento;
    }

}