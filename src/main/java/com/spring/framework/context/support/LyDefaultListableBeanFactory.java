package com.spring.framework.context.support;

import com.spring.framework.beans.config.LyBeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liuyan
 * @date 2019/6/18 16:17
 */
public class LyDefaultListableBeanFactory extends LyAbstractApplicationContext {
    protected Map<String,LyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String,LyBeanDefinition>();

    protected void onRefresh(){

    }

    @Override
    protected void refreshBeanFactory() {

    }

}
