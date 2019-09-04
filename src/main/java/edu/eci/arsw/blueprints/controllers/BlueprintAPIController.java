/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;

/**
 *
 * @author hcadavid
 */
@RestController
public class BlueprintAPIController {
    
	@Autowired
    private BlueprintsServices bs;
	
	@RequestMapping(value="/blueprints",method = RequestMethod.GET)
	public ResponseEntity<?> getBlueprintsHandler(){
		try {
			return new ResponseEntity<>(bs.getBlueprints(), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE,null,e);
			return new ResponseEntity<>("Could not find blueprints",HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/blueprints/{author}",method = RequestMethod.GET)
	public ResponseEntity<?> getBlueprintsByAuthorHandler(@PathVariable("author") String author){
		try {
			return new ResponseEntity<>(bs.getBlueprintsByAuthor(author),HttpStatus.ACCEPTED);
		} catch (BlueprintNotFoundException e) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE,null,e);
			return new ResponseEntity<>("Could not find blueprints made by "+author,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/blueprints/{author}/{bpName}")
	public ResponseEntity<?> getBlueprintHandler(@PathVariable("author") String author,@PathVariable("bpName") String bprintname){
		
		try {
			return new ResponseEntity<>(bs.getBlueprint(author, bprintname),HttpStatus.ACCEPTED);
		} catch (BlueprintNotFoundException e) {
			Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE,null,e);
			return new ResponseEntity<>("could not find blueprint "+bprintname+" by "+author,HttpStatus.NOT_FOUND);
		}
		
	}
}

