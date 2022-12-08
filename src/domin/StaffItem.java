package user.domin;

public class StaffItem {
    private String staffNum;
    private String staffName;
    private String passwd;
    private String type;

    public StaffItem() {
        super();
    }

    public StaffItem(String staffNum, String staffName, String passwd, String type) {
        super();
        this.staffNum = staffNum;
        this.staffName = staffName;
        this.passwd = passwd;
        this.type = type;
    }

    public String getStaffNum() {
        return staffNum;
    }

    public void setStaffNum(String staffNum) {
        this.staffNum = staffNum;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "StaffItem [staffNum=" + staffNum + ", staffName=" + staffName + ", staffPasswd=" + passwd
                + ", staffType=" + type + "]";
    }
}
