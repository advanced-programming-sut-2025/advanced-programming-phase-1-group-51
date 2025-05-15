package Models;

public class Result {
    private final boolean success;
    private final String message;

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Result combine(Result other) {
        return new Result(
                this.success && other.success,
                this.message + (other.message.isEmpty() ? "" : "\n" + other.message)
        );
    }

    public static Result success(String message) {
        return new Result(true, message);
    }

    public static Result failure(String message) {
        return new Result(false, message);
    }

    @Override
    public String toString() {
        return message; // Or format it however you prefer
    }

    // Getters
    public boolean isSuccessful() { return success; }
    public String getMessage() { return message; }
}
