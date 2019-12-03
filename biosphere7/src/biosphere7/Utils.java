package biosphere7;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Quelques fonctions utiles au projet. Vous devez comprendre ce que font ces
 * méthodes (voir leur documentation), mais pas comment elles le font (leur
 * code).
 *
 * À faire évoluer en fonction des nouvelles natures de case, des nouvelles
 * espèces de plantes, etc.
 */
public class Utils {

    /**
     * Caractère pour indiquer une plante possédée par les rouges.
     */
    public final static char CAR_ROUGE = 'R';

    /**
     * Caractère pour indiquer une plante possédée par les bleus.
     */
    public final static char CAR_BLEU = 'B';

    /**
     * Caractère indiquant la nature "Terre" de la case.
     */
    public final static char CAR_TERRE = ' ';

    /**
     * Caractère indiquant la nature "EAU" de la case.
     */
    public final static char CAR_EAU = 'E';

    /**
     * Caractère pour indiquer une case sans plante.
     */
    public final static char CAR_VIDE = ' ';

    /**
     * Caractère pour indiquer une case avec un pommier.
     */
    public final static char CAR_POMMIER = 'P';

    /**
     * Nombre d'èspeces de plantes
     */
    public final static char[] ESPECES = new char[]{'P', 'S', 'B', 'D', 'T', 'H'};

