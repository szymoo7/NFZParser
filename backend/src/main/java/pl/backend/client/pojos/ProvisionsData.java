package pl.backend.client.pojos;

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

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public double getPatientPrice() {
        return patientPrice;
    }

    public void setPatientPrice(double patientPrice) {
        this.patientPrice = patientPrice;
    }

    @Override
    public String toString() {
        return "ProvisionsData{" +
                "medicineProduct='" + medicineProduct + '\'' +
                ", internationalName='" + internationalName + '\'' +
                ", dose='" + dose + '\'' +
                ", pack='" + pack + '\'' +
                ", marketPrice=" + marketPrice +
                ", patientPrice=" + patientPrice +
                '}';
    }
}
