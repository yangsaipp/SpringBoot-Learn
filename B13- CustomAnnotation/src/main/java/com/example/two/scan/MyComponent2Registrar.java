package com.example.two.scan;

import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import com.example.two.MyComponent2;

/**
 * 参考：org.springframework.cloud.netflix.ribbon.RibbonClientConfigurationRegistrar
 * 自己构建ClassPathBeanDefinitionScanner来扫描指定包路径下的类，并将符合条件（包含有注解MyComponent2）的类定义加入容器中
 * @author yangsai
 *
 */
public class MyComponent2Registrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		Map<String, Object> attrs = importingClassMetadata.getAnnotationAttributes(
				MyScan.class.getName(), true);
		// 
		ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
		scanner.addIncludeFilter(new AnnotationTypeFilter(MyComponent2.class));
		int size = scanner.scan((String[])attrs.get("basePackages"));
		System.out.println("======== MyComponent2Registrar :"  + size);
	}

}
