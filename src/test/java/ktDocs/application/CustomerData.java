package ktDocs.application;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerData {
    @JsonProperty("abonentId")
    private long abonentId;
    private String addressKz;
    private String addressRu;
    private long customerAccountId;
    private int filialId;
    private String filialName;
    private String nameRu;
    private String nameKz;
    private String uin;

    public CustomerData(long abonentId, String addressKz, String addressRu, long customerAccountId, int filialId, String filialName, String nameRu, String nameKz, String uin) {
        this.abonentId = abonentId;
        this.addressKz = addressKz;
        this.addressRu = addressRu;
        this.customerAccountId = customerAccountId;
        this.filialId = filialId;
        this.filialName = filialName;
        this.nameRu = nameRu;
        this.nameKz = nameKz;
        this.uin = uin;
    }

    public CustomerData() {

    }

    // Getters and Setters
    public long getAbonentId() {
        return abonentId;
    }

    public void setAbonentId(long abonentId) {
        this.abonentId = abonentId;
    }

    public String getAddressKz() {
        return addressKz;
    }

    public void setAddressKz(String addressKz) {
        this.addressKz = addressKz;
    }

    public String getAddressRu() {
        return addressRu;
    }

    public void setAddressRu(String addressRu) {
        this.addressRu = addressRu;
    }

    public long getCustomerAccountId() {
        return customerAccountId;
    }

    public void setCustomerAccountId(long customerAccountId) {
        this.customerAccountId = customerAccountId;
    }

    public int getFilialId() {
        return filialId;
    }

    public void setFilialId(int filialId) {
        this.filialId = filialId;
    }

    public String getFilialName() {
        return filialName;
    }

    public void setFilialName(String filialName) {
        this.filialName = filialName;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameKz() {
        return nameKz;
    }

    public void setNameKz(String nameKz) {
        this.nameKz = nameKz;
    }

    public String getUin() {
        return uin;
    }

    public void setUin(String uin) {
        this.uin = uin;
    }
}
