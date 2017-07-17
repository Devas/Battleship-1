package com.java_academy.battleship.helper;

import java.util.Locale;
import java.util.Scanner;

/** Just for test with UI, after tests to remove*/
public class I18NTestMainToRemove {
	
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("Please choose the language/Wybierz język: 1 - English, 2 - Polish. Default English.");
    	String lang = s.nextLine();
    	
    	createIOResolver(lang);
    	
    	System.out.println(I18NResolver.getI18NResolverInstance().getMsgByKey("hello.world"));
	}

    private static void createIOResolver(String langNumber) {
		switch(langNumber) {
			case "1":
				I18NResolver.createI18NResolver(new Locale("en", "EN"));
				break;
			case "2":
				I18NResolver.createI18NResolver(new Locale("pl", "PL"));
				break;
			default:
				I18NResolver.createI18NResolver(new Locale("en", "EN"));
		}
	}
}
