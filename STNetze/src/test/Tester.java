package test;

import model.PTNet;
import model.Place;
import model.Relation;
import model.Transition;

import org.junit.Assert;
import org.junit.Test;

public class Tester {

	@Test
	public void testNet1() {
		PTNet net = new PTNet();
		
		Place pos1 = new Place();
		Place pos2 = new Place();
		
		Transition trans1 = new Transition();
		Transition trans2 = new Transition();
		
		Relation rel1 = new Relation(pos1, trans1, 1);
		Relation rel2 = new Relation(trans2, pos1, 1);
		Relation rel3 = new Relation(trans1, pos2, 2);
		Relation rel4 = new Relation(pos2, trans2, 2);
		
		net.addPlace(pos1);
		net.addPlace(pos2);
		net.addTransition(trans1);
		net.addTransition(trans2);
		net.addRelation(rel1);
		net.addRelation(rel2);
		net.addRelation(rel3);
		net.addRelation(rel4);
		net.setMarkers(pos1, 1);
		
		Assert.assertTrue(net.isFinite());
		Assert.assertTrue(net.hasCycle());
	}
	
	@Test
	public void testNet2NoCycles() {
		PTNet net = new PTNet();
		
		Place pos1 = new Place();
		Place pos2 = new Place();
		
		Transition trans = new Transition();
		
		Relation rel1 = new Relation(pos1, trans, 1);
		Relation rel2 = new Relation(trans, pos2, 1);
		
		net.addPlace(pos1);
		net.addPlace(pos2);
		net.addTransition(trans);
		net.addRelation(rel1);
		net.addRelation(rel2);
		net.setMarkers(pos1, 5);
		
		Assert.assertTrue(net.isFinite());
		Assert.assertFalse(net.hasCycle());
	}
	
	
	@Test
	public void testNet3IsNotFinite() {
		PTNet net = new PTNet();
		
		Place pos1 = new Place();
		Place pos2 = new Place();
		
		Transition trans1 = new Transition();
		Transition trans2 = new Transition();
		
		Relation rel1 = new Relation(pos1, trans1, 1);
		Relation rel2 = new Relation(trans2, pos1, 2);
		Relation rel3 = new Relation(trans1, pos2, 1);
		Relation rel4 = new Relation(pos2, trans2, 1);
		
		net.addPlace(pos1);
		net.addPlace(pos2);
		net.addTransition(trans1);
		net.addTransition(trans2);
		net.addRelation(rel1);
		net.addRelation(rel2);
		net.addRelation(rel3);
		net.addRelation(rel4);
		net.setMarkers(pos1, 1);
		
		Assert.assertFalse(net.isFinite());
		Assert.assertTrue(net.hasCycle());
	}
	
	@Test
	public void testNet4NoCycles() {
		PTNet net = new PTNet();
		
		Place pos1 = new Place();
		Place pos2 = new Place();
		Place pos3 = new Place();
		Place pos4 = new Place();
		Place pos5 = new Place();
		Place pos6 = new Place();
		
		Transition trans1 = new Transition();
		Transition trans2 = new Transition();
		Transition trans3 = new Transition();
		Transition trans4 = new Transition();
		
		Relation rel1 = new Relation(pos1, trans1, 2);
		Relation rel2 = new Relation(trans1, pos2, 1);
		Relation rel3 = new Relation(trans1, pos3, 1);
		Relation rel4 = new Relation(pos2, trans2, 1);
		Relation rel5 = new Relation(pos3, trans3, 1);
		Relation rel6 = new Relation(trans2, pos4, 1);
		Relation rel7 = new Relation(trans3, pos5, 1);
		Relation rel8 = new Relation(pos4, trans4, 1);
		Relation rel9 = new Relation(pos5, trans4, 1);
		Relation rel10 = new Relation(trans4, pos6, 2);
		
		net.addPlace(pos1);
		net.addPlace(pos2);
		net.addPlace(pos3);
		net.addPlace(pos4);
		net.addPlace(pos5);
		net.addPlace(pos6);
		net.addTransition(trans1);
		net.addTransition(trans2);
		net.addTransition(trans3);
		net.addTransition(trans4);
		net.addRelation(rel1);
		net.addRelation(rel2);
		net.addRelation(rel3);
		net.addRelation(rel4);
		net.addRelation(rel5);
		net.addRelation(rel6);
		net.addRelation(rel7);
		net.addRelation(rel8);
		net.addRelation(rel9);
		net.addRelation(rel10);
		net.setMarkers(pos1, 2);
		
		Assert.assertTrue(net.isFinite());
		Assert.assertFalse(net.hasCycle());
	}
	
