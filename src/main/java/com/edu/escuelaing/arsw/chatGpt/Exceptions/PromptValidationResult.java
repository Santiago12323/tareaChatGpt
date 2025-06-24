package com.edu.escuelaing.arsw.chatGpt.Exceptions;

public class PromptValidationResult {
    private final boolean valid;
    private final String errorMessage;

    private PromptValidationResult(boolean valid, String errorMessage) {
        this.valid = valid;
        this.errorMessage = errorMessage;
    }

    public static PromptValidationResult ok() {
        return new PromptValidationResult(true, null);
    }

    public static PromptValidationResult error(String message) {
        return new PromptValidationResult(false, message);
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
