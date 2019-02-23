package com.tao.main;

import com.tao.file.File;

import java.io.*;
import java.util.ArrayList;

// 保存当前用户信息到文件和读取当前信息到文件
public class FIleOperate {
    // 由文件读取
    public static File readFile() {
        String filename = ("D:\\test.txt");
        java.io.File newFile = new java.io.File(filename);
        File f = null;
        FileInputStream in;
        try {
            in = new FileInputStream(newFile);
            ObjectInputStream objIn = new ObjectInputStream(in);
            f = (File) objIn.readObject();
        } catch (IOException e) {
            // 没有找到文件的情况
            f = null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (f == null) {
            f = new File("ROOT", null,
                    new ArrayList<>(), 0, null);
        }
        return f;
    }

    // 写入文件
    public static void write(File root) {
        java.io.File file = new java.io.File("D:\\test.txt");
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(root);
            objOut.flush();
            objOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
