package fr.umontpellier.iut.conquest.board.memento;

import java.util.Arrays;

import fr.umontpellier.iut.conquest.Pawn;

public class BoardMemento {
    private Pawn[][] field;

    /**
     * @param field
     */
    public BoardMemento(Pawn[][] field) {
        this.field = new Pawn[field.length][field.length];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                this.field[i][j] = (field[i][j] == null) ? null : new Pawn(field[i][j].getPlayer());
            }
        }
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