package fr.uga.miashs.inff3.yangy;

import java.util.Arrays;

public class Navire {
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees; 
	private int nbTouchees = 0;
	
	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		this.debut = debut;
		this.nbTouchees = 0;
		this.partiesTouchees = new Coordonnee [longueur];
		if (estVertical == true)
		{
			this.fin = new Coordonnee (debut.getColonne(),debut.getLigne()+longueur-1);
			}
		else
		{
			this.fin = new Coordonnee (debut.getColonne()+longueur-1,debut.getLigne());
		}
		
	}
	
	 /**
	 *
	 */
	public String toString() {
		 return "{Navire début = "+ debut + ", fin = "+ fin + ", parties touchées = "+Arrays.toString(Arrays.copyOf(partiesTouchees, nbTouchees))+ "}"; 
	 }
	
	 public Coordonnee getDebut() {
		 return this.debut;
	 }
	
	 public Coordonnee getFin() {
		 return this.fin;
	 }

	 public boolean contient(Coordonnee c) {
		 if(c.getColonne()>=debut.getColonne()&& c.getLigne()>=debut.getLigne()&&c.getColonne()<=fin.getColonne()&&c.getLigne()<=fin.getLigne())
			 return true;
		 else
			 return false;
	 }
	 
	 public static boolean intersectionNonVide(int d1, int f1,int d2,int f2)
	 {
		 //报错信息,因为是默认d1大于f1，d2大于f2的。 
		 return f1>=d2 && f2>=d1;
	 }
	 
	 public boolean chevauche(Navire n)
	 {
		 //intersetion non vide sur ligne
		 return intersectionNonVide(this.debut.getLigne(),this.fin.getLigne(),n.debut.getLigne(),n.fin.getLigne()) 
			 && intersectionNonVide(this.debut.getColonne(),this.fin.getColonne(),n.debut.getColonne(),n.fin.getColonne()) ;
		
	 }
	 
	 public boolean touche(Navire n)
	 {
		 //问一下重合就一定接触吗
		 
		 if(intersectionNonVide(this.debut.getLigne(),this.fin.getLigne(),n.debut.getLigne(),n.fin.getLigne()) )
			 return this.debut.getColonne() - n.fin.getColonne() ==1 || n.debut.getColonne() - this.fin.getColonne()==1;
		 if (intersectionNonVide(this.debut.getColonne(),this.fin.getColonne(),n.debut.getColonne(),n.fin.getColonne()))
		 	return this.debut.getLigne() - n.fin.getLigne() ==1 || n.debut.getLigne() - this.fin.getLigne()==1;
		 else
			 return false;
		
	 }
	 
	 public boolean estTouche(Coordonnee c)
	 {
		 for(int i = 0; i<nbTouchees;i++)
		 {
			 if(c.getColonne() == partiesTouchees[i].getColonne() && c.getLigne() == partiesTouchees[i].getLigne())
				 return true;
		 }
		 
		 return false;
	 }
	 
	 public void recoiTri(Coordonnee c)
	 {
		 //java中给一个一维表格添加新的元素
		 if(this.contient(c)&&this.estTouche(c)== false)
		 {
			partiesTouchees[nbTouchees]=c;
			nbTouchees++;
		 }
			 
	 }
	 
	 public boolean estTouche()
	 {
		 if(nbTouchees==0 )
			 return false;
		 else
			 return true;
	 }
	 
	 public boolean estCloue()
	 {
		 if(nbTouchees == partiesTouchees.length)
			 return true;
		 else
			 return false;
	 }
	 public static void main (String arg[])
	 {
		 Navire navire1 = new Navire(new Coordonnee("C2"),3,false);
		 System.out.print(navire1.estTouche(new Coordonnee("C2")));
	 }
	 
	 
	 
	 
	 
	 

}
