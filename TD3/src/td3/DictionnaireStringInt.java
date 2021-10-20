package td3;

import java.util.Arrays;

/**
 * @author yangyang
 *
 */
public class DictionnaireStringInt {
	public String[] cles = new String[10]; // 创建一个含有10个String的表格用于存储clés
	public int[] valeurs = new int[10];// 创建一个含有10个int类型的表格用于存储对应clé的valeurs

	int nbElements = 0;// 我们设置nbElements用于存储dictionaire中含有的元素数量

	/**
	 * 
	 * @param c 你要找的这个clé
	 * @return 返回该clé在dictionaire 的对应序号（就是tableaux的index）
	 */
	public int rechercherIdx(String c) {
		int idx = 0;

		while (idx < nbElements) {
			if (c.equals(cles[idx])) {
				// 找到了这个clé,返回找到的idx
				return idx;
			}

			idx++;
		}
		// 没找到返回一个负数
		return -nbElements - 1;
	}

	/**
	 * @param c 你要找的这个clé
	 * @return 返回该clé在dictionaire 的对应的valeur
	 */
	public int rechercherValeur(String c) {
		int recherche = rechercherIdx(c);
		if (recherche < 0) {
			// 没找到返回-1
			return -1;
		}
		// 找到了返回这clé对应的Valeur
		return valeurs[recherche];

	}

	public void agrandie() {
		/*
		 * 方法一：调用copyOf 一步到位 if(nbELements == cles.length { cles=Arrays.copyOf(cles,
		 * cles.length+10); values =Arrays.copyOf(valeurs, valeurs.length+10); }
		 */

		/*
		 * 方法二
		 */
		// 创建比之前表格多10个元素的表格
		String[] newCles = new String[cles.length + 10];
		int[] newValeurs = new int[valeurs.length + 10];
		// 复制之前表格的内容进入新的表格
		for (int i = 0; i < nbElements; i++) {
			newCles[i] = cles[i];
			newValeurs[i] = valeurs[i];
		}
		// 用新的表格替代旧的表格
		cles = newCles;
		valeurs = newValeurs;

	}

	public void ajouterModifier(int valeurAjoute, String clesAjoute) {
		// 如果还没有插入新的元素前，元素的数量就超过了列表的长度，调用agrandir()函数
		if (nbElements > valeurs.length) {
			agrandie();
		}

		// 我们没有找到了dictionnaire里与clésAjoute相同的元素，将其插入到最后一行
		if (rechercherIdx(clesAjoute) < 0) {
			cles[nbElements] = clesAjoute;
			valeurs[nbElements] = valeurAjoute;
		}
		// 我们找到了dictionnaire里面与插入的clésAjoute相同的元素，直接对其valeurs进行更改
		else {
			valeurs[rechercherIdx(clesAjoute)] = valeurAjoute;

		}
	}

	public void supprimer(String clesSupprime) {
		int idx = rechercherIdx(clesSupprime);
		if (idx < 0)
			return;
		else {
			// 把最后一个元素放入要找的元素的位置，
			cles[idx] = cles[nbElements - 1];
			valeurs[idx] = valeurs[nbElements - 1];
			// 删除dictionnaire中最后一个元素
			cles[nbElements - 1] = null;
			valeurs[nbElements - 1] = 0;
			nbElements -= 1;
		}
	}

	// redéfinir la méthodes(toString() est déjà exsite dans la classe objet)
	public String toString() {
		String affiche = "{";
		int i = 0;
		// 进行简单地判断,当不是最后一个元素的时候结尾处不打逗号
		while (i < nbElements - 1) {
			affiche = affiche + "[" + cles[i] + " : " + valeurs[i] + "] " + ", ";
			i++;
		}
		// 当是最后一个元素的时候结尾处打逗号
		if (i == nbElements - 1) {
			affiche = affiche + "[" + cles[i] + " : " + valeurs[i] + "]";
		}
		affiche = affiche + " }";

		return affiche;
	}

	// méthode 2
	public String toString2() {
		String affiche = "{";
		int i = 0;
		while (i < nbElements) {
			affiche = affiche + "[" + cles[i] + " : " + valeurs[i] + "] " + ", ";
			i++;
		}
		// 直接调用subString里面的方法，把affiche里面最后一个元素删除
		if (nbElements > 0) {
			affiche = affiche.substring(0, affiche.length() - 1);
		}
		affiche = affiche + " }";

		return affiche;
	}

	public int getNbElements() {
		return nbElements;
	}