	@Test
	public void testNet5NoCycles() {
		PTNet net = new PTNet();
		
		Place pos1 = new Place();
		Place pos2 = new Place();
		Place pos3 = new Place();
		Place pos4 = new Place();
		Place pos5 = new Place();
		Place pos6 = new Place();
		
		Transition trans1 = new Transition();
		Transition trans2 = new Transition();
		Transition trans3 = new Transition();
		Transition trans4 = new Transition();
		Transition trans5 = new Transition();
		Transition trans6 = new Transition();
		
		Relation rel1 = new Relation(pos1, trans1, 1);
		Relation rel2 = new Relation(trans1, pos2, 1);
		Relation rel3 = new Relation(pos2, trans2, 1);
		Relation rel4 = new Relation(pos2, trans3, 1);
		Relation rel5 = new Relation(trans2, pos3, 1);
		Relation rel6 = new Relation(trans3, pos4, 1);
		Relation rel7 = new Relation(pos3, trans4, 1);
		Relation rel8 = new Relation(pos4, trans5, 1);
		Relation rel9 = new Relation(trans4, pos5, 1);
		Relation rel10 = new Relation(trans5, pos5, 1);
		Relation rel11 = new Relation(pos5, trans6, 1);
		Relation rel12 = new Relation(trans6, pos6, 1);
		
		net.addPlace(pos1);
		net.addPlace(pos2);
		net.addPlace(pos3);
		net.addPlace(pos4);
		net.addPlace(pos5);
		net.addPlace(pos6);
		net.addTransition(trans1);
		net.addTransition(trans2);
		net.addTransition(trans3);
		net.addTransition(trans4);
		net.addRelation(rel1);
		net.addRelation(rel2);
		net.addRelation(rel3);
		net.addRelation(rel4);
		net.addRelation(rel5);
		net.addRelation(rel6);
		net.addRelation(rel7);
		net.addRelation(rel8);
		net.addRelation(rel9);
		net.addRelation(rel10);
		net.addRelation(rel11);
		net.addRelation(rel12);
		net.setMarkers(pos1, 1);
		
		Assert.assertTrue(net.isFinite());
		Assert.assertFalse(net.hasCycle());
	}
	
	@Test
	public void testNet6() {
		PTNet net = new PTNet();
		
		Place pos1 = new Place();
		Place pos2 = new Place();
		Place pos3 = new Place();
		Place pos4 = new Place();
		Place pos5 = new Place();
		
		Transition trans1 = new Transition();
		Transition trans2 = new Transition();
		Transition trans3 = new Transition();
		Transition trans4 = new Transition();
		
		Relation rel1 = new Relation(pos1, trans1, 1);
		Relation rel2 = new Relation(trans1, pos2, 1);
		Relation rel3 = new Relation(pos2, trans2, 1);
		Relation rel4 = new Relation(trans2, pos1, 1);
		Relation rel5 = new Relation(trans2, pos3, 3);
		Relation rel6 = new Relation(pos3, trans1, 3);
		Relation rel7 = new Relation(pos4, trans4, 1);
		Relation rel8 = new Relation(trans4, pos5, 1);
		Relation rel9 = new Relation(pos5, trans3, 1);
		Relation rel10 = new Relation(trans3, pos4, 1);
		Relation rel11 = new Relation(trans4, pos3, 1);
		Relation rel12 = new Relation(pos3, trans3, 1);
		
		net.addPlace(pos1);
		net.addPlace(pos2);
		net.addPlace(pos3);
		net.addPlace(pos4);
		net.addPlace(pos5);
		net.addTransition(trans1);
		net.addTransition(trans2);
		net.addTransition(trans3);
		net.addTransition(trans4);
		net.addRelation(rel1);
		net.addRelation(rel2);
		net.addRelation(rel3);
		net.addRelation(rel4);
		net.addRelation(rel5);
		net.addRelation(rel6);
		net.addRelation(rel7);
		net.addRelation(rel8);
		net.addRelation(rel9);
		net.addRelation(rel10);
		net.addRelation(rel11);
		net.addRelation(rel12);
		net.setMarkers(pos1, 3);
		net.setMarkers(pos3, 3);
		net.setMarkers(pos5, 5);
		
		Assert.assertTrue(net.isFinite());
		Assert.assertTrue(net.hasCycle());
	}
	
