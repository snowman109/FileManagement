//package com.tao.main;
//
//import com.tao.file.File;
//
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);
//        File now = FileCommond.ROOT;
//        label:
//        while (true) {
//            System.out.print("#" + now.getName() + "/");
//            String command = s.nextLine();
//            String[] params = command.split(" ");
//            if (params.length < 1) {
//                System.out.println("输入错误");
//            }
//            String cmd = params[0];
//
//            switch (cmd) {
//                case OperateConst.CD:
//                    now = FileCommond.cd(params[1], now);
//                    break;
//                case OperateConst.LS:
//                    FileCommond.ls(now);
//                    break;
//                case OperateConst.MKDIR:
//                    FileCommond.mkdir(params[1], now);
//                    break;
//                case OperateConst.READ:
//                    FileCommond.read(params[1], now);
//                    break;
//                case OperateConst.WRITE:
//                    if (params.length == 3) {
//                        FileCommond.write(now, params[1], params[2]);
//                    } else {
//                        FileCommond.write(now, params[1]);
//                    }
//                    break;
//                case OperateConst.PWD:
//                    FileCommond.pwd(now);
//                    break;
//                case OperateConst.RM:
//                    FileCommond.rm(now, params[1], 1);
//                    break;
//                case OperateConst.RMDIR:
//                    FileCommond.rm(now, params[1], 0);
//                    break;
//                case OperateConst.EXIT:
//                    FIleOperate.write(FileCommond.ROOT);
//                    break label;
//                case "/?":
//                    System.out.println("cd path 切换到指定路径");
//                    System.out.println("ls 查看当前目录内容");
//                    System.out.println("mkdir name 在当前目录创建文件夹");
//                    System.out.println("read name 读取当前目录下的某个文件");
//                    System.out.println("write name [text] 在当前目录下创建文件，" +
//                            "如果text不为空，将text写入文件中");
//                    System.out.println("pwd 显示当前路径");
//                    System.out.println("rm name 删除当前路径下的某个文件");
//                    System.out.println("rmdir name 删除当前路径下的某个文件夹");
//                    System.out.println("exit 退出");
//                    break;
//            }
//        }
//    }
//}
//
