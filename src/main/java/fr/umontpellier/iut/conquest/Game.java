package fr.umontpellier.iut.conquest;

import fr.umontpellier.iut.conquest.strategies.Strategy;

import static java.lang.Math.pow;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Modélise une partie de Conquest.
 */

public class Game {
    /**
     * Scanner permettant de lire les entrées.
     */
    private static Scanner scan;
    /**
     * Le plateau de jeu.
     */
    private Board board;
    /**
     * Les joueurs.
     */
    private Player[] players = new Player[2];

    /**
     * Constructeur.
     * Crée un plateau à partir de sa taille (impaire).
     * Crée les joueurs à partir de leur stratégie et leur nom.
     */
    public Game(int size, Strategy strategy1, String name1, Strategy strategy2, String name2) {
        board = new Board(size);
        players[0] = new Player(strategy1, this, name1, 1);
        players[1] = new Player(strategy2, this, name2, 2);
    }

    /**
     * Constructeur pour Test.
     * Prend un plateau prédéfini.
     * Crée les joueurs à partir de leur stratégie et leur nom.
     */
    public Game(Board board, Strategy strategy1, String name1, Strategy strategy2, String name2) {
        this.board = board;
        players[0] = new Player(strategy1, this, name1, 1);
        players[1] = new Player(strategy2, this, name2, 2);
    }

    /**
     * Les méthodes suivantes sont utilisées pour les tests automatiques. Il ne faut pas les utiliser.
     */
    public static Scanner getScan() {
        return scan;
    }

    public static void initInput(InputStream inputStream) {
        scan = new Scanner(inputStream);
    }

    public Player[] getPlayers() {
        return players;
    }

    /**
     * Getter.
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Fait tourner une partie.
     *
     * @param hardcore : Si hardcore = 1, les joueurs ne peuvent pas annuler les coups joués.
     *                 Sinon harcdore = 0 et les joueurs ont le droit d'annuler un ou plusieurs coups à chaque fois qu'un coup est joué.
     */
    public void run(int hardcore) {
        // Le joueur qui commence est le premier joueur.
        Player player = players[0];

        // Initialise le jeu.
        initGame();

        // Fait tourner le jeu.
        while (!isFinished()) {

            // Affiche le plateau.
            System.out.println(board);

            // Demande au joueur courant de jouer.
            if (!board.getValidMoves(player).isEmpty()) {
                board.movePawn(player.play());
            }

            // En mode non-hardcore, demande au joueur s'il veut annuler un ou plusieurs coups.
            if (hardcore == 0) {
                player = confirmOrUndoMove(player);
            }
            // Change de joueur.
            player = getOtherPlayer(player);
        }

        // Affiche le plateau final.
        System.out.println(board);

        // Affiche le nom du vainqueur.
        System.out.println("Le vainqueur est " + getWinner().getName() + " !");
    }

    /**
     * Initialise le jeu.
     */
    private void initGame() {
        board.initField(players[0], players[1]);
    }

    /**
     * Prends un joueur en entrée et retourne l'autre joueur.
     */
    public Player getOtherPlayer(Player player) {
        return (player.equals(players[0])) ? players[1] : players[0];
    }

    /**
     * Teste si la partie est finie.
     * Rappel :
     * - La partie est finie quand il n'y a plus de case libre.
     * - La partie est finie quand l'un des deux joueurs n'a plus de pions.
     */
    public boolean isFinished() {
        return noFreeSquare() || noPawns();
    }

    /**
     * Retourne le joueur qui a gagné la partie.
     * Rappel : Le joueur qui gagne est celui qui possède le plus de pions.
     */
    public Player getWinner() {
        return (nbPawnsPlayer0() > nbPawnsPlayer1()) ? players[0] : players[1];
    }

    /**
     * Demande au joueur s'il veut annuler (ou pas) un ou plusieurs coups.
     * <p>
     * Tant que le joueur player le désire et que l'on n'est pas revenu au début de la partie en cours,
     * propose à player de reculer d'un coup en faisant saisir 1, ou 0 sinon.
     * Cette méthode doit donc modifier l'état de l'attribut board.
     *
     * @param player : le joueur courant.
     * @return Player : le joueur dont il est le tour de jouer.
     */
    private Player confirmOrUndoMove(Player player) {
        int nbUndoMove = 0;

        while (canUndoMove() && playerWantsUndoMove()) {
            nbUndoMove++;
            throw new RuntimeException("Not implemented");
        }
        //TODO ajout board dans les souvenirs
        return getPlayerOfTurn(player, nbUndoMove);
    }

    private boolean canUndoMove() {
        throw new RuntimeException("Not implemented");
    }

    private Player getPlayerOfTurn(Player player, int nbUndoMove) {
        boolean nbUndoIsPair = (nbUndoMove % 2) == 0;
        return nbUndoIsPair ? player : getOtherPlayer(player);
    }

    private boolean playerWantsUndoMove() {
        int nbUndo;
        do {
            System.out.println("Voulez-vous annuler le tour (1 pour Oui : 0 pour Non) :");
            nbUndo = scan.nextInt();
        } while (nbUndo > 1);
        return nbUndo == 1;
    }

    // #region méthode privées isFinished()
    private boolean noPawns() {
        return nbPawnsPlayer0() == 0 || nbPawnsPlayer1() == 0;
    }

    private boolean noFreeSquare() {
        final double NUMBER_OF_SQUARE = pow(board.getSize(), 2);
        int numberOfPawns = nbPawnsPlayer0() + nbPawnsPlayer1();

        return numberOfPawns == NUMBER_OF_SQUARE;
    }

    private int nbPawnsPlayer1() {
        return board.getNbPawns(players[1]);
    }

    private int nbPawnsPlayer0() {
        return board.getNbPawns(players[0]);
    }
    // #endregion méthode privées isFinished()
}

