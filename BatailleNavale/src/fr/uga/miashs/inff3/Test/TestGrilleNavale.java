package fr.uga.miashs.inff3.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.uga.miashs.inff3.yangy.Coordonnee;
import fr.uga.miashs.inff3.yangy.GrilleNavale;
import fr.uga.miashs.inff3.yangy.Navire;

class TestGrilleNavale {
	
	GrilleNavale grilleNavale1 = new GrilleNavale(8);
	
	Navire navire1 = new Navire(new Coordonnee("C2"),3,false);
	Navire navire2 = new Navire(new Coordonnee("E2"),3,false);
	Navire navire3 = new Navire(new Coordonnee("D2"),4,true);
	Navire navire4 = new Navire(new Coordonnee("A4"),3,false);
	Navire navire5 = new Navire(new Coordonnee("C4"),3,true);
	Navire navire6 = new Navire(new Coordonnee("F3"),3,false);
	Navire navire7 = new Navire(new Coordonnee("C3"),3,false);
	Navire navire8 = new Navire(new Coordonnee("E4"),4,true);
	
	Navire navire9 = new Navire(new Coordonnee("E4"),5,false);
	Navire navire10 = new Navire(new Coordonnee("H6"),4,true);
	
	Coordonnee B2 = new Coordonnee (1,1);
	Coordonnee H8 = new Coordonnee (7,7);
	Coordonnee A8 = new Coordonnee (7,0);
	
	Coordonnee H9 = new Coordonnee (8,7);
	Coordonnee I4 = new Coordonnee (3,8);
	
	
	Coordonnee dansUn = new Coordonnee("C2");
	Coordonnee dansQuatre= new Coordonnee("C4");
	Coordonnee dansHuite = new Coordonnee("E6");
	
	

	@Test
	void testAjouteNavire() {
		//船号为1，4，6，8的可以被加入
		assertTrue(grilleNavale1.ajouteNavire(navire1));
		assertFalse(grilleNavale1.ajouteNavire(navire2));
		assertFalse(grilleNavale1.ajouteNavire(navire3));
		assertTrue(grilleNavale1.ajouteNavire(navire4));
		assertFalse(grilleNavale1.ajouteNavire(navire5));
		assertTrue(grilleNavale1.ajouteNavire(navire6));
		assertFalse(grilleNavale1.ajouteNavire(navire7));
		assertTrue(grilleNavale1.ajouteNavire(navire8));	
		assertFalse(grilleNavale1.ajouteNavire(navire9));
		assertFalse(grilleNavale1.ajouteNavire(navire10));
	}
	
	@Test
	void testAjouteDansTirsRecusEtRecoitTriEstTouche() {
		grilleNavale1.ajouteNavire(navire1);
		grilleNavale1.ajouteNavire(navire4);
		grilleNavale1.ajouteNavire(navire6);
		grilleNavale1.ajouteNavire(navire8);
		assertTrue(grilleNavale1.recoitTir(dansUn));
		assertTrue(grilleNavale1.recoitTir(dansQuatre));
		assertTrue(grilleNavale1.recoitTir(dansHuite));
		
		
		assertFalse(grilleNavale1.recoitTir(dansHuite));
		assertFalse(grilleNavale1.recoitTir(B2));
		assertFalse(grilleNavale1.recoitTir(H8));
		assertFalse(grilleNavale1.recoitTir(A8));
		assertFalse(grilleNavale1.recoitTir(H9));
		assertFalse(grilleNavale1.recoitTir(I4));
		
		assertTrue(grilleNavale1.estTouche(dansHuite));
		assertTrue(grilleNavale1.estTouche(dansUn));
		assertTrue(grilleNavale1.estTouche(dansQuatre));	
	}
	
	@Test
	void estALEau()
	{
		grilleNavale1.ajouteNavire(navire1);
		grilleNavale1.ajouteNavire(navire4);
		grilleNavale1.ajouteNavire(navire6);
		grilleNavale1.ajouteNavire(navire8);
		grilleNavale1.recoitTir(dansUn);
		grilleNavale1.recoitTir(dansQuatre);
		grilleNavale1.recoitTir(dansHuite);
		grilleNavale1.recoitTir(new Coordonnee("A1"));
		grilleNavale1.recoitTir(new Coordonnee("C5"));
		//没有击中的坐标
		assertTrue(grilleNavale1.estALEau(new Coordonnee("A1")));
		assertTrue(grilleNavale1.estALEau(new Coordonnee("C5")));
		//一下三个坐标在triRecus表里而且成功击中了船
		assertFalse(grilleNavale1.estALEau(dansUn));
		assertFalse(grilleNavale1.estALEau(dansQuatre));
		assertFalse(grilleNavale1.estALEau(dansHuite));
		//G2不在triRecus表里面
		assertFalse(grilleNavale1.estALEau(new Coordonnee("G2")));	
	}
	
	@Test
	void testPerduEtTestEstCoule()
	{
		grilleNavale1.ajouteNavire(navire1);
		grilleNavale1.ajouteNavire(navire4);
		grilleNavale1.ajouteNavire(navire6);
		grilleNavale1.ajouteNavire(navire8);
		//随意射击
		grilleNavale1.recoitTir(new Coordonnee("C2"));
		grilleNavale1.recoitTir(new Coordonnee("D2"));
		//此时船1还没有沉没
		assertFalse(grilleNavale1.estCoule(new Coordonnee("C2")));
		//再给一击
		grilleNavale1.recoitTir(new Coordonnee("E2"));
		//船1沉没了
		assertTrue(grilleNavale1.estCoule(new Coordonnee("C2")));
		//此时别的船还在所以还没perdu
		assertFalse(grilleNavale1.perdu());
		//现在把4，6，8号船也击落
		//4号船
		grilleNavale1.recoitTir(new Coordonnee("A4"));
		grilleNavale1.recoitTir(new Coordonnee("B4"));
		grilleNavale1.recoitTir(new Coordonnee("C4"));
		//8号船
		grilleNavale1.recoitTir(new Coordonnee("E4"));
		grilleNavale1.recoitTir(new Coordonnee("E5"));
		grilleNavale1.recoitTir(new Coordonnee("E6"));
		grilleNavale1.recoitTir(new Coordonnee("E7"));
		//6号船
		grilleNavale1.recoitTir(new Coordonnee("F3"));
		grilleNavale1.recoitTir(new Coordonnee("G3"));
		grilleNavale1.recoitTir(new Coordonnee("H3"));
		
		assertTrue(grilleNavale1.perdu());
	}
	
	@Test
	void testPlacementAuto()
	{
		grilleNavale1.placementAuto(new int[]{5,4,3});
		
	}

}
