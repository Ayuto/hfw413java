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

}
