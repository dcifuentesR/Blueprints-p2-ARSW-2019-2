package edu.eci.arsw.blueprints.filters.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

public class SubsamplingFilterTest {

	@Test
	public void test() {
		
	}
	
	@Test
	public void filterTest() {
		
		SubsamplingFilter f = new SubsamplingFilter();
		
		Point[] pts = new Point[] {new Point(1, 0),new Point(2, 0),new Point(3, 0)};
		Blueprint bp = new Blueprint("arthur", "arthur's print", pts);
		
		assertEquals("",1,f.filter(bp).getPoints().size());
		
		pts = new Point[] {new Point(1, 0),new Point(2, 0),new Point(3, 0),new Point(4, 0)};
		bp = new Blueprint("arthur", "arthur's print", pts);
		
		assertEquals("",2,f.filter(bp).getPoints().size());
		
		
	}

}
