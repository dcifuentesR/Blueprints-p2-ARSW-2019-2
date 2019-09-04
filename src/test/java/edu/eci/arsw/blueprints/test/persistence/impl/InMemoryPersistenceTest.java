/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
                
        
    }
    
    @Test
    public void getBlueprintTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp= new InMemoryBlueprintPersistence();
        
        Point[] pts= new Point[]{new Point(0,0),new Point(10,10)};
        
        Blueprint bp=new Blueprint("john", "thepaint", pts);
        ibpp.saveBlueprint(bp);
        
        Blueprint bp2= new Blueprint("amy", "thepaint", pts);
        ibpp.saveBlueprint(bp2);
        

        Blueprint bp3= new Blueprint("amy", "amy's blueprint", pts);
        ibpp.saveBlueprint(bp3);
        
        assertEquals("getBlueprint doesn't get accurate blueprint if the name is the same as another", bp, ibpp.getBlueprint("john", "thepaint"));
        
        assertNull("expected getBlueprint to return null because the author doesn't exist", ibpp.getBlueprint("arthur", "thepaint"));
        assertNull("expected getBlueprint to return null because the blueprint doesn't exist", ibpp.getBlueprint("john", "this doesn't exist"));
        assertNull("expected getBlueprint to return null because the provided author did not make this blueprint", ibpp.getBlueprint("john", "amy's blueprint"));
    }
    
    @Test
    public void getBlueprintsByAuthorTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp= new InMemoryBlueprintPersistence();
        
        Point[] pts= new Point[]{new Point(0,0),new Point(10,10)};
        Set<Blueprint> bprints = new HashSet<>();
        Blueprint bp=new Blueprint("john", "thepaint", pts);
        ibpp.saveBlueprint(bp);
        bprints.add(bp);
        
        Blueprint bp2= new Blueprint("amy", "thepaint", pts);
        ibpp.saveBlueprint(bp2);
        bprints.add(bp2);
        
        Blueprint bp3 =new Blueprint("john", "thepaint 2: electric boogaloo", pts);
        ibpp.saveBlueprint(bp3);
        bprints.add(bp3);
        
        Blueprint bp4= new Blueprint("amy", "thepaint2", pts);
        ibpp.saveBlueprint(bp4);
        bprints.add(bp4);
        
        Blueprint bp5=new Blueprint("john", "the revenge of thepaint", pts);
        ibpp.saveBlueprint(bp5);
        bprints.add(bp5);
        
        Blueprint bp6= new Blueprint("amy", "thepaint3", pts);
        ibpp.saveBlueprint(bp6);
        bprints.add(bp6);
        
        try {
        	ibpp.getBlueprintsByAuthor("arthur");
        	fail("An exception was expected because the given author doesnt exist");
        }catch (BlueprintNotFoundException e) {
			// TODO: handle exception
		}
        
        for(Blueprint b:ibpp.getBlueprintsByAuthor("john"))
            assertEquals("every author should have the same name",b.getAuthor(), "john");
    }


    
}
