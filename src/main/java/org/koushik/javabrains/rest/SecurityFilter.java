package org.koushik.javabrains.rest;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.client.Entity;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;

@Provider
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "secured ";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

		// Az összes végpont védve van, de csak a /secured végpontot szeretném védeni
		if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {

			// Kiolvassa az Authorization fejlécet
			List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER);

			// Ha van ilyen fejléc
			if (authHeader != null && authHeader.size() > 0) {

				// Kiválasztom az 1. fejlécet
				String authToken = authHeader.get(0);

				// Hozzáteszem a Basic kulcsszót a tokenhez
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");

				// Visszafejtem a szöveget
				String decodedString = Base64.decodeAsString(authToken);

				// Kiolvasom a felhasználónevet és jelszót
				StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();

				// Kezdeteles ellenőrzés, ha minden rendben, akkor minden megy tovább
				if ("user".equals(username) && "password".equals(password)) {
					return;
				}

				// Válasz a hitelesítetlen felhasználónak
				Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
						.entity("user cannot access the resource.").build();

				// Behelyezem a futtató környezetben, így lehetővé teszi a kérés megszakítását
				requestContext.abortWith(unauthorizedStatus);
			}
		}
	}

}
