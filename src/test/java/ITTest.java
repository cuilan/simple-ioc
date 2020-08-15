import cn.cuilan.service.IndexService;
import org.junit.Test;

import cn.cuilan.beans.factory.BeanFactory;
import cn.cuilan.ClassPathXmlApplicationContext;

public class ITTest {

    @Test
    public void test() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("/applicationContext.xml");
        IndexService indexService = (IndexService) beanFactory.getBean("indexService");
        indexService.index();
    }

}
