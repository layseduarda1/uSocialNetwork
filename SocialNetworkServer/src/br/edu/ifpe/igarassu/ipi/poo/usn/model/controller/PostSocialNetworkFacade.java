package br.edu.ifpe.igarassu.ipi.poo.usn.model.controller;

import java.util.List;

import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post.Post;
import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post.PostDAO;
import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post.PostArrayListDAO;

public class PostSocialNetworkFacade implements PostNetworkFacade {
	
	//colection of Post
	private PostDAO PostDAO = new PostArrayListDAO();

	@Override
	public void addPost(Post post) {
		this.PostDAO.add(post);
		
	}

	@Override
	public Post searchPost(long id) {
		return this.PostDAO.searchById(id);
	}

	@Override
	public List<Post> listPost() {
		return this.PostDAO.list();
	}

	@Override
	public void removePost(int id) {
		this.PostDAO.removeById(id);
		
	}

	@Override
	public List<Post> searchPostbyname_acc(String name_account) {
		return this.PostDAO.searchPostbyname_acc(name_account);
	}

	@Override
	public int numberofUsers() {
		return this.PostDAO.size();
	}

}
