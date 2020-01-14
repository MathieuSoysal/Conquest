package fr.umontpellier.iut.conquest.board.memento;

import fr.umontpellier.iut.conquest.Pawn;

public class BoardMemento {
    private Pawn[][] field;

    /**
     * @param field
     */
    public BoardMemento(Pawn[][] field) {
        this.field = deepCopy(field);
    }

    private Pawn[][] deepCopy(Pawn[][] fieldSource) {
        Pawn[][] fieldDestination = new Pawn[fieldSource.length][fieldSource.length];
        for (int i = 0; i < fieldSource.length; i++) {
            for (int j = 0; j < fieldSource.length; j++) {
                fieldDestination[i][j] = (fieldSource[i][j] == null) ? null : new Pawn(fieldSource[i][j].getPlayer());
            }
        }
        return fieldDestination;
    }

    /**
     * @return the field
     */
    public Pawn[][] getField() {
        return deepCopy(field);
    }
}