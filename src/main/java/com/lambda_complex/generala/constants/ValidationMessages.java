package com.lambda_complex.generala.constants;

public class ValidationMessages {
    public static  final String NOT_NULL = "can not be null";
    public static  final String NOT_BLANK = "can not be blank";
    public static final String REQUIRED = "is required";
    public static final String NAME_SIZE = "must be between "+ ValidationConstraints.MIN_NAME_CHARS +" y "+ValidationConstraints.MAX_NAME_CHARS+" caracteres";
    public static final String PASS_SIZE = "must be between "+ ValidationConstraints.MIN_PASS_CHARS +" y "+ValidationConstraints.MAX_PASS_CHARS+" caracteres";
    public static final String EMAIL_SIZE = "must have a maximum of "+ValidationConstraints.MAX_EMAIL_CHARS+" caracteres";
}
