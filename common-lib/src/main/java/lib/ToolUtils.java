package lib;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
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


    @Deprecated
    public static Class<?> getClass(String jar ,String className) throws Exception {
        URL url = new URL(jar);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
        return urlClassLoader.loadClass(className);
    }

    public static Class<?> getClass(File jarFile ,String className) throws Exception {
        URL uri = jarFile.toURI().toURL();
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{uri}, Thread.currentThread().getContextClassLoader());
        return urlClassLoader.loadClass(className);
    }




}
