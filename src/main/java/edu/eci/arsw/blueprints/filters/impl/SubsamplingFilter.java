package edu.eci.arsw.blueprints.filters.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;

import edu.eci.arsw.blueprints.filters.BlueprintFilter;
import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;

@Component("bpf")
public class SubsamplingFilter implements BlueprintFilter {

	@Override
	public Blueprint filter(Blueprint bp) {
		List<Point> points=new LinkedList<Point>(bp.getPoints());
		for(int i=0;i<points.size();i++)//lol--- i can't belive this worked--- 
			points.remove(i);
		return new Blueprint(bp.getAuthor(), bp.getName(), points.toArray(new Point[0]));
	}

}
