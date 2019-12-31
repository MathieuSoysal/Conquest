package fr.umontpellier.iut.conquest;

import java.util.Arrays;

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