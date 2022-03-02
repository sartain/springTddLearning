package com.alex.springtdd.integration_test;

import com.alex.springtdd.Card;
import com.alex.springtdd.CardStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardIntegrationTests {

	@Test
	void retrieveCardGivenIndex() {
		WebClient client = WebClient.create("http://localhost:8080");
		Integer index = 1;
		Mono<Card> result = client.get()
				.uri("/cards")
				.headers(e -> e.setBasicAuth("root", "1234"))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(Card.class);
		StepVerifier.create(result)
						.expectNext(CardStore.getCardFromList(1));
	}
}
