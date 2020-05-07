package ua.nure.smirnov.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	
	private static final String REGEX_MAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	 public static final String REGEX_NAME_EN = "[a-zA-Z]";
	 public static final String REGEX_NAME_RU = "[А-Яа-яіїІЇ]";
	 private static final String REGEX_CARD_NUMBER = "([2-6]([0-9]{3}) ?)(([0-9]{4} ?){3})";
	 private static final String REGEX_CVV = "[0-9]{3}";
	
	public boolean validatorMail(String login) {
		Pattern patt = Pattern.compile(REGEX_MAIL);
		Matcher match = patt.matcher(login);
		return match.matches();
	}

	public boolean validatorNameEngRusUa(String name) {
		boolean bool = true;
		Pattern patt = Pattern.compile(REGEX_NAME_EN);
		Matcher match = patt.matcher(name);
		bool = match.matches();
		Pattern patt2 = Pattern.compile(REGEX_NAME_RU);
		Matcher match2 = patt2.matcher(name);
		bool = match2.matches();
		return bool;
	}
	
	 public boolean validatorcardNumber(String cardNumber) {
		 boolean bool = true;
		 Pattern p = Pattern.compile(REGEX_CARD_NUMBER);
		 Matcher m = p.matcher(cardNumber);
		 bool = m.matches();
		 return bool;
	 }
	 
	 public boolean validatorcardCVV(String cvv) {
		 boolean bool = true;
		 Pattern p = Pattern.compile(REGEX_CVV);
		 Matcher m = p.matcher(cvv);
		 bool = m.matches();
		 return bool;
	 }
}
