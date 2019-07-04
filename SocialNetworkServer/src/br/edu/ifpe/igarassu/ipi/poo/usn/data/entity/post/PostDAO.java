package br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post;

import java.util.List;

public interface PostDAO {
	
		/*
		 * List all post's
		 */
		public List<Post> list();
		
		
		/*
		 * Add Post a collection
		 */
		public void add(Post post);

		/*
		 * Remove a Post 
		 */
		public void removeById(int id);
		
		/*
		 * Search Post by id
		 */
		public Post searchById(long id);
		
		/*
		 * Search Post by name account
		 */		
		public List<Post> searchPostbyname_acc(String name_account);

		/*
		 * return mumber of Post
		 */
		public int size();
		
		

}
