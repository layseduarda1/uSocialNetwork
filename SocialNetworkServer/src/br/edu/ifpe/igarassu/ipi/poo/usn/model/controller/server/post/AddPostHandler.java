package br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.post;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.PostNetworkFacade;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.PostSocialNetworkFacade;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.AbstractHandlerPost;
import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.user.UserArrayListDAO;
import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post.Post;

public class AddPostHandler extends AbstractHandlerPost {

	public AddPostHandler(PostNetworkFacade facade) {
		super(facade);
	}
	
	@Override
	public void handle(HttpExchange ex) throws IOException {
		try {
			printRequestInfo(ex);
			
			Map<String, Object> parameters = parsePostParameters(ex);
			
			String name = parameters.get("name").toString();
			int id = Integer.parseInt(parameters.get("id").toString());
			String subtitle = parameters.get("subtitle").toString();
			String mention_name_acc = parameters.get("mention_n_acc").toString();
			String location = parameters.get("location").toString();
			
			int i = super.getFacade().numberofUsers();
			
			UserArrayListDAO user = new UserArrayListDAO();
			
			Post post = new Post(i,user.searchById(id),subtitle,mention_name_acc,location);
			
			super.getFacade().addPost(post);
			
			
			String response = "Sucess";
			
			ex.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
			
			if(ex.getRequestMethod().equalsIgnoreCase("OPTION")) {
				ex.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
	            ex.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
	            ex.sendResponseHeaders(204, -1);
	            return;
			}
			
			ex.sendResponseHeaders(200, response.length());
			OutputStream os = ex.getResponseBody();
			os.write(response.getBytes());
			os.close();
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			String response = "Failure";
			
			ex.sendResponseHeaders(401, response.length());
			
			OutputStream os = ex.getResponseBody();
			os.write(response.getBytes());
			os.close();
			
			
		}
		
	}

	

}
