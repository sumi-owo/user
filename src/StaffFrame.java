package user;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import user.domin.StaffItem;
import user.service.StaffService;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class StaffFrame extends JFrame implements ActionListener {
    private StaffService staffService = new StaffService();
    private JPanel contentPane;
    private JScrollPane scrollPane;
    protected JTable table;
    protected JButton jb_add;
    protected JButton jb_update;
    protected JButton jb_delete;
    protected JButton jb_exit;
    protected JButton jb_refresh;
    private JComboBox cb_select;
    private JPanel panel;
    private JLabel lblNewLabel;
    private static String num;
    private static String order;
    private String[] listMethod = new String[]{"用户编号升序", "用户编号降序", "用户姓名升序", "用户姓名降序", "用户类别升序", "用户类别降序"};
    /**
     * Launch the application.
     */


    /**
     * Create the frame.
     */
    public StaffFrame() {
        init();

    }

    public void init() {
        setFont(new Font("Dialog", Font.BOLD, 12));
        setTitle("用户管理系统");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 672, 546);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();

        panel = new JPanel();
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(panel, GroupLayout.PREFERRED_SIZE, 622, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 621, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(16, Short.MAX_VALUE))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
                                .addGap(32)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        //监听器注册
        jb_add = new JButton("添加用户");
        jb_add.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jb_add.addActionListener(this);

        jb_update = new JButton("修改用户");
        jb_update.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jb_update.addActionListener(this);

        jb_delete = new JButton("删除用户");
        jb_delete.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jb_delete.addActionListener(this);

        jb_exit = new JButton("退出");
        jb_exit.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jb_exit.addActionListener(this);

        jb_refresh = new JButton("刷新");
        jb_refresh.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        jb_refresh.addActionListener(this);

        cb_select = new JComboBox<>(listMethod);
        cb_select.setToolTipText("");

        lblNewLabel = new JLabel("重新排序");
        lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));

        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(jb_exit, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                .addComponent(jb_add, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(jb_delete, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(gl_panel.createSequentialGroup()
                                                                .addComponent(jb_refresh, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(jb_update, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
                                                .addGap(95)
                                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(cb_select, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
                                .addGap(224))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(27)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(jb_refresh, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jb_update, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                                        .addComponent(jb_add, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jb_delete, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(jb_exit, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(cb_select, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(62, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        cb_select.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println(1);
                    order = getOrder(cb_select.getSelectedIndex());
                    query();
                }
            }
        });

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table.getSelectedRow();
                num = (String) table.getValueAt(i, 0);
            }
        });


        table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        table.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[]{
                        "用户编号", "用户名称", "用户密码", "用户类别"
                }
        ));
//		scrollPane.setColumnHeaderView(table);
        scrollPane.setViewportView(table);
        contentPane.setLayout(gl_contentPane);
    }

    //添加用户方法
    public void addStaffItem() {

    }

    public void updateStaffItem() {

    }

    public void delStaffItem() {

    }

    public void query(){

    }

    public static String getNum() {
        return num;
    }

    public static String getOrderS(){
        return order;
    }

    public String getOrder(int i) {
        switch (i) {
            case 0:
                return "select * from user order by staffNum ASC";
            case 1:
                return "select * from user order by staffNum DESC";
            case 2:
                return "select * from user order by staffName ASC";
            case 3:
                return "select * from user order by staffName DESC";
            case 4:
                return "select * from user order by type ASC";
            case 5:
                return "select * from user order by type DESC";
            default:
                return "select * from user order by staffNum";
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(jb_add)) {
            addStaffItem();
        }
        if (obj.equals(jb_update)) {
            updateStaffItem();
        }
        if (obj.equals(jb_delete)) {
            delStaffItem();
        }
        if (obj.equals(jb_exit)) {
            int n = JOptionPane.showConfirmDialog(this, "你真的要退出吗?", "退出确认", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                System.exit(0);
            }
        }
        if (obj.equals(jb_refresh)) {
            queryStaffItems();
        }
    }

    //controller
    public void queryStaffItems() {
        String[] thead = {"用户编号", "用户名称", "用户密码", "用户类别"};
        List<StaffItem> dataList = staffService.queryStaffItems();
        String[][] tbody = List2Array(dataList);
        TableModel dataModel = new DefaultTableModel(tbody, thead);
        table.setModel(dataModel);
    }


    public String[][] List2Array(List<StaffItem> list) {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    StaffFrame frame = new StaffFrame();
                    frame.setVisible(true);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
