package in.pathri.gaana.utilities;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
import in.pathri.gaana.dao.User;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class UserLogin {
	static final Logger logger = LogManager.getLogger();
	private static User user;
	
	public static String doLogin(User user){
		String token = "";
		Map<String, String> params = new HashMap<String,String>();
		params.put("type", "nxtgen_authenticate");
		params.put("username", user.getUserName());
		params.put("password", user.getPassword());
		
		String login_url = MiscUtilities.getGaanURL(Global.LOGIN_ENDPOINT);
		
		try {
			String loginResponse = HTTPHelper.sendPost(login_url, params);
			JSONObject userData = (JSONObject) JSONValue.parse(loginResponse);
			token = userData.getAsString("token");
		} catch (Exception e) {
			logger.catching(e);
			token = "";
		}
		
		return token;
	}
	
	public static User promptForCred(){
		String userName = "";
		String password = "";
		
		Scanner input = new Scanner(System.in);
		
		System.out.println(Global.USERNAME_PROMPT);
		userName = input.nextLine();
		
		System.out.println(Global.PASSWORD_PROMPT);
		password = input.nextLine();
		
//		input.close();
		
		return new User(userName, password); 
	}
	
	public static User getUserCred(){
		User user = PropertyReader.getUserCred();
		if(user.isEmpty()){
			return promptForCred();
		}
		return user;
	}
	
	public static boolean doLogin(){
		if(null == user){
			user = promptForCred();
		}
		if(null != user && !user.hasToken()){
			String token = doLogin(user);
			if(!token.isEmpty()){
				user.setToken(token);
			}			
		}
		if(user.hasToken()){
			logger.info("User Token::{}",user.getToken());
			//TODO: Set service with token
			return true;			
		}
		return false;
	}
}
