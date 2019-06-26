package com.spring.framework.context;

import com.spring.framework.beans.LyBeanFactory;
import com.spring.framework.beans.config.LyBeanDefinition;
import com.spring.framework.context.support.LyBeanDefinitionReader;
import com.spring.framework.context.support.LyDefaultListableBeanFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuyan
 * @date 2019/6/18 16:09
 */
public class LyApplicationContext extends LyDefaultListableBeanFactory implements LyBeanFactory {
    //beanDefinitionMap用来保存配置信息
    protected Map<String,LyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String,LyBeanDefinition>();


    private String [] configLocations;

    private LyBeanDefinitionReader reader;

    public LyApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    protected void refresh() {

        this.reader = new LyBeanDefinitionReader(configLocations);
        List<String> beanDefinitions = reader.loadBeanDefinitions();

        doRegistry(beanDefinitions);
        doAutowrited();
    }

    private void doAutowrited() {

    }

    private void doRegistry(List<String> beanDefinitions) {

        try {
            for (String className : beanDefinitions) {
                Class<?> aClass = Class.forName(className);
                if(aClass.isInterface()){
                    continue;
                }
                LyBeanDefinition beanDefinition = reader.registerBean(className);
                if(beanDefinition != null){
                    this.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
                }

                Class<?>[] interfaces = aClass.getInterfaces();
                for (Class<?> i: interfaces) {
                    //如果是多个实现类，只能覆盖
                    //为什么？因为Spring没那么智能，就是这么傻
                    //这个时候，可以自定义名字
                    this.beanDefinitionMap.put(i.getName(),beanDefinition);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
