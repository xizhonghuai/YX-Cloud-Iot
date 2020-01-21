package lib;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @ClassName ToolUtils
 * @Description: TODO
 * @Author xizhonghuai
 * @Date 2020/1/15
 * @Version V1.0
 **/
public class ToolUtils {

    public static Class<?> getClass(String jar ,String className) throws Exception {
        URL url = new URL("file:\\"+jar);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
        return urlClassLoader.loadClass(className);
    }



}
