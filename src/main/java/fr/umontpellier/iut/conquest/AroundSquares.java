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

    private int incr = 0;

    /**
     * @param aroundSquare
     */
    public AroundSquares(Square centerSquare,final int RANGE, final int MAXIMUM_FIELD) {
        this.MAXIMUM_FIELD = MAXIMUM_FIELD; 
        this.RANGE = RANGE;
        
        aroundSquareRow = minimumCoordinate(centerSquare.getRow());
        aroundSquareColumn = minimumCoordinate(centerSquare.getColumn());

        aroundSquare = new Square(aroundSquareRow, aroundSquareColumn);

        MAX_ROW = maximumCoordinate(aroundSquareRow);
        MAX_COLUMN = maximumCoordinate(aroundSquareColumn);
    }

    public boolean haseNext(){
        return (((aroundSquareColumn + incr) < MAX_COLUMN) || (aroundSquareRow++ <= MAX_ROW ));
    }

    public void next(){
        incr = (incr+1)%(RANGE+1);
        aroundSquare.setRow(aroundSquareRow%(MAX_ROW+1));
        aroundSquare.setColumn(aroundSquareColumn + incr);
    }

    private int minimumCoordinate(int coordinate){
        int interval = coordinate - RANGE;
        return (interval < MINIMUM_FIELD)?MINIMUM_FIELD:interval;
    }

    private int maximumCoordinate(int coordinate){
        int interval = coordinate + RANGE;
        return (interval > MAXIMUM_FIELD)?MAXIMUM_FIELD:interval;
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