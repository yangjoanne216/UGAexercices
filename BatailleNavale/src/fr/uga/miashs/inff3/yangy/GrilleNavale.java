package fr.uga.miashs.inff3.yangy;

import java.util.Arrays;
import java.util.Random;

public class GrilleNavale {
	
	/**
    * Le tableau des navires contenus dans la grille
    */
	private Navire[] navires; 

	/**
    * Le nombre de navires contenus dans navires
    */
	private int nbNavires;

   /**
    * Taille de la grille, la grille est carrée
    */
	private int tailleGrille;

   /**
    * Tableau des tirs recus
    */
	private Coordonnee[] tirsRecus; 

	/**
    * Nombre de tirs contenus dans tirRecus
    */
	private int nbTirsRecus;

	/**
	 * Initialise une grille navale de taille donnée 
	 * * Tous les attributs doivent être initialisés 
	 * * @param taille la taille de la grille
	 */
	public GrilleNavale(int taille) {	
		navires = new Navire[10];
		nbNavires = 0;
		tailleGrille = taille;
		tirsRecus = new  Coordonnee[taille*taille];
		nbTirsRecus = 0;	
	}
  
	/** 
	 * Ajoute un navire dans la grille.
	 * Un navire n'est pas ajouté si ses dimensions font qu'il sort de la
	 * grille ou si il touche ou chevauche un navire déjà présent dans la grille
    * L'attribut navire est agrandi automatiquement de 5 éléments si besoin
    * @param n le navire à ajouter
    * @return true si le navire a été ajouté. 
    */
	public boolean ajouteNavire(Navire n){
		//先判断这个船的坐标是否都在grille里面
		if(!estDansGrille(n.getDebut())||!estDansGrille(n.getFin()))
			return false;
		//再判断是否与已经放入的navires的一样，是否在周围，是否重合
		for(int i=0; i<nbNavires;i++)
		{
			if(n.touche(navires[i])||n.chevauche(navires[i]))
			{
				return false;
			}
		}
		//如果遍历了一遍已经在grille的船只了，就判断需不需要扩大Arrays
		if(nbNavires==navires.length)
		{
			navires=Arrays.copyOf(navires, nbNavires+5);
		}
		navires[nbNavires]=n;
		nbNavires++;
		return true;
	}
 
	/**
	 * Place automatiquement tailleNavires.length navires
	 * dont les tailles sont données dans tailleNavires
	 * @param taillesNavires tableau donnant les tailles des navires à créer
	et à placer dans la grille
    */
	public void placementAuto(int[] taillesNavires) {
	//一艘船一艘船的放置
		for(int i = 0; i < taillesNavires.length; i++) {
			boolean result = false;
			//当成功放置好船后跳出本次循环进入下一次循环
			while (result == false)
			{   
				Random random = new Random();
				Coordonnee debut = new Coordonnee((int)(Math.random()*100%tailleGrille),(int)(Math.random()*100%tailleGrille));
				result = ajouteNavire(new Navire(debut,taillesNavires[i],random.nextBoolean()));
			}
				
		}
		
	}
	
	/**
	 * Teste si la coordonnée passée en paramètre est contenue dans la grille 
	 * @param c référence non nulle vers la coordonnée à tester
	 * @return true si la coordonnée est dans la grille
	 */
	private boolean estDansGrille(Coordonnee c) {
		if(c.getColonne()<tailleGrille&&c.getLigne()<tailleGrille)
			return true;
		else	
			return false;
		
	}
   
	/**
    * Teste si la coordonnée passée en paramètre est dans l'ensemble des tirs
	reçus sur la grille
	* @param c référence non nulle vers la coordonnée à tester
	* @return true si la coordonnée est dans l'ensemble des tirs reçus sur la
	grille */
	
	private boolean estDansTirsRecus(Coordonnee c) {
		for(int i=0;i<nbTirsRecus;i++)
		{
			if(tirsRecus[i].getLigne()==c.getLigne()&&tirsRecus[i].getColonne()==c.getColonne()) { 
				return true;
			}
		}
		return false;			
	}
	
