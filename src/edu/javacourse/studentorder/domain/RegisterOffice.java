package edu.javacourse.studentorder.domain;

public class RegisterOffice {
    private Long officeId;
    private String officeAreaId;
    private String getOfficeName;

    public RegisterOffice() {
    }

    public RegisterOffice(Long officeId, String officeAreaId, String getOfficeName) {
        this.officeId = officeId;
        this.officeAreaId = officeAreaId;
        this.getOfficeName = getOfficeName;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public String getOfficeAreaId() {
        return officeAreaId;
    }

    public void setOfficeAreaId(String officeAreaId) {
        this.officeAreaId = officeAreaId;
    }

    public String getGetOfficeName() {
        return getOfficeName;
    }

    public void setGetOfficeName(String getOfficeName) {
        this.getOfficeName = getOfficeName;
    }
}