	@Test
	public void testNet7IsNotFinite() {
		PTNet net = new PTNet();
		
		Place pos1 = new Place();
		Place pos2 = new Place();
		Place pos3 = new Place();
		Place pos4 = new Place();
		Place pos5 = new Place();
		
		Transition trans1 = new Transition();
		Transition trans2 = new Transition();
		Transition trans3 = new Transition();
		Transition trans4 = new Transition();
		
		Relation rel1 = new Relation(pos1, trans1, 1);
		Relation rel2 = new Relation(trans1, pos2, 1);
		Relation rel3 = new Relation(pos2, trans2, 1);
		Relation rel4 = new Relation(trans2, pos1, 2);
		Relation rel5 = new Relation(trans2, pos3, 3);
		Relation rel6 = new Relation(pos3, trans1, 3);
		Relation rel7 = new Relation(pos4, trans4, 1);
		Relation rel8 = new Relation(trans4, pos5, 2);
		Relation rel9 = new Relation(pos5, trans3, 1);
		Relation rel10 = new Relation(trans3, pos4, 1);
		Relation rel11 = new Relation(trans4, pos3, 1);
		Relation rel12 = new Relation(pos3, trans3, 1);
		
		net.addPlace(pos1);
		net.addPlace(pos2);
		net.addPlace(pos3);
		net.addPlace(pos4);
		net.addPlace(pos5);
		net.addTransition(trans1);
		net.addTransition(trans2);
		net.addTransition(trans3);
		net.addTransition(trans4);
		net.addRelation(rel1);
		net.addRelation(rel2);
		net.addRelation(rel3);
		net.addRelation(rel4);
		net.addRelation(rel5);
		net.addRelation(rel6);
		net.addRelation(rel7);
		net.addRelation(rel8);
		net.addRelation(rel9);
		net.addRelation(rel10);
		net.addRelation(rel11);
		net.addRelation(rel12);
		net.setMarkers(pos1, 3);
		net.setMarkers(pos3, 3);
		net.setMarkers(pos5, 5);
		
		Assert.assertFalse(net.isFinite());
		Assert.assertTrue(net.hasCycle());
	}
	
