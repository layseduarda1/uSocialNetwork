package br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.post;

import java.util.List;
import java.io.IOException;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post.Post;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.PostSocialNetworkFacade;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.UserSocialNetworkFacade;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.AbstractHandlerPost;

public class ListPostHandler extends AbstractHandlerPost{


	public ListPostHandler(PostSocialNetworkFacade facade) {
		super(facade);
	}

	@Override
	public void handle(HttpExchange ex) throws IOException {
		try {
			
			printRequestInfo(ex);
			
			System.out.println("List Post");
			List<Post> post = super.getFacade().listPost();
			
			ObjectMapper mapper = new ObjectMapper();
			
			String response = mapper.writeValueAsString(post);
			
			//System.out.println(response);
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
			
			//ex.sendResponseHeaders(401, response.length);
			
		}
		
	}
	

}
