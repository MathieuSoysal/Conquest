package fr.umontpellier.iut.conquest;

import java.util.ArrayList;
import java.util.List;

/**
 * Modélise un plateau.
 */
public class Board {
    public static void main(String[] args) {
        Board b;
        Player player1 = new Player(null, null, null, 1);
        Player player2 = new Player(null, null, null, 2);
        Pawn[][] field = { // field :
                { null, null, null, null, null }, // row 0
                { null, null, null, null, null }, // row 1
                { null, null, new Pawn(player1), null, null }, // row 2
                { null, null, null, null, null }, // row 3
                { null, null, null, null, null }, // row 4
        };
        b = new Board(field);
        System.out.println(b.toString());
    }
    /**
     * Tableau des pions.
     */
    private Pawn[][] field;

    /**
     * Constructeur.
     *
     * @param size : la taille du plateau.
     */
    public Board(int size) {
        field = new Pawn[size][size];
    }

    /**
     * Constructeur pour Test.
     *
     * @param field : plateau prédéfini.
     */
    public Board(Pawn[][] field) {
        this.field = field;
    }

    /**
     * Les méthodes suivantes sont utilisées pour les tests automatiques. Il ne faut pas les utiliser.
     */
    public Pawn[][] getField() {
        return field;
    }

    /**
     * Retourne la taille du plateau.
     */
    public int getSize() {
        return field.length;
    }

    /**
     * Affiche le plateau.
     */
    public String toString() {
        int size = field.length;
        StringBuilder b = new StringBuilder();
        for (int r = -1; r < size; r++) {
            for (int c = -1; c < size; c++) {
                if (r == -1 && c == -1) {
                    b.append("_");
                } else if (r == -1) {
                    b.append("_").append(c);
                } else if (c == -1) {
                    b.append(r).append("|");
                } else if (field[r][c] == null) {
                    b.append("_ ");
                } else if (field[r][c].getPlayer().getColor() == 1) {
                    b.append("X ");
                } else {
                    b.append("O ");
                }
            }
            b.append("\n");
        }
        b.append("---");
        return b.toString();
    }

    /**
     * Initialise le plateau avec les pions de départ.
     * Rappel :
     * - player1 commence le jeu avec un pion en haut à gauche (0,0) et un pion en bas à droite.
     * - player2 commence le jeu avec un pion en haut à droite et un pion en bas à gauche.
     */
    public void initField(Player player1, Player player2) {
        initPawnsFirstPlayer(player1);
        initPawnsSecondPlayer(player2);
    }

    private void initPawnsFirstPlayer(Player player1) {
        field[0][0] = new Pawn(player1);
        field[field.length-1][field.length-1] = new Pawn(player1);
    }

    private void initPawnsSecondPlayer(Player player2) {
        field[0][field.length-1] = new Pawn(player2);
        field[field.length-1][0] = new Pawn(player2);
    }


    /**
     * Vérifie si un coup est valide.
     * Rappel :
     * - Les coordonnées du coup doivent être dans le plateau.
     * - Le pion bougé doit appartenir au joueur.
     * - La case d'arrivée doit être libre.
     * - La distance entre la case d'arrivée est au plus 2.
     */
    public boolean isValid(Move move, Player player) {
        Square startingSquare = new Square(move.getRow1(), move.getColumn1());
        Square arrivalSquare = new Square(move.getRow2(), move.getColumn2());

        return coordinatesIsIntoField(move) && isValidPlayer(startingSquare, player) && isValidArrivalSquare(arrivalSquare)
                && isValidDistance(move);
    }
    
