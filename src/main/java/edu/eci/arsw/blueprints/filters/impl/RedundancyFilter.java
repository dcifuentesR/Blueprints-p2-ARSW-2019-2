package edu.eci.arsw.blueprints.filters.impl;

import java.util.LinkedList;
import java.util.List;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

public class RedundancyFilter implements BlueprintFilter {

	@Override
	public Blueprint filter(Blueprint bp) {
		List<Point> points=new LinkedList<Point>(bp.getPoints());
		for(int i=1;i<points.size();i++)
			if(points.get(i).getX() == points.get(i-1).getX()
			&& points.get(i).getY() == points.get(i-1).getY()) {
				points.remove(i-1);
				i=i-1;
			}
		
		return new Blueprint(bp.getAuthor(), bp.getName(), points.toArray(new Point[0]));
		
		
	}

}