	public String rechercherCleAvecValeurMax() {
		String cleCherche = "";
		if (nbElements == 0)
			cleCherche = "Nous n'avons pas d'éléments dans ce dictionnaire";
		else {
			int valeurCherche = valeurs[0];
			for (int i = 0; i < nbElements; i++) {
				if (valeurs[i] > valeurCherche) {
					valeurCherche = valeurs[i];
					cleCherche = cleCherche + cles[i];

				}
			}
		}

		return cleCherche;
	}

	public String[] construireTableauDesCles() {
		String[] res = new String [nbElements];
		for(int i = 0; i<nbElements; i++)
		{
			res[i]=cles[i];
		}
		return res;
	}
	
	
	/**二分法查找
	 * Des notes
	 * 时间复杂度 
	 * rechercher un élément d'un tableau qui a n élement： 
	 *  		 table non trié O(n) 
	 * 			 table trié O(log2(n))
	 * @param c
	 * @return
	 */
	int rechercherDichotomique(String c)
	{
		int start = 0;
		int fin = 0 ;
		int middle =(start + fin)/2;
		int equal = c.compareTo(cles[middle]);
		while (start <= fin && equal != 0)
		{
			if(equal < 0)
			{
				fin = middle-1;
			}
			
			else
				start = middle+1;
			middle = (fin + start)/2;
			equal = c.compareTo(cles[middle]);			
			
		}
		
		return (start>fin)? -(middle+1) : middle;
		
	}
	
	/** 二分法查找（递归法）
	 * @param c
	 * @param start
	 * @param fin
	 * @return
	 */
	int rechercherDichotomique2(String c, int start, int fin)
	{
		if(start > fin)
			return -1;
		else 
		{
			int middle = (start + fin)/2;
			int compare=c.compareTo(cles[middle]);
			if (compare<0)
				return rechercherDichotomique2(c,start,fin-1);				
			else if (compare>0)
				return rechercherDichotomique2(c,start-1,fin);
			else
				return middle;
			
			
		}   
	}
	
	/*
	 * int start =0;
	 * int fin = nbElements;
	 * while(start > fin)
	 * 
	 */
	/**
	 *  Proposer une nouvelle méthode d’ajout (remplaçant celle de la Question 6) qui insère
	 * un couple (clé ;valeur) à la bonne place selon l’ordre alphabétique des clés. Modifier également, la
	 * 	méthode de suppression, pour qu’elle préserve l’ordre dans le dictionnaire.
	 * 
	 *  Ajout 时间复杂度(sans recherche)
	 *  trié: O(1)
	 *  non trié:O(n)
	 * @param c
	 * @param v
	 * @return
	 */
	void DeletInPositionRight(String cle, int valeur)
	{
				int indexFind = rechercherDichotomique(cle);
				// 我们没有找到了dictionnaire里面与clé相同的元素,直接返回
				
				if (indexFind < 0) {
					return;
				}
				// 我们找到了dictionnaire里与clé将其删除
				else {				
					for(int i = nbElements;i>indexFind;i--)
					{
						//declage 一个移动	
						cles[i] = cles[i+1];
						valeurs[i] = valeurs[i+1];
						//将最后一个元素删除
						cles[nbElements - 1] = null;
						valeurs[nbElements-1] = 0;
						nbElements --;
						
					}		
					
					}
				
	}
	
	/**
	 *  Proposer une nouvelle méthode d’ajout (remplaçant celle de la Question 6) qui insère
	 * un couple (clé ;valeur) à la bonne place selon l’ordre alphabétique des clés. Modifier également, la
	 * 	méthode de suppression, pour qu’elle préserve l’ordre dans le dictionnaire. 
	 * @param c
	 * @param v
	 * @return
	 */
	void AddInPositionRight(String cle, int valeur)
	{
				int indexFind = rechercherDichotomique(cle);
				// 我们找到了dictionnaire里面与插入的clésAjoute相同的元素，直接对其valeur进行更改
				
				if (indexFind >= 0) {
					valeurs[rechercherIdx(cle)] = valeur;	
				}
				// 我们没有找到了dictionnaire里与clésAjoute相同的元素，将其按顺序插入
				else {			
					indexFind = -indexFind-1;
					// 如果还没有插入新的元素前，元素的数量就超过了列表的长度，调用agrandir()函数
					if (nbElements >= valeurs.length) {
						agrandie();
					}
					for(int i = nbElements;i>indexFind;i--)
					{
						//declage 一个移动	
						cles[i] = cles[i-1];
						valeurs[i] = valeurs[i-1];
						
					}	
					//在合适的位置插入
					cles[indexFind] = cle;
					valeurs[indexFind]=valeur;
					nbElements ++;
				}
				
	}
	
	
}
