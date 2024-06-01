package com.company.company;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.company.company.entity.Company;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyRepositoryApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateCompanySuccess() {
		var company = new Company(
				"Gleason LLC",
				"0235 Elton Centers",
				"São Paulo",
				"Brasil",
				"Austen_Sanford71@yahoo.com",
				"298-553-3887");

		webTestClient
				.post()
				.uri("/company")
				.bodyValue(company)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].name").isEqualTo(company.getName())
				.jsonPath("$[0].address").isEqualTo(company.getAddress())
				.jsonPath("$[0].city").isEqualTo(company.getCity())
				.jsonPath("$[0].country").isEqualTo(company.getCountry())
				.jsonPath("$[0].email").isEqualTo(company.getEmail())
				.jsonPath("$[0].phoneNumber").isEqualTo(company.getPhoneNumber());
	}

	@Test
	void testCreateCompanyFailure() {
		webTestClient
		.post()
		.uri("/company")
		.bodyValue(new Company("","","","","",""))
		.exchange()
		.expectStatus().isBadRequest();
	}

}
