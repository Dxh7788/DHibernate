import com.work.hn.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HibernateTest {

    @Test
    public void test0(){
        Configuration configuration = new Configuration().configure();
        System.out.println("================Hibernate session配置完成,开始操作数据库============");
        System.out.println("================开始创建ServiceRegistry================");
        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        System.out.println("================开始创建session================");
        SessionFactory sf = configuration.buildSessionFactory(sr);
        Session session = sf.openSession();

        User user = new User();
        user.setId("1");
        user.setName("123");
        user.setPwd("123456");
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }
}
