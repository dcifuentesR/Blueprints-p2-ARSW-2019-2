/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

/**
 *
 * @author hcadavid
 */
@Component("bpp")
public class InMemoryBlueprintPersistence implements BlueprintsPersistence {

	private final Map<Tuple<String, String>, Blueprint> blueprints = new ConcurrentHashMap<>();

	public InMemoryBlueprintPersistence() {
		// load stub data
		Point[] pts = new Point[] { new Point(140, 140), new Point(115, 115) };
		Blueprint bp = new Blueprint("_authorname_", "_bpname_ ", pts);
		blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
		
		pts = new Point[] { new Point(20, 20), new Point(30, 30) };
		Blueprint bpJ1 = new Blueprint("john", "john's print", pts);
		blueprints.put(new Tuple<>(bpJ1.getAuthor(), bpJ1.getName()), bpJ1);
		
		pts = new Point[] { new Point(40, 40), new Point(50, 50) };
		Blueprint bpJ2 = new Blueprint("john", "john's print 2: electric boogalo", pts);
		blueprints.put(new Tuple<>(bpJ2.getAuthor(), bpJ2.getName()), bpJ2);
		
		pts = new Point[] { new Point(100, 100), new Point(200, 200) };
		Blueprint bpA = new Blueprint("arthur", "arthur's print", pts);
		blueprints.put(new Tuple<>(bpA.getAuthor(), bpA.getName()), bpA);

	}

	@Override
	public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
		if (blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName()))) {
			throw new BlueprintPersistenceException("The given blueprint already exists: " + bp);
		} else {
			blueprints.put(new Tuple<>(bp.getAuthor(), bp.getName()), bp);
		}
	}

	@Override
	public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
		return blueprints.get(new Tuple<>(author, bprintname));
	}

	@Override
	public Set<Blueprint> getBlueprintsByAuthor(String author) throws BlueprintNotFoundException {
		Set<Blueprint> bprints = new HashSet<Blueprint>();
		for (Blueprint b : blueprints.values())
			if (b.getAuthor().equals(author))
				bprints.add(b);

		if (bprints.isEmpty())
			throw new BlueprintNotFoundException("the given author doesn't exist");
		return bprints;
	}

	@Override
	public Set<Blueprint> getBlueprints() {
		return new HashSet<Blueprint>(blueprints.values());
	}

	@Override
	public void updateBlueprint(Blueprint bp) throws BlueprintNotFoundException {
		if(!blueprints.containsKey(new Tuple<>(bp.getAuthor(), bp.getName())))
			throw new BlueprintNotFoundException("the given blueprint doesn't exist");
		
		blueprints.replace(new Tuple<String, String>(bp.getAuthor(), bp.getName()), bp);
		
	}

}
