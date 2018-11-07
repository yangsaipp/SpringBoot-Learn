package RegiestBeanByImport2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 扫描指定目录下的类，将使用所有使用Mapper注解的类注册到Spring中
 * @author  杨赛
 * @since   jdk1.7
 * @version 2018年11月6日 杨赛
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MapperAutoConfig.class})
public class DemoApplicationTests {

    @Autowired
    CountryMapper countryMapper;

    @Test
    public void contextLoads() {
        System.out.println(countryMapper);
        System.out.println(countryMapper.getClass().getAnnotations()[0].annotationType());
        Assert.assertNotNull(countryMapper);
    }
}