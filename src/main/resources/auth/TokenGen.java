package auth;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class TokenGen {
	private final static String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final static int N = alphabet.length();
    private static TokenGen gen;
    
    private ArrayList<String> oneTimeTokens;
    
    private TokenGen(){
    	oneTimeTokens = new ArrayList<String>();
    }
    
    public static TokenGen getInstance(){
    	if(gen == null){
    		gen = new TokenGen();
    	}
    	return gen;
    }
    
    public boolean cashToken(String token){
    	if(oneTimeTokens.contains(token)){
    		oneTimeTokens.remove(token);
    		return true;
    	}
    	return false;
    }
	
	public String generateToken() {
		
	    Random r = new Random();
	    
	    String token = "";
	    
	    for (int i = 0; i < 20; i++) {
	        token += alphabet.charAt(r.nextInt(N));
	    }
	    
	    oneTimeTokens.add(token);
		return token;
	}
}
