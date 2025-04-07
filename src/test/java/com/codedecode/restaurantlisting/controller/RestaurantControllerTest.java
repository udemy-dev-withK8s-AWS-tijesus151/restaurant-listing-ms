package com.codedecode.restaurantlisting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.codedecode.restaurantlisting.dto.RestaurantDTO;
import com.codedecode.restaurantlisting.service.RestaurantService;

public class RestaurantControllerTest {

	@InjectMocks
	RestaurantController restaurantController;
	
	@Mock
	RestaurantService restaurantService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this); //in order for Mock and InjectMocks annotations to take effect, you need to call MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testFetchAllRestaurants() {
		// Mock the service behavior
		List<RestaurantDTO> mockRestaurants = Arrays.asList(
                new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"),
                new RestaurantDTO(2, "Restaurant 2", "Address 2", "city 2", "Desc 2")
        );
		
		when(restaurantService.findAllRestaurants()).thenReturn(mockRestaurants);
		
		// Call the controller method
		ResponseEntity<List<RestaurantDTO>> response = restaurantController.fetchAllRestaurants();
		
		// Verify the response
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockRestaurants, response.getBody());
		
		// Verify that the service method was called
		verify(restaurantService, times(1)).findAllRestaurants();
	}
	
	@Test
    public void testSaveRestaurant() {
        // Create a mock restaurant to be saved
        RestaurantDTO mockRestaurant =  new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        mockRestaurant.toString();
        mockRestaurant.hashCode();
        // Mock the service behavior
        when(restaurantService.addRestaurantInDB(mockRestaurant)).thenReturn(mockRestaurant);

        // Call the controller method
        ResponseEntity<RestaurantDTO> response = restaurantController.saveRestaurant(mockRestaurant);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockRestaurant, response.getBody());

        // Verify that the service method was called
        verify(restaurantService, times(1)).addRestaurantInDB(mockRestaurant);
    }

	@Test
    public void testFindRestaurantById() {
        // Create a mock restaurant ID
        Integer mockRestaurantId = 1;

        // Create a mock restaurant to be returned by the service
        //RestaurantDTO mockRestaurant = new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        Optional<RestaurantDTO> mockRestaurant = Optional.of(new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"));

        // Mock the service behavior
        //when(restaurantService.fetchRestaurantById(mockRestaurantId)).thenReturn(new ResponseEntity<>(mockRestaurant, HttpStatus.OK));
        when(restaurantService.fetchRestaurantById(mockRestaurantId)).thenReturn(mockRestaurant);

        // Call the controller method
        ResponseEntity<RestaurantDTO> response = restaurantController.findRestaurantById(mockRestaurantId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        //assertEquals(mockRestaurant, response.getBody());
        assertEquals(mockRestaurant.get(), response.getBody());

        // Verify that the service method was called
        verify(restaurantService, times(1)).fetchRestaurantById(mockRestaurantId);
    }
	
	@Test
    public void testFindRestaurantById_NonExistingId() {
        // Create a mock restaurant ID
        Integer mockRestaurantId = 1;

        // Create a mock restaurant to be returned by the service
        //RestaurantDTO mockRestaurant = new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1");
        //Optional<RestaurantDTO> mockRestaurant = Optional.of(new RestaurantDTO(1, "Restaurant 1", "Address 1", "city 1", "Desc 1"));

        // Mock the service behavior
        //when(restaurantService.fetchRestaurantById(mockRestaurantId)).thenReturn(new ResponseEntity<>(mockRestaurant, HttpStatus.OK));
        when(restaurantService.fetchRestaurantById(mockRestaurantId)).thenReturn(Optional.empty());

        // Call the controller method
        ResponseEntity<RestaurantDTO> response = restaurantController.findRestaurantById(mockRestaurantId);

        // Verify the response
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        //assertEquals(mockRestaurant, response.getBody());
        assertEquals(null, response.getBody());

        // Verify that the service method was called
        verify(restaurantService, times(1)).fetchRestaurantById(mockRestaurantId);
    }
	
}
