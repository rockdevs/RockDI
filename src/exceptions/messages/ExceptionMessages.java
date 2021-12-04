package exceptions.messages;

public enum ExceptionMessages {
    INVALID_CLASS_PATH("INVALID_CLASS_PATH"),
    INVALID_MAIN_CLASS_NAME("INVALID_MAIN_CLASS_NAME"),
    NO_ARGUMENT_CONSTRUCTOR("NO_ARGUMENT_CONSTRUCTOR")
    ;

    private final String message;
    ExceptionMessages(String message) {
        this.message=message;
    }

    public String getMessage() {
        return message;
    }
}
