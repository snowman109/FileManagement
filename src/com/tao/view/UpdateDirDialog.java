package com.tao.view;

import com.tao.file.File;
import com.tao.main.FIleOperate;
import com.tao.main.FileCommond;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateDirDialog extends JDialog implements ActionListener {
    // 显示文本
    private JLabel jl;
    // 文本输入
    private JTextField jtf;
    private JPanel jp;
    private JButton jb;
    private File file;
    private File now;
    private Manager manager;

    // 构造函数，画出更新文件夹面板
    UpdateDirDialog(File file, File now, Manager manager) {
        this.file = file;
        this.now = now;
        this.manager = manager;
        jl = new JLabel("文件夹名");
        // 传入的是初始值
        jtf = new JTextField(file.getName());
        jp = new JPanel();
        jb = new JButton("保存");
        jb.addActionListener(this);
        jb.setActionCommand("save");

        // 顺序不能错
        jp.add(jl);
        jp.add(jtf);
        jp.add(jb);
        this.add(jp);
        this.setTitle("修改" + file.getName());
        this.setSize(300, 100);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            file.setName(jtf.getText());
            // 更新 用于更新表格的数据
            now.updateData();
            // 更新表格
            manager.jTable.revalidate();
            manager.jScrollPane.revalidate();
            // 保存文件
            FIleOperate.write(FileCommond.ROOT);
            // 当前面板消失
            this.dispose();
        }
    }
}
