package fr.umontpellier.iut.conquest.strategies;

import java.util.List;

import fr.umontpellier.iut.conquest.board.Board;
import fr.umontpellier.iut.conquest.board.Move;
import fr.umontpellier.iut.conquest.Player;

public class Naive implements Strategy {

    @Override
    public Move getMove(Board board, Player player) {
        List<Move> validMoves = board.getValidMoves(player);
        if (!validMoves.isEmpty()) {
            int randomIndex = (int) (Math.random() * (validMoves.size() - 1));
            return validMoves.get(randomIndex);
        }
        return null;
    }

}