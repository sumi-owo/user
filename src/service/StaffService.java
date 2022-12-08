package user.service;

import user.dao.StaffDao;
import user.domin.StaffItem;

import java.util.List;

public class StaffService {
    private StaffDao staffDao = new StaffDao();

    public List<StaffItem> queryStaffItems() {
        return staffDao.queryAllData();
    }

    public boolean addStaffItem(StaffItem staffItem) {
        List<StaffItem> list = queryStaffItems();
        for (StaffItem item : list) {
            if (staffItem.getStaffNum().equals(item.getStaffNum())) {
                return false;
            }
        }
        staffDao.addStaffItem(staffItem);
        return true;
    }

    public boolean updateStaffItem(StaffItem staffItem) {
        List<StaffItem> list = queryStaffItems();
        for (StaffItem item : list) {
            if (staffItem.getStaffNum().equals(item.getStaffNum())) {
                staffDao.updateStaffItem(staffItem);
                return true;
            }
        }
        return false;
    }

    public boolean delStaffItem(String delNum) {
        List<StaffItem> list = queryStaffItems();
        for (StaffItem staffItem : list) {
            if (delNum.equals(staffItem.getStaffNum())) {
                staffDao.deleteStaff(delNum);
                return true;
            }
        }
        return false;
    }

    public List<StaffItem> query() {
        return staffDao.query();
    }
}
