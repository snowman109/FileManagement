package com.tao.view;

import com.tao.file.File;
import com.tao.main.FIleOperate;
import com.tao.main.FileCommond;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateDialog extends JDialog implements ActionListener {
    private JLabel jl1, jl2;
    private JTextField jtf;
    private JRadioButton jrb1, jrb2;
    private JButton jb;
    private JPanel jp1, jp2;
    private ButtonGroup bg;
    private File now;
    private Manager manager;

    // 构造文件，画出创建的面板
    CreateDialog(File now, Manager manager) {
        this.now = now;
        this.manager = manager;
        jl1 = new JLabel("文件名");
        // 居中
        jl1.setHorizontalAlignment(SwingConstants.CENTER);
        jl2 = new JLabel("类型");
        jl2.setHorizontalAlignment(SwingConstants.CENTER);
        // 赋值宽度
        jtf = new JTextField(16);
        jrb1 = new JRadioButton("文件", true);
        jrb2 = new JRadioButton("文件夹");
        // 保证单选
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);

        jp1 = new JPanel();
        jp1.add(jrb1);
        jp1.add(jrb2);
        jb = new JButton("确定");
        jb.addActionListener(this);
        jb.setActionCommand("add");
        jp2 = new JPanel();
        jp2.setLayout(new GridLayout(2, 2));
        jp2.add(jl1);
        jp2.add(jtf);
        jp2.add(jl2);
        jp2.add(jp1);
        this.add(jp2, BorderLayout.CENTER);
        this.add(jb, BorderLayout.SOUTH);
        this.setSize(300, 150);
        this.setVisible(true);
        this.setTitle("添加");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 添加
        if (e.getActionCommand().equals("add")) {
            String fileName = jtf.getText();
            boolean success = false;
            // 选中文件
            if (bg.getSelection().equals(jrb1.getModel())) {
                success = FileCommond.write(now, fileName);
            } else {
                success = FileCommond.mkdir(fileName, now);
            }
            if (success) {
                now.updateData();
                manager.jTable.revalidate();
                manager.jScrollPane.revalidate();
                FIleOperate.write(FileCommond.ROOT);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "添加失败");
            }
        }
    }
}
