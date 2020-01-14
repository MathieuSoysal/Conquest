package fr.umontpellier.iut.conquest.strategies;

import fr.umontpellier.iut.conquest.Board;
import fr.umontpellier.iut.conquest.Move;
import fr.umontpellier.iut.conquest.Pawn;
import fr.umontpellier.iut.conquest.Player;
import fr.umontpellier.iut.conquest.board.memento.BoardMemento;

class Speculation implements Strategy {

    private Board board;

    private Player player1;
    private Player player2;

    private int maxNbPawns;

    // main qui permet de simuler le cout de Speculation selon la disposition du plateau
    public static void main(String[] args) {
        Speculation speculation = new Speculation();

        Player firstPlayer = new Player(null, null, null, 1);
        Player secondPlayer = new Player(null, null, null, 2);

        // pion de Speculation
        Pawn pawnP1 = new Pawn(firstPlayer);

        // pion appartenant à l'adversaire de Speculation
        Pawn pawnP2 = new Pawn(secondPlayer);

        Pawn[][] field = { // disposition du plateau :
                { null, null, null, null, null }, // row : 0
                { null, null, null, null, null }, // row : 1
                { null, null, pawnP1, null, null }, // row : 2
                { null, null, null, null, null }, // row : 3
                { null, null, null, null, null }, // row : 4
        };
        Board board = new Board(field);
        Move move = speculation.getMove(board, firstPlayer);

        if (board.isValid(move, firstPlayer))
            board.movePawn(move);
        System.out.println(board);
    }

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