package RegiestBeanByImport.model;

import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class MapperAutoConfiguredMyBatisRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		Map<String, Object> attrs = importingClassMetadata.getAnnotationAttributes(Mapper.class.getName(), true);
		if (attrs == null) {
			return;
		}
		String name = (String) attrs.get("name");
		String type = (String) attrs.get("type");
		Class<?> cType;
		try {
			cType = Class.forName(type);
			System.out.println("====================" + name + ":" + type);
			BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(cType);
			// builder.addConstructorArgValue(name);
			// builder.addConstructorArgValue(configuration);
			registry.registerBeanDefinition(name, builder.getBeanDefinition());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}