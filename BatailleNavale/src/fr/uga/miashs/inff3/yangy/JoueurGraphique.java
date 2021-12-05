package fr.uga.miashs.inff3.yangy;

import java.awt.Color;

import javax.swing.JOptionPane;

public class JoueurGraphique extends Joueur {
	
	GrilleNavaleGraphique grilleDuJeu;
	GrilleGraphique grilleDuTirs; 

	/**
	 * @param grilleNavaleGraphique la grille du jeu
	 * @param grillegraphique la grille de tris
	 */
	public JoueurGraphique(GrilleNavaleGraphique grilleNavaleGraphique,GrilleGraphique grillegraphique )
	{
		super(grilleNavaleGraphique.getTaille());
		this.grilleDuJeu = grilleNavaleGraphique;
		this.grilleDuTirs = grillegraphique;
		
	}

	@Override
	protected void retourAttaque(Coordonnee c, int etat) {
		switch(etat){
		case A_L_EAU:
			grilleDuTirs.colorie(c, Color.blue);
			break;
		case TOUCHE:
			grilleDuTirs.colorie(c, Color.red);
			JOptionPane.showMessageDialog(grilleDuTirs, "vous avez touché un navire en "+c.toString());
			break;
		case COULE:
			grilleDuTirs.colorie(c, Color.red);
			JOptionPane.showMessageDialog(grilleDuTirs, "vous avez coulé un navire en "+c.toString());
			break;
		case GAMEOVER:
			grilleDuTirs.colorie(c, Color.red);
			JOptionPane.showMessageDialog(grilleDuTirs, "Super! Vous avez gagné!!!");
			break;
		default:
			break;
		}
			
	}

	@Override
	protected void retourDefense(Coordonnee c, int etat) {
		// TODO Auto-generated method stub
		switch(etat) {
		case TOUCHE:
			JOptionPane.showMessageDialog(grilleDuTirs, "Oh,votre navire est touché en "+c.toString());
			break;
		case A_L_EAU:
			break;
		case COULE:
			JOptionPane.showMessageDialog(grilleDuTirs, "Ohlala, votre navire est coulé en "+c.toString());
			break;
		case GAMEOVER:
			JOptionPane.showMessageDialog(grilleDuTirs, "Sorry,Vous avez perdu");
			break;
		default:
			break;
		}
	}

	@Override
	public Coordonnee choisirAttaque() {
		// TODO Auto-generated method stub
		return grilleDuTirs.getCoordonneeSelectionnee();
	}

	@Override
	public int defendre(Coordonnee c) {
		// TODO Auto-generated method stub
		if(grilleDuJeu.recoitTir(c)){
			if(grilleDuJeu.perdu()) {
				return GAMEOVER;
			}
			else if(grilleDuJeu.estCoule(c)){
				return COULE;
			}
			else
				return TOUCHE;
		}
		return A_L_EAU;
	}

}
