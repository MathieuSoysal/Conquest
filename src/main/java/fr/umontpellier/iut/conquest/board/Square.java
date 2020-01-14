package fr.umontpellier.iut.conquest.board;

import java.util.Objects;

public class Square {
    private final int row;
    private final int column;

    /**
     * @param row
     * @param column
     */
    public Square(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    // #region equals and hashCode
    /**
     * Un coup est identifié par ses attributs.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Square square = (Square) o;
        return row == square.getRow() && column == square.getColumn();
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
    // #endregion equals and hashCode
}