package org.springframework.beans.factory.support;

/**
 * Bean引用
 * <p>当一个类中需要含有Bean成员引用时，在BeanDefinition记录propertyValues属性时，
 * 会对这个成员用BeanReference进行转换，使得在Bean实例化后的初始化populateBean过程中，通过instanceof识别出位Bean引用，则需要递归去getOrCreate引用成员</p>
 * <p>Note: 对Bean成员进行BeanReference转换的目的也很清晰，其实是因为在记录BeanDefinition#propertyValues时，根本就还没有创建出成员对象，那怎么让他记录到这个引用，并且标注是个BeanReference呢？
 * Spring给出的答案就是：通过定义函数式接口BeanReference, 然后让其返回成员的beanName，也就是说我们会记录下这个beanName，并且类型是BeanReference。那么之后在populateBean阶段，就可以轻松根据
 * BeanReference#getBeanName拿到bean，从而在beanDefinitionMap，以及singletonObjects容器中获取到创建这个成员bean的信息，或者直接从容器里获取，然后进行populate</p>
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/6
 */
public interface BeanReference {
    /**
     * 获取被代理的Bean Name
     *
     * @return beanName
     */
    String getBeanName();
}
