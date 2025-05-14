package Models.Enums.Types;

public enum BackpackType {
    DEFAULT(12, "Default"),
    GIANT(24, "Giant"),
    DELUXE(Double.POSITIVE_INFINITY, "Deluxe");

    final private double capacity;
    final private String name;

    BackpackType(double maxCapacity, String name) {
        this.capacity = maxCapacity;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public double getCapacity() {
        return capacity;
    }
}
