package kr.seoin.springboot.myrestfulservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class MyRestfulServiceApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(MyRestfulServiceApplication.class, args);

//		for(String beanName : ac.getBeanDefinitionNames()){
//			System.out.println(beanName);
//		}
	}

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

}
