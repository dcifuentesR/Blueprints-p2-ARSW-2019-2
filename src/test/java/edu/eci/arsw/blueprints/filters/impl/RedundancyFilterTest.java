package edu.eci.arsw.blueprints.filters.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

public class RedundancyFilterTest {

	@Test
	public void test() {
	}
	
	@Test
	public void filterTest() {
		
		RedundancyFilter f = new RedundancyFilter();
		
		Point repeatedA = new Point(10, 0);
		Point repeatedB = new Point(10, 0);
		
		Point[] pts = new Point[] {repeatedA,repeatedB};
		Blueprint b = new Blueprint("john", "john's print", pts);
		
		assertEquals("should filter when the only two points are repeated",repeatedB,f.filter(b).getPoints().get(0));
		
		pts = new Point[] {new Point(10,0),new Point(10,0),new Point(10,0)
						  ,new Point(10,0),new Point(10,0),repeatedB};
		b = new Blueprint("john","john's print",pts);
//		System.out.println(b.getPoints());
//		Blueprint fb=f.filter(b);
//		System.out.println(fb.getPoints());
		assertEquals("there should only be one point after filtering if every point is the same",repeatedB,f.filter(b).getPoints().get(0));
		
		
	}

}
