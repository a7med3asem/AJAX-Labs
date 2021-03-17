package com.mak.validation;

import java.util.regex.Pattern;

public class MessageValidator implements Validator<String>{
	
	@Override
	public boolean isValid(String input) {
		return Pattern.matches(".+", input);
	}
	
}
