package com.codedecode.restaurantlisting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.entity.Restaurant;
import com.codedecode.restaurantlisting.mapper.RestaurantMapper;
import com.codedecode.restaurantlisting.repo.RestaurantRepo;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepo restaurantRepo;

	public List<RestaurantDTO> findAllRestaurants() {
		
		List<Restaurant> restaurants = restaurantRepo.findAll();
		List<RestaurantDTO> restaurantDTOs = restaurants.stream()
				.map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant))
				.collect(Collectors.toList())
				;
		
		return restaurantDTOs ;
	}

	public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
		Restaurant restaurant = RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO);
		Restaurant restaurantAdded = restaurantRepo.save(restaurant);
		RestaurantDTO restaurantDTOAdded = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurantAdded);
		return restaurantDTOAdded;
	}

	public Optional<RestaurantDTO> fetchRestaurantById(int id) {
		Optional<Restaurant> restaurant = restaurantRepo.findById(id);
		if(restaurant.isPresent()) {
			RestaurantDTO restaurantDTO = RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get());
			return Optional.of(restaurantDTO);
		}
		return Optional.empty();
		
		//return ;
		/*if(restaurant.isPresent()) {
			return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant.get());
		}
		return null;
		*/
	}
}
