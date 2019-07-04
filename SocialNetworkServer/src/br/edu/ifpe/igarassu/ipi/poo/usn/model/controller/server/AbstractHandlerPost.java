package br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import br.edu.ifpe.igarassu.ipi.poo.usn.model.controller.PostSocialNetworkFacade;

public abstract class AbstractHandlerPost implements HttpHandler{
	
	
	private PostSocialNetworkFacade  facade = null;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	
	public AbstractHandlerPost(PostSocialNetworkFacade facade) {
		this.facade = facade;
	}
	
	public PostSocialNetworkFacade getFacade() {
		return this.facade;
	}
	
	public void setMapper(ObjectMapper mapper) {
		this.mapper = mapper;
	}
	
	
	public static void parseQuery(String query, Map<String, Object> parameters) throws UnsupportedEncodingException {
		if (query != null) {
			String pairs[] = query.split("[&]");
			for (String pair : pairs) {
				String param[] = pair.split("[=]");
				String key = null;
				String value = null;
				if (param.length > 0) {
					key = URLDecoder.decode(param[0], System.getProperty("file.encoding"));
				}

				if (param.length > 1) {
					value = URLDecoder.decode(param[1], System.getProperty("file.encoding"));
				}

				if (parameters.containsKey(key)) {
					Object obj = parameters.get(key);
					if (obj instanceof List<?>) {
						List<String> values = (List<String>) obj;
						values.add(value);

					} else if (obj instanceof String) {
						List<String> values = new ArrayList<String>();
						values.add((String) obj);
						values.add(value);
						parameters.put(key, values);
					}
				} else {
					parameters.put(key, value);
				}
			}
		}
	}
	
	protected Map<String, Object> parsePostParameters(HttpExchange exchange) throws UnsupportedEncodingException, IOException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
		BufferedReader br = new BufferedReader(isr);
		String query = br.readLine();
		parseQuery(query, parameters);
		
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
		    System.out.println(entry.getKey() + "/" + entry.getValue());
		}

		return parameters;
	}
	
	
	
	public static void printRequestInfo(HttpExchange exchange) throws MalformedURLException {
		System.out.println("-- request --");
		System.out.println(exchange.getRequestBody());
		String requestedURL = "http://" + exchange.getRequestHeaders().getFirst("Host") + exchange.getRequestURI();
		System.out.println(requestedURL);
		URL u = new URL(requestedURL);
		System.out.println("Host: " + u.getHost());
		System.out.println("Port: " + u.getPort());

		System.out.println("-- headers --");
		Map<String, List<String>> requestHeaders = exchange.getRequestHeaders();
		requestHeaders.entrySet().forEach(System.out::println);

		System.out.println("-- principle --");
		Principal principal = exchange.getPrincipal();
		System.out.println(principal);

		System.out.println("-- HTTP method --");
		String requestMethod = exchange.getRequestMethod();
		System.out.println(requestMethod);

		System.out.println("-- query --");
		URI requestURI = exchange.getRequestURI();
		String query = requestURI.getQuery();
		System.out.println(query);

		System.out.println("-- context --");
		HttpContext context = exchange.getHttpContext();
		System.out.println(context.getPath());
	}

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// .....
		
	}

	

}
