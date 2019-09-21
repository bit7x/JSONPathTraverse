package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping
	public ResponseEntity<?> parseAndReturn() {
		RestAssured.baseURI = "http://www.mocky.io/v2/5d859d93320000411007b28f";
		Response response = RestAssured.given().when().get();
		JsonPath jsonPath = response.getBody().jsonPath();
		List<Map> map =  jsonPath.param("name", "Basic").get("Promotions.findAll { Promotions -> Promotions.Name == name }");
		return new ResponseEntity<String>((String) map.get(0).get("Description"), HttpStatus.OK);
	}
	
}
