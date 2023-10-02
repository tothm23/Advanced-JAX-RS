package org.koushik.javabrains.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import org.koushik.javabrains.messenger.model.Message;

public class RestApiClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();
		Response response = client.target("http://localhost:8080/advanced-jaxrs-01/webapi/messages/1").request().get();
		Message message = response.readEntity(Message.class);
		System.out.println(message.getMessage());
	}

}
