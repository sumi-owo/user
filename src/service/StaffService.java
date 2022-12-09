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
        for (int i = 0; i < list.size(); i++) {
            if (staffItem.getStaffNum().equals(list.get(i).getStaffNum())) {
                return false;
            }
        }
        staffDao.addStaffItem(staffItem);
        return true;
    }

    public boolean updateStaffItem(StaffItem staffItem) {
        List<StaffItem> list = queryStaffItems();
        for (int i = 0; i < list.size(); i++) {
            if (staffItem.getStaffNum().equals(list.get(i).getStaffNum())) {
                staffDao.updateStaffItem(staffItem);
                return true;
            }
        }
        return false;
    }

    public boolean delStaffItem(String delNum) {
        List<StaffItem> list = queryStaffItems();
        for (int i = 0; i < list.size(); i++) {
            if (delNum.equals(list.get(i).getStaffNum())) {
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
