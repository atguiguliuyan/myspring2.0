package com.spring.framework.beans;

public interface LyBeanFactory {
    /**
     * 根据beanName从ioc容器中获得一个bean
     * @param beanName
     * @return
     */
    Object getBean(String beanName);

}
