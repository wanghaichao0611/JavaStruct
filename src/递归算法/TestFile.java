package 递归算法;


import java.io.File;

public class TestFile {
    private static String level = "";

    private static final String pathName = "e:/test";

    public static void main(String[] args) {
        showAllFiles(new File(pathName).listFiles());
        showAllDirectory(pathName);
    }

    //非排序优化打印
    public static void showAllFiles(File[] files) {
        //列举出所有子目录和文件
        for (int i = 0; files != null && i < files.length; i++) {
            File file = files[i];
            //直接优化递归打印
            System.out.println(file.getName());
            if (file.isDirectory()) {
                showAllFiles(file.listFiles());
            }
        }
    }

    //排序优化打印
    public static void showAllDirectory(String path) {
        File dir = new File(path);
        File[] dirs = dir.listFiles();// 列出目录的所有子目录和文件
        for (int i = 0; dirs != null && i < dirs.length; i++) {
            File f = dirs[i];
            if (f.isFile()) {//如果是文件打印出文件名
                System.out.println(level + f.getName());
            } else if (f.isDirectory()) {//如果是目录递归调用直到所有目录
                //递归完成
                System.out.println(level + f.getName());
                level += "----";
                showAllDirectory(f.getAbsolutePath());
                level = level.substring(0, level.lastIndexOf("----"));

            }
        }
    }
}
