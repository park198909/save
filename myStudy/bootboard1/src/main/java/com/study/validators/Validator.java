package com.study.validators;

public interface Validator<T> extends RequiredValidator {
    void check(T t);
}
