package com.spring.framework.beans.config;

import lombok.Data;

/**
 * @author liuyan
 * @date 2019/6/18 16:22
 */
@Data
public class LyBeanDefinition {

    private String beanClassName;
    private boolean lazyInit=false;
    private String factoryBeanName;

}
