package fr.uga.miashs.inff3.yangy;

public class Coordonnee {
private int ligne;
private int colonne;

public Coordonnee(String coordonnee)
{
	this.ligne= Integer.parseInt(coordonnee.substring(1))-1;
	//si on typer un charactère miniscule comme a,b,c
	if(Character.isLowerCase(coordonnee.charAt(0)))
	{
		this.colonne=coordonnee.charAt(0)-'a';
	}
	//si on typer un charactère majuscule comme A,B,C
	else if(Character.isUpperCase(coordonnee.charAt(0)))
	{
		this.colonne=coordonnee.charAt(0)-'A';
	}
	else
	{
		System.err.println("Des eurreurs quand on créer un coordonnée");
	}
	
}

public Coordonnee(int colone, int ligne)
{
	this.ligne=ligne;
	this.colonne=colonne;
}

/**
 * @return
 */
public int getLigne()
{
  return this.ligne;
}

/**
 * @return
 */
public int getColonne()
{
	return this.colonne;
}

/**
 *
 */
public String toString()
{
	String alphape= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	return ""+(char)alphape.charAt(colonne)+(ligne+1);
}

/**
 * @param o
 * @return
 */
public boolean equals(Object o)
{
	if(o instanceof Coordonnee)
	{
		Coordonnee c = (Coordonnee) o;
		return this.colonne==c.colonne && this.ligne== c.ligne;
	}
	else
		return false;
		
}

public boolean voisine(Coordonnee o)
{
	
		return Math.abs(this.colonne-o.colonne)+Math.abs(this.ligne-o.ligne)==1;
	
}

/**
 * @param o
 * @return
 */

public int compareTo(Coordonnee o)
{
	//如果行列都相同返回零
	if(this.equals(o)==true)
	{
		return 0;
	}
	//如果行不同，则只比较行之间的差异
	else if(this.ligne!=o.ligne)
	{
		return this.ligne-o.ligne;
	}
	//如果行相同，则比较列之间的差异
	else
	{
		return this.colonne-o.colonne;
	}
	
}

}
