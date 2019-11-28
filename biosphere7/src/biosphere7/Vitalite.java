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
     * @param b lavitalité des bleus
     */
    Vitalite(int r, int b) {
        vitalite = new int[]{r, b};
    }
    /**
     * Constructeur vitalité vide
     * rouge = 0 et bleu = 0
     */
    Vitalite(){
        vitalite = new int[]{0,0};
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
        int bleu = 0;
        int rouge = 0;
        for (int lig = 0; lig < Coordonnees.NB_LIGNES; lig++) { // parcour tout le tableau et compte les arbres avec elur vitalité
            for (int col = 0; col < Coordonnees.NB_COLONNES; col++) {
                if (plateau[lig][col].espece != CAR_VIDE) {
                    if (plateau[lig][col].couleur == Utils.CAR_ROUGE) {
                        rouge += plateau[lig][col].vitalite;
                    } else if (plateau[lig][col].couleur == Utils.CAR_BLEU) {
                        bleu += plateau[lig][col].vitalite;
                    }
                }
            }
        }
        Coordonnees[] coordsVoisin = Utils.arbreVoisins(plateau, coordCase);

        if (action != 'C' && action != 'F') {// quand on plante un pommier
            if (!Utils.etouffe(plateau, coordCase, 4) && plateau[coordCase.ligne][coordCase.colonne].espece == CAR_VIDE) {
                if (couleurJoueur == Utils.CAR_ROUGE) {
                    rouge += vitalitePlanterSymbiose(plateau, coordsVoisin, niveau, Utils.CAR_ROUGE);

                } else if (couleurJoueur == Utils.CAR_BLEU) {
                    bleu += vitalitePlanterSymbiose(plateau, coordsVoisin, niveau, Utils.CAR_BLEU);

                }
                rouge -= vitaliteArbresVoisinsEtouffent(plateau, coordsVoisin, niveau, Utils.CAR_ROUGE);
                bleu -= vitaliteArbresVoisinsEtouffent(plateau, coordsVoisin, niveau, Utils.CAR_BLEU);

            }

        } else if (action == 'F') {
            if (coordCase.ligne < Coordonnees.NB_LIGNES && coordCase.ligne >= 0 && coordCase.colonne < Coordonnees.NB_COLONNES && coordCase.colonne >= 0) {
                char espece = plateau[coordCase.ligne][coordCase.colonne].espece;
                Case caseCentrale = plateau[coordCase.ligne][coordCase.colonne];
                switch (espece) {
                    case 'P':
                    case 'S':
                        // Arbres
                        if (caseCentrale.vitalite < 9) {
                            if (caseCentrale.couleur == Utils.CAR_BLEU) {
                                bleu++;
                            } else if (caseCentrale.couleur == Utils.CAR_ROUGE) {
                                rouge++;
                            }
                        }
                        break;
                    case 'B':
                        // Arbustes
                        if (caseCentrale.vitalite + 2 <= 9) {
                            if (caseCentrale.couleur == Utils.CAR_BLEU) {
                                bleu += 2;
                            } else if (caseCentrale.couleur == Utils.CAR_ROUGE) {
                                rouge += 2;
                            }
                        } else {
                            if (caseCentrale.couleur == Utils.CAR_BLEU) {
                                bleu += 9 - caseCentrale.vitalite;
                            } else if (caseCentrale.couleur == Utils.CAR_ROUGE) {
                                rouge += 9 - caseCentrale.vitalite;;
                            }
                        }
                        break;
                    case 'D':
                    case 'T':
                    case 'H':
                        // Légumes
                        if (caseCentrale.vitalite + 3 <= 9) {
                            if (caseCentrale.couleur == Utils.CAR_BLEU) {
                                bleu += 3;
                            } else if (caseCentrale.couleur == Utils.CAR_ROUGE) {
                                rouge += 3;
                            }
                        } else {
                            if (caseCentrale.couleur == Utils.CAR_BLEU) {
                                bleu += 9 - caseCentrale.vitalite;
                            } else if (caseCentrale.couleur == Utils.CAR_ROUGE) {
                                rouge += 9 - caseCentrale.vitalite;;
                            }
                        }
                        break;
                    default:
                        // vide
                        break;
                }
            }

        } else if (action == 'C') {     // quand on coupe un arbre
            if (coordCase.ligne < Coordonnees.NB_LIGNES && coordCase.ligne >= 0 && coordCase.colonne < Coordonnees.NB_COLONNES && coordCase.colonne >= 0) {
                Case caseCentrale = plateau[coordCase.ligne][coordCase.colonne];

                if (caseCentrale.espece != CAR_VIDE && caseCentrale.couleur == Utils.CAR_BLEU) {
                    bleu -= caseCentrale.vitalite;

                } else if (caseCentrale.espece != CAR_VIDE && caseCentrale.couleur == Utils.CAR_ROUGE) {
                    rouge -= caseCentrale.vitalite;

                }

                for (int i = 0; i < 4; i++) {
                    if (coordsVoisin[i].ligne != -1 && coordsVoisin[i].colonne != -1) {
                        if (plateau[coordsVoisin[i].ligne][coordsVoisin[i].colonne].couleur == Utils.CAR_BLEU
                                && plateau[coordsVoisin[i].ligne][coordsVoisin[i].colonne].vitalite < 9) {
                            bleu++;
                        } else if (plateau[coordsVoisin[i].ligne][coordsVoisin[i].colonne].couleur == Utils.CAR_ROUGE
                                && plateau[coordsVoisin[i].ligne][coordsVoisin[i].colonne].vitalite < 9) {
                            rouge++;
                        }
                    }
                }
            }
        }

        vitalite[0] = rouge;
        vitalite[1] = bleu;
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
                    if (Utils.etouffe(plateau, coordsVoisin[i], 3) && couleurCase == plateau[coordsVoisin[i].ligne][coordsVoisin[i].colonne].couleur) {
                        // etouffe à trois arbres parceque l'on vien d'en planter un à coté mais qu'il n'est que imaginé pour le moment
                        compteur += plateau[coordsVoisin[i].ligne][coordsVoisin[i].colonne].vitalite;
                    }
                }
            }
        }
        return compteur;
    }

}
