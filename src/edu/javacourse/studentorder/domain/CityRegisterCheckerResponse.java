package edu.javacourse.studentorder.domain;

public class CityRegisterCheckerResponse {
    //existing - существующий - может принимать да или нет
//temporal - временный - может принимать да, нет и null
// в случае когда ексистинг = нет темпорал = нулл
    private boolean existing;
    private Boolean temporal;

    public boolean isExisting() {
        return existing;
    }
    public void setExisting(boolean existing) {
        this.existing = existing;
    }
    public Boolean getTemporal() {
        return temporal;
    }
    public void setTemporal(Boolean temporal) {
        this.temporal = temporal;
    }

    @Override
    public String toString() {
        return "CityRegisterCheckerResponse{" +
                "existing=" + existing +
                ", temporal=" + temporal +
                '}';
    }
}
