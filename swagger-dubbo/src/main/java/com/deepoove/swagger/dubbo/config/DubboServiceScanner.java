package com.deepoove.swagger.dubbo.config;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.spring.ServiceBean;

import io.swagger.config.Scanner;

@Component
public class DubboServiceScanner implements Scanner {

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<Class<?>> classes() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		Map<?, ServiceBean> serviceBeans = ServiceBean.getSpringContext()
				.getBeansOfType(ServiceBean.class);
		for (ServiceBean<?> bean : serviceBeans.values()) {
			classes.add(bean.getRef().getClass());
		}

		for(ServiceConfig<?> providers: getAnnotatonProviders()){
			classes.add(providers.getRef().getClass());
		}
		return classes;
	}

	public Set<ServiceConfig<?>>  getAnnotatonProviders(){

		try {
		AnnotationBean annotationBean = (AnnotationBean) ServiceBean.getSpringContext().getBean(AnnotationBean.class.getName());
			Field field = AnnotationBean.class.getDeclaredField("serviceConfigs");
			field .setAccessible(true);
			Set<ServiceConfig<?>> serviceConfigs = (Set<ServiceConfig<?>>) field .get(annotationBean);

			return serviceConfigs;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
		return new HashSet<ServiceConfig<?>>(0);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<Class<?>, Object> interfaceMapRef() {
		Map<Class<?>, Object> interfaceMapRef = new HashMap<Class<?>, Object>();

		for(ServiceConfig serviceConfig:getAnnotatonProviders()){
			Class<?> interfaceClass = serviceConfig.getInterfaceClass();
			interfaceMapRef.put(interfaceClass, serviceConfig.getRef());
		}

		Map<?, ServiceBean> serviceBeans = ServiceBean.getSpringContext()
				.getBeansOfType(ServiceBean.class);
		for (ServiceConfig<?> bean : serviceBeans.values()) {
			Class<?> interfaceClass = bean.getInterfaceClass();
			interfaceMapRef.put(interfaceClass, bean.getRef());
		}

		return interfaceMapRef;
	}

	@Override
	public boolean getPrettyPrint() {
		return false;
	}

	@Override
	public void setPrettyPrint(boolean shouldPrettyPrint) {
	}

}
