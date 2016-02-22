package auth;

import java.util.ArrayList;

public class UserService {
	private ArrayList<User> users;
	
	public UserService(){
		users = new ArrayList<User>();
		String salt1 = HashDigest.getSalt();
		users.add(new User("gebruiker", HashDigest.hash("wachtwoord1", salt1), salt1, "rogierpijpers1992@gmail.com"));
	}
	
	
	public boolean authenticate(String username, String password){
		try{
			User user = getUser(username);
			if(user.getPassword().equals(HashDigest.hash(password, user.getSalt()))){
				return true;
			}
		}catch(NullPointerException e){
			return false;
		}
		return false;
	}
	
	public User getUser(String username){
		for(User user : users){
			if(user.getUsername().equals(username)){
				return user;
			}
		}
		return null;
	}
}
