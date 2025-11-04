package enums;

public class StatusExample {
    public static void main(String[] args) {
        Status status = Status.SUCCESS;
        System.out.println(status + " - Code: " + status.getCode() + ", Message: " + status.getMessage());
    }
}

enum Status {
    SUCCESS(200, "Operation successful"),
    ERROR(500, "Internal server error"),
    NOT_FOUND(404, "Resource not found");

    private final int code;
    private final String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
