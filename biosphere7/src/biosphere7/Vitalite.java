/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biosphere7;

import static biosphere7.Utils.CAR_VIDE;

/**
 *
 * @author aclaveau
 */
public class Vitalite {

    /**
     * Vitalité sur le plateau.
     */
    int[] vitalite;

    /**
     * Constructeur de la class vitalité
     *
     * @param r la vitalité des rouges
     * @param b la vitalité des bleus
     */
    Vitalite(int r, int b) {
        vitalite = new int[]{r, b};
    }

    /**
     * Constructeur vitalité vide rouge = 0 et bleu = 0
     */
    Vitalite() {
        vitalite = new int[]{0, 0};
    }

    /**
     * Calcul le total de vitalité des arbres par joueurs sur le plateau suivant
     * l'action et les renvoi
     *
     * @param plateau le plateau considéré
     * @param couleurJoueur couleur du joueur
     * @param action l'action a réaliser (couper, planter, ...)
     * @param coordCase coordonée de la case courante
     * @param niveau niveau de jeu
     */
    void calculVitalite(Case[][] plateau, char couleurJoueur, char action, Coordonnees coordCase, int niveau) {
        vitalite[1] = 0;
        vitalite[0] = 0;
        for (int lig = 0; lig < Coordonnees.NB_LIGNES; lig++) { // parcour tout le tableau et compte les arbres avec elur vitalité
            for (int col = 0; col < Coordonnees.NB_COLONNES; col++) {
                if (plateau[lig][col].espece != CAR_VIDE) {
                    if (plateau[lig][col].couleur == Utils.CAR_ROUGE) {
                        vitalite[0] += plateau[lig][col].vitalite;
                    } else if (plateau[lig][col].couleur == Utils.CAR_BLEU) {
                        vitalite[1] += plateau[lig][col].vitalite;
                    }
                }
            }
        }
        Coordonnees[] coordsVoisinPlein = Utils.plantesVoisines(plateau, coordCase, false);
        Coordonnees[] coordsVoisinVide = Utils.plantesVoisines(plateau, coordCase, true);
        switch (action) {
            case 'P':
            case 'S':
            case 'B':
            case 'D':
            case 'T':
            case 'H':
                // quand on plante une plante
                if (!Utils.etouffe(plateau, coordCase, 4) && plateau[coordCase.ligne][coordCase.colonne].espece == CAR_VIDE) {
                    ajoutVitalite(plateau, coordCase, couleurJoueur, true, vitalitePlanterSymbiose(plateau, coordsVoisinPlein, niveau, couleurJoueur));
                    vitalite[0] -= vitaliteArbresVoisinsEtouffent(plateau, coordsVoisinPlein, niveau, Utils.CAR_ROUGE);
                    vitalite[1] -= vitaliteArbresVoisinsEtouffent(plateau, coordsVoisinPlein, niveau, Utils.CAR_BLEU);
                }
                break;
            case 'F':
                // quand on fertilise
                if (Utils.estDansPlateau(plateau, coordCase)) {
                    char espece = plateau[coordCase.ligne][coordCase.colonne].espece;
                    switch (espece) {
                        case 'P':
                        case 'S':
                            // Arbres
                            ajoutVitalite(plateau, coordCase, couleurJoueur, false, 1);
                            break;
                        case 'B':
                            // Arbustes
                            ajoutVitalite(plateau, coordCase, couleurJoueur, false, 2);
                            break;
                        case 'D':
                        case 'T':
                        case 'H':
                            // Légumes
                            ajoutVitalite(plateau, coordCase, couleurJoueur, false, 3);
                            break;
                        default:
                            // vide
                            break;
                    }
                }
                break;
            case 'C':
                // quand on coupe un arbre
                if (Utils.estDansPlateau(plateau, coordCase)) {
                    Case caseCentrale = plateau[coordCase.ligne][coordCase.colonne];
                    ajoutVitalite(plateau, coordCase, couleurJoueur, false, -caseCentrale.vitalite);

                    for (int i = 0; i < 4; i++) {
                        if (coordsVoisinPlein[i].ligne != -1 && coordsVoisinPlein[i].colonne != -1) {
                            ajoutVitalite(plateau, coordsVoisinPlein[i], couleurJoueur, false, 1);
                        }
                    }
                }
                break;
            case 'I':
                // quand on dissémine
                if (plateau[coordCase.ligne][coordCase.colonne].espece != Utils.CAR_VIDE
                        && !Utils.estAutoFéconde(plateau, coordCase)
                        && Utils.unVoisinDeLaMemeEspece(plateau, coordCase)) {
                    //quand on a des plantes autoStériles
                    for (int i = 0; i < coordsVoisinPlein.length; i++) {
                        if (coordsVoisinPlein[i].ligne == -1
                                && coordsVoisinPlein[i].colonne == -1
                                && Utils.estDansPlateau(plateau, coordsVoisinVide[i])) {
                            ajoutVitalite(plateau,  new Coordonnees(coordsVoisinVide[i].ligne, coordsVoisinVide[i].colonne),
                                    couleurJoueur, true,Utils.vitaliteVoisinPlusFaible(plateau,coordCase));
                        }
                    }
                } else if (plateau[coordCase.ligne][coordCase.colonne].espece != Utils.CAR_VIDE
                        && Utils.estAutoFéconde(plateau, coordCase)) {
                    //quand on a des plantes autoFécondes 
                    for (int i = 0; i < coordsVoisinPlein.length; i++) {
                        if (coordsVoisinPlein[i].ligne == -1 && coordsVoisinPlein[i].colonne == -1 
                                && Utils.estDansPlateau(plateau, coordsVoisinVide[i])) {
                            ajoutVitalite(plateau,  new Coordonnees(coordsVoisinVide[i].ligne, coordsVoisinVide[i].colonne), couleurJoueur, true, 1);
                        }
                    }
                }
                break;

            default:
                System.out.println("Action non valide pour le calcule de vitalité");
                break;
        }
    }

