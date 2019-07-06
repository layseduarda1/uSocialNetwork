package br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.edu.ifpe.igarassu.ipi.poo.usn.data.access.arraylist.ArrayListDAO;
import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post.Post;
import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post.PostDAO;

public class PostArrayListDAO extends ArrayListDAO<Post> implements PostDAO{


	@Override
	public List<Post> searchPostbyname_acc(String name_account) {
		List<Post> post = new ArrayList<Post>();
		
		for(Iterator<Post> iterator  = this.list().iterator(); iterator.hasNext();) {
			Post el = iterator.next();
			if(el.getUser().getName_account().toLowerCase().contains(name_account.toLowerCase())) {
				post.add(el);
			}
			
		}
		return post;
	}

	@Override
	public void removeById(int id) {
		// TODO Auto-generated method stub
		
	}




}
