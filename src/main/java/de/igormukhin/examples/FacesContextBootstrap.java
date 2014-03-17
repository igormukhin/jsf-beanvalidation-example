package de.igormukhin.examples;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.validator.BeanValidator;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;

@WebListener
public class FacesContextBootstrap implements ServletContextListener {
	public static final String BEANS_VALIDATION_AVAILABILITY_CACHE_KEY = "javax.faces.BEANS_VALIDATION_AVAILABLE";

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// http://stackoverflow.com/questions/19637996/bean-validation-doesnt-work-with-mojarra-2-2-4
		Map<String, Object> applicationMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
		applicationMap.remove(BEANS_VALIDATION_AVAILABILITY_CACHE_KEY);
		// applicationMap.put(BeanValidator.VALIDATOR_FACTORY_KEY, Validation.buildDefaultValidatorFactory());

		// http://docs.jboss.org/hibernate/validator/5.1/reference/en-US/html_single/
		// Example 4.4. Using a specific resource bundle
		ResourceBundleMessageInterpolator rbmi = new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("de.igormukhin.messages.beanvalidation"));
		ValidatorFactory validatorFactory = Validation
				.byDefaultProvider()
				.configure()
				.messageInterpolator(rbmi)
				.buildValidatorFactory();

		applicationMap.put(BeanValidator.VALIDATOR_FACTORY_KEY, validatorFactory);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}
}