    /**
     * Déplace un pion.
     *
     * @param move : un coup valide.
     *             Rappel :
     *             - Si le pion se déplace à distance 1 alors il se duplique pour remplir la case d'arrivée et la case de départ.
     *             - Si le pion se déplace à distance 2 alors il ne se duplique pas : la case de départ est maintenant vide et la case d'arrivée remplie.
     *             - Dans tous les cas, une fois que le pion est déplacé, tous les pions se trouvant dans les cases adjacentes à sa case d'arrivée prennent sa couleur.
     */
    public void movePawn(Move move) {
        int startingColumn = move.getColumn1();
        int startingRow = move.getRow1();

        Player actualPlayer = field[startingRow][startingColumn].getPlayer();

        Square arrivalSquare = new Square(move.getRow2(), move.getColumn2());

        // on remplie la case d'arrivée
        colorPawnOfSquare(actualPlayer, arrivalSquare);

        // on vide la case de départ s'il a fait une distance supérieur à 1
        if (!distanceIsRespected(move, 1))
            field[startingRow][startingColumn] = null;

        // on colorie les cases autour de la case d'arrivée
        colorAroundPawnsOfSquare(arrivalSquare);
    }

    /**
     * Retourne la liste de tous les coups valides de player.
     * S'il n'y a de coup valide, retourne une liste vide.
     */
    public List<Move> getValidMoves(Player player) {
        List<Move> validMoves = new ArrayList<>();

        // on parcourt toutes les cases
        for (int startingRow = 0; startingRow < field.length; startingRow++) {
            for (int startingColumn = 0; startingColumn < field.length; startingColumn++) {
                
                // on vérifie si c'est une case qui appartient au joeur actuel 
                if (isValidPlayer(field[startingRow][startingColumn], player))
                    appendValidMovesAroundStartingSquare(validMoves, new Square(startingRow, startingColumn));
            }
        }
        return validMoves;
    }

    /**
     * Retourne le nombre de pions d'un joueur.
     */
    public int getNbPawns(Player player) {
        int sumPawnsPlayer = 0;

        for(int i = 0; i < field.length; i++){
            for(int j = 0; j < field.length; j++) {
                if(isValidPlayer(field[i][j], player)) {
                    sumPawnsPlayer++;
                }
            }
        }
        return sumPawnsPlayer;
    }

    // #region Méthodes privé (boite à outils pour méthode public)

    // #region Outils méthode isValid

    // #region coordinatesIsIntoField
    private boolean coordinatesIsIntoField(Move move) {
        Square startingSquare = new Square(move.getRow1(), move.getColumn1());
        Square arrivalSquare = new Square(move.getRow2(), move.getColumn2());

        return isIntoField(startingSquare) && isIntoField(arrivalSquare);
    }

    private boolean isIntoField(Square square) {
        return isIntoField(square.getRow()) && isIntoField(square.getColumn());
    }

    private boolean isIntoField(int coordinate) {
        return 0 <= coordinate && coordinate < field.length;
    }
    // #endregion coordinatesIsIntoField

    // isValidPlayer
    private boolean isValidPlayer(Square startingSquare, Player player) {
        Pawn startingPawn = field[startingSquare.getRow()][startingSquare.getColumn()];

        return isValidPlayer(startingPawn, player);
    }

    // isValidArrivalSquare
    private boolean isValidArrivalSquare(Square arrivalSquare) {
        Pawn arrivalCase = field[arrivalSquare.getRow()][arrivalSquare.getColumn()];

        return arrivalCase == null;
    }

    // #region isValidDistance
    private boolean isValidDistance(Move move) {
        final int AUTHORIZED_DISTANCE = 2;
        return distanceIsRespected(move, AUTHORIZED_DISTANCE);
    }

    private boolean distanceIsRespected(Move move, final int AUTHORIZED_DISTANCE){
        int startingRow = move.getRow1();
        int arrivalRow = move.getRow2();
        int startingColumn = move.getColumn1();
        int arrivalColumn = move.getColumn2();

        return distanceIsRespected(AUTHORIZED_DISTANCE, startingRow, arrivalRow)
                && distanceIsRespected(AUTHORIZED_DISTANCE, startingColumn, arrivalColumn);
    }

    private boolean distanceIsRespected(final int AUTHORIZED_DISTANCE, int startingDistance, int arrivalDistance) {
        int distance = Math.abs(startingDistance - arrivalDistance);

        return distance <= AUTHORIZED_DISTANCE;
    }
    // #endregion isValidDistance
    // #endregion Outils méthode isValid

