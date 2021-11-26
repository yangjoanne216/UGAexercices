import java.util.Arrays;

public class Espace {
	private int largeur;
	private int hauteur;
	private Chenille[] chenilles;
	private int nbChenilles;
	
	public Espace(int largeur, int hauteur)
	{
		this.largeur = largeur;
		this.hauteur = hauteur;
		chenilles=new Chenille[5];
		nbChenilles = 0;
	}
	
	public Espace()
	{
		this(10,10);
	}
	
	public Espace(int largeurEtHauteur)
	{
		this(largeurEtHauteur,largeurEtHauteur);
	}
	
	
	
	public void addChenille(Chenille c)
	{
		if(nbChenilles==chenilles.length)
		{
			chenilles=Arrays.copyOf(chenilles, chenilles.length+5);
		}
		
		chenilles[nbChenilles]=c;
		nbChenilles++;
		
	}
	public boolean contient(Case c)
	{
		return(c.getAbscisse()>-1&&c.getOrdonnee()>-1&&c.getAbscisse()<largeur&&c.getOrdonnee()<hauteur);
	}
	
	public boolean caseOccupee(Case c)
	{
		for(int i=0; i<nbChenilles;i++)
		{
			if(( chenilles[i]).estSur(c))
				return true;
		}
		
		return false;
	}
	//这个方法很重要值得一看
	public Case caseAuHasard()
	{
		//注意此处(int) Math.random()*largeur == 0
		return new Case((int)(Math.random()*largeur),(int)(Math.random()*hauteur));
	}
	
	public Case caseLibreAuHasard()
	{
		Case c = caseAuHasard();
		Case d = c;
		while(caseOccupee(c))
		{
			//此处的理解很重要，甚至可以记录下来
			c = new Case((c.getAbscisse()+1)%largeur,(c.getOrdonnee()+(c.getAbscisse()+1)/largeur)%hauteur);
			if(c.equals(d))
			{
				throw new RuntimeException("Pas de Case limite");
			}
		}	
		return c;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<hauteur;i++)
		{
			for(int j=0; j<largeur;j++)
			{
				sb.append(".");
			}
			sb.append("\n");
		}
		for(int i = 0;i<nbChenilles;i++)
		{
			Case[] cases = chenilles[i].getCases();
			for(int k=0; k<cases.length;k++)
			{
				char c = (char) ('a'+i);
				if(k==0)
				{
					c = (char) ('A'+i);
				}
				sb.setCharAt((cases[i].getOrdonnee()*(largeur+1))+cases[i].getAbscisse(),c);
			}
		}
		return sb.toString();
	}
		
	public static void main(String[] args)
	{
		Espace myEspace = new Espace(10,10);
		System.out.print(myEspace.toString());
		//System.out.print("what's it?");
	}
		
}


	
	
	
	


