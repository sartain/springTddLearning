package com.alex.springtdd.integration_test;

import com.alex.springtdd.Card;
import com.alex.springtdd.CardStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CardIntegrationTests {

	@Test
	void retrieveSingleCard() {
		WebClient client = WebClient.create("http://localhost:8080");
		Integer index = 1;
		Mono<Card> result = client.get()
				.uri("/card")
				.headers(e -> e.setBasicAuth("root", "1234"))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(Card.class);
		StepVerifier.create(result)
						.expectNext(CardStore.getCardFromList(1));
	}

	@Test
	void retrieveSingleCardGivenIndex() {
		WebClient client = WebClient.create("http://localhost:8080");
		Integer index = 2;
		Mono<Card> result = client.get()
				.uri("/cards/" + index)
				.headers(e -> e.setBasicAuth("root", "1234"))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(Card.class);
		StepVerifier.create(result)
				.expectNext(CardStore.getCardFromList(2));
	}
}
