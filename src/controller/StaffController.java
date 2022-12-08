package user.controller;

import com.formdev.flatlaf.FlatLightLaf;
import user.StaffFrame;
import user.domin.StaffItem;
import user.service.StaffService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;
import java.util.Objects;

public class StaffController extends StaffFrame {
    private StaffService staffService = new StaffService();

    public StaffController() {
        super();
        this.setVisible(true);
        login();
    }

    public void login() {
        List<StaffItem> dataList = staffService.queryStaffItems();
        boolean status = false;
        boolean vaildfalse;
        int num = 0;

        for (int j = 0; j < 3; j++) {
            vaildfalse = true;
            String username = JOptionPane.showInputDialog(null, "请输入用户名", "登录", JOptionPane.INFORMATION_MESSAGE);
            String passwd = JOptionPane.showInputDialog(null, "请输入密码", "登录", JOptionPane.INFORMATION_MESSAGE);
            for (int i = 0; i < dataList.size(); i++) {
                if (username == null || passwd == null) {
                    vaildfalse = false;
                    num++;
                    if (num < 3) {
                        JOptionPane.showMessageDialog(null, "你没有输入任何内容或点击了关闭按钮，超过三次无效登录系统将自动退出\nERROR: NO_INPUT_OR_CLICKED_CLOSE", "警告", JOptionPane.WARNING_MESSAGE);
                    }
                    int is = JOptionPane.showConfirmDialog(null, "你想要退出吗?", "退出确认", JOptionPane.YES_NO_OPTION);
                    if (is == 0 && num < 3) {
                        System.exit(0);
                    }
                    break;
                }
                StaffItem staffItem = dataList.get(i);
                if (username.equals(staffItem.getStaffName())) {
                    if (passwd.equals(staffItem.getPasswd())) {
                        if (staffItem.getType().equals("系统管理员")) {
                            JOptionPane.showMessageDialog(null, "欢迎", "提示", JOptionPane.INFORMATION_MESSAGE);
                            queryStaffItem();
                            status = true;
                            i = j = dataList.size();
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "你的权限不足以登录此系统，请与管理员联络以获取更多信息\nERROR: ACCESS_DENIED", "访问遭拒", JOptionPane.ERROR_MESSAGE);
                            System.exit(0);
                        }
                    }
                }
            }
            if (!status && vaildfalse) {
                num++;
                if (num < 3) {
                    JOptionPane.showMessageDialog(null, "用户名或密码无效，超过三次无效登录系统将自动退出\nERROR: WRONG_USERNAME_OR_PASSWORD", "警告", JOptionPane.WARNING_MESSAGE);
                }
            }
            if (vaildfalse) {
                vaildfalse = true;
            }
        }
        if (num > 2) {
            JOptionPane.showMessageDialog(null, "使用无效的用户名或密码超过三次，系统将自动退出", "访问遭拒", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void queryStaffItem() {
        List<StaffItem> dataList = staffService.queryStaffItems();
        String[] thead = {"用户编号", "用户名称", "用户密码", "用户类别"};
        String[][] tbody = list2Array(dataList);
        TableModel dataModel = new DefaultTableModel(tbody, thead);
        table.setModel(dataModel);
    }

    public String[][] list2Array(List<StaffItem> list) {
        String[][] tbody = new String[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            StaffItem staffItem = list.get(i);
            System.out.println(staffItem.getStaffNum());
            tbody[i][0] = staffItem.getStaffNum();
            tbody[i][1] = staffItem.getStaffName();
            tbody[i][2] = staffItem.getPasswd();
            tbody[i][3] = staffItem.getType();
        }
        return tbody;
    }

    public void addStaffItem() {
        String num = JOptionPane.showInputDialog(this, "输入用户编号", "添加用户", JOptionPane.QUESTION_MESSAGE);
        String name = JOptionPane.showInputDialog(this, "输入用户姓名", "添加用户", JOptionPane.QUESTION_MESSAGE);
        String passwd = JOptionPane.showInputDialog(this, "输入用户明文密码", "添加用户", JOptionPane.QUESTION_MESSAGE);
        String type = JOptionPane.showInputDialog(this, "输入用户类别", "添加用户", JOptionPane.QUESTION_MESSAGE);
        if (Objects.equals(num, "") || Objects.equals(name, "") || Objects.equals(passwd, "") || Objects.equals(type, "")) {
            JOptionPane.showMessageDialog(this, "添加失败，不允许输入空数据\nERROR: MORE_THAN_ONE_INPUT_IS_EMPTY", "错误", JOptionPane.ERROR_MESSAGE);
        } else if (Objects.equals(num, null) || Objects.equals(name, null) || Objects.equals(passwd, null) || Objects.equals(type, null)) {
            JOptionPane.showMessageDialog(this, "添加失败，请检查输入是否合法\nERROR: MORE_THAN_ONE_INPUT_IS_NULL", "错误", JOptionPane.ERROR_MESSAGE);
        } else {
            StaffItem s = new StaffItem(num, name, passwd, type);
            boolean flag = staffService.addStaffItem(s);
            if (flag) {
                queryStaffItem();
                JOptionPane.showMessageDialog(this, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "添加失败，请检查输入是否合法\nERROR: INVALID_INPUT", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void updateStaffItem() {
        String num = StaffFrame.getNum();
        if (Objects.equals(num, null)) {
            num = JOptionPane.showInputDialog(this, "输入用户编号，用户编号必须是数据库中存在的编号", "修改用户", JOptionPane.QUESTION_MESSAGE);
            if (Objects.equals(num, null)) {
                JOptionPane.showMessageDialog(this, "修改失败，请检查输入是否合法\nERROR: MORE_THAN_ONE_INPUT_IS_NULL", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int sw = JOptionPane.showConfirmDialog(this, "你想要修改选中的用户吗？", "", JOptionPane.YES_NO_OPTION);
        if (sw == -1) {
            num = JOptionPane.showInputDialog(this, "输入用户编号，用户编号必须是数据库中存在的编号", "修改用户", JOptionPane.QUESTION_MESSAGE);
            if (Objects.equals(num, null)) {
                JOptionPane.showMessageDialog(this, "修改失败，请检查输入是否合法\nERROR: MORE_THAN_ONE_INPUT_IS_NULL", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String name = JOptionPane.showInputDialog(this, "输入用户姓名", "修改用户", JOptionPane.QUESTION_MESSAGE);
        String passwd = JOptionPane.showInputDialog(this, "输入用户明文密码", "修改用户", JOptionPane.QUESTION_MESSAGE);
        String type = JOptionPane.showInputDialog(this, "输入用户类别", "修改用户", JOptionPane.QUESTION_MESSAGE);
        if (Objects.equals(num, "") || Objects.equals(name, "") || Objects.equals(passwd, "") || Objects.equals(type, "")) {
            JOptionPane.showMessageDialog(this, "修改失败，不允许输入空数据\nERROR: MORE_THAN_ONE_INPUT_IS_EMPTY", "错误", JOptionPane.ERROR_MESSAGE);
        } else if (Objects.equals(name, null) || Objects.equals(passwd, null) || Objects.equals(type, null)) {
            JOptionPane.showMessageDialog(this, "修改失败，请检查输入是否合法\nERROR: MORE_THAN_ONE_INPUT_IS_NULL", "错误", JOptionPane.ERROR_MESSAGE);
        } else {
            StaffItem s = new StaffItem(num, name, passwd, type);
            boolean flag = staffService.updateStaffItem(s);
            if (flag) {
                queryStaffItem();
                JOptionPane.showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "修改失败，无法找到指定的用户\nERROR: NO_SUCH_USER", "错误", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void delStaffItem() {
        String num = StaffFrame.getNum();
        if (Objects.equals(num, null)) {
            num = JOptionPane.showInputDialog(this, "输入用户编号，用户编号必须是数据库中存在的编号", "删除用户", JOptionPane.QUESTION_MESSAGE);
        }
        int sw = JOptionPane.showConfirmDialog(this, "你想要删除选中的用户吗？", "", JOptionPane.YES_NO_OPTION);
        if (sw == -1) {
            num = JOptionPane.showInputDialog(this, "输入用户编号，用户编号必须是数据库中存在的编号", "删除用户", JOptionPane.QUESTION_MESSAGE);
        }
        boolean flag = staffService.delStaffItem(num);
        if (flag) {
            queryStaffItem();
            JOptionPane.showMessageDialog(this, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "删除失败，无法找到指定的用户\nERROR: NO_SUCH_USER", "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void query() {
        List<StaffItem> dataList = staffService.query();
        String[] thead = {"用户编号", "用户名称", "用户密码", "用户类别"};
        String[][] tbody = list2Array(dataList);
        TableModel dataModel = new DefaultTableModel(tbody, thead);
        table.setModel(dataModel);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            new StaffController();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}