package pl.backend.client.enums;

public enum Catalog {
    A("1a", "Jednorodne grupy pacjentów"),
    B("1b", "Katalog świadczeń odrębnych"),
    C("1c", "Katalog świadczeń do sumowania"),
    D("1d", "Katalog świadczeń radioterapii"),
    W("1w", "Katalog świadczeń wysokospecjalistycznych");

    private final String code;
    private final String name;

    Catalog(String code, String name) {
        this.code = code;
        this.name = name;
    }
}

