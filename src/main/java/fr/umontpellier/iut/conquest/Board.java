package fr.umontpellier.iut.conquest;

import java.util.List;

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
        initPawnFirstPlayer(player1);
        initPawnSecondPlayer(player2);
    }

    private void initPawnSecondPlayer(Player player2) {
        field[0][field.length-1] = new Pawn(player2);
        field[field.length-1][0] = new Pawn(player2);
    }

    private void initPawnFirstPlayer(Player player1) {
        field[0][0] = new Pawn(player1);
        field[field.length-1][field.length-1] = new Pawn(player1);
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

    private boolean coordinatesIsIntoField(Move move) {

        int column2 = move.getColumn2();
        int column1 = move.getColumn1();
        int row2 = move.getRow2();
        int row1 = move.getRow1();

        boolean startingCase = isIntoField(column1) && isIntoField(row1);
        boolean arrivalCase = isIntoField(column2) && isIntoField(row2);

        return startingCase && arrivalCase;
    }

    private boolean isIntoField(int coordinate) {
        return 0 <= coordinate && coordinate < field.length;
    }

    private boolean validPlayer(Move move, Player player) {

        Pawn startingCase = field[move.getRow1()][move.getColumn1()];
        boolean pawnNotNull = startingCase != null;

        boolean validPlayer = pawnNotNull && startingCase.getPlayer().equals(player);
        return validPlayer;
    }

    private boolean validArrivalCase(Move move) {
        Pawn arrivalCase = field[move.getRow2()][move.getColumn2()];

        boolean validArrivalCase = arrivalCase == null;
        return validArrivalCase;
    }

    private boolean validDistance(Move move) {
        boolean valideDistanceRow = valideDistance(move.getRow1(),move.getRow2());
        boolean valideDistanceColumn = valideDistance(move.getColumn1(),move.getColumn2());

        return valideDistanceColumn && valideDistanceRow;
    }

    private boolean valideDistance(int distance1, int distance2) {
        return Math.abs(distance1 - distance2) <=2;
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
        throw new RuntimeException("Not implemented");
    }

    /**
     * Retourne la liste de tous les coups valides de player.
     * S'il n'y a de coup valide, retourne une liste vide.
     */
    public List<Move> getValidMoves(Player player) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Retourne le nombre de pions d'un joueur.
     */
    public int getNbPawns(Player player) {
        throw new RuntimeException("Not implemented");
    }
}
