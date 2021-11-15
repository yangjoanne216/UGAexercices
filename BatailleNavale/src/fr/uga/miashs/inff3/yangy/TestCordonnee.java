package fr.uga.miashs.inff3.yangy;

public class TestCordonnee {
	public static void main (String arg[])
	{
		
		Coordonnee A1 = new Coordonnee("A1");
		Coordonnee Z1 = new Coordonnee("Z1");
		//test toString
		if(A1.toString().equals("A1")&&Z1.toString().equals("Z1"))
		{
			System.out.println("test1 Past");
		}
		else
		{
			System.out.println("il y a des problème,Z1.toString affiche "+ Z1.toString());
		}
			
		
		//test getLigne 
		if(A1.getLigne()==0)
		{
			System.out.println("test2 Past");
		}
		else
		{
			System.out.println("il y a des problème sur getligne,A1.getligne() affiche "+ A1.getLigne());
		}
	}

}
