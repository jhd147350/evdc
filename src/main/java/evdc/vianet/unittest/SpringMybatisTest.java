package evdc.vianet.unittest;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import evdc.vianet.auth.entity.User;
import evdc.vianet.auth.mapper.UserMapper;

public class SpringMybatisTest{
	public UserMapper mapper;
	protected ApplicationContext aplcation = null; //注意引包
	@Test
	public void main() {
		aplcation = new ClassPathXmlApplicationContext("spring-mybatis.xml","mybatis.xml");//初始化上下文；
		mapper = (UserMapper) aplcation.getBean("userMapper");
		List<User> users = mapper.findAllUsers();
		System.out.println("user size is "+users.size());
	}
}
