package pl.backend.client.enums;

public enum Age {
    ONE("poniżej 1", 1),
    TWO("1-6", 2),
    THREE("7-18", 3),
    FOUR("19-40", 4),
    FIVE("41-60", 5),
    SIX("61-80", 6),
    SEVEN("81 i więcej", 7),
    EIGHT("wiek nieustalony", 8);

    private final String category;
    private final int number;

    Age(String category, int number) {
        this.category = category;
        this.number = number;
    }

    @Override
    public String toString() {
        return category;
    }

    public int getNumber() {
        return number;
    }

}
