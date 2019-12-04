package biosphere7;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests sur la classe Utils.
 */
public class UtilsTest {

    /**
     * Test de la fonction caseDepuisCodage.
     */
    @Test
    public void testCaseDepuisCodage() {
        Case laCase;
        // case vide
        laCase = Utils.caseDepuisCodage("---", "   ");
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(Utils.CAR_VIDE, laCase.espece);
        // un pommier possédé par les rouges
        laCase = Utils.caseDepuisCodage("---", "PR4");
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(Utils.ESPECES[0], laCase.espece);
        assertEquals(Utils.CAR_ROUGE, laCase.couleur);
        assertEquals(4, laCase.vitalite);
        // un pommier possédé par les bleus
        laCase = Utils.caseDepuisCodage("---", "PB9");
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(Utils.ESPECES[0], laCase.espece);
        assertEquals(Utils.CAR_BLEU, laCase.couleur);
        assertEquals(9, laCase.vitalite);
    }

    /**
     * Test de la fonction plateauDepuisTexte().
     */
    @Test
    public void testPlateauDepuisTexte() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU1);
        Case laCase;
        // une case avec un pommier bleu
        laCase = plateau[0][0];
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(Utils.ESPECES[0], laCase.espece);
        assertEquals(Utils.CAR_BLEU, laCase.couleur);
        assertEquals(4, laCase.vitalite);
        // une case avec un pommier rouge
        laCase = plateau[13][13];
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(Utils.ESPECES[0], laCase.espece);
        assertEquals(Utils.CAR_ROUGE, laCase.couleur);
        assertEquals(1, laCase.vitalite);
        // une case vide
        laCase = plateau[0][1];
        assertEquals(Utils.CAR_TERRE, laCase.nature);
        assertEquals(Utils.CAR_VIDE, laCase.espece);
    }

    /**
     * Test de la fonction nettoyerTableau().
     */
    @Test
    public void testNettoyerTableau() {

        String tab[], tabNettoye[];

        // tableau de taille 0
        tab = new String[0];
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(0, tabNettoye.length);

        // tableau de taille 1 avec 1 élément
        tab = new String[1];
        tab[0] = "coucou";
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(1, tabNettoye.length);
        assertEquals("coucou", tabNettoye[0]);

        // tableau de taille 1 avec 0 élément (null)
        tab = new String[1];
        tab[0] = null;
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(0, tabNettoye.length);

        // tableau de taille 1 avec 0 élément ("")
        tab = new String[1];
        tab[0] = "";
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(0, tabNettoye.length);

        // tableau de taille 2 avec 1 élément ("")
        tab = new String[2];
        tab[0] = "";
        tab[1] = "hello";
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(1, tabNettoye.length);
        assertEquals("hello", tabNettoye[0]);

        // un cas plus complet
        tab = new String[7];
        tab[0] = "";
        tab[1] = "hello";
        tab[2] = null;
        tab[3] = "";
        tab[4] = "hello";
        tab[5] = "";
        tab[6] = null;
        tabNettoye = Utils.nettoyerTableau(tab);
        assertEquals(2, tabNettoye.length);
        assertEquals("hello", tabNettoye[0]);
        assertEquals("hello", tabNettoye[1]);
    }

    /**
     * Test de la methode unVoisinDeLaMemeEspece
     */
    @Test
    public void testUnVoisinDeLaMemeEspece() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU2);

        assertTrue(Utils.unVoisinDeLaMemeEspece(plateau, Coordonnees.depuisCars('a', 'A')));
        assertFalse(Utils.unVoisinDeLaMemeEspece(plateau, Coordonnees.depuisCars('f', 'B')));
        assertTrue(Utils.unVoisinDeLaMemeEspece(plateau, Coordonnees.depuisCars('f', 'D')));
        assertTrue(Utils.unVoisinDeLaMemeEspece(plateau, Coordonnees.depuisCars('e', 'D')));
    }

    /**
     * Test de la methode vitaliteVoisinPlusFaible
     */
    @Test
    public void testVitaliteVoisinPlusFaible() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU2);

        assertEquals(1, Utils.vitaliteVoisinPlusFaible(plateau, Coordonnees.depuisCars('f', 'D')));
        assertEquals(2, Utils.vitaliteVoisinPlusFaible(plateau, Coordonnees.depuisCars('f', 'E')));
        assertEquals(6, Utils.vitaliteVoisinPlusFaible(plateau, Coordonnees.depuisCars('g', 'D')));
        assertEquals(1, Utils.vitaliteVoisinPlusFaible(plateau, Coordonnees.depuisCars('d', 'D')));
    }

    /**
     * Test de la methode estAutoSteril
     */
    @Test
    public void testEstAutoSteril() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU2);

        assertTrue(Utils.estAutoFéconde(plateau, Coordonnees.depuisCars('a', 'A')));
        assertTrue(Utils.estAutoFéconde(plateau, Coordonnees.depuisCars('e', 'D')));
        assertFalse(Utils.estAutoFéconde(plateau, Coordonnees.depuisCars('f', 'D')));
        assertFalse(Utils.estAutoFéconde(plateau, Coordonnees.depuisCars('e', 'E')));
    }

    /**
     * Test de la fonction RegardeSiArbre
     */
    @Test
    public void testRegardeSiArbre() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3);

        Coordonnees coordTest = new Coordonnees(0, 0);
        Coordonnees coordNull = new Coordonnees(-1, -1);
        Assert.assertEquals(coordNull, Utils.regardeSiArbre(plateau, coordTest, false));

        coordTest.colonne = 1;
        coordTest.ligne = 1;
        Assert.assertEquals(coordTest, Utils.regardeSiArbre(plateau, coordTest, false));
    }

    /**
     * Test de la fonction ArbreVoisins
     */
    @Test
    public void testArbresVoisins() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU3);

        Coordonnees[] coordsAttendu = new Coordonnees[4];
        for (int i = 0; i < 4; i++) {
            coordsAttendu[i] = new Coordonnees(-1, -1);
        }

        Coordonnees coord = new Coordonnees(1, 1);
        Assert.assertArrayEquals(coordsAttendu, Utils.plantesVoisines(plateau, coord, false));

        coord.ligne = 0;
        coord.colonne = 12;
        coordsAttendu[2].ligne = 0;
        coordsAttendu[2].colonne = 13;
        //Assert.assertArrayEquals(coordsAttendu, joueur.arbreVoisins(plateau, coord));
        Assert.assertEquals(coordsAttendu[2].colonne, Utils.plantesVoisines(plateau, coord, false)[2].colonne);

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
        Assert.assertArrayEquals(coordsAttendu, Utils.plantesVoisines(plateau, coord, false));

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
        Assert.assertArrayEquals(coordsAttendu, Utils.plantesVoisines(plateau, coord, false));
        //Assert.assertEquals(coordsAttendu[0].ligne, joueur.arbreVoisins(plateau, coord)[0].ligne);
    }

    /**
     * Test de la fonction etouffe
     */
    @Test
    public void testEtouffe() {
        JoueurBiosphere7 joueur = new JoueurBiosphere7();
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU4);

        Coordonnees coord = new Coordonnees(0, 0);
        Assert.assertEquals(false, Utils.etouffe(plateau, coord, 4));
        coord.ligne = 12;
        coord.colonne = 4;
        Assert.assertEquals(true, Utils.etouffe(plateau, coord, 4));
        coord.ligne = 0;
        coord.colonne = 13;
        Assert.assertEquals(false, Utils.etouffe(plateau, coord, 4));

        plateau = Utils.plateauDepuisTexte(PLATEAU_NIVEAU6);
        Assert.assertEquals(false, Utils.etouffe(plateau, Coordonnees.depuisCars('m', 'E'), 4));
        Assert.assertEquals(false, Utils.etouffe(plateau, Coordonnees.depuisCars('f', 'A'), 4));
        Assert.assertEquals(false, Utils.etouffe(plateau, Coordonnees.depuisCars('f', 'B'), 3));

    }

    /**
     * Test de la methode regardeSiVoisinEau
     */
    @Test
    public void testRegardeSiVoisinEau() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU1);
        assertEquals(1, Utils.regardeSiVoisinEau(plateau, Coordonnees.depuisCars('f', 'B')));
        assertEquals(1, Utils.regardeSiVoisinEau(plateau, Coordonnees.depuisCars('e', 'C')));
        assertEquals(1, Utils.regardeSiVoisinEau(plateau, Coordonnees.depuisCars('e', 'C')));
        assertEquals(1, Utils.regardeSiVoisinEau(plateau, Coordonnees.depuisCars('f', 'D')));
        assertEquals(1, Utils.regardeSiVoisinEau(plateau, Coordonnees.depuisCars('g', 'C')));
        assertEquals(0, Utils.regardeSiVoisinEau(plateau, Coordonnees.depuisCars('f', 'A')));

    }

    /**
     * Test de la methode calculDistanceManhattan
     */
    @Test
    public void testCalculDistanceManhattan() {
        Coordonnees coord1 = new Coordonnees(0, 0);
        Coordonnees coord2 = new Coordonnees(1, 0);
        assertEquals(0, Utils.calculDistanceManhattan(coord1, coord1));
        assertEquals(0, Utils.calculDistanceManhattan(coord2, coord2));
        assertEquals(1, Utils.calculDistanceManhattan(coord1, coord2));
        assertEquals(1, Utils.calculDistanceManhattan(coord2, coord1));
        coord2.colonne = 1;
        assertEquals(2, Utils.calculDistanceManhattan(coord2, coord1));
        assertEquals(2, Utils.calculDistanceManhattan(coord1, coord2));
        coord2.colonne = 8;
        assertEquals(9, Utils.calculDistanceManhattan(coord2, coord1));
        assertEquals(9, Utils.calculDistanceManhattan(coord1, coord2));
        coord2.ligne = 12;
        assertEquals(20, Utils.calculDistanceManhattan(coord2, coord1));
        assertEquals(20, Utils.calculDistanceManhattan(coord1, coord2));
    }

    /**
     * Test de la methode regardeSiArbreVoisinDejaTrouve
     */
    @Test
    public void testRegardeSiArbreVoisinDejaTrouve() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_FORET);
        Coordonnees[] tabForet = {Coordonnees.depuisCars('g', 'G'), null, null, null, null, null};
        Coordonnees[] tabForetAttendu = {Coordonnees.depuisCars('g', 'G'), Coordonnees.depuisCars('h', 'G'), null, null, null, null};

        assertArrayEquals(tabForetAttendu, Utils.regardeSiPlanteVoisineDejaTrouve(plateau, tabForet,'P',Utils.CAR_TERRE));

        /*tabForet[0] = Coordonnees.depuisCars('m', 'C');
        tabForetAttendu[0] = Coordonnees.depuisCars('m', 'C');
        tabForetAttendu[1] = Coordonnees.depuisCars('m', 'D');
        assertArrayEquals(tabForetAttendu, Utils.regardeSiArbreVoisinDejaTrouve(plateau, tabForet));
        tabForetAttendu[2] = Coordonnees.depuisCars('l', 'C');
        assertArrayEquals(tabForetAttendu, Utils.regardeSiArbreVoisinDejaTrouve(plateau, tabForet));*/

        Coordonnees[] tabForetPlein = {Coordonnees.depuisCars('c', 'I'), Coordonnees.depuisCars('c', 'J'), Coordonnees.depuisCars('c', 'K'),
            Coordonnees.depuisCars('c', 'L'), Coordonnees.depuisCars('c', 'M'), Coordonnees.depuisCars('c', 'N')};

        assertArrayEquals(tabForetPlein, Utils.regardeSiPlanteVoisineDejaTrouve(plateau, tabForetPlein,'P',Utils.CAR_TERRE));

        Coordonnees[] tabForet2 = {Coordonnees.depuisCars('g', 'G'), Coordonnees.depuisCars('h', 'G'), null, null, null, null};

        assertArrayEquals(tabForet2, Utils.regardeSiPlanteVoisineDejaTrouve(plateau, tabForet2,'P',Utils.CAR_TERRE));
    }    
    
    /**
     * Test de la methode esrEnLisière
     */
    @Test
    public void testEstEnLisière() {
        Case[][] plateau = Utils.plateauDepuisTexte(PLATEAU_FORET);
        assertFalse(Utils.esrEnLisière(plateau, Coordonnees.depuisCars('g', 'G')));
        assertTrue(Utils.esrEnLisière(plateau, Coordonnees.depuisCars('i', 'G')));
        assertTrue(Utils.esrEnLisière(plateau, Coordonnees.depuisCars('m', 'G')));
        assertFalse(Utils.esrEnLisière(plateau, Coordonnees.depuisCars('a', 'A')));
        assertTrue(Utils.esrEnLisière(plateau, Coordonnees.depuisCars('b', 'J')));
        assertTrue(Utils.esrEnLisière(plateau, Coordonnees.depuisCars('b', 'N')));
        assertTrue(Utils.esrEnLisière(plateau, Coordonnees.depuisCars('c', 'K')));
        assertTrue(Utils.esrEnLisière(plateau, Coordonnees.depuisCars('d', 'N')));
    }

    /**
     * Test de la methode nbrCasePleinTab
     */
    public void testNbrCasePleineTab() {
        Coordonnees[] tabTest = {new Coordonnees(0, 0),new Coordonnees(0, 0),new Coordonnees(0, 0),null,null,null};
        Coordonnees[] tabTest2 = {null,null,null};
        Coordonnees[] tabTest3 = {new Coordonnees(0, 0),new Coordonnees(0, 0),new Coordonnees(0, 0)};
        
        assertEquals(3,Utils.nbrDeCasePleineDansUnTableau(tabTest));
        assertEquals(0,Utils.nbrDeCasePleineDansUnTableau(tabTest2));
        assertEquals(3,Utils.nbrDeCasePleineDansUnTableau(tabTest3));       
    }
    
    /**
     * Plateau de test 1
     */
    final String PLATEAU1
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|PB4|   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|PR9|   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+E--+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |PR3|   |   |   |   |   |   |   |   |   |   |   |   |\n"
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
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |PR1|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";

    /**
     * Plateau de test 2
     */
    final String PLATEAU2
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|PB4|   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|PR9|   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |DR8|   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |DR5|DB1|TR9|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |PR3|HR1|HR8|HR2|HR2|   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |HB7|HR9|HR6|   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |PR1|   |   |   |   |   |   |   |   |   |   |\n"
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
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |PR1|\n"
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
     * Plateau de test 1
     */
    final String PLATEAU_FORET
            = "   A   B   C   D   E   F   G   H   I   J   K   L   M   N \n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "a|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "b|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "c|   |   |   |   |   |   |   |   |PR1|PB1|PR1|PR1|PR1|PR9|\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "d|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "e|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "f|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "g|   |   |   |   |   |   |PR1|   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "h|   |   |   |   |   |   |PR1|   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "i|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "j|   |   |   |   |   |   |PR1|PR1|   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "k|   |   |   |   |   |PR1|PR1|PR1|   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "l|   |   |PR1|   |   |   |PR1|   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "m|   |   |PR1|PR1|   |   |   |PR1|   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n"
            + "n|   |   |   |   |   |   |   |   |   |   |   |   |   |   |\n"
            + " +---+---+---+---+---+---+---+---+---+---+---+---+---+---+\n";
}
