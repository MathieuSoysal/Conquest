
package fr.umontpellier.iut.conquest.board;

class AroundSquares {
    private int aroundSquareRow;
    private int aroundSquareColumn;

    private Square aroundSquare;

    private static final int MINIMUM_FIELD = 0;
    private final int MAXIMUM_FIELD;

    private final int MAX_COLUMN;
    private final int MAX_ROW;

    private final int RANGE;
    private final int MIN_ROW;

    /**
     * @param aroundSquare
     */
    public AroundSquares(Square centerSquare, final int RANGE, final int MAXIMUM_FIELD) {

        this.MAXIMUM_FIELD = MAXIMUM_FIELD;
        this.RANGE = RANGE;

        MIN_ROW = minimumCoordinate(centerSquare.getRow());
        aroundSquareRow = MIN_ROW;
        aroundSquareColumn = minimumCoordinate(centerSquare.getColumn());

        aroundSquare = new Square(aroundSquareRow, aroundSquareColumn);

        MAX_ROW = maximumCoordinate(centerSquare.getRow());
        MAX_COLUMN = maximumCoordinate(centerSquare.getColumn());
    }

    public boolean haseNext() {
        return (aroundSquareRow <= MAX_ROW);
    }

    public void next() {
        aroundSquareRow += ((aroundSquareColumn == MAX_COLUMN) ? 1 : 0);
        aroundSquareColumn = ((aroundSquareColumn + 1) % (MAX_COLUMN + 1) == MINIMUM_FIELD) ? MIN_ROW
                : (aroundSquareColumn + 1) % (MAX_COLUMN + 1);

        aroundSquare = new Square(aroundSquareRow, aroundSquareColumn);
    }

    private int minimumCoordinate(int coordinate) {
        int interval = coordinate - RANGE;
        return (interval < MINIMUM_FIELD) ? MINIMUM_FIELD : interval;
    }

    private int maximumCoordinate(int coordinate) {
        int interval = coordinate + RANGE;
        return (interval > MAXIMUM_FIELD) ? MAXIMUM_FIELD : interval;
    }

    /**
     * @return the aroundSquare
     */
    public Square getAroundSquare() {
        return aroundSquare;
    }
}