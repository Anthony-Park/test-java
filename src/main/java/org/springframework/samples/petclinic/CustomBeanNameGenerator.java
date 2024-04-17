package org.springframework.samples.petclinic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CustomBeanNameGenerator implements BeanNameGenerator {

	private static final Logger log = LoggerFactory.getLogger(CustomBeanNameGenerator.class);

	//public CustomBeanNameGenerator() {}

	@Override
	public String generateBeanName(BeanDefinition beanDefinition, BeanDefinitionRegistry beanDefinitionRegistry) {
		final String result;
		result = generateFullBeanName((AnnotatedBeanDefinition) beanDefinition);

		if (isRepository(beanDefinition))
			log.warn("-------------------------");

		log.warn(">>> " + result);
		return result;
	}

	private String generateFullBeanName(final AnnotatedBeanDefinition definition) {
		return definition.getMetadata().getClassName();
	}

	private Set<String> getAnnotationTypes(final BeanDefinition definition) {
		final AnnotatedBeanDefinition annotatedDef = (AnnotatedBeanDefinition) definition;
		final AnnotationMetadata metadata = annotatedDef.getMetadata();
		return metadata.getAnnotationTypes();
	}

	private boolean isRepository(final BeanDefinition definition) {
		if (definition instanceof AnnotatedBeanDefinition) {
			// definition에 속한 모든 Annotation을 가져옴.
			final Set<String> annotationTypes = getAnnotationTypes(definition);

			// annotation 중 @Controller이거나 @RestController 일 경우 Controller로 인식
			for (final String annotationType : annotationTypes) {
//				if (annotationType.equals(Controller.class.getName())) {
//					return true;
//				}
//				if (annotationType.equals(RestController.class.getName())) {
//					return true;
//				}
				if (annotationType.equals(Repository.class.getName())) {
					return true;
				}
			} // for
		} // if

		return false;
	}
}
/*
public class CustomBeanNameGenerator implements BeanNameGenerator {
	// basePackages 외에 scaning된 beanGenerator
	private static final BeanNameGenerator DELEGATE = new AnnotationBeanNameGenerator();

	// VersioningBeanNameGenerator 대상 package 경로
	private List<String> basePackages = new ArrayList<>();

	@Override public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
		if(isTargetPackageBean(definition)) {
			return getBeanName(definition);
		}

		return DELEGATE.generateBeanName(definition, registry);
	}

	private boolean isTargetPackageBean(BeanDefinition definition) {
		String beanClassName = getBeanName(definition);
		return basePackages.stream().anyMatch(beanClassName::startsWith);
	}

	private String getBeanName(BeanDefinition definition) {
		return definition.getBeanClassName();
	}

	public boolean addBasePackages(String path) {
		return this.basePackages.add(path);
	}
}
*/
/*
public class CustomBeanNameGenerator implements BeanNameGenerator {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomBeanNameGenerator.class);

	// 이 클래스는 Spring이 기본적으로 사용하는 BeanNameGenerator. 컨트롤러가 아닐경우 이 클래스를 사용하려고 선언
	private final AnnotationBeanNameGenerator defaultGenerator = new AnnotationBeanNameGenerator();

	@Override
	public String generateBeanName(final BeanDefinition definition, final BeanDefinitionRegistry registry) {

		final String result;

		// definition이 컨트롤러일 경우 패키지 이름을 포함한 Bean 이름을, 아닐경우 Spring 기본형식을 따름
		if (isRepository(definition)) {
			result = generateFullBeanName((AnnotatedBeanDefinition) definition);
		} else {
			result = this.defaultGenerator.generateBeanName(definition, registry);
		}

		LOGGER.debug("Registered Bean Name : " + result);

		return result;
	}

	private String generateFullBeanName(final AnnotatedBeanDefinition definition) {
		// 패키지를 포함한 전체 이름을 반환
		return definition.getMetadata().getClassName();
	}

	private Set<String> getAnnotationTypes(final BeanDefinition definition) {
		final AnnotatedBeanDefinition annotatedDef = (AnnotatedBeanDefinition) definition;
		final AnnotationMetadata metadata = annotatedDef.getMetadata();
		return metadata.getAnnotationTypes();
	}

	// Controller인지 판별하는 메서드
	private boolean isRepository(final BeanDefinition definition) {
		if (definition instanceof AnnotatedBeanDefinition) {
			// definition에 속한 모든 Annotation을 가져옴.
			final Set<String> annotationTypes = getAnnotationTypes(definition);

			// annotation 중 @Controller이거나 @RestController 일 경우 Controller로 인식
			for (final String annotationType : annotationTypes) {
//				if (annotationType.equals(Controller.class.getName())) {
//					return true;
//				}
//				if (annotationType.equals(RestController.class.getName())) {
//					return true;
//				}
				if (annotationType.equals(Repository.class.getName())) {
					return true;
				}
			} // for
		} // if

		return false;
	}
}
*/
