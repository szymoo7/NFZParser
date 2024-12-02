package pl.backend.client.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueAttributes {
    private String benefit;
    private String provider;
    private String place;
    private String address;
    private String locality;
    private String phone;
    private String date;

    public String getBenefit() {
        return normalizeText(benefit);
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getProvider() {
        return normalizeText(provider);
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPlace() {
        return normalizeText(place);
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return normalizeText(address);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocality() {
        return normalizeText(locality);
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getPhone() {
        return normalizeText(phone);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return normalizeText(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Nazwa świadczenia: " + benefit +
                ", Świadczeniodawca: " + provider +
                ", Miejsce: " + place +
                ", Adres: " + address +
                ", Miejscowość: " + locality +
                ", Telefon: " + phone +
                ", Data: " + date;
    }

    private String normalizeText(String field) {
        if (field == null || field.isEmpty()) {
            return field;
        }
        return field.substring(0, 1).toUpperCase() + field.substring(1).toLowerCase();
    }

}
