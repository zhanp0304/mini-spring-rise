package org.springframework.beans.factory.support;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * Cglib实例化Bean策略，如果需要对Bean中的method进行重写的话，那么就必需要用到Cglib技术来生成Bean实例
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/6
 */
@Slf4j
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(String beanName, BeanDefinition beanDefinition) throws BeansException {
        log.info("using the CglibSubclassingInstantiationStrategy to create the bean instance");
        // TODO: 写个简单的例子，暂时不想深入研究CGLIB，大概知道有这么个东西就行，后续感兴趣，需要时再去了解

        Enhancer enhancer = new Enhancer();
        // set the original class
        enhancer.setSuperclass(beanDefinition.getClazz());
        // invokeSuper -> call original method on targetObj with args
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> proxy.invokeSuper(obj, args));
        // create the proxy class as the bean instance
        return enhancer.create();
    }
}
