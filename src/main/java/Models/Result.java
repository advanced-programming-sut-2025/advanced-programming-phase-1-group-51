package Models;

public class Result {
    private final boolean success;
    private final String message;
    private final Object data;  // New field for additional data

    public Result(boolean success, String message) {
        this(success, message, null);  // Chain to new constructor
    }

    // New constructor with data
    public Result(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Updated combine method (handles data)
    public Result combine(Result other) {
        return new Result(
                this.success && other.success,
                this.message + (other.message.isEmpty() ? "" : "\n" + other.message),
                other.data != null ? other.data : this.data  // Prefer other's data if exists
        );
    }

    // Existing success/failure methods
    public static Result success(String message) {
        return new Result(true, message);
    }

    public static Result failure(String message) {
        return new Result(false, message);
    }

    // New success method with data
    public static Result success(String message, Object data) {
        return new Result(true, message, data);
    }

    @Override
    public String toString() {
        return message;
    }

    // Getters
    public boolean isSuccessful() { return success; }
    public String getMessage() { return message; }
    public Object getData() { return data; }  // New getter
}
