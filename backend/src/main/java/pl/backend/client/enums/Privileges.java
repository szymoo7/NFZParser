package pl.backend.client.enums;

public enum Privileges {
    S("Uprawnienie 75+"),
    X("Uprawnienie spoza 75+");

    private String text;

    Privileges(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public String getCode() {
        return name().substring(0, 1);
    }
}