    /**
     * Fonction qui renvoie une copie du tableau sans les cases non utilisées,
     * c'est-à-dire contenant null ou la chaîne vide. Par exemple {"Coucou", "",
     * null, "Hello", null} renvoie {"Coucou", "Hello"}.
     *
     * @param actions le tableau à nettoyer
     * @return le tableau nettoyé
     */
    public static String[] nettoyerTableau(final String[] actions) {
        return Arrays.stream(actions)
                .filter(a -> ((a != null) && (!"".equals(a))))
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

    /**
     * Construit un plateau à partir de sa représentation sour forme texte,
     * comme renvoyé par formatTexte(), avec coordonnées et séparateurs.
     *
     * @param texteOriginal le texte du plateau
     * @return le plateau
     */
    public static Case[][] plateauDepuisTexte(final String texteOriginal) {
        final Case[][] plateau = new Case[Coordonnees.NB_LIGNES][Coordonnees.NB_COLONNES];
        final String[] lignes = texteOriginal.split("\n");
        for (int lig = 0; lig < Coordonnees.NB_LIGNES; lig++) {
            final String ligne1 = lignes[2 * lig + 1];
            final String ligne2 = lignes[2 * lig + 2];
            for (int col = 0; col < Coordonnees.NB_COLONNES; col++) {
                final String codageLigne1 = ligne1.substring(2 + 4 * col, 2 + 4 * col + 3);
                final String codageLigne2 = ligne2.substring(2 + 4 * col, 2 + 4 * col + 3);
                plateau[lig][col] = caseDepuisCodage(codageLigne1, codageLigne2);
            }
        }
        return plateau;
    }

    /**
     * Construit une case depuis son codage.
     *
     * @param ligne1 codage de la case, première ligne
     * @param ligne2 codage de la case, deuxième ligne
     * @return case correspondante
     */
    public static Case caseDepuisCodage(final String ligne1, final String ligne2) {
        // vérification des arguments
        if (ligne1.length() != 3 || ligne2.length() != 3) {
            throw new IllegalArgumentException(
                    "Un codage de ligne doit être sur 3 caractères par ligne.");
        }
        Case laCase = new Case(CAR_VIDE, CAR_ROUGE, 0, CAR_TERRE);
        //
        // ligne 1
        //
        // 1er caractère : nature
        char carNature = ligne1.charAt(0);
        if (carNature == '-') {
            laCase.nature = Utils.CAR_TERRE;
        } else {
            laCase.nature = carNature;
        }
        // 2ème caractère : rien
        // 3ème caractère : rien
        //
        // ligne 2
        //
        // 1er caractère : espèce
        laCase.espece = ligne2.charAt(0);
        // 2ème caractère : couleur
        char carCouleur = ligne2.charAt(1);
        if (laCase.espece == CAR_VIDE) {
            if (carCouleur != CAR_VIDE) {
                throw new IllegalArgumentException("Cette case ne contient pas de plante,"
                        + " donc ne devrait pas avoir de couleur associée.");
            }
        } else {
            if (carCouleur != CAR_BLEU && carCouleur != CAR_ROUGE) {
                throw new IllegalArgumentException(
                        "Caractère couleur non admis : " + carCouleur);
            }
            laCase.couleur = carCouleur;
        }
        // 3ème caractère : vitalité
        char carVitalite = ligne2.charAt(2);
        if (laCase.espece == CAR_VIDE) {
            if (carVitalite != CAR_VIDE) {
                throw new IllegalArgumentException("Cette case ne contient pas de plante,"
                        + " donc ne devrait pas avoir de vitalité associée.");
            }
            laCase.vitalite = 0;
        } else {
            laCase.vitalite = Integer.parseInt("" + carVitalite);
        }
        return laCase;
    }

    /**
     * Afficher les action possibles déjà calculées.
     *
     * @param actionsPossibles les actions possibles calculées
     */
    static void afficherActionsPossibles(String[] actionsPossibles) {
        System.out.println(Arrays.deepToString(actionsPossibles));
    }

    /**
     * Indique si une action est présente parmi les actions possibles calculées.
     *
     * @param actionsPossibles actions possibles calculées
     * @param action l'action à tester
     * @return vrai ssi l'action est présente parmi les actions possibles
     */
    static boolean actionsPossiblesContient(String[] actionsPossibles,
            String action) {
        return Arrays.asList(actionsPossibles).contains(action);
    }

    /**
     * Indique si, parmi les actions possibles calculées, l'une d'elle COMMENCE
     * par une chaîne donnée.
     *
     * @param actionsPossibles actions possibles calculées
     * @param debutAction le début de l'action à tester
     * @return vrai ssi une action possible commence par la chaine debutAction
     */
    static boolean uneActionPossibleCommencePar(String[] actionsPossibles,
            String debutAction) {
        return Arrays.stream(actionsPossibles)
                .filter(a -> a != null)
                .anyMatch(a -> a.startsWith(debutAction));
    }

    /**
     * Si l'arbre contenue sur la case donnée est entre 4 arbres ( valeur
     * pourCombienArbre ) alors revoi vrai, sinon faux
     *
     * @param plateau le plateau considéré
     * @param coord coordoné de la case dont on vas dire si elle étouffe
     * @param nbrArbrePourEtouffer nombre d'arbre nécéssaires pour étouffer un
     * arbre
     * @return true, si l'arbre étouffe, sinon faux
     */
    static boolean etouffe(Case[][] plateau, Coordonnees coord, int nbrArbrePourEtouffer) {
        Coordonnees[] tabArbresVoisins = plantesVoisines(plateau, coord, false);

        if (coord.ligne == 0 || coord.ligne == 13 || coord.colonne == 0 || coord.colonne == 13) {
            return false;
        } else if (nbrArbrePourEtouffer % 4 == 0) {
            return !(tabArbresVoisins[0].ligne == -1 || tabArbresVoisins[0].colonne == -1
                    || tabArbresVoisins[1].ligne == -1 || tabArbresVoisins[1].colonne == -1
                    || tabArbresVoisins[2].ligne == -1 || tabArbresVoisins[2].colonne == -1
                    || tabArbresVoisins[3].ligne == -1 || tabArbresVoisins[3].colonne == -1);

        } else if (nbrArbrePourEtouffer % 4 != 0) {
            int nbrVoisins = 0;
            for (int i = 0; i < 4; i++) {
                if (tabArbresVoisins[i].ligne != -1 || tabArbresVoisins[i].colonne != -1) {
                    nbrVoisins++;
                }
            }
            return nbrVoisins >= nbrArbrePourEtouffer % 4;
        }

        return false; // toutes les cas on était traités plus haut, ce return n'arrive donc jamais
    }

    /**
     * Retourne les coordonés d'une case si elle n'est pas vide
     *
     * @param plateau le plateau considéré
     * @param coord coordonnées de la case à regarder
     * @param inverse regarde si les cases sont vide au lieu de pleines
     * @return les coordonées de la case si il possède un arbre, sinon des
     * coordonnées nul
     */
    static Coordonnees regardeSiArbre(Case[][] plateau, Coordonnees coord, boolean inverse) {
        if (coord.ligne < Coordonnees.NB_LIGNES && coord.ligne >= 0
                && coord.colonne < Coordonnees.NB_COLONNES && coord.colonne >= 0) {
            if (!inverse) {
                if (plateau[coord.ligne][coord.colonne].espece != CAR_VIDE) {
                    return coord;
                }
            } else {
                if (plateau[coord.ligne][coord.colonne].espece == CAR_VIDE) {
                    return coord;
                }
            }

        }
        return (new Coordonnees(-1, -1));
    }

    /**
     * A partir d'une case vérifie si les cases voisines contiennent un arbre et
     * retorune leurs coordonnées selon si l'on veut seuelement les pleines ou
     * toutes les casesc
     *
     * @param plateau le plateau considéré
     * @param coord coordoné de la case dont on vas chercher les arbres voisins
     * @param toute si vaut true on retourne toutes les coordonnés
     * @return un tableau contenant les coordonées de arbres voisons si il y en
     * as, sinon -1,-1 ( sauf si toute vaut true)
     */
    static Coordonnees[] plantesVoisines(Case[][] plateau, Coordonnees coord, boolean toute) {
        Coordonnees[] coordsVoisins = new Coordonnees[4]; // 4 correspond au nombre de voisins possibles maximum

        Coordonnees coordsTmp1 = new Coordonnees(coord.ligne + 1, coord.colonne);
        Coordonnees coordsTmp2 = new Coordonnees(coord.ligne - 1, coord.colonne);
        Coordonnees coordsTmp3 = new Coordonnees(coord.ligne, coord.colonne + 1);
        Coordonnees coordsTmp4 = new Coordonnees(coord.ligne, coord.colonne - 1);

        if (!toute) {
            coordsVoisins[0] = regardeSiArbre(plateau, coordsTmp1, false);

            coordsVoisins[1] = regardeSiArbre(plateau, coordsTmp2, false);

            coordsVoisins[2] = regardeSiArbre(plateau, coordsTmp3, false);

            coordsVoisins[3] = regardeSiArbre(plateau, coordsTmp4, false);
        } else {

            coordsVoisins[0] = coordsTmp1;
            coordsVoisins[1] = coordsTmp2;
            coordsVoisins[2] = coordsTmp3;
            coordsVoisins[3] = coordsTmp4;
        }
        return coordsVoisins;
    }

    /**
     * Vérifie si une case est Auto Stérile ( T ou H ) ou Auto Féconde ( P , S ,
     * D , B)
     *
     * @param plateau le plateau considéré
     * @param coord coordoné de la case dont on vas chercher les arbres voisins
     * @return true si la plante est autoSterile, faux sinon, soit si elle est
     * autoFeconde
     */
    static boolean estAutoFéconde(Case[][] plateau, Coordonnees coord) {
        switch (plateau[coord.ligne][coord.colonne].espece) {
            case 'P':
            case 'S':
            case 'B':
            case 'D':
                return true;
            case 'T':
            case 'H':
            default:
                return false;
        }
    }

    /**
     * Vérifie si une case à un voisin de la même espèce qu'elle
     *
     * @param plateau le plateau considéré
     * @param coord coordoné de la case dont on vas chercher les arbres voisins
     * @return true si il y a au moins un voisin de la même espèce sinon false
     */
    static boolean unVoisinDeLaMemeEspece(Case[][] plateau, Coordonnees coord) {
        for (int i = 0; i < 4; i++) {
            Coordonnees coordVoisin = plantesVoisines(plateau, coord, false)[i];
            if (coordVoisin.ligne != -1 && coordVoisin.colonne != -1) {
                if (plateau[coordVoisin.ligne][coordVoisin.colonne].espece == plateau[coord.ligne][coord.colonne].espece) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Cherche la valeur minimum entre les vialités voisines et celle de la case
     * centrale
     *
     * @param plateau le plateau considéré
     * @param coord coordoné de la case dont on vas chercher les arbres voisins
     * @return la valeur minimum de vitalité trouvé dans les cases voisins
     * pleines et la case centrale
     */
    static int vitaliteVoisinPlusFaible(Case[][] plateau, Coordonnees coord) {
        Coordonnees[] coordsVoisins = plantesVoisines(plateau, coord, false);
        int valMin = plateau[coord.ligne][coord.colonne].vitalite;
        for (int i = 0; i < 4; i++) {
            if (coordsVoisins[i].ligne != -1 && coordsVoisins[i].colonne != -1 && plateau[coordsVoisins[i].ligne][coordsVoisins[i].colonne].espece == plateau[coord.ligne][coord.colonne].espece) {
                if (plateau[coordsVoisins[i].ligne][coordsVoisins[i].colonne].vitalite < valMin) {
                    valMin = plateau[coordsVoisins[i].ligne][coordsVoisins[i].colonne].vitalite;
                }
            }
        }
        return valMin;
    }

    /**
     * Vérifie si des coordonnées indique une case dans le tableau
     *
     * @param plateau le plateau considéré
     * @param coord coordoné de la case dont on vas chercher les arbres voisins
     * @return true si la les coordonées donné sont compris dans les cases du
     * tableau
     */
    static boolean estDansPlateau(Case[][] plateau, Coordonnees coord) {
        if (coord.ligne < Coordonnees.NB_LIGNES && coord.ligne >= 0 && coord.colonne < Coordonnees.NB_COLONNES && coord.colonne >= 0) {
            return true;
        }
        return false;
    }

    /**
     * Vérifie si une case à au moins une case voisine de nature Eau
     *
     * @param plateau le plateau considéré
     * @param coord coordoné de la case dont on vas chercher les arbres voisins
     * @return 1 si la case à au moins une case voisine de nature Eau, sinon 0
     * (cela correspond à la vitalité à ajouter, c'est pour cela qu'on ne
     * retourne pas un booléen)
     */
    static int regardeSiVoisinEau(Case[][] plateau, Coordonnees coord) {
        Coordonnees[] coordsVoisins = plantesVoisines(plateau, coord, true);
        int i = -1;

        do {
            i++;
            if (i >= 4) {
                return 0;
            }
            while (!estDansPlateau(plateau, coordsVoisins[i])) {
                i++;
                if (i >= 4) {
                    return 0;
                }
            }
        } while (!estEau(plateau, coordsVoisins[i]));
        return 1;
    }

    /**
     * Vérifie si une case est de nature EAU
     *
     * @param plateau le plateau considéré
     * @param coord coordoné de la case à vérifier
     * @return vrai si la case est de nature EAU, sinon faux
     */
    static boolean estEau(Case[][] plateau, Coordonnees coord) {
        if (plateau[coord.ligne][coord.colonne].nature == CAR_EAU) {
            return true;
        }
        return false;
    }

    /**
     * Vérifie si deux plantes sont de la mème catégorie
     *
     * @param espece1 espèce de la première plante à tester
     * @param espece2 espèce de la deuxième plante à tester
     * @return vrai si elles sont de la même catégorie sinon faux
     */
    static boolean estDeLaMemeCategorie(char espece1, char espece2) {
        switch (espece1) {
            case 'S':
            case 'P':
                switch (espece2) {
                    case 'S':
                    case 'P':
                        return true;
                    default:
                        break;
                }
                break;
            case 'B':
                if (espece2 == 'B') {
                    return true;
                }
                break;
            case 'D':
            case 'T':
            case 'H':
                switch (espece2) {
                    case 'D':
                    case 'T':
                    case 'H':
                        return true;
                    default:
                        break;
                }
                break;
        }
        return false;
    }

    /**
     * Calcul la distance de manhattan entre deux cases
     *
     * @param coord1 coordonnées de la premmière case
     * @param coord1 coordonnées de la deuxième case
     * @return la distance de manhattan entre les deux case
     */
    static int calculDistanceManhattan(Coordonnees coord1, Coordonnees coord2) {
        return Math.abs(coord2.ligne - coord1.ligne) + Math.abs(coord2.colonne - coord1.colonne);
    }

    /**
     * Vérifie si unne case est à la lisière d'une foret d'au moins six arbres
     * voisins
     *
     * @param plateau le plateau considéré
     * @param coordCase la case qui est peut-être en lisière
     * @return true si la case donnée est en lisière de foret, sinon faux
     */
    static boolean esrEnLisière(Case[][] plateau, Coordonnees coordCase) {
        Coordonnees[] tabCoordsVoisin = plantesVoisines(plateau, coordCase, true);
        boolean foretTrouve = false;
        int nbrArbreValide = 0;
        Coordonnees[] tabCoordsVoisinValide = {new Coordonnees(-1, -1), new Coordonnees(-1, -1), new Coordonnees(-1, -1), new Coordonnees(-1, -1)};

        for (int j = 0; j < tabCoordsVoisin.length; j++) {
            if (nbrArbreValide < tabCoordsVoisin.length && estDansPlateau(plateau, tabCoordsVoisin[j])
                    && estDeLaMemeCategorie(plateau[tabCoordsVoisin[j].ligne][tabCoordsVoisin[j].colonne].espece, 'P')) {
                tabCoordsVoisinValide[nbrArbreValide] = tabCoordsVoisin[j];
                nbrArbreValide++;
            }
        }
        /*
        while (i < tabCoordsVoisin.length && (!estDansPlateau(plateau, tabCoordsVoisin[i])
                || !estDeLaMemeCategorie(plateau[tabCoordsVoisin[i].ligne][tabCoordsVoisin[i].colonne].espece, 'P'))) {
            i++;
        }*/

        int i = 0;
        while (!foretTrouve && i < nbrArbreValide && nbrArbreValide != 0
                && estDansPlateau(plateau, tabCoordsVoisinValide[i])) {
            Coordonnees[] tabForet = {tabCoordsVoisinValide[i], null, null, null, null, null};
            foretTrouve = false;
            int j = 0;
            while (j < tabForet.length && tabForet[tabForet.length - 1] == null) {
                tabForet = regardeSiPlanteVoisineDejaTrouve(plateau, tabForet, 'P');
                j++;
            }
            if (tabForet[tabForet.length - 1] == null) {
                foretTrouve = false;
            } else {
                foretTrouve = true;
            }
            i++;
        }
        return foretTrouve;
    }

    /**
     * Ajoute un arbre ou pas suivant s'il y en as un qui est voisin d'un des
     * arbres dont les coordonnées sont dans le tableau donné et qui n'as pas
     * ses coordonnées dans le tableau et qui est un arbre
     *
     * @param plateau le plateau considéré
     * @param coordsForet tableau contenant les arbres déjà trouvé dans la
     * recherche de foret
     * @return le tableau contenant les arbres déjà trouvés et un nouveau si il
     * y en as un voisin qui n'est aps déjà dans le tableau
     */
    static Coordonnees[] regardeSiPlanteVoisineDejaTrouve(Case[][] plateau, Coordonnees[] coordsForet, char espece) {
        int f = 0;
        int cpt = 0;
        while (f < coordsForet.length && coordsForet[f] != null) {
            f++;
        }
        if (f >= coordsForet.length) {
            return coordsForet; // il y a déjà une Foret d'au moins six arbres
        } else {
            for (int k = 0; k < f; k++) { // si f = 0 retourne le tableau, il n'y as pas d'arbre de référence pour en chercher un nouveau (n'est pas sencé arriver)
                Coordonnees[] tabVoisin = plantesVoisines(plateau, coordsForet[k], true);
                for (int i = 0; i < tabVoisin.length; i++) {
                    if (estDansPlateau(plateau, tabVoisin[i])
                            && estDeLaMemeCategorie(plateau[tabVoisin[i].ligne][tabVoisin[i].colonne].espece, espece)) {
                        cpt = 0;
                        for (int j = 0; j < f; j++) {
                            if (tabVoisin[i].ligne == coordsForet[j].ligne
                                    && tabVoisin[i].colonne == coordsForet[j].colonne) {
                                cpt++;
                            }
                        }
                        if (cpt == 0) {
                            coordsForet[f] = new Coordonnees(tabVoisin[i].ligne, tabVoisin[i].colonne);
                            return coordsForet;
                        }
                    }
                }
            }
            return coordsForet;
        }
    }

    /**
     * Créé un tableau et y ajoute toutes les cases voisines de la même espèces 
     * ou de nature EAU à partir de celle donnée
     *
     * @param plateau le plateau considéré
     * @param coordCase la case qui est peut-être en lisière
     * @return un tableau contenant toutes les cases touché par l'attaque de chapignon
     */
    static Coordonnees[] tableauCoordToucheChampi(Case[][] plateau, Coordonnees coordCase) {
        Coordonnees[] tabToucheChampi = new Coordonnees[Coordonnees.NB_LIGNES * Coordonnees.NB_COLONNES];
        tabToucheChampi[1] = new Coordonnees(coordCase.ligne, coordCase.colonne);
        // while faire tant qu'on obtien pas deux fois le même !
        regardeSiPlanteVoisineDejaTrouve(plateau, tabToucheChampi, plateau[coordCase.ligne][coordCase.colonne].espece);
        // puis le retourner !
        return null;
    }

}
