package evdc.vianet.test;

import java.io.FileNotFoundException;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Log4jConfigurer;

public class MySpringJUnit4ClassRunner4Log4j extends SpringJUnit4ClassRunner{
	static {  
        try {  
            Log4jConfigurer.initLogging("/WEB-INF/log4j.properties");  
          ///  Log4jConfigurer.initLogging("/WEB-INF/log4j.properties");
        } catch (FileNotFoundException ex) {  
            System.err.println("Cannot Initialize log4j");  
        }  
    } 

	public MySpringJUnit4ClassRunner4Log4j(Class<?> clazz) throws InitializationError {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

}
