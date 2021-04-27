package com.cg.fms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.fms.dto.Land;
import com.cg.fms.exception.NoDataFoundException;
import com.cg.fms.service.LandServiceImpl;

@RestController
	@RequestMapping("/land")
	public class LandController {
		@Autowired
		LandServiceImpl landservice= new LandServiceImpl();
		
		public LandServiceImpl getLandService() {
			return landservice;
		}

		public void setLandService(LandServiceImpl landservice) {
			this.landservice = landservice;
		}
		
		/***********
		 * Method      : getAllLands
		 * @return       Response Entity of Object type
		 * Description : This method fetches all the contracts.
		 * @getmapping : Get mapping expects a PathVariable to be passed 
			 *               which is then used to return the entity object 
			 *               that is fetched from the database.
		 **********/
		@GetMapping(value="/all",produces="application/json")
		public ResponseEntity<List<Land>> getAllLands(){
			System.out.println("it worked");
			return new ResponseEntity<List<Land>>(landservice.getAllLands(),HttpStatus.OK);
		}
		/***********
		 * Method      : getLand  
		 * @param        Id
		 * @return       Response Entity of Object type
		 * Description : This method fetches the contract based on id.
		 * @getmapping : Get mapping expects a PathVariable to be passed 
			 *               which is then used to return the entity object 
			 *               that is fetched from the database.
		 **********/
		@GetMapping(value="/{survey_no}",produces="application/json")
		public ResponseEntity<Optional<Land>> getLand(@PathVariable("survey_no")String surveyno){
			Optional<Land> l=null; 
			l=landservice.getLand(surveyno);
			System.out.println(l.toString());
			if(l.isPresent()) {
	    		return new ResponseEntity<Optional<Land>>(l,HttpStatus.OK);
	    	}
	    	else 
	    		throw new NoDataFoundException("No Land data found with given Land ID: "+ surveyno);
		}
		/***********
		 * Method      : addLand      
		 * @param       Land
		 * @return       Response Entity of Object type
		 * Description : This method adds a new Contract.
		 * @postmapping: Post mapping requests a body from the user
		 * 				 which is then persisted onto the database.
		 **********/
		@PostMapping(consumes="application/json")
		public ResponseEntity<HttpStatus> addLand(@RequestBody Land land) {
			landservice.addLand(land);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		/***********
		 * Method      : updateLand      
		 * @param        Land
		 * @return       Response Entity of Object type
		 * Description : This method updates the Land details.
		 * @PutMapping annotation  is used for mapping HTTP PUT requests onto specific handler methods.
		 **********/
		@PutMapping(consumes="application/json")
		public ResponseEntity<HttpStatus> updateLand(@RequestBody Land land) {
			landservice.updateLand(land);
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		/***********
		 * Method      : deleteLand      
		 * @param        Id
		 * @return       Response Entity of Object type
		 * Description : This method deletes the Land based on id.
		 * @deletemapping: @DeleteMapping annotation maps HTTP DELETE requests onto specific handler methods.
		 **********/
		@DeleteMapping(value="/{survey_no}")
		public ResponseEntity<HttpStatus> deleteLand(@PathVariable("survey_no")String surveyno){
			Optional<Land> l=null; 
			l=landservice.getLand(surveyno);
			if(l.isPresent()) {
				landservice.removeLandDetails(surveyno);
				return new ResponseEntity<HttpStatus>(HttpStatus.OK);
	    	}
	    	else 
	    		throw new NoDataFoundException("No Land data found with given Land ID: "+ surveyno);
			
		}
	}

