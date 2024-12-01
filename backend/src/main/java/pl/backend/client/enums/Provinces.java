package pl.backend.client.enums;

public enum Provinces {
    /*
        Kod województwa
        Dostępne wartości:
        01 – dolnośląskie
        02 – kujawsko-pomorskie
        03 – lubelskie
        04 – lubuskie
        05 - łódzkie
        06 – małopolskie
        07 – mazowieckie
        08 – opolskie
        09 – podkarpackie
        10 – podlaskie
        11 – pomorskie
        12 – śląskie
        13 – świętokrzyskie
        14 – warmińsko-mazurskie
        15 – wielkopolskie
        16 – zachodniopomorskie
    */

    DOLNOSLASKIE("01", "Dolnośląskie"),
    KUJAWSKO_POMORSKIE("02", "Kujawsko-pomorskie"),
    LUBELSKIE("03", "Lubelskie"),
    LUBUSKIE("04", "Lubuskie"),
    LODZKIE("05", "Łódzkie"),
    MALOPOLSKIE("06", "Małopolskie"),
    MAZOWIECKIE("07", "Mazowieckie"),
    OPOLSKIE("08", "Opolskie"),
    PODKARPACKIE("09", "Podkarpackie"),
    PODLASKIE("10", "Podlaskie"),
    POMORSKIE("11", "Pomorskie"),
    SLASKIE("12", "Śląskie"),
    SWIETOKRZYSKIE("13", "Świętokrzyskie"),
    WARMINSKO_MAZURSKIE("14", "Warmińsko-mazurskie"),
    WIELKOPOLSKIE("15", "Wielkopolskie"),
    ZACHODNIOPOMORSKIE("16", "Zachodniopomorskie");

    private final String code;
    private final String name;

    Provinces(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
