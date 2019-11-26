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
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaN,13,7"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PfA,13,8"));
        //assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PmE,13,8"));

        niveau = 6;
        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU6);
        couleur = 'R';
        actionsPossibles = joueur.actionsPossibles(plateau, couleur, niveau);
        Utils.afficherActionsPossibles(actionsPossibles);

        assertTrue(Utils.uneActionPossibleCommencePar(actionsPossibles, "PbB"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PbB,21,12"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PaN,21,12"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PfA,21,12"));

        assertTrue(Utils.uneActionPossibleCommencePar(actionsPossibles, "PmE"));
        assertTrue(Utils.actionsPossiblesContient(actionsPossibles, "PmE,13,10"));

    }

    /**
     * Test de la fonction ajoutActionPommier.
     */
    @Test
    public void testAjoutActionPommier() {
        JoueurBiosphere7 joueur = new JoueurBiosphere7();
        // pour l'instant pas d'action possible
        assertEquals(0, joueur.nbActions);
        // on crée le tableau d'actions et on en ajoute une
        String[] actions = new String[30];
        joueur.ajoutActionPommier(Coordonnees.depuisCars('f', 'D'), actions);
        // l'action est devenue possible
        assertTrue(Utils.actionsPossiblesContient(actions, "PfD,0,0"));
        // une action possible mais qui n'a pas encore été ajoutée
        assertFalse(Utils.actionsPossiblesContient(actions, "PbH,0,0"));
        // pour l'instant une seule action possible
        assertEquals(1, joueur.nbActions);
        // ajout d'une deuxième action possible
        joueur.ajoutActionPommier(Coordonnees.depuisCars('b', 'H'), actions);
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

        joueur.ajoutActionCouper(Coordonnees.depuisCars('f', 'D'), actions);
        assertTrue(Utils.actionsPossiblesContient(actions, "CfD,0,0"));
        assertFalse(Utils.actionsPossiblesContient(actions, "PbH,0,0"));

    }

    /**
     * Test de la fonction ArbreVoisins
     */
    @Test
    public void testArbresVoisins() {
        JoueurBiosphere7 joueur = new JoueurBiosphere7();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3);

        Coordonnees[] coordsAttendu = new Coordonnees[4];
        for (int i = 0; i < 4; i++) {
            coordsAttendu[i] = new Coordonnees(-1, -1);
        }

        Coordonnees coord = new Coordonnees(1, 1);
        Assert.assertArrayEquals(coordsAttendu, joueur.arbreVoisins(plateau, coord));

        coord.ligne = 0;
        coord.colonne = 12;
        coordsAttendu[2].ligne = 0;
        coordsAttendu[2].colonne = 13;
        //Assert.assertArrayEquals(coordsAttendu, joueur.arbreVoisins(plateau, coord));
        Assert.assertEquals(coordsAttendu[2].colonne, joueur.arbreVoisins(plateau, coord)[2].colonne);

        coord.ligne = 12;
        coord.colonne = 4;
        coordsAttendu[0].ligne = 13;
        coordsAttendu[0].colonne = 4;
        coordsAttendu[1].ligne = 11;
        coordsAttendu[1].colonne = 4;
        coordsAttendu[2].ligne = 12;
        coordsAttendu[2].colonne = 5;
        coordsAttendu[3].ligne = 12;
        coordsAttendu[3].colonne = 3;
        Assert.assertArrayEquals(coordsAttendu, joueur.arbreVoisins(plateau, coord));

        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU6);
        coord.ligne = 12;
        coord.colonne = 4;
        coordsAttendu[0].ligne = -1;
        coordsAttendu[0].colonne = -1;
        coordsAttendu[1].ligne = 11;
        coordsAttendu[1].colonne = 4;
        coordsAttendu[2].ligne = 12;
        coordsAttendu[2].colonne = 5;
        coordsAttendu[3].ligne = 12;
        coordsAttendu[3].colonne = 3;
        Assert.assertArrayEquals(coordsAttendu, joueur.arbreVoisins(plateau, coord));
        //Assert.assertEquals(coordsAttendu[0].ligne, joueur.arbreVoisins(plateau, coord)[0].ligne);
    }

    /**
     * Test de la fonction RegardeSiArbre
     */
    @Test
    public void testRegardeSiArbre() {
        JoueurBiosphere7 joueur = new JoueurBiosphere7();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3);

        Coordonnees coordTest = new Coordonnees(0, 0);
        Coordonnees coordNull = new Coordonnees(-1, -1);
        Assert.assertEquals(coordNull, joueur.regardeSiArbre(plateau, coordTest));

        coordTest.colonne = 1;
        coordTest.ligne = 1;
        Assert.assertEquals(coordTest, joueur.regardeSiArbre(plateau, coordTest));
    }

    /**
     * Test de la fonction etouffe
     */
    @Test
    public void testEtouffe() {
        JoueurBiosphere7 joueur = new JoueurBiosphere7();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);

        Coordonnees coord = new Coordonnees(0, 0);
        Assert.assertEquals(false, joueur.etouffe(plateau, coord, 4));
        coord.ligne = 12;
        coord.colonne = 4;
        Assert.assertEquals(true, joueur.etouffe(plateau, coord, 4));
        coord.ligne = 0;
        coord.colonne = 13;
        Assert.assertEquals(false, joueur.etouffe(plateau, coord, 4));

        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU6);
        Assert.assertEquals(false, joueur.etouffe(plateau, Coordonnees.depuisCars('m', 'E'), 4));
        Assert.assertEquals(false, joueur.etouffe(plateau, Coordonnees.depuisCars('f', 'A'), 4));
        Assert.assertEquals(false, joueur.etouffe(plateau, Coordonnees.depuisCars('f', 'B'), 3));

    }

    @Test
    public void testSymbiose() {
        JoueurBiosphere7 joueur = new JoueurBiosphere7();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);
        char couleurJoueur = 'R';
        assertEquals(1, joueur.vitalitePlanterSymbiose(plateau, joueur.arbreVoisins(plateau, Coordonnees.depuisCars('a', 'A')), 6, couleurJoueur));

        assertEquals(1, joueur.vitalitePlanterSymbiose(plateau, joueur.arbreVoisins(plateau, Coordonnees.depuisCars('f', 'A')), 6, couleurJoueur));
        assertEquals(4, joueur.vitalitePlanterSymbiose(plateau, joueur.arbreVoisins(plateau, Coordonnees.depuisCars('e', 'I')), 6, couleurJoueur));
       // assertEquals(3, joueur.vitalitePlanterSymbiose(plateau, joueur.arbreVoisins(plateau, Coordonnees.depuisCars('e', 'M')), 6, couleurJoueur));
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
     */
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
     * Un plateau pour tester le niveau 4.
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
}