	@Test
	public void testNet8NoCycles() {
		PTNet net = new PTNet();
		
		Place pos1 = new Place();
		Place pos2 = new Place();
		Place pos3 = new Place();
		Place pos4 = new Place();
		Place pos5 = new Place();
		Place pos6 = new Place();
		
		Transition trans1 = new Transition();
		Transition trans2 = new Transition();
		Transition trans3 = new Transition();
		Transition trans4 = new Transition();
		
		Relation rel1 = new Relation(pos1, trans1, 1);
		Relation rel2 = new Relation(trans1, pos2, 1);
		Relation rel3 = new Relation(pos2, trans2, 1);
		Relation rel4 = new Relation(pos2, trans3, 1);
		Relation rel5 = new Relation(trans2, pos1, 1);
		Relation rel6 = new Relation(trans2, pos3, 1);
		Relation rel7 = new Relation(pos3, trans1, 1);
		Relation rel8 = new Relation(trans3, pos4, 1);
		Relation rel9 = new Relation(pos4, trans4, 1);
		Relation rel10 = new Relation(trans4, pos5, 1);
		Relation rel11 = new Relation(pos5, trans3, 1);
		Relation rel12 = new Relation(trans4, pos3, 1);
		Relation rel13 = new Relation(trans4, pos6, 1);
		
		net.addPlace(pos1);
		net.addPlace(pos2);
		net.addPlace(pos3);
		net.addPlace(pos4);
		net.addPlace(pos5);
		net.addTransition(trans1);
		net.addTransition(trans2);
		net.addTransition(trans3);
		net.addTransition(trans4);
		net.addRelation(rel1);
		net.addRelation(rel2);
		net.addRelation(rel3);
		net.addRelation(rel4);
		net.addRelation(rel5);
		net.addRelation(rel6);
		net.addRelation(rel7);
		net.addRelation(rel8);
		net.addRelation(rel9);
		net.addRelation(rel10);
		net.addRelation(rel11);
		net.addRelation(rel12);
		net.addRelation(rel13);
		net.setMarkers(pos1, 1);
		net.setMarkers(pos3, 1);
		net.setMarkers(pos5, 1);
		
		Assert.assertTrue(net.isFinite());
		Assert.assertFalse(net.hasCycle()); // sagt dass es zyklisch ist, ist es aber eigentlich nicht
	}

	@Test
	public void testNet9NoCyclesAndIsNotFinite() {
		PTNet net = new PTNet();
		
		Place pos1 = new Place();
		Place pos2 = new Place();
		Place pos3 = new Place();
		Place pos4 = new Place();
		Place pos5 = new Place();
		Place pos6 = new Place();
		
		Transition trans1 = new Transition();
		Transition trans2 = new Transition();
		Transition trans3 = new Transition();
		Transition trans4 = new Transition();
		
		Relation rel1 = new Relation(pos1, trans1, 1);
		Relation rel2 = new Relation(trans1, pos2, 1);
		Relation rel3 = new Relation(pos2, trans2, 1);
		Relation rel4 = new Relation(pos2, trans3, 1);
		Relation rel5 = new Relation(trans2, pos1, 2);
		Relation rel6 = new Relation(trans2, pos3, 1);
		Relation rel7 = new Relation(pos3, trans1, 1);
		Relation rel8 = new Relation(trans3, pos4, 1);
		Relation rel9 = new Relation(pos4, trans4, 1);
		Relation rel10 = new Relation(trans4, pos5, 2);
		Relation rel11 = new Relation(pos5, trans3, 1);
		Relation rel12 = new Relation(trans4, pos3, 1);
		Relation rel13 = new Relation(trans4, pos6, 1);
		
		net.addPlace(pos1);
		net.addPlace(pos2);
		net.addPlace(pos3);
		net.addPlace(pos4);
		net.addPlace(pos5);
		net.addTransition(trans1);
		net.addTransition(trans2);
		net.addTransition(trans3);
		net.addTransition(trans4);
		net.addRelation(rel1);
		net.addRelation(rel2);
		net.addRelation(rel3);
		net.addRelation(rel4);
		net.addRelation(rel5);
		net.addRelation(rel6);
		net.addRelation(rel7);
		net.addRelation(rel8);
		net.addRelation(rel9);
		net.addRelation(rel10);
		net.addRelation(rel11);
		net.addRelation(rel12);
		net.addRelation(rel13);
		net.setMarkers(pos1, 1);
		net.setMarkers(pos3, 1);
		net.setMarkers(pos5, 1);
		
		Assert.assertFalse(net.isFinite());
		Assert.assertFalse(net.hasCycle()); // sagt dass es zyklisch ist, ist es aber eigentlich nicht
	}
}
