package edu.eci.arsw.blueprints.filters;

import edu.eci.arsw.blueprints.model.Blueprint;

public interface BlueprintFilter {
	
	/**
	 * filters a blueprint
	 * @param bp the blueprint to be filtered
	 * @return the filtered blueprint
	 */
	public Blueprint filter(Blueprint bp);

}
