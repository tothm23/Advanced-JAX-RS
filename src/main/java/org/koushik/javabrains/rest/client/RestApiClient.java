package org.koushik.javabrains.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.koushik.javabrains.messenger.model.Message;

public class RestApiClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		
		// A válasz az osztály példánya
		Message messageMessage = client.target("http://localhost:8080/advanced-jaxrs-01/webapi/messages/1")
				.request(MediaType.APPLICATION_JSON).get(Message.class);
		
		// A válasz egyszerű szöveg
		String messageString = client.target("http://localhost:8080/advanced-jaxrs-01/webapi/messages/1")
				.request(MediaType.APPLICATION_JSON).get(String.class);

		System.out.println(messageMessage);
		System.out.println(messageString);
	}

}
