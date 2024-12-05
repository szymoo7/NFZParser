package pl.backend.client.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HospitalizationByAge {
    private int year;
    private String branch;
    private String hospitalType;
    private int numberOfPatients;
    private int numberOfHospitalizations;
    private double percentage;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getHospitalType() {
        return hospitalType;
    }

    public void setHospitalType(String hospitalType) {
        this.hospitalType = hospitalType;
    }

    public int getNumberOfPatients() {
        return numberOfPatients;
    }

    public void setNumberOfPatients(int numberOfPatients) {
        this.numberOfPatients = numberOfPatients;
    }

    public int getNumberOfHospitalizations() {
        return numberOfHospitalizations;
    }

    public void setNumberOfHospitalizations(int numberOfHospitalizations) {
        this.numberOfHospitalizations = numberOfHospitalizations;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "HospitalizationByAge{" +
                "year=" + year +
                ", branch='" + branch + '\'' +
                ", hospitalType='" + hospitalType + '\'' +
                ", numberOfPatients=" + numberOfPatients +
                ", numberOfHospitalizations=" + numberOfHospitalizations +
                ", percentage=" + percentage +
                '}';
    }
}