    // #region Outils méthode movePawn
    private void colorAroundPawnsOfSquare(Square arrivalSquare) {

        int arrivalRow = arrivalSquare.getRow();
        int arrivalColumn = arrivalSquare.getColumn();

        Player actualPlayer = field[arrivalRow][arrivalColumn].getPlayer();
        int playerColor = actualPlayer.getColor();

        final int MIN_COLUMN = minus1IntoField(arrivalColumn);
        final int MAX_COLUMN = plus1IntoField(arrivalColumn);

        final int MIN_ROW = minus1IntoField(arrivalRow);
        final int MAX_ROW = plus1IntoField(arrivalRow);

        // on parcours les cases autour de la case d'arrivée
        for (int aroundRow = MIN_ROW; aroundRow <= MAX_ROW; aroundRow++) {
            for (int aroundColumn = MIN_COLUMN; aroundColumn <= MAX_COLUMN; aroundColumn++) {

                // on vérifier si c'est une case qui peut être colorié
                if (canColorWithPlayerColor(playerColor, field[aroundRow][aroundColumn]))
                    colorPawnOfSquare(actualPlayer, new Square(aroundRow, aroundColumn));
            }
        }
    }

    private void colorPawnOfSquare(Player owner, Square aroundSquare) {
        field[aroundSquare.getRow()][aroundSquare.getColumn()] = new Pawn(owner);
    }

    private boolean canColorWithPlayerColor(int playerColor, Pawn targetedPawn) {
        return targetedPawn != null && targetedPawn.getPlayer().getColor() != playerColor;
    }

    private int plus1IntoField(int coordinate) {
        return plusIntoField(coordinate, 1);
    }

    private int minus1IntoField(int coordinate) {
        return minusIntoField(coordinate, 1);
    }
    // #endregion Outils méthode movePawn

    // #region Outils méthode getValidMoves
    private boolean isValidPlayer(Pawn startingPawn, Player player) {
        int playerColor = player.getColor();
        boolean pawnNotNull = startingPawn != null;

        return pawnNotNull && (startingPawn.getPlayer().getColor() == playerColor);
    }

    private void appendValidMovesAroundStartingSquare(List<Move> validMoves, Square startingSquare) {
        int startingRow = startingSquare.getRow();
        int startingColumn = startingSquare.getColumn();

        final int MIN_ROW = minus2IntoField(startingRow);
        final int MAX_ROW = plus2IntoField(startingRow);

        final int MIN_COLUMN = minus2IntoField(startingColumn);
        final int MAX_COLUMN = plus2IntoField(startingColumn);

        // on parcourt toutes les cases autour de la case d'arrivée
        for (int aroundRow = MIN_ROW; aroundRow <= MAX_ROW; aroundRow++) {
            for (int aroundColumn = MIN_COLUMN; aroundColumn <= MAX_COLUMN; aroundColumn++) {

                // on vérifie si c'est une case où l'on peut jouer
                if (field[aroundRow][aroundColumn] == null)
                    validMoves.add(new Move(startingRow, startingColumn, aroundRow, aroundColumn));
            }
        }
    }

    private int plus2IntoField(int coordinate) {
        return plusIntoField(coordinate, 2);
    }

    private int minus2IntoField(int coordinate) {
        return minusIntoField(coordinate, 2);
    }
    // #endregion Outils méthode getValidMoves

    private int minusIntoField(int coordinate, final int SUBTRACTOR) {
        final int MINIMUM_FIELD = 0;
        int minimumCoordinate = (coordinate - SUBTRACTOR);
        return (minimumCoordinate < MINIMUM_FIELD) ? MINIMUM_FIELD : minimumCoordinate;
    }

    private int plusIntoField(int coordinate, final int ADDER) {
        final int MAXIMUM_FIELD = field.length - 1;
        int maximumCoordinate = coordinate + ADDER;
        return (maximumCoordinate > MAXIMUM_FIELD) ? MAXIMUM_FIELD : maximumCoordinate;
    }
    // #endregion Méthodes privé (boite à outils pour méthode public)
}
