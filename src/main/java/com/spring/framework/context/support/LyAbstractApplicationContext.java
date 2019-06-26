package com.spring.framework.context.support;

/**
 * @author liuyan
 * @date 2019/6/18 16:11
 */
public abstract class LyAbstractApplicationContext {



    //受保护的只提供给子类重写
    protected void refresh(){

    }




    protected abstract void refreshBeanFactory();
}
