package com.sonatel.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sonatel.inventory.services.inventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api")
@Tag(name = "Inventaire Sonis", description = "Api pour gérer toutes les données de Sonis")
public class inventoryRestController {
	
	@Autowired
	private inventoryService inventoryService;
	
	 @Operation(summary = "Récupérer des données dynamiques de Sonis",
             description = "Effectue un appel GET sur l'outil d'inventaire Sonis avec un endpoint dynamique")
	 @GetMapping("/{proxy}")
	    public ResponseEntity<String> getData( 
	      @Parameter(description = "Nom du proxy à appeler", required = true)
	      @RequestParam(name = "proxy", required = true) String proxy) {
	      System.out.println("Appel reçu avec endpoint : " + proxy);
	      return inventoryService.getRequest(proxy);
  }
	 
	 @Operation(summary = "Envoyer des données dynamiques à Sonis",
             description = "Effectue un appel POST vers l'outil d'inventaire Sonis avec un endpoint dynamique")
	 @PostMapping("/{proxy}")
		  public ResponseEntity<String> saveRequest( 
		      @Parameter(description = "Lien du proxy à soumettre", required = true)
		      @RequestParam(name = "proxy", required = true) String proxy, 
		      @RequestBody(required = false) Object body) {
		
		      System.out.println("Appel reçu avec endpoint : " + proxy);
		
		      return inventoryService.saveRequest(proxy, body);
		  }
	
	 
	
			/*
			 * @GetMapping("/{proxy}/{id}") public ResponseEntity<String> getDataById(
			 * 
			 * @Parameter(description = "Nom de l'endpoint à appeler", required = true)
			 * 
			 * @RequestParam(name = "proxy", required = true) String endpoint,
			 * 
			 * @RequestParam(name = "id", required = true) Long id ) {
			 * System.out.println("Appel reçu avec endpoint : " + endpoint); return
			 * inventoryService.GetRequestID(endpoint,id); }
			 */
	 
			/*
			 * @GetMapping("/products") public ResponseEntity<String> getProducts() { return
			 * inventoryService.makeRequest("products", HttpMethod.GET, null); }
			 */
	 
	   
	 
			/*
			 * @RequestMapping(value = "/{endpoint}", method = {RequestMethod.POST,
			 * RequestMethod.DELETE}) public ResponseEntity<String> handleRequest(
			 * 
			 * @PathVariable (name = "endpoint", required = true) String endpoint,
			 * 
			 * @RequestBody(required = false) Object body,
			 * 
			 * @RequestParam(required = false) String method) {
			 * 
			 * 
			 * HttpMethod httpMethod = HttpMethod.GET; if (method != null) { try {
			 * httpMethod = HttpMethod.valueOf(method.toUpperCase()); } catch
			 * (IllegalArgumentException e) {
			 * 
			 * return ResponseEntity.badRequest().body("Méthode HTTP non valide."); } }
			 * 
			 * 
			 * return inventoryService.makeRequest(endpoint, httpMethod, body); }
			 */
}
