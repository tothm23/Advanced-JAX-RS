package org.koushik.javabrains.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.koushik.javabrains.messenger.model.Message;

public class RestApiClient {

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();

		WebTarget baseTarget = client.target("http://localhost:8080/advanced-jaxrs-01/webapi/");
		WebTarget messagesTarget = baseTarget.path("messages");
		WebTarget singleMessageTarget = messagesTarget.path("{messageId}");

		Message message = singleMessageTarget.resolveTemplate("messageId", "2").request(MediaType.APPLICATION_JSON)
				.get(Message.class);

		Message newMesage = new Message(4, "My new message from JAX-RS client", "tothmilan");
		Response postResponse = messagesTarget.request().post(Entity.json(newMesage));
		Message createdMessage = postResponse.readEntity(Message.class);

		if (postResponse.getStatus() != 201) {
			System.out.println("Error!");
		}

		System.out.println(createdMessage.getMessage());

	}

}
