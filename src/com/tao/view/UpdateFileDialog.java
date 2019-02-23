package com.tao.view;

import com.tao.file.File;
import com.tao.main.FIleOperate;
import com.tao.main.FileCommond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateFileDialog extends JDialog implements ActionListener {
    private JPanel jp1;
    private JButton jb;
    // 很大的文本编辑
    private JTextArea jta;
    private JScrollPane jsp;
    private JTextField jtf;
    private JLabel jl;
    private File file;
    private File now;
    private Manager manager;

    // 传manager是为了对主面板的table进行更新
    // 构造函数，画出更新文件面板
    UpdateFileDialog(File file, File now, Manager manager) {
        this.file = file;
        this.now = now;
        this.manager = manager;
        jp1 = new JPanel();
        jb = new JButton("保存");
        jb.addActionListener(this);
        jb.setActionCommand("save");
        jl = new JLabel("文件名");
        // 初始化宽度为10，赋初值为file.getName
        jtf = new JTextField(10);
        jtf.setText(file.getName());

        jp1.add(jl);
        jp1.add(jtf);
        jp1.add(jb);

        // 新建大文本编辑，赋初值为file.getText() == null ? "" : file.getText()
        jta = new JTextArea();
        jta.setText(file.getText() == null ? "" : file.getText());
        jsp = new JScrollPane(jta);
        jsp.setSize(290, 550);

        this.add(jp1, BorderLayout.NORTH);
        this.add(jsp, BorderLayout.CENTER);
        this.setTitle("修改" + file.getName());
        this.setSize(300, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("save")) {
            file.setName(jtf.getText());
            file.setText(jta.getText());

            // 更新data
            now.updateData();
            // 更新主面板的表格
            manager.jTable.revalidate();
            manager.jScrollPane.revalidate();
            // 文件保存
            FIleOperate.write(FileCommond.ROOT);
            // 消失
            this.dispose();
        }
    }
}
