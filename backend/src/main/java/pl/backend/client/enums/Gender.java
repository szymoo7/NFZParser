package pl.backend.client.enums;

public enum Gender {
    M("Mężczyzna"),
    F("Kobieta"),
    N("Nieznana");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getCode() {
        return name().substring(0, 1);
    }
}
