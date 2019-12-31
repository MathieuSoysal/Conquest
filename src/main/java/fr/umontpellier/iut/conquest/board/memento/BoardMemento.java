package fr.umontpellier.iut.conquest.board.memento;

import java.util.Arrays;

import fr.umontpellier.iut.conquest.Pawn;

public class BoardMemento {
    private Pawn[][] field;

    /**
     * @param field
     */
    public BoardMemento(Pawn[][] field) {
        this.field = field;
    }

    /**
     * @return the field
     */
    public Pawn[][] getField() {
        return field;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "BoardMemento [field=" + Arrays.toString(field) + "]";
    }


    
}