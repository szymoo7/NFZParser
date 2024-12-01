package pl.backend.client.enums;

public enum Case {
    STABILNY(1, "Stabilny"),
    PILNY(2, "Pilny");

    private final int number;
    private final String name;

    Case(int number, String name) {
        this.number = number;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
