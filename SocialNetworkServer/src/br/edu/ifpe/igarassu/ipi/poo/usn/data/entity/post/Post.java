package br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.post;


import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.Entity;
import br.edu.ifpe.igarassu.ipi.poo.usn.data.entity.user.User;


public class Post extends Entity{
	

	private String extension;
	private int id_user;
	private String subtitle;
	private String  mention_name_acc;
	private User user;
	private String location;

	public Post(long id, User user, String subtitle, String mention,String location) {
		super(id);
		setUser(user);
		setSubtitle(subtitle);
		setMention_name_acc(mention);
		setLocation(location);
				
	}


	public String getExtension() {
		return extension;
	}



	public void setExtension(String extension) {
		this.extension = extension;
	}


	public int getId_user() {
		return id_user;
	}



	public void setId_user(int id_user) {
		this.id_user = id_user;
	}



	public String getMention_name_acc() {
		return mention_name_acc;
	}



	public void setMention_name_acc(String mention_name_acc) {
		this.mention_name_acc = mention_name_acc;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}


	public String getSubtitle() {
		return subtitle;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}



	
	
	

}