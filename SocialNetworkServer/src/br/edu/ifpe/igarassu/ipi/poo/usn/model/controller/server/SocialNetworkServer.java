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

package br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post.Post;
import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.user.User;
import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.user.UserArrayListDAO;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.SocialNetworkFacade;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.UserSocialNetworkFacade;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.user.AddUserHandler;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.user.GetUserByIdHandler;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.user.ListUserHandler;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.user.RemoveUserByIdHandler;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.user.SearchByNameUserHandler;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.PostNetworkFacade;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.PostSocialNetworkFacade;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.post.AddPostHandler;
import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server.post.ListPostHandler;

/**
 *
 * REST API Server of the Social Network
 * 
 * @author Allan Diego Silva Lima - allan.lima@igarassu.ifpe.edu.br
 *
 */
public class SocialNetworkServer {
	public static void main(String[] args) throws Exception {
		
		UserSocialNetworkFacade facade = new SocialNetworkFacade();
		PostNetworkFacade facadep = new PostSocialNetworkFacade() ;

		populateUsers(facade);
		popPost(facadep);

		HttpServer server = HttpServer.create(new InetSocketAddress(3000), 0);

		// each request has its own handler 
		server.createContext("/", new RootHandler());
		server.createContext("/user/get", new GetUserByIdHandler(facade));
		server.createContext("/user/add", new AddUserHandler(facade));
		server.createContext("/user/remove", new RemoveUserByIdHandler(facade));
		server.createContext("/user/search", new SearchByNameUserHandler(facade));
		server.createContext("/user/list", new ListUserHandler(facade));
		server.createContext("/post/add", new AddPostHandler(facadep));
		server.createContext("/post/list", new ListPostHandler(facadep));
		
		server.setExecutor(null);

		server.start();
	}

	private static void populateUsers(UserSocialNetworkFacade facade) {
		facade.addUser(new User(0, "Carla", "312","carla12@bol.com",9888888,"@carlinha"));
		facade.addUser(new User(1, "Carlos", "541","carlos157@gmail.com",9878787,"@157carloZ"));
		facade.addUser(new User(2, "Marcos", "451","marco2005@gmail.com",9779999,"@mcMR"));
		facade.addUser(new User(3, "Joao", "123","jojo22@hotmail.com",9223333,"@jojo222"));
		facade.addUser(new User(4, "Joana", "171","joaninhagatinha32@hotmail.com",923232323,"@joaninha44"));
	}
	
	
	private static void popPost(PostNetworkFacade facadep) {
		
		UserArrayListDAO user = new UserArrayListDAO();
		
		facadep.addPost(new Post(0,user.searchById(0),"Foto com os miguxos","@allan","Campus do Amor"));
		facadep.addPost(new Post(1,user.searchById(0),"Work Hard","@ifpe","Minha Casa"));
		facadep.addPost(new Post(2,user.searchById(1),"Amanha so funciono com energetico","","Abreu Abreu"));
		facadep.addPost(new Post(3,user.searchById(2),"Oq e dormi","@ranieri","Campus do Amor"));
		facadep.addPost(new Post(4,user.searchById(3),"Que sono Bom","","Paulista"));
		facadep.addPost(new Post(5,user.searchById(4),"Pq nao sou de Humanas kkk","","Garanhuns"));
	}

}
