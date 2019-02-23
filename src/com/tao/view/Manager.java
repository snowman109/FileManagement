package com.tao.view;

import com.tao.file.File;
import com.tao.main.FIleOperate;
import com.tao.main.FileCommond;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class Manager extends JFrame implements ActionListener {
    // 表格
    public JTable jTable;
    // 滚动条
    public JScrollPane jScrollPane;
    // 按钮
    private JButton jb1, jb2, jb3, jb4, jb5;
    // 面板
    private JPanel jp;

    public static Vector<Vector> data = new Vector<>(); // 表格的数据
    private static File now = FileCommond.ROOT;
    private static String path;
    private Vector<String> titleName;

    // 构造函数，创建主面板
    Manager() {
        // 写表格标题
        titleName = new Vector<>();
        titleName.add("文件名");
        titleName.add("类型");
        titleName.add("大小");

        now.updateData();
        path = FileCommond.pwd(now);
        jTable = new JTable(data, titleName);
        jScrollPane = new JScrollPane(jTable);
        jb1 = new JButton("新建");
        jb1.addActionListener(this);
        jb1.setActionCommand("add");
        jb2 = new JButton("删除");
        jb2.addActionListener(this);
        jb2.setActionCommand("delete");
        jb3 = new JButton("修改");
        jb3.addActionListener(this);
        jb3.setActionCommand("update");
        jb4 = new JButton("后退");
        jb4.addActionListener(this);
        jb4.setActionCommand("back");
        jb5 = new JButton("前进");
        jb5.addActionListener(this);
        jb5.setActionCommand("go");

        jp = new JPanel();
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb3);
        jp.add(jb4);
        jp.add(jb5);

        this.setTitle(path);
        this.add(jp, "North");
        this.add(jScrollPane);
        this.setSize(600, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    // 主函数，运行
    public static void main(String[] args) {
        Manager m = new Manager();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "update": {
                int rowNum = this.jTable.getSelectedRow();
                if (rowNum == -1) {
                    // 啥都没选
                    JOptionPane.showMessageDialog(this, "选择错误");
                } else {
                    File f = now.getSon().get(rowNum);
                    if (f.getType() == 0) {
                        // 文件夹
                        UpdateDirDialog udd = new UpdateDirDialog(f, now, this);
                    } else {
                        UpdateFileDialog ufd = new UpdateFileDialog(f, now, this);
                    }
                }
                break;
            }
            case "add":
                CreateDialog createDialog = new CreateDialog(now, this);
                break;
            case "delete": {
                int rowNum = this.jTable.getSelectedRow();
                if (rowNum == -1) {
                    JOptionPane.showMessageDialog(this, "选择错误");
                } else {
                    File f = now.getSon().get(rowNum);
                    boolean success = FileCommond.rm(now, f.getName(), f.getType());
                    if (success) {
                        now.updateData();
                        FIleOperate.write(FileCommond.ROOT);
                        jTable.revalidate();
                        jScrollPane.revalidate();
                    } else {
                        JOptionPane.showMessageDialog(this, "删除失败");
                    }
                }
                break;
            }
            case "go": {
                int rowNum = this.jTable.getSelectedRow();
                if (rowNum == -1) {
                    JOptionPane.showMessageDialog(this, "选择错误");
                } else {
                    File f = now.getSon().get(rowNum);
                    now = FileCommond.cd(f.getName(), now);
                    now.updateData();
                    jTable.revalidate();
                    jScrollPane.revalidate();
                    path = FileCommond.pwd(now);
                    this.setTitle(path);
                    this.revalidate();
                }
                break;
            }
            case "back":
                now = FileCommond.cd("..", now);
                now.updateData();
                jTable.revalidate();
                jScrollPane.revalidate();
                path = FileCommond.pwd(now);
                this.setTitle(path);
                this.revalidate();
                break;
        }
    }
}