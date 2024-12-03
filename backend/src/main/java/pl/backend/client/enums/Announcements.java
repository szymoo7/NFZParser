package pl.backend.client.enums;

public enum Announcements {
    Y("Pozycje zawarte w obwieszczeniu"),
    N("Pozycje nie zawarte w obwieszczeniu");

    private String text;

    Announcements(String text) {
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
