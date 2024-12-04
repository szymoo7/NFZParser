package pl.backend.client.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvisionsData {
    private String medicineProduct;
    private String internationalName;
    private String dose;
    private String pack;
    private double marketPrice;
    private double patientPrice;

    public String getMedicineProduct() {
        return medicineProduct;
    }

    public void setMedicineProduct(String medicineProduct) {
        this.medicineProduct = medicineProduct;
    }

    public String getInternationalName() {
        return internationalName;
    }

    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getMarketPrice() {
        return String.format("%.2f", marketPrice);
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getPatientPrice() {
        return String.format("%.2f", patientPrice);
    }

    public void setPatientPrice(double patientPrice) {
        this.patientPrice = patientPrice;
    }

    @Override
public String toString() {
    return String.format("ProvisionsData{medicineProduct='%s', internationalName='%s'," +
                    " dose='%s', pack='%s', marketPrice=%.2f, patientPrice=%.2f}",
            medicineProduct, internationalName, dose, pack, marketPrice, patientPrice);
}
}
