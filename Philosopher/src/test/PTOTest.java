package test;

import model.PTOMonitor;

import org.junit.Test;

public class PTOTest {

	@Test
	public void test() {
		final PTOMonitor monitor = PTOMonitor.getMonitor();
		
		monitor.addPhilosopher(5);
		monitor.startSimulation(1000 * 5);
		
		
	}

}
