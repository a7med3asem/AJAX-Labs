package com.mak.validation;

public interface Validator<T> {
	boolean isValid(T input);
}