   /**
    * Ajoute un tir dans le tableau tirsRecus.
    * Pour être ajouté un tir doit être dans la grille et ne doit pas etre
	déjà présent dans tirsRecus
    * @param c la coordonnée à ajouter
    * @return true si la coordonnée a été ajoutée dans tirsRecus */
	private boolean ajouteDansTirsRecus(Coordonnee c){ 
		if((!estDansTirsRecus(c)&&estDansGrille(c)))
		{
			tirsRecus[nbTirsRecus] = c;
			nbTirsRecus++;
			return true;
		}
		else
			return false;
			
	}
   /**
    * Gère la réception d'un tir sur la grille.
    * Si le tir est ajouté dans les tir recus alors ce tir est également
	envoyé
    * à chaue navire contenu dans la grille
    * @param c une référence vers la coordonnée du tir à receptionner 
    * @return true si le tir à touché un navire de la grille
    */
	public boolean recoitTir(Coordonnee c) {
		//先把Tirs加入到tirsRecus
		if(ajouteDansTirsRecus(c)) 
		{
			for(int i = 0; i<nbNavires;i++)
			{			
					if(navires[i].contient(c))
					{
						navires[i].recoiTri(c);	
						return true;
					}		
			}
		}
		
		return false;
	}
	
   
	/**
    * Teste si l'un des navires présent dans la grille est touché au niveau de
	 * la coordonnée passée en paramètre
	 * @param c ue référence vers la coordonnée à tester	
	 * @return true si l'un des navire de la grille est touché sur cette coordonnée
	 */
	public boolean estTouche(Coordonnee c) {
		for(int i = 0; i<nbNavires;i++)
		{		
			if(navires[i].estTouche(c))
				return true;
		}
		return false;
	}

	/**
	 * Teste si un tir est dans les tirs recus mais n'a touché aucun navire
	 * @param c ue référence vers la coordonnée à tester
	 * @return true si la coordonnée est dans tirs reçus mais aucun bateau n'a 
	 *  été touché
	 */
	public boolean estALEau(Coordonnee c) {
		if(estDansTirsRecus(c)&&!estTouche(c))
		{
			return true;
		}
		else
			return false;
		}

	
	/**
	 * Teste si tous les navires de la grille sont coulés
	 * @return true si tous les navires contenus dans la grille sont coulés 
	 * */
	public boolean perdu() {
		for(int i=0;i<nbNavires;i++)
		{
			//只要有一艘船没沉没就返回false
			if(!navires[i].estCloue())
				return false;
		}
		return true;
	}

   /**
    * Donne la taille de la grille
    * @return la taille de la grille
    */
	public int getTaille() {
		return tailleGrille;
	} 

	/**
    * Si l'un des navires est présent sur la coordonnée c,
    * la méthode teste si ce navire est est coulé
    * @param c une référence vers la coordonnée à tester
    * @return true si un navire est présent sur cette coordonnée et
    * si il est coulé
    */
	public boolean estCoule(Coordonnee c) {
		for(int i=0; i < nbNavires; i++)
		{
			//找到位于c处的船
			if(navires[i].contient(c))
			{
				return navires[i].estCloue();
			}
		}
		return false;	
	}
	
	public static void main(String arg[]) {
		GrilleNavale grilleNavale1 = new GrilleNavale(8);
		Navire navire1 = new Navire(new Coordonnee("C2"),3,false);
		Navire navire2 = new Navire(new Coordonnee("E2"),3,false);
		Navire navire3 = new Navire(new Coordonnee("D2"),4,true);
		Navire navire4 = new Navire(new Coordonnee("A4"),3,false);
		Navire navire5 = new Navire(new Coordonnee("C4"),3,true);
		Navire navire6 = new Navire(new Coordonnee("F3"),3,false);
		Navire navire7 = new Navire(new Coordonnee("C3"),3,false);
		Navire navire8 = new Navire(new Coordonnee("E4"),4,true);
		Navire navire10 = new Navire(new Coordonnee("H6"),4,true);
		Coordonnee dansUn = new Coordonnee("C2");
		Coordonnee dansQuatre= new Coordonnee("C4");
		Coordonnee dansHuite = new Coordonnee("E6");
		System.out.println("H8 在grilleNavale "+grilleNavale1.estDansTirsRecus(dansHuite));

		System.out.println("receit Tir est "+grilleNavale1.recoitTir(dansHuite));
		System.out.println("H8 在grilleNavale "+grilleNavale1.estDansTirsRecus(dansHuite));

		System.out.println((int)(Math.random()*100%7));
		grilleNavale1.placementAuto(new int[]{5,4,3});
		for(int i=0; i<3; i++)
		{
			System.out.println(grilleNavale1.navires[i].toString());
		}
	}
	
	
	
	

}
