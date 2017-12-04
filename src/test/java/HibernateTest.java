import com.work.hn.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateTest {

    @Test
    public void test0(){
        Configuration configuration = new Configuration().configure();
        System.out.println("================Hibernate session配置完成,开始操作数据库============");
        System.out.println("================开始创建ServiceRegistry================");
        /**
         * ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
         */
        System.out.println("================开始创建session================");
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();

        //generate entity User
        User user = new User("1","xxx","123456");
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
    }
}
