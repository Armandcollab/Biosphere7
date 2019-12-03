package biosphere7;

import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Tests unitaires de la classe JoueurBiosphere7.
 */
public class JoueurBiosphere7Test {

    // actionsPossibles pour le niveau 1 :
    /**
     * Test de la fonction actionsPossibles.
     */
    /*
    @Test
    public void testActionsPossibles() {
        JoueurBiosphere7 joueur = new JoueurBiosphere7();
        // un plateau sur lequel on veut tester actionsPossibles()
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_VIDE);
        // on choisit la couleur du joueur
        char couleur = 'R';
        // on choisit le niveau
        int niveau = 1;
        // on lance actionsPossibles
        String[] actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        // on peut afficher toutes les actions possibles calculées :
        Utils.afficherActionsPossibles(actionsPossibles);
        // on peut aussi tester si une action est dans les actions possibles :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaB,1,0"));
        // on peut aussi tester si une action n'est pas dans les actions 
        // possibles :
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PaO,1,0"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PaA,0,0"));
        // testons les 4 coins :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaA,1,0"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PnA,1,0"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaN,1,0"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PnN,1,0"));        
        // vérifions s'il y a le bon nombre d'actions possibles :
        assertEquals(Coordonnees.NB_LIGNES * Coordonnees.NB_COLONNES,
                actionsPossibles.length);
    }
     */
    /**
     * Test de la fonction actionsPossibles et de calculVitalite.
     */
    @Test
    public void testActionsPossibles() {
        JoueurBiosphere7 joueur = new JoueurBiosphere7();
        // plateau, couleur et niveau
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU2);
        char couleur = 'R';
        int niveau = 2;
        // on lance actionsPossibles
        String[] actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        // testons les 4 coins :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaA,3,2"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PnA,3,2"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PaN,3,2"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PnN,3,2"));
        // on peut poser sur une case quelconque vide :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PkD,3,2"));
        // on ne peut pas poser sur une case occupée :
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PfA,3,2"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PeI,3,2"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PhJ,3,2"));

        // Mêmes testes pour le joueur bleu
        couleur = 'B';
        // on lance actionsPossibles
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        // testons les 4 coins :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaA,2,3"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PnA,2,3"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PaN,2,3"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PnN,2,3"));
        // on peut poser sur une case quelconque vide :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PkD,2,3"));
        // on ne peut pas poser sur une case occupée :
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PfA,2,3"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PeI,2,3"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PhJ,2,3"));
        // nombre correct d'actions possibles :
        // pour niveau 2
        // assertEquals(Coordonnees.NB_LIGNES * Coordonnees.NB_COLONNES - 4,
        //      actionsPossibles.length);

