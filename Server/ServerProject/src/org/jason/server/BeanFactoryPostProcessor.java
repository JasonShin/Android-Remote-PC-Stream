package org.jason.server;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class BeanFactoryPostProcessor implements org.springframework.beans.factory.config.BeanFactoryPostProcessor{

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFacotry)
			throws BeansException {
		// TODO Auto-generated method stub
		System.out.println("Bean factory is getting executed");
	}

}
