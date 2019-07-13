/*
 *
 * IFPE - Federal Institute of Education, Science and Technology of Pernambuco
 * Informatics for the Internet
 * Object Oriented Programming
 * Professor: Allan Lima - allan.lima@igarassu.ifpe.edu.br
 * 
 * Public domain code, feel free to use, modify and redistribute it 
 *
 */

package br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.user;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.user.User;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.UserSocialNetworkFacade;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.AbstractHandler;

/**
 *
 * Handles a request to add an user.
 * 
 * @author Allan Diego Silva Lima - allan.lima@igarassu.ifpe.edu.br
 *
 */
public class AddUserHandler extends AbstractHandler {

	/**
	 * 
	 * Initializes its attributes
	 * 
	 * @param facade the facade of the system, containing the methods necessary to
	 *               the operation handled by this class
	 */
	public AddUserHandler(UserSocialNetworkFacade facade) {
		super(facade);
	}

	/**
	 * 
	 * Handles a request to add an user.
	 * 
	 * @param exchange the object containing the metadata of the request
	 * 
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		try {
			printRequestInfo(exchange);

			// get the post metadata
			Map<String, Object> parameters = parsePostParameters(exchange);
			System.out.println(parameters);

			// gets the data nedded to crate a new user
			String name = parameters.get("name").toString();
			String password = parameters.get("password").toString();
			String email = parameters.get("email").toString();
			int telefone = Integer.parseInt((String) parameters.get("telefone"));
			String name_account = parameters.get("name_account").toString();
			
			int id = super.getFacade().numberOfUsers();

			System.out.println("add user " + id + " | " + name + " | " + password + " | " + email + " | " + telefone + " | "+ name_account );

			User newUser = new User(id, name, password, email, telefone, name_account);

			// TODO handle errors correctly

			// performs the add
			super.getFacade().addUser(newUser);

			// TODO change the response to a JSON Object
			String response = "Sucess";
			
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
			
			if(exchange.getRequestMethod().equalsIgnoreCase("OPTION")) {
				exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
	            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
	            exchange.sendResponseHeaders(204, -1);
	            return;
			}

			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();

		} catch (Exception ex) {
			ex.printStackTrace();

			// TODO change the response to a JSON Object
			String response = "Failure";

			exchange.sendResponseHeaders(401, response.length());

			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}

	}

}