    /**
     * Renvoi la vitalité à ajouter suivant les arbres autours d'elle Attention
     * à tester si la case centrale (dont sont issus les cases voisines) est
     * bien vide !!!!
     *
     * @param plateau le plateau considéré
     * @param coordsVoisin tableau contenant les coordonées de arbres voisons si
     * il y en as, sinon -1,-1
     * @param niveau niveau de jeu
     * @param couleurJoueur couleur du joueur
     * @return vitalité à ajouter
     */
    static int vitalitePlanterSymbiose(Case[][] plateau, Coordonnees[] coordsVoisin, int niveau, char couleurJoueur) {
        int compteur = 1; // au moins un car on plante un arbre
        if (niveau >= 5) {
            for (int i = 0; i < 4; i++) {
                if (coordsVoisin[i].ligne != -1 && coordsVoisin[i].colonne != -1) {
                    if (plateau[coordsVoisin[i].ligne][coordsVoisin[i].colonne].couleur == couleurJoueur) {
                        compteur++;
                    }
                }
            }
        }

        return compteur;
    }

    /**
     * Renvoi la vitalité à enlever suivant si les arbres voisins étouffent et
     * leur couleur
     *
     * @param plateau le plateau considéré
     * @param coordsVoisin tableau contenant les coordonées de arbres voisons si
     * il y en as, sinon -1,-1
     * @param niveau niveau de jeu
     * @param couleurCase couleur de l'arbre centrale
     * @return vitalité à enlever
     */
    static int vitaliteArbresVoisinsEtouffent(Case[][] plateau, Coordonnees[] coordsVoisin, int niveau, char couleurCase) {
        int compteur = 0;
        if (niveau >= 6) {
            for (int i = 0; i < 4; i++) {
                if (coordsVoisin[i].ligne != -1 && coordsVoisin[i].colonne != -1) {
                    if (Utils.etouffe(plateau, coordsVoisin[i], 3) 
                            && couleurCase == plateau[coordsVoisin[i].ligne][coordsVoisin[i].colonne].couleur) {
                        // etouffe à trois arbres parceque l'on vien d'en planter un à coté mais qu'il n'est que imaginé pour le moment
                        compteur += plateau[coordsVoisin[i].ligne][coordsVoisin[i].colonne].vitalite;
                    }
                }
            }
        }
        return compteur;
    }

    /**
     * Ajoute la vitalité suivant la couleur de la case ou celle du joueur
     * courant
     *
     * @param plateau le plateau considéré
     * @param coordsCase coordonées de la case courante
     * @param couleurJoueur la couleur du joueur courant
     * @param joueurOuCase Si true alors ajoute la vitalité suivant la couleur
     * du joueur courant sinon ajoute la vitalité suivant la couleur de la case
     * courante
     * @param vitAjout le nombre de vitalité à ajouter
     */
    void ajoutVitalite(Case[][] plateau, Coordonnees coordsCase, char couleurJoueur, boolean joueurOuCase, int vitAjout) {
        // attention à ce que les coordonnées de case en paramètre soit bien dans le tableau !
        if (!joueurOuCase) {
            if (plateau[coordsCase.ligne][coordsCase.colonne].espece != Utils.CAR_VIDE) {
                if (plateau[coordsCase.ligne][coordsCase.colonne].couleur == Utils.CAR_BLEU) {
                    if (plateau[coordsCase.ligne][coordsCase.colonne].vitalite + vitAjout <= 9) {
                        vitalite[1] += vitAjout;
                    } else {
                        vitalite[1] += 9 - plateau[coordsCase.ligne][coordsCase.colonne].vitalite;
                    }
                } else if (plateau[coordsCase.ligne][coordsCase.colonne].couleur == Utils.CAR_ROUGE) {
                    if (plateau[coordsCase.ligne][coordsCase.colonne].vitalite + vitAjout <= 9) {
                        vitalite[0] += vitAjout;
                    } else {
                        vitalite[0] += 9 - plateau[coordsCase.ligne][coordsCase.colonne].vitalite;
                    }
                }
            }
        } else {
            if (couleurJoueur == Utils.CAR_BLEU) {
                if (plateau[coordsCase.ligne][coordsCase.colonne].vitalite + vitAjout <= 9) {
                    vitalite[1] += vitAjout;
                } else {
                    vitalite[1] += 9 - plateau[coordsCase.ligne][coordsCase.colonne].vitalite;
                }
            } else if (couleurJoueur == Utils.CAR_ROUGE) {
                if (plateau[coordsCase.ligne][coordsCase.colonne].vitalite + vitAjout <= 9) {
                    vitalite[0] += vitAjout;
                } else {
                    vitalite[0] += 9 - plateau[coordsCase.ligne][coordsCase.colonne].vitalite;
                }
            }
        }
    }
}
