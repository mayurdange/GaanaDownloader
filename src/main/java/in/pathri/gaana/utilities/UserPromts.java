package in.pathri.gaana.utilities;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import in.pathri.gaana.constants.Global;
import in.pathri.gaana.constants.Language;
import in.pathri.gaana.constants.NewSearchPromt;
import in.pathri.gaana.constants.SearchType;
import in.pathri.gaana.dao.User;

public class UserPromts {
	static final Logger logger = LogManager.getLogger();
	private static Scanner input;
	static {
		input = new Scanner(System.in);
	}
	public static User promptForCred(){
		String userName = "";
		String password = "";
		
		System.out.println(Global.USERNAME_PROMPT);
		userName = input.nextLine();
		
		System.out.println(Global.PASSWORD_PROMPT);
		password = input.nextLine();
		
//		input.close();
		
		return new User(userName, password); 
	}
	
	public static boolean doNewSearch(){
		String option = "";
		System.out.println(Global.DO_NEW_SEARCH_PROMPT);
		printOptionNumber(NewSearchPromt.NEW_SEARCH.getOption());System.out.println(NewSearchPromt.NEW_SEARCH.getValue());
		printOptionNumber(NewSearchPromt.DOWNLOAD.getOption());System.out.println(NewSearchPromt.DOWNLOAD.getValue());	
//		logger.info(NewSearchPromt.values().toString());
		while((NewSearchPromt.NEW_SEARCH.getOption() != MiscUtilities.parseInt(option)) && (NewSearchPromt.DOWNLOAD.getOption() != MiscUtilities.parseInt(option))){
			System.out.println(Global.OPTION_PROMT);
			option = input.nextLine();
			logger.info("selected option:{}",option);
		}
		NewSearchPromt response = NewSearchPromt.getEnum(option);
		if(response == NewSearchPromt.NEW_SEARCH){
			return true;
		}
		return false;
	}

	public static void promptWrongCred(String message) {
		System.out.println(message);
	}

	public static Language getSearchLanguage() {
		String userResponse = "";
		int option = 0;
		Language response = null;
		System.out.println(Global.LANGUAGE_PROMPT);
		
		for(Language v : Language.values()){
			printOptionNumber(v.getOption());System.out.println(v.getValue());
		}
		
		while(null == response){
			System.out.println(Global.OPTION_PROMT);
			userResponse = input.nextLine();
			logger.info("selected option:{}",userResponse);
			response = Language.getEnum(MiscUtilities.parseInt(userResponse));			
		}
		return response;
	}

	public static SearchType getSearchType() {
		String userResponse = "";
		int option = 0;
		SearchType response = null;
		System.out.println(Global.SEARCHTYPE_PROMPT);
		
		for(SearchType v : SearchType.values()){
			printOptionNumber(v.getOption());System.out.println(v.getValue());
		}
		
		while(null == response){
			System.out.println(Global.OPTION_PROMT);
			userResponse = input.nextLine();
			logger.info("selected option:{}",userResponse);
			response = SearchType.getEnum(MiscUtilities.parseInt(userResponse));			
		}
		return response;
	}
	
	private static void printOptionNumber(int option){
		String toPrint = "[" + option + "]";
		System.out.print(toPrint);
	}
}
