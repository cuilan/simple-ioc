import cn.cuilan.AnnotationConfigApplicationContext;
import cn.cuilan.dao.IndexDao;
import cn.cuilan.service.IndexService;
import org.junit.Test;

import cn.cuilan.beans.factory.BeanFactory;
import cn.cuilan.ClassPathXmlApplicationContext;

public class ITTest {

    @Test
    public void testXml() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("/applicationContext.xml");
        IndexService indexService = (IndexService) beanFactory.getBean("indexService");
        indexService.index();
    }

    @Test
    public void testAnnotation() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("cn.cuilan");
        IndexDao indexDao = (IndexDao) context.getBean("indexDao");
        indexDao.index();
    }

}
