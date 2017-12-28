package evdc.vianet.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.SpringJtaSynchronizationAdapter;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(locations=
// {"file:src/main/webapp/WEB-INF/spring-web.xml","file:src/main/webapp/WEB-INF/spring-mybatis.xml"})
@ContextConfiguration(locations = { "classpath:spring-mybatis.xml", "classpath:spring-web.xml" })
@WebAppConfiguration
@Transactional
public class SpringBaseTest {

}
