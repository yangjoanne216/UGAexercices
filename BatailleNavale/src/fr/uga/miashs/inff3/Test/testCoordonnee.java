package fr.uga.miashs.inff3.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import fr.uga.miashs.inff3.yangy.Coordonnee;

class testCoordonnee {
	
	Coordonnee A1 = new Coordonnee("A1");
	Coordonnee B1 = new Coordonnee(1,0);
	Coordonnee B1plus = new Coordonnee(1,0);
	Coordonnee  B2 = new Coordonnee(1,1);
	Coordonnee A2 = new Coordonnee("A2");
	Coordonnee A2plus = new Coordonnee("A2");
	Coordonnee Z1 = new Coordonnee("Z1");
	Coordonnee Z9 = new Coordonnee("Z9");
	Coordonnee Y9 = new Coordonnee("Y9");
	
	
	@Test
	void testGetLigne() {
		
		assertEquals(A1.getLigne(),0);
		assertEquals(B2.getLigne(),1);
		assertEquals(B1.getLigne(),0);
		assertEquals(A2.getLigne(),1);
		assertEquals(Z9.getLigne(),8);
		assertEquals(Y9.getLigne(),8);	
	}
	

	@Test
	void testGetColone() {
		
		assertEquals(A1.getColonne(),0);
		assertEquals(B1.getColonne(),1);
		assertEquals(B2.getColonne(),1);
		assertEquals(A2.getColonne(),0);
		assertEquals(Z9.getColonne(),25);
		assertEquals(Y9.getColonne(),24);		
	}
	
	@Test
	void testToString() {
		assertEquals(A1.toString(),"A1");
		assertEquals(B1.toString(),"B1");
		assertEquals(A2.toString(),"A2");
		assertEquals(Z9.toString(),"Z9");
		assertEquals(Y9.toString(),"Y9");	
		
	}
	
	@Test
	void testEquals() {
		assertTrue(A2.equals(A2plus));
		assertTrue(B1.equals(B1plus));
		assertFalse(A1.equals(A2));
		assertFalse(Z9.equals(Y9));
		
	}
	
	@Test
	void testVoisine() {
		assertTrue(A2.voisine(A1));
		assertTrue(B1.voisine(A1));
		assertFalse(A1.voisine(B2));
		assertFalse(A1.voisine(A1));
		
	}
	
	void testCompareTo() {
		assertTrue(A1.compareTo(A2)<0);
		assertTrue(A1.compareTo(A1)==0);
		assertTrue(Z1.compareTo(A1)>0);
		assertTrue(Z9.compareTo(Z1)>0);
		
	}
	
	

}
