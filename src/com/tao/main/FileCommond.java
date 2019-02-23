package com.tao.main;

import com.tao.file.File;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 文件系统的操作
public class FileCommond {
    public static File ROOT;
    private static final String PARENT = "..";

    // 每次启动时读取上次的记录
    static {
        ROOT = FIleOperate.readFile();
    }

    // 真正的添加文件
    private static boolean add(String name, File now, String text) {
        File nFile = new File(name, now, null, 1, text);
        if (!now.contain(nFile)) {
            now.addSon(nFile);
        } else {
            System.out.println("该文件已存在");
            return false;
        }
        return true;
    }

    // 创建文件夹
    public static boolean mkdir(String name, File now) {
        File nDir = new File(name, now, new ArrayList<>(), 0, null);
        if (!now.contain(nDir)) {
            now.addSon(nDir);
        } else {
            System.out.println("该文件夹已存在");
            return false;
        }
        return true;
    }

    // 切换路径
    public static File cd(String path, File now) {
        if (now.getType() == 1) {
            System.out.println("不支持的命令");
            return now;
        }
        List<String> names = Arrays.asList(path.split("/"));
        File _now = now;
        for (String name : names) {
            boolean isFind = false;
            if (name.equals(PARENT)) {
                if (!_now.equals(ROOT)) {
                    _now = _now.getParent();
                    isFind = true;
                }
            } else {
                List<File> sons = _now.getSon();
                for (File f : sons) {
                    if (f.getName().equals(name) && f.getType() == 0) {
                        _now = f;
                        isFind = true;
                        break;
                    }
                }
            }
            if (!isFind) {
                System.out.println("未找到指定路径");
                return now;
            }
        }
        return _now;
    }

    /**
     * 添加文件前的一些判断
     */
    public static boolean write(File now, String... params) {
        if (params.length > 2 || params.length < 1) {
            System.out.println("参数错误");
            return false;
        }
        if (now.getType() == 1) {
            System.out.println("不支持的命令");
            return false;
        }
        String name = params[0];
        String text = "";
        if (params.length == 2) {
            text = params[1];
        }
        return add(name, now, text);
    }

//    public static void read(String name, File now) {
//        if (now.getType() == 1) {
//            System.out.println("不支持的命令");
//            return;
//        }
//        List<File> sons = now.getSon();
//        File goal = null;
//        for (File f : sons) {
//            if (f.getName().equals(name) && f.getType() == 1) {
//                goal = f;
//                break;
//            }
//        }
//        if (goal == null) {
//            System.out.println("未找到指定路径");
//            return;
//        }
//        System.out.println(goal.getText());
//    }
//
//    public static void ls(File now) {
//        if (now.getType() == 1) {
//            System.out.println("不支持的命令");
//            return;
//        }
//        List<File> sons = now.getSon();
//        sons.sort(Comparator.comparingInt(File::getType));
//        for (File f : sons) {
//            System.out.println(f.getName() + "-------" +
//                    (f.getType() == 0 ? "文件夹" : "文件"));
//        }
//    }

    // 查看当前路径
    public static String pwd(File now) {
        List<String> paths = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        File next = now;
        while (!next.equals(ROOT)) {
            paths.add(next.getName());
            next = next.getParent();
        }

        paths.add(ROOT.getName());

        for (int i = paths.size(); i > 0; i--) {
            if (i == 1) {
                path.append(paths.get(i - 1));
            } else {
                path.append(paths.get(i - 1)).append("/");
            }
        }
        return path.toString();
    }

    // 删除文件或文件夹
    public static boolean rm(File now, String name, int type) {
        if (now.getType() == 1) {
            System.out.println("不支持的命令");
            return false;
        }
        List<File> sons = now.getSon();
        File goal = null;
        for (File f : sons) {
            if (f.getName().equals(name) && f.getType() == type) {
                if (type == 0 && f.getSon() != null && f.getSon().size() != 0) {
                    System.out.println("文件夹下有内容，无法删除");
                    return false;
                }
                goal = f;
                break;
            }
        }
        if (goal == null) {
            System.out.println("要删除的文件(夹)不存在");
            return false;
        } else {
            now.rmSon(goal);
        }
        return true;
    }

}
