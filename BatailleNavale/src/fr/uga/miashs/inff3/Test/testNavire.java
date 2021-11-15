package fr.uga.miashs.inff3.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.uga.miashs.inff3.yangy.Coordonnee;
import fr.uga.miashs.inff3.yangy.Navire;

class testNavire {
	
	Navire navire1 = new Navire(new Coordonnee("C2"),3,false);
	Navire navire2 = new Navire(new Coordonnee("E2"),3,false);
	Navire navire3 = new Navire(new Coordonnee("D2"),4,true);
	Navire navire4 = new Navire(new Coordonnee("A4"),3,false);
	Navire navire5 = new Navire(new Coordonnee("C4"),3,true);
	Navire navire6 = new Navire(new Coordonnee("F3"),3,false);
	Navire navire7 = new Navire(new Coordonnee("C3"),3,false);
	Navire navire8 = new Navire(new Coordonnee("E4"),4,true);
	
	
	
	Coordonnee dansUn = new Coordonnee("C2");
	Coordonnee dansUnEtDeux = new Coordonnee("E2");
	Coordonnee dansTrois = new Coordonnee ("D3");
	Coordonnee dansQuatreEtCinq = new Coordonnee("C4");
    Coordonnee dansAucune1 = new Coordonnee("H0");
    Coordonnee dansAucune2 = new Coordonnee("D6");

	@Test
	void testString() {                  
		assertEquals(navire1.toString(),"{Navire début = C2, fin = E2, parties touchées = []}");
	}
	
	
	@Test
	void testGetDebut() {
		assertEquals(new Coordonnee("C2"),navire1.getDebut());
		assertEquals(new Coordonnee("E2"),navire2.getDebut());
		assertEquals(new Coordonnee("D2"),navire3.getDebut());
		assertEquals(new Coordonnee("A4"),navire4.getDebut());
		assertEquals(new Coordonnee("C4"),navire5.getDebut());
		assertEquals(new Coordonnee("F3"),navire6.getDebut());
	}
	
	@Test
	void testGetFin() {
		assertEquals(new Coordonnee("E2"),navire1.getFin());
		assertEquals(new Coordonnee("G2"),navire2.getFin());
		assertEquals(new Coordonnee("D5"),navire3.getFin());
		assertEquals(new Coordonnee("C4"),navire4.getFin());
		assertEquals(new Coordonnee("C6"),navire5.getFin());
		assertEquals(new Coordonnee("H3"),navire6.getFin());
	}
	
	@Test
	void testContient() {
		assertTrue(navire1.contient(dansUn));
		assertTrue(navire1.contient(dansUnEtDeux));
		assertTrue(navire2.contient(dansUnEtDeux));
		assertTrue(navire3.contient(dansTrois));
		assertTrue(navire4.contient(dansQuatreEtCinq));
		assertTrue(navire5.contient(dansQuatreEtCinq));
		
		assertFalse(navire1.contient(dansTrois));
		assertFalse(navire1.contient(dansAucune1));
		assertFalse(navire3.contient(dansUnEtDeux));
		assertFalse(navire4.contient(dansAucune2));
	}
	
	@Test
	void testIntersectionNonVide() {
		//assertTrue
		assertTrue(Navire.intersectionNonVide(2, 4, 3, 3));
		assertTrue(Navire.intersectionNonVide(3, 3, 2, 4));
		assertTrue(Navire.intersectionNonVide(1, 7, 2, 4));
		assertTrue(Navire.intersectionNonVide(6, 8, 8, 8));
	    assertTrue(Navire.intersectionNonVide(1, 1, 1, 1));
		//assertFalse
	    assertFalse(Navire.intersectionNonVide(2, 5, 1, 1));
		assertFalse(Navire.intersectionNonVide(3, 5, 1, 2));
		assertFalse(Navire.intersectionNonVide(4, 5, 6, 7));
		assertFalse(Navire.intersectionNonVide(1, 2, 3, 4));
		assertFalse(Navire.intersectionNonVide(1, 1, 2, 2));		
	}
	
	@Test
	void testChevauche(){
		//assertTrue
		assertTrue(navire1.chevauche(navire2));
		assertTrue(navire1.chevauche(navire3));
		assertTrue(navire3.chevauche(navire7));
		assertTrue(navire4.chevauche(navire5));
		assertTrue(navire5.chevauche(navire4));
		//assertFalse
		assertFalse(navire1.chevauche(navire6));
		assertFalse(navire2.chevauche(navire4));
		assertFalse(navire1.chevauche(navire7));
		assertFalse(navire3.chevauche(navire5));
		assertFalse(navire4.chevauche(navire6));
		assertFalse(navire5.chevauche(navire2));		
	}
	
	@Test
	void testTouche()
	{	
		//assertTrue
		assertTrue(navire3.touche(navire5));
		assertTrue(navire3.touche(navire5));
		assertTrue(navire4.touche(navire3));
		assertTrue(navire7.touche(navire8));
		assertTrue(navire7.touche(navire6));
		
		
		
		//assertFalse
		assertFalse(navire1.touche(navire3));//接触不代表重合
		assertFalse(navire4.touche(navire5));//此处把重合当作接触了
		assertFalse(navire6.touche(navire8));//on considère que deux navires adjacents au niveau de la diagonale ne se touchent pa
		assertFalse(navire1.touche(navire6));
		assertFalse(navire1.touche(navire5));
		assertFalse(navire4.touche(navire8));
		assertFalse(navire1.touche(navire6));
		
	}
	@Test
	void testRecoiTri_TestTouches_TestCloue()
	{
		assertFalse(navire1.estTouche(new Coordonnee("C2")));
		assertFalse(navire1.estCloue());
		navire1.recoiTri(dansUn);
		assertTrue(navire1.estTouche(new Coordonnee("C2")));
		assertTrue(navire1.estTouche());
		navire1.recoiTri(new Coordonnee("D2"));
		navire1.recoiTri(new Coordonnee("E2"));
		assertTrue(navire1.estCloue());
		
		
	}
	
	
	
	
	
	
	

}
