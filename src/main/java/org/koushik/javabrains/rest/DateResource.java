package org.koushik.javabrains.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("test")
public class DateResource {

	@GET
	// Választható adattípusok
	@Produces(value = { MediaType.TEXT_PLAIN, "text/shortdate" })
	public String getRequestedDate(@PathParam("dateString") MyDate myDate) {
		return "Got " + myDate.toString();
	}
}
