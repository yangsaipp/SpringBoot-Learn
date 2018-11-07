package RegiestBeanByImport;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import RegiestBeanByImport.model.CountryMapper;
import RegiestBeanByImport.model.MapperAutoConfig;

/**
 * 使用注解的方式申明要动态注入的bean的信息
 * @author  杨赛
 * @since   jdk1.7
 * @version 2018年11月6日 杨赛
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {MapperAutoConfig.class})
public class AnApplicationTests {

    @Autowired
    CountryMapper countryMapper;

    @Test
    public void contextLoads() {
        System.out.println("====================" + countryMapper);
        Assert.assertNotNull(countryMapper);
    }
}