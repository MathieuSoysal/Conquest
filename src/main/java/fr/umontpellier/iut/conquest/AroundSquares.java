
package fr.umontpellier.iut.conquest;

class AroundSquares {
    private int aroundSquareRow;
    private int aroundSquareColumn;

    private Square aroundSquare;

    private static final int MINIMUM_FIELD = 0;
    private int MAXIMUM_FIELD;

    private int MAX_COLUMN;
    private int MAX_ROW;

    private int RANGE;


    public static void main(String[] args) {
        for (AroundSquares aS = new AroundSquares(new Square(3, 3), 2, 4); aS.haseNext(); aS.next()) {
            System.out
                    .println("Row : " + aS.getAroundSquare().getRow() + " Column :" + aS.getAroundSquare().getColumn());
        }
    }

    /**
     * @param aroundSquare
     */
    public AroundSquares(Square centerSquare, final int RANGE, final int MAXIMUM_FIELD) {

        this.MAXIMUM_FIELD = MAXIMUM_FIELD;
        this.RANGE = RANGE;

        aroundSquareRow = minimumCoordinate(centerSquare.getRow());
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
        aroundSquareColumn = (aroundSquareColumn + 1) % ((MAX_COLUMN) + 1);

        aroundSquare.setRow(aroundSquareRow);
        aroundSquare.setColumn(aroundSquareColumn );
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
     * @return the aroundSquareRow
     */
    public int getAroundSquareRow() {
        return aroundSquareRow;
    }

    /**
     * @return the aroundSquareColumn
     */
    public int getAroundSquareColumn() {
        return aroundSquareColumn;
    }

    /**
     * @return the aroundSquare
     */
    public Square getAroundSquare() {
        return aroundSquare;
    }
}