        // pour l'action couper NIVEAU 3
        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3);
        niveau = 3;
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "CbB,15,7"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "CaM,16,7"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "CaN,16,7"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "CfA,15,9"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "CmE,17,8"));

        /*
        niveau = 4;
        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        couleur = 'R';
        // on lance actionsPossibles
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        // testons les 4 possibilité d'arbres voisins :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PbB,14,5"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaN,15,5"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PfA,16,5"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PmE,18,5"));
        
        // on ne peut pas poser sur une case occupée :
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PeA,14,5"));
        
        couleur = 'B';
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PbB,13,6"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaN,13,7"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PfA,13,8"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PmE,13,10"));
         */
        niveau = 5;
        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        couleur = 'R';
        // on lance actionsPossibles
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        // testons les 4 possibilité d'arbres voisins :
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PbB,14,5"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaN,14,5"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PfA,14,5"));
        //assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PmE,16,5")); //devient faux au niveau six car on n'ajoute plus 1

        // on ne peut pas poser sur une case occupée :
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "PeA,14,5"));

        couleur = 'B';
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PbB,13,6"));
        assertTrue(Utils.uneActionPossibleCommencePar(actionsPossibles, "PaN"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaN,13,7"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PfA,13,8"));
        //assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PmE,13,8"));

        niveau = 6;

        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU6);
        couleur = 'R';
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);

        assertTrue(Utils.uneActionPossibleCommencePar(actionsPossibles, "PbB"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PbB,21,12"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaN,21,12"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PfA,21,12"));

        assertTrue(Utils.uneActionPossibleCommencePar(actionsPossibles, "PmE"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PmE,13,10"));

        niveau = 7;
        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU7);
        couleur = 'R';
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        assertTrue(Utils.uneActionPossibleCommencePar(actionsPossibles, "FaM"));

        niveau = 9;
        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        couleur = 'B';
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "O,16,22"));

        plateau = Utils.plateauDepuisTexte(PLATEAU_VIDE);
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        assertFalse(Utils.uneActionPossibleCommencePar(actionsPossibles, "O"));
        assertFalse(Utils.actionsPossiblesContient(actionsPossibles, "O,0,0"));

        niveau = 10;
        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        couleur = 'R';
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        assertTrue(Utils.uneActionPossibleCommencePar(actionsPossibles, "RPbD"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "RPbD,25,27"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "RHiD,21,27"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "RTiD,21,27"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "RDiD,21,27"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "RBiD,21,27"));

        niveau = 11;
        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU9);
        couleur = 'R';
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "FaD,26,30"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "FnI,24,28"));

        niveau = 12;
        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU12);
        couleur = 'B';
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "BkL,88,93"));

        // log
        niveau = 13;
        plateau = Utils.plateauDepuisTexte(PLATEAU_log);
        couleur = 'B';
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        Utils.afficherActionsPossibles(actionsPossibles);
        assertTrue(Utils.uneActionPossibleCommencePar(actionsPossibles, "AbF"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "AfA,16,16"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "AbF,15,15"));

    }

    /**
     * Test de la fonction ajoutAction.
     */
    @Test
    public void testAjoutAction() {
        JoueurBiosphere7 joueur = new JoueurBiosphere7();
        // pour l'instant pas d'action possible
        assertEquals(0, joueur.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        String[] actions = new String[30];
        Vitalite vit = new Vitalite();
        joueur.ajoutAction(Coordonnees.depuisCars('f', 'D'), actions, "P", vit);
        // l'action est devenue possible
        assertTrue(Utils.actionsPossiblesContient(actions, "PfD,0,0"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(Utils.actionsPossiblesContient(actions, "PbH,0,0"));
        // pour l'instant une seule action possible
        assertEquals(1, joueur.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutAction(Coordonnees.depuisCars('b', 'H'), actions, "P", vit);
        // l'action a bien été ajoutée
        assertTrue(Utils.actionsPossiblesContient(actions, "PbH,0,0"));
        // désormais, deux actions possibles
        assertEquals(2, joueur.nbActions);

    }

    /**
     * Test de la fonction ajoutActionCouper
     */
    @Test
    public void testAjoutActionCouper() {
        JoueurBiosphere7 joueur = new JoueurBiosphere7();
        assertEquals(0, joueur.nbActions);
        String[] actions = new String[30];
        Vitalite vit = new Vitalite();

        joueur.ajoutAction(Coordonnees.depuisCars('f', 'D'), actions, "C", vit);
        assertTrue(Utils.actionsPossiblesContient(actions, "CfD,0,0"));
        assertFalse(Utils.actionsPossiblesContient(actions, "PbH,0,0"));

    }

    /**
     * Un plateau de base, sous forme de chaîne. Pour construire une telle
     * chaîne depuis votre sortie.log, déclarez simplement : final String
     * MON_PLATEAU = ""; puis copiez le plateau depuis votre sortie.log, et
     * collez-le entre les guillemets. Puis Alt+Shift+f pour mettre en forme.
     */
    final String PLATEAU_VIDE
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    /**
     * Un plateau pour tester le niveau 2.
     */
    final String PLATEAU_NIVEAU2
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |   |PB1|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |PR1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|PR1|   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |PB1|   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    /**
     * Un plateau pour tester le niveau 3.
     */ // 16 R & 7 B
    final String PLATEAU_NIVEAU3
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |PB1|PB1|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |PR1|   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |PR1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|PB1|   |   |   |   |   |   |PR1|PR1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|PR1|PB1|   |   |   |   |   |   |PR1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |PR1|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |PB1|PB1|PR9|   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |PB1|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    /**
     * Un plateau pour tester le niveau 4.
     */ // 5 vitalitées bleu et 13 rouge
    final String PLATEAU_NIVEAU4
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |PB1|   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |PR1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|PB1|   |   |   |   |   |   |PR1|   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |PB1|   |   |   |   |   |   |PR1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |PR1|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |PB1|   |PR9|   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |PB1|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
    /**
     * Un plateau pour tester le niveau 6.
     */ // 12 vitalitées bleu et 20 rouge
    final String PLATEAU_NIVEAU6
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |PB1|   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |PB1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |PB1|PR1|PB1|   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|PB1|   |   |   |   |   |   |PR1|   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |PB1|   |   |   |   |   |   |PR1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |PB1|   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |PB1|   |PB1|   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |PR1|   |   |   |   |   |PB1|   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |PR1|PR1|PR1|   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |PR1|PB2|   |PR9|PR1|   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |PR1|   |PR1|   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    /**
     * Un plateau pour tester le niveau 7.
     */ // 12 vitalitées bleu et 20 rouge
    final String PLATEAU_NIVEAU7
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |PB1|   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |FB1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |HB1|PR1|PB1|   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|DB1|   |   |   |   |   |   |SR1|   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |PB1|   |   |   |   |   |   |PR1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |PB1|   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |   |PB1|   |PB1|   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |PR1|   |   |   |   |   |PB1|   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |PR1|PR1|PR1|   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |PR1|PB2|   |PR9|PR1|   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |PR1|   |PR1|   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    /**
     * Un plateau pour tester le niveau 9 et 10.
     */ // 23 R et 27 B
    final String PLATEAU_NIVEAU9
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |HB1|   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |HR2|   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|PR1|   |   |SR1|   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |BR1|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |PR2|HB1|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |DB8|   |   |   |   |PB1|   |DR1|   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |TR1|   |   |   |TB1|   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |PR5|   |   |   |   |   |   |   |   |BR1|   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |HB1|   |   |   |   |   |BB4|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |PR3|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |SR4|   |   |PR1|   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |HB1|   |   |SB8|   |   |   |   |DB1|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+";

    /**
     * le plateau du dernier sortie.log
     */ //7 rouges 6 bleus 
    final String PLATEAU_NIVEAU12
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N\n"
            + " +---+---+---+---+---+---+---+---+---+---+E--+---+---+---+\n"
            + "a|   |HB5|   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+E--+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |HR7|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+E--+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |HB4|   |   |DB2|   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |BB6|   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |DR2|   |PR7|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+E--+---+---+\n"
            + "f|   |   |   |PB3|SB8|PB4|   |   |   |   |BR1|   |PR5|   |\n"
            + " +---+---+---+---+---+---+E--+---+---+---+---+---+---+---+\n"
            + "g|   |   |TB1|   |   |SB2|   |SB3|   |   |   |   |PR5|   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |DR9|SR2|SB3|   |   |   |   |   |SB4|PR5|   |\n"
            + " +---+---+E--+E--+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |BB3|   |   |   |   |   |DR3|   |PR5|   |\n"
            + " +---+E--+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |HR4|   |   |   |HR3|PB3|PR5|   |\n"
            + " +---+E--+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |PR5|BB5|   |HR9|   |PB6|   |   |SB1|   |   |   |\n"
            + " +---+E--+---+---+---+---+---+---+---+---+---+E--+---+---+\n"
            + "l|   |   |SR2|SB6|   |   |   |   |   |   |PB3|   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |SB1|PR6|   |   |   |   |   |SR3|PB4|PB1|   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |PB9|   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+";
    /**
     * le plateau du dernier sortie.log
     */ //16 R et 17 B
    final String PLATEAU_log
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N\n"
            + " +---+E--+E--+E--+E--+---+---+---+---+---+---+---+---+---+\n"
            + "a|HR2|   |   |   |   |PR2|   |   |   |   |SB1|BR1|   |   |\n"
            + " +E--+E--+E--+E--+E--+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |DB4|   |   |   |   |   |   |   |   |\n"
            + " +E--+E--+---+E--+E--+E--+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |BR1|SB1|\n"
            + " +---+---+---+E--+E--+E--+E--+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+E--+E--+E--+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |DB1|   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|PB1|   |   |   |   |   |   |   |   |   |   |TB1|TR1|   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |   |   |   |BB1|   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |   |   |   |   |   |   |BR1|   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|DR1|TR2|   |   |DB1|   |PB1|   |DR1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |   |   |DB1|   |   |   |HB1|PR1|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |PR1|   |   |   |   |TR1|   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |BB3|   |   |SR1|   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+";

}
