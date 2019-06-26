package com.spring.framework.context.support;

import com.spring.framework.beans.config.LyBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author liuyan
 * @date 2019/6/24 17:31
 */
public class LyBeanDefinitionReader {

    private Properties config=new Properties();

    private List<String> registryBeanClasses=new ArrayList<>();


    private final String SCAN_PACKAGE = "scanPackage";
    public LyBeanDefinitionReader(String... locations) {
        //在Spring中是通过Reader去查找和定位对不对
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:",""));

        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(null != is){is.close();}
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.","/"));

        File classDir = new File(url.getFile());

        for (File file : classDir.listFiles()){
            if(file.isDirectory()){
                doScanner(packageName + "." +file.getName());
            }else {
                registryBeanClasses.add(packageName + "." + file.getName().replace(".class",""));
            }
        }
    }

    private String lowerFirstCase(String str){
        char [] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public List<String> loadBeanDefinitions() {
        return registryBeanClasses;
    }

    public LyBeanDefinition registerBean(String className) {
        if(this.registryBeanClasses.contains(className)){
            LyBeanDefinition beanDefinition = new LyBeanDefinition();
            beanDefinition.setBeanClassName(className);
            beanDefinition.setFactoryBeanName(lowerFirstCase(className.substring(className.lastIndexOf(".") + 1)));
            return beanDefinition;
        }
        return null;
    }
}
