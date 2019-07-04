package br.edu.ifpe.igarassu.ipi.poo.usn.model.controller;

import java.util.List;

import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post.Post;

public interface PostNetworkFacade {
	
	//add Post
	public void addPost(Post post);
	
	//search Post
	public Post searchPost(long id);
	
	// return the post of user
	public List<Post> listPost();
	
	
	//remove of Post
	public void removePost(int id);
	
	
	/*
	 * Return number of post
	 */
	public int numberofUsers();
	
	/*
	 * Search Post by name account
	 */		
	public List<Post> searchPostbyname_acc(String name_account);
	

}
