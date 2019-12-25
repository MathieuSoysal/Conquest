package fr.umontpellier.iut.conquest;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

/**
 * Modélise un plateau.
 */
public class Board {
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

        return coordinatesIsIntoField(move) && validPlayer(move, player) && validArrivalCase(move)
                && validDistance(move);
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

        int arrivalColumn = move.getColumn2();
        int arrivalRow = move.getRow2();

        boolean respectedDistanceColumn = respectsDistance(1, startingColumn, arrivalColumn);
        boolean respectedDistanceRow = respectsDistance(1, startingRow, arrivalRow);

        // on remplie la case d'arrivée
        transferOwnership(startingColumn, startingRow, arrivalRow, arrivalColumn);

        // on vide la case de départ s'il a fait une distance supérieur à 1
        if (!(respectedDistanceColumn && respectedDistanceRow))
            field[startingRow][startingColumn] = null;

        colorAround(arrivalColumn, arrivalRow);
    }

    /**
     * Retourne la liste de tous les coups valides de player.
     * S'il n'y a de coup valide, retourne une liste vide.
     */
    public List<Move> getValidMoves(Player player) {
        List<Move> validMoves = new ArrayList<>();

        for (int startingRow = 0; startingRow < field.length; startingRow++) {
            for (int startingColumn = 0; startingColumn < field.length; startingColumn++) {
                if (validPlayer(field[startingRow][startingColumn], player))
                    appendValidMovesAroundStartingPawn(validMoves, startingRow, startingColumn);
            }
        }
        return validMoves;
    }

    /**
     * Retourne le nombre de pions d'un joueur.
     */
    public int getNbPawns(Player player) {
        int nombre = 0;
        int i = 0;
        int j = 0;
        while(i < field.length-1) {
            while(j < field.length-1) {
                if (field[i][j].getPlayer().getColor() == player.getColor()){
                    nombre++;
                }
                j++;
            }
            i++;
        }
        return nombre;
    }

    // #region Méthodes privé (boite à outils pour méthode public)

    // #region Outils méthode isValid

    // #region coordinatesIsIntoField
    private boolean coordinatesIsIntoField(Move move) {

        int startingRow = move.getRow1();
        int startingColumn = move.getColumn1();

        int arrivalRow = move.getRow2();
        int arrivalColumn = move.getColumn2();

        return isIntoField(startingRow, startingColumn) && isIntoField(arrivalRow, arrivalColumn);
    }

    private boolean isIntoField(int row, int column) {
        return isIntoField(column) && isIntoField(row);
    }

    private boolean isIntoField(int coordinate) {
        return 0 <= coordinate && coordinate < field.length;
    }
    // #endregion coordinatesIsIntoField

    // validPlayer
    private boolean validPlayer(Move move, Player player) {
        Pawn startingPawn = field[move.getRow1()][move.getColumn1()];
        return validPlayer(startingPawn, player);
    }

    // validArrivalCase
    private boolean validArrivalCase(Move move) {
        Pawn arrivalCase = field[move.getRow2()][move.getColumn2()];

        return arrivalCase == null;
    }

    // #region validDistance
    private boolean validDistance(Move move) {
        int startingRow = move.getRow1();
        int arrivalRow = move.getRow2();
        int startingColumn = move.getColumn1();
        int arrivalColumn = move.getColumn2();

        return validDistance(startingRow, arrivalRow) && validDistance(startingColumn, arrivalColumn);
    }

    private boolean validDistance(int startingDistance, int arrivalDistance) {
        return respectsDistance(2, startingDistance, arrivalDistance);
    }
    
    private boolean respectsDistance(final int AUTHORIZED_DISTANCE,int startingDistance, int arrivalDistance){
        int distance = Math.abs(startingDistance - arrivalDistance);

        return distance <= AUTHORIZED_DISTANCE;
    }
    // #endregion validDistance
    // #endregion Outils méthode isValid

    // #region Outils méthode movePawn
    private void colorAround(int arrivalColumn, int arrivalRow) {
        int playerColor = field[arrivalRow][arrivalColumn].getPlayer().getColor();

        for (int i = minus1IntoField(arrivalRow); i < plus1IntoField(arrivalRow); i++) {
            for (int j = minus1IntoField(arrivalColumn); j < plus1IntoField(arrivalColumn); j++) {

                if (canColorWithPlayerColor(playerColor, i, j))
                    transferOwnership(arrivalColumn, arrivalRow, i, j);
            }
        }
    }

    private void transferOwnership(int ownerColumn, int ownerRow, int tenantRow, int tenantColumn) {
        Pawn ownerPawn = new Pawn(field[ownerRow][ownerColumn].getPlayer());
        field[tenantRow][tenantColumn] = ownerPawn;
    }

    private boolean canColorWithPlayerColor(int playerColor, int i, int j) {
        return field[i][j] != null && field[i][j].getPlayer().getColor() != playerColor;
    }

    private int plus1IntoField(int coordinate) {
        final int MAXIMUM_VALUE_FIELD = field.length - 1;
        return (coordinate == MAXIMUM_VALUE_FIELD) ? MAXIMUM_VALUE_FIELD : (coordinate + 1);
    }
    
    private int minus1IntoField(int coordinate) {
        final int MINIMUM_VALUE_FIELD = 0;
        return (coordinate == MINIMUM_VALUE_FIELD) ? MINIMUM_VALUE_FIELD : coordinate - 1;
    }
    // #endregion Outils méthode movePawn

    //#region Outils méthode getValidMoves
    private boolean validPlayer(Pawn startingPawn, Player player) {
        int playerColor = player.getColor();
        boolean pawnNotNull = startingPawn != null;

        return pawnNotNull && (startingPawn.getPlayer().getColor() == playerColor);
    }

    private void appendValidMovesAroundStartingPawn(List<Move> validMoves, int i, int j) {
        final int MIN_ROW = minus2IntoField(i);
        final int MAX_ROW = plus2IntoField(i);

        final int MIN_COLUMN = minus2IntoField(j);
        final int MAX_COLUMN = plus2IntoField(j);

        for (int aroundRow = MIN_ROW; aroundRow <= MAX_ROW; aroundRow++) {
            for (int aroundColumn = MIN_COLUMN; aroundColumn <= MAX_COLUMN; aroundColumn++) {
                if (field[aroundRow][aroundColumn] == null)
                    validMoves.add(new Move(i, j, aroundRow, aroundColumn));
            }
        }
    }

    private int plus2IntoField(int coordinate) {
        final int AUTHORIZED_DIFFERENCE = 2;
        final int MAXIMUM_VALUE_FIELD = field.length - 1;

        int difference = MAXIMUM_VALUE_FIELD - coordinate;
        int differenceAdjustment = (difference < AUTHORIZED_DIFFERENCE) ? difference : AUTHORIZED_DIFFERENCE;

        return coordinate + differenceAdjustment;
    }

    private int minus2IntoField(int coordinate) {
        final int AUTHORIZED_DIFFERENCE = 2;
        final int MINIMUM_VALUE_FIELD = 0;

        int difference = (coordinate - AUTHORIZED_DIFFERENCE) - MINIMUM_VALUE_FIELD;

        return (difference < MINIMUM_VALUE_FIELD) ? MINIMUM_VALUE_FIELD : difference;
    }
    //#endregion Outils méthode getValidMoves
    // #endregion Méthodes privé (boite à outils pour méthode public)
}
