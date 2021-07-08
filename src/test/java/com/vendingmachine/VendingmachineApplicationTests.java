package com.vendingmachine;

import com.vendingmachine.entity.CashFlow;
import com.vendingmachine.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = VendingMachineApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VendingmachineApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port;
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAllProducts() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/retrieveAllProducts",
				HttpMethod.GET, entity, String.class);

		assertNotNull(response.getBody());
	}

	@Test
	public void testAddProduct() {
		Product product = new Product();
		product.setQuantity(25);
		product.setProductId("C3");
		product.setCost(10);
		product.setProductName("Red Bull");

		ResponseEntity<Product> postResponse = restTemplate.postForEntity(getRootUrl() + "/createProduct", HttpMethod.POST, Product.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

	@Test
	public void testGetCashFlow() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/retrieveAllProducts",
				HttpMethod.GET, entity, String.class);

		assertNotNull(response.getBody());
	}

	@Test
	public void testAddCashFlow() {
		CashFlow cashFlow = new CashFlow();
		cashFlow.setAmount(15);
		cashFlow.setDenomination(20);
		cashFlow.setDescription("R20 note");

		ResponseEntity<Product> postResponse = restTemplate.postForEntity(getRootUrl() + "/addCashFlow", HttpMethod.POST, Product.class);
		assertNotNull(postResponse);
		assertNotNull(postResponse.getBody());
	}

}
