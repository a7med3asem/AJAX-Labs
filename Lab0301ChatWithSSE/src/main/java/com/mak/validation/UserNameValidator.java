package com.mak.validation;

import java.util.regex.Pattern;

public class UserNameValidator implements Validator<String>{
	
	@Override
	public boolean isValid(String input) {
		return Pattern.matches("[a-zA-Z1-9]+", input);
	}
	
}
