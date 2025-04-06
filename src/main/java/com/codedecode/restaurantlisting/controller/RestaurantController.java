package com.codedecode.restaurantlisting.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.service.RestaurantService;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin("*")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("/fetchAllRestaurants")
	public ResponseEntity<List<RestaurantDTO>> fetchAllRestaurants(){
		List<RestaurantDTO> allRestaurants = restaurantService.findAllRestaurants();
		return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
	}
	
	@PostMapping("/addRestaurant")
	public ResponseEntity<RestaurantDTO> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO){
		RestaurantDTO restaurantDTOAdded = restaurantService.addRestaurantInDD(restaurantDTO);
		/*if(restaurantAdded!=null) {
			return new ResponseEntity<>(restaurantDTO, HttpStatus.CREATED);
		}*/
		
		return new ResponseEntity<>(restaurantDTOAdded, HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchById/{id}")
	public ResponseEntity<RestaurantDTO> findRestaurantById(@PathVariable int id){
		Optional<RestaurantDTO> restaurantDTO = restaurantService.fetchRestaurantById(id);
		if(restaurantDTO.isPresent()) {
			return new ResponseEntity<>(restaurantDTO.get(), HttpStatus.OK); 
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
