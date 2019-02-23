package com.tao.file;

import com.tao.view.Manager;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

/*
implements Serializable 为了实现类的序列化
 */
public class File implements Serializable {
    private String name;
    private File parent;
    private List<File> son;
    private int type; // 0文件夹 1文件
    private String text; // 文件内容,是文件夹的话，为null

    // 构造函数
    public File(String name, File parent, List<File> son, int type, String text) {
        this.name = name;
        this.parent = parent;
        this.son = son;
        this.type = type;
        this.text = type == 0 ? null : text;
    }

    // 返回传入的文件和调用该函数的文件是否相等
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o; // 将o强转成file类型
        return type == file.type &&
                Objects.equals(name, file.name) &&
                Objects.equals(parent, file.parent);
    }

    // 得到对象的hashcode
    @Override
    public int hashCode() {
        return Objects.hash(name, parent, type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getParent() {
        return parent;
    }

    public void setParent(File parent) {
        this.parent = parent;
    }

    public List<File> getSon() {
        return son;
    }

    public void setSon(List<File> son) {
        this.son = son;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // 添加文件到儿子里
    public void addSon(File nFile) {
        this.son.add(nFile);
    }

    // 判断儿子里是否包含file
    public boolean contain(File file) {
        for (File f : this.son) {
            if (f.equals(file)) {
                return true;
            }
        }
        return false;
    }

    public void rmSon(File f) {
        this.son.remove(f);
    }

    /**
     * 为了更新显示的JTable
     */
    public void updateData() {
        Vector<Vector> data = Manager.data;
        data.clear();
        for (File f : son) {
            Vector v = new Vector();
            v.add(f.getName());
            v.add(f.getType() == 0 ? "文件夹" : "文件");
            if (f.getType() == 1) {
                v.add(f.getText() == null ? 0 : f.getText().length());// 如果直接f.getText().length()，f.getText()可能为空，会出空指针异常
            } else {
                v.add(null); // 不加会出表格填充异常
            }
            data.add(v);
        }
    }
}
