
public class Chenille 
{
 int tailleEffective=1; //la taille effective de chenille
 int tailleAdulte=10;
 Case[] corps = new Case[tailleEffective];
 Espace espace;
 public Chenille(Espace espace)
 {
	 this.espace = espace;
	 corps[0]=espace.caseLibreAuHasard();
 }
 
 public Chenille(int tailleAdulte,Espace espace)
 {
	 this(espace);
	 this.tailleAdulte = tailleAdulte;
	 
 }
 
 public Chenille(int tailleAdulte,Espace espace,Case tete)
 {
	 this(tailleAdulte,espace);
	 corps[0]=tete;
 }
 
 public boolean estSur(Case c)
 {
	 for(int i=0; i<corps.length;i++)
	 {
		 if(c==corps[i])
			 return true;
	 }
	 return false;
 }
 
 public Case[] getCases()
 {
	 return corps;
 }
 
 public void Avancer()
 { 
	 //先判断虫子是否还有增长空间
	 if(tailleEffective==tailleAdulte)
		throw new RuntimeException("ne peut pas avancer, ce chenille est adulte");	
	 //先判断是不是所有的周边cases都被占据了
	 if(espace.caseOccupee(corps[0].voisines()[0])&&espace.caseOccupee(corps[0].voisines()[1])&&espace.caseOccupee(corps[0].voisines()[2])&&espace.caseOccupee(corps[0].voisines()[3]))
		throw new RuntimeException("Tous les voisines de la tête sont occupées"); 
	 
	 Case[] nouvelleCorp = new Case[corps.length+1];
	 Case nouvelleTete = corps[0].voisines()[(int)(Math.random()%4)];
	 if(espace.caseOccupee(nouvelleTete))
	 {
		 nouvelleTete = corps[0].voisines()[(int)(Math.random()%4)];
	 }
	 nouvelleCorp[0] = nouvelleTete;
	 for(int i=0;i<corps.length;i++)
	 {
		 nouvelleCorp[i+1] = nouvelleCorp[i];
	 }
	 
	 corps=nouvelleCorp;
	 tailleEffective++;
 }
 
 public String toString()
 {
	 String result ="Chenille("+tailleEffective+"/"+tailleAdulte+"):[";
	 
	 for(int i=0;i<tailleEffective;i++)
	 {
		 result = result + corps[i].toString()+", ";
	 }

	 result = result.substring(0, result.length()-2)+"]"; //删除最后两个字符
	 return result;
 }

 public static void main(String[] args)
 {
	 Espace myEspace = new Espace();
	 Chenille premierChenille = new Chenille(myEspace);
	 Chenille deuximeChenille = new Chenille(myEspace);
	 myEspace.addChenille(premierChenille);
	 myEspace.addChenille(deuximeChenille);
	 System.out.println(premierChenille.toString());
	 premierChenille.Avancer();
	 System.out.print(myEspace.toString());
 }

}

