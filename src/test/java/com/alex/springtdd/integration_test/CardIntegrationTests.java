package com.alex.springtdd.integration_test;

import com.alex.springtdd.Card;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardIntegrationTests {

	@Test
	void retrieveCardGivenIndex() {
		WebClient client = WebClient.create("localhost:8081");
		int index = 1;
		Mono<Card> result = client.get()
				.uri("/cards/{index}", index).accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Card.class);
		assertEquals("Alex", result.block().getName());
	}



}
