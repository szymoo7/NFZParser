package pl.backend.client.pojos;

import java.util.UUID;

public class Table {
    private int year;
    private UUID id;
    private String type;
    private String url;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public UUID getId() {
        return id;
    }

    public void setId(String id) {
        this.id = UUID.fromString(id);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Table{" +
                "year=" + year +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
