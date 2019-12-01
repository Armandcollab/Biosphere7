package biosphere7;

import static biosphere7.Utils.CAR_VIDE;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Joueur implémentant les actions possibles à partir d'un plateau, pour un
 * niveau donné.
 */
public class JoueurBiosphere7 implements IJoueurBiosphere7 {

    /**
     * Nombre maximal d'actions possibles, tous niveaux confondus.
     */
    final static int MAX_NB_ACTIONS = 35285;

    /**
     * Compte le nombre d'actions possibles déjà entrées dans le tableau des
     * actions possibles.
     */
    int nbActions;

    /**
     * Cette méthode renvoie, pour un plateau donné et un joueur donné, toutes
     * les actions possibles pour ce joueur.
     *
     * @param plateau le plateau considéré
     * @param couleurJoueur couleur du joueur
     * @param niveau le niveau de la partie à jouer
     * @return l'ensemble des actions possibles
     */
    @Override
    public String[] actionsPossibles(Case[][] plateau, char couleurJoueur, int niveau) {
        // afficher l'heure de lancement
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        System.out.println("actionsPossibles : lancement le "
                + format.format(new Date()));
        // calculer les actions possibles
        String actions[] = new String[MAX_NB_ACTIONS];
        nbActions = 0;
        for (int lig = 0; lig < Coordonnees.NB_LIGNES; lig++) { // Lorsque l'on plante unne plante
            for (int col = 0; col < Coordonnees.NB_COLONNES; col++) {
                Coordonnees coord = new Coordonnees(lig, col);
                Vitalite vit = new Vitalite();
                for (int i=0; i<Utils.ESPECES.length ; i++) {
                    vit.calculVitalite(plateau, couleurJoueur, Utils.ESPECES[i], coord, niveau);
                    if (plateau[lig][col].espece == Utils.CAR_VIDE && !Utils.etouffe(plateau, coord, 4) && plateau[lig][col].nature == Utils.CAR_TERRE) {
                        ajoutAction(coord, actions, Utils.ESPECES[i]+"", vit);
                    }
                }
                if (plateau[lig][col].espece != Utils.CAR_VIDE && !Utils.estAutoFéconde(plateau, coord) && Utils.unVoisinDeLaMemeEspece(plateau, coord)) {
                    vit.calculVitalite(plateau, couleurJoueur, 'I', coord, niveau);
                    ajoutAction(coord, actions, "I", vit);
                } else if (plateau[lig][col].espece != Utils.CAR_VIDE && Utils.estAutoFéconde(plateau, coord)) {
                    vit.calculVitalite(plateau, couleurJoueur, 'I', coord, niveau);
                    ajoutAction(coord, actions, "I", vit);
                }
                Coordonnees coordsCasePourVoisin = new Coordonnees(-1, -1); // Lorsque l'on coupe / fertilise une plante / rotationne
                if (plateau[lig][col].espece != CAR_VIDE) {
                    coordsCasePourVoisin.ligne = lig;
                    coordsCasePourVoisin.colonne = col;
                    vit.calculVitalite(plateau, couleurJoueur, 'C', coordsCasePourVoisin, niveau);
                    ajoutAction(coordsCasePourVoisin, actions, "C", vit);
                    vit.calculVitalite(plateau, couleurJoueur, 'F', coordsCasePourVoisin, niveau);
                    ajoutAction(coordsCasePourVoisin, actions, "F", vit);
                    if (plateau[lig][col].couleur == couleurJoueur) {
                        for (int i = 0; i < Utils.ESPECES.length; i++) {
                            if (Utils.ESPECES[i] != plateau[lig][col].espece) {
                                vit.calculVitalite(plateau, couleurJoueur, 'R', coordsCasePourVoisin, niveau);
                                ajoutAction(coordsCasePourVoisin, actions, "R" + Utils.ESPECES[i], vit);
                            }
                        }
                    }

                }
            }
        }
        //ombre 
        Vitalite vit = new Vitalite();
        Coordonnees coordNULL = new Coordonnees(0, 0);
        vit.calculVitalite(plateau, couleurJoueur, ' ', coordNULL, niveau);
        int[] vitAvant = {vit.vitalite[0], vit.vitalite[1]};
        vit.calculVitalite(plateau, couleurJoueur, 'O', coordNULL, niveau);
        if (vitAvant[0] != vit.vitalite[0] || vitAvant[1] != vit.vitalite[1]) {
            ajoutAction(coordNULL, actions, "O", vit);
        }

        System.out.println("actionsPossibles : fin");
        return Utils.nettoyerTableau(actions);
    }

    /**
     * Ajout d'une action de plantation dans l'ensemble des actions possibles.
     *
     * @param coord coordonnées de la case où planter le pommier
     * @param actions l'ensemble des actions possibles (en construction)
     * @param StringAction le(s) caractère(s) correspondant à l'action à exécuter
     */
    void ajoutAction(Coordonnees coord, String[] actions, String StringAction, Vitalite vit) {
        if (!"O".equals(StringAction)) {
            String action = StringAction + "" + coord.carLigne() + "" + coord.carColonne() + "," + vit.vitalite[0] + "," + vit.vitalite[1];
            actions[nbActions] = action;
            nbActions++;
        } else {
            String action = StringAction + "," + vit.vitalite[0] + "," + vit.vitalite[1];
            actions[nbActions] = action;
            nbActions++;
        }
    }

    void test() {
        Vitalite v = new Vitalite();
        System.out.println(v.vitalite[1]);
    }
}
