package com.alex.springtdd.integration_test;

import com.alex.springtdd.Card;
import com.alex.springtdd.CardStore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Require the application to be running
 */

@SpringBootTest
class CardIntegrationTests {

	private final WebClient client = WebClient.create("https://localhost:8080");

	@Test
	void retrieveSingleCard() {
		WebClient client = WebClient.create("http://localhost:8080");
		Mono<Card> result = client.get()
				.uri("/card?index=" + 1)
				.headers(e -> e.setBasicAuth("root", "1234"))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(Card.class);
		StepVerifier.create(result)
				.expectNext(CardStore.getCardFromList(1))
				.verifyComplete();
	}

	@Test
	void retrieveSingleCardGivenIndex() {
		WebClient client = WebClient.create("http://localhost:8080");
		int index = 2;
		Mono<Card> result = client.get()
				.uri("/card?index=" + index)
				.headers(e -> e.setBasicAuth("root", "1234"))
				.retrieve()
				.bodyToMono(Card.class);
		StepVerifier.create(result)
				.expectNext(CardStore.getCardFromList(2))
				.verifyComplete();
	}

	@Test
	void retrieveAllCards() {
		WebClient client = WebClient.create("http://localhost:8080");
		Flux<Card> result = client.get()
				.uri("/cards")
				.headers(e -> e.setBasicAuth("root", "1234"))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToFlux(Card.class);
		StepVerifier.create(result)
				.expectNext(CardStore.cardList.get(0))
				.expectNext(CardStore.cardList.get(1))
				.expectNext(CardStore.cardList.get(2))
				.expectNext(CardStore.cardList.get(3))
				.verifyComplete();
	}

	@Test
	void freezeCard() {
		WebClient client = WebClient.create("http://localhost:8080");
		int index = 1;
		Mono<HttpStatus> result = client.post()
				.uri("/cards/freeze?index=" + index)
				.headers(e -> e.setBasicAuth("root", "1234"))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.retrieve()
				.bodyToMono(HttpStatus.class);
		StepVerifier.create(result)
				.expectNext(HttpStatus.OK)
				.verifyComplete();
	}
}
