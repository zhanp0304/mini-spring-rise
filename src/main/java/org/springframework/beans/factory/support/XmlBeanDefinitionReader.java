package org.springframework.beans.factory.support;

import cn.hutool.core.util.XmlUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.PropertyValues;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;

/**
 * XmlBeanDefinitionReader
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/7
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        super(beanDefinitionRegistry);
    }

    @Override
    public void loadBeanDefinition(Resource resource) {
        Assert.notNull(resource, "resource cannot be null");
        try (InputStream is = resource.getInputStream()) {
            doLoadBeanDefinitions(is);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("the file not found", e);
        } catch (IOException ioException) {
            throw new BeansException("IOException parsing XML document from " + resource, ioException);
        }

    }

    protected void doLoadBeanDefinitions(InputStream is) {
        Document document = XmlUtil.readXML(is);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            if (!(node instanceof Element)) {
                continue;
            }
            processBeanTag(node);
        }
    }

    private void processBeanTag(Node node) {
        if (BEAN_ELEMENT.equals(node.getNodeName())) {
            String beanId = ((Element) node).getAttribute(ID_ATTRIBUTE);
            if (beanId.isEmpty()) {
                throw new BeansException(MessageFormat.format("beanId cannot be empty in xml, nodeName[{0}]", node.getNodeName()));
            }

            String beanName = ((Element) node).getAttribute(NAME_ATTRIBUTE);
            String beanClass = ((Element) node).getAttribute(CLASS_ATTRIBUTE);
            beanName = beanName.isEmpty() ? beanId : beanName;

            try {
                BeanDefinition beanDefinition = new BeanDefinition(Class.forName(beanClass));
                NodeList children = node.getChildNodes();
                processBeanProperty(children, beanDefinition);

                // register beanDefinition
                getRegistry().registerBeanDefinition(beanName, beanDefinition);
            } catch (ClassNotFoundException e) {
                throw new BeansException(MessageFormat.format("clazz[{0}] not found", beanClass));
            }
        }
    }

    private void processBeanProperty(NodeList children, BeanDefinition beanDefinition) {
        for (int j = 0; j < children.getLength(); j++) {
            Node child = children.item(j);
            if (!(child instanceof Element)) {
                continue;
            }
            if (PROPERTY_ELEMENT.equals(child.getNodeName())) {
                String propertyName = ((Element) child).getAttribute(NAME_ATTRIBUTE);
                String propertyValue = ((Element) child).getAttribute(VALUE_ATTRIBUTE);
                String ref = ((Element) child).getAttribute(REF_ATTRIBUTE);

                if (ref.isEmpty()) {
                    beanDefinition.addPropertyValue(new PropertyValues.PropertyValue(propertyName, propertyValue));
                } else {
                    beanDefinition.addPropertyValue(new PropertyValues.PropertyValue(propertyName, (BeanReference) () -> propertyName));
                }
            }
        }
    }
}
