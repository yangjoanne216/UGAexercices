
public class Case {
private int abscisse;
private int ordonnee;

public Case(int abscisse,int ordonnee)
{
	this.abscisse = abscisse;
	this.ordonnee = ordonnee;
}

public int getAbscisse()
{
	return this.abscisse;
}

public int getOrdonnee()
{
	return this.ordonnee;
}

public Case est()
{
	return new Case(abscisse+1,ordonnee);
}

public Case nord()
{
	return new Case(abscisse,ordonnee-1);
}

public Case ouest()
{
	return new Case(abscisse-1,ordonnee);
}

public Case sud()
{
	return new Case(abscisse,ordonnee+1);
}
public Case[] voisines()
{
	return new Case[] {est(),nord(),ouest(),sud()};
}

public boolean equals(Object o)
{
	
	if(o instanceof Case)
	{
		Case c = (Case) o ;
		return (abscisse==c.abscisse&&ordonnee==c.ordonnee);
	}
	
	return false;
	
}

public String toString()
{
	return "("+abscisse+" , "+ordonnee+")";
}

}
