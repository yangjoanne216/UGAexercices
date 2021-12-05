package fr.uga.miashs.inff3.yangy;

import java.awt.Color;

public class GrilleNavaleGraphique extends GrilleNavale{
	
	GrilleGraphique grilleGraphique;
	
	public GrilleNavaleGraphique(int taille) {
		super(taille);
		grilleGraphique = new GrilleGraphique(taille);
		// TODO Auto-generated constructor stub
	}
	
	public GrilleGraphique getGrilleGraphique() {
		return grilleGraphique;
	}
	
	public boolean ajouteNavire(Navire n) {
		if(super.ajouteNavire(n))
		{
			grilleGraphique.colorie(n.getDebut(), n.getFin(), Color.green);
			return true;
		}
		
		else
			return false;
		
	
	}
	
	public boolean recoitTir(Coordonnee c) {
		if(super.recoitTir(c))
		{
			grilleGraphique.colorie(c,Color.red);
			return true;
		}
		else
			return false;
	}
	

}
