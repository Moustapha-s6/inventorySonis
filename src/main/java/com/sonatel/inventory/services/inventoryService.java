package com.sonatel.inventory.services;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@Service
public class inventoryService {
	
	 private final RestTemplate restTemplate = new RestTemplate();
	    private final String BASE_URL = "https://fakestoreapi.com/";

	    public ResponseEntity<String> makeRequest(String endpoint, HttpMethod method, Object body) {
	        String url = BASE_URL + endpoint;
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
	        HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);
	        
	        return restTemplate.exchange(url, method, requestEntity, String.class);
	    }
	    
	    public ResponseEntity<String> getRequest(String endpoint) {
	        String url = BASE_URL + endpoint;
	        System.out.println("l'url complet est : "+ url);
	        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
	    }
	    
	    
	    public ResponseEntity<String> saveRequest(String endpoint, Object body) {
	        String url = BASE_URL + endpoint;
	        HttpEntity<Object> requestEntity = new HttpEntity<>(body);

	        System.out.println("URL complète : " + url);
	        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
	    }
	    
	    public ResponseEntity<String> GetRequestID(String endpoint, Long id) {
	        String url = BASE_URL + endpoint + "/" + id;
	      //  System.out.println("l'url complet est : "+ url);
	        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
	    }
	    
	    

}
