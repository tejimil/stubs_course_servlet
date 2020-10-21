package ru.appline.logic;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Model instance = new Model();
	private final Map<Integer, User> model;
	
	public static Model getInstance() {
		return instance;
	}
	
	private Model() {
		model = new HashMap<>();
		
		model.put(1, new User("User","Userov",55555));
		model.put(2, new User("Admin","Adminov",99999));
		model.put(3, new User("Moderator","Moderatorov",109999));
		model.put(4, new User("Moderatr","Moderatrov",88888));
	}
	
	public void add(User user, int id) {
		model.put(id, user);
	}
	
	public Map<Integer, User> getFromList(){
		return model;
	}
}
