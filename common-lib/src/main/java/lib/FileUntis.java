package lib;

import java.io.File;

/**
 * @ClassName FileUntis
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/2
 * @Version V1.0
 **/
public class FileUntis {


    /**
     * 创建父级文件夹
     *
     * @param file
     *            完整路径文件名(注:不是文件夹)
     */
    public static void createParentPath(File file) {
        File parentFile = file.getParentFile();
        if (null != parentFile && !parentFile.exists()) {
            parentFile.mkdirs(); // 创建文件夹
            createParentPath(parentFile); // 递归创建父级目录
        }
    }
}
