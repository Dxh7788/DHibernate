import com.work.hn.model.User;
import com.work.hn.util.ParseHqlHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
    /**
     * Hibernate 解析HQL
     * */
    @Test
    public void testNoneParseHql(){
        String hql = "from User u";
        String sql = ParseHqlHelper.noneParamsParser(hql);
        System.out.println(sql);
    }

    @Test
    public void testHolderParserHql(){
        String hql = "from User u where  u.namer.id=?";
        List params = new ArrayList<String>();
        params.add("1");
        String sql = ParseHqlHelper.holderParamsParser(hql,params);
        System.out.println(sql);
    }
    /**
     * test merge
     * */
    @Test
    public void testMerge(){
        Configuration configuration = new Configuration().configure();
        System.out.println("================Hibernate session配置完成,开始操作数据库============");
        System.out.println("================开始创建ServiceRegistry================");
        /**
         * ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
         */
        System.out.println("================开始创建session================");
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        User user0 = new User("1","xxx","123456");
        User user1 = new User("1","xxd","123456");
        /**
         * 制造merge预发条件,ID冲突
         * 会报一个a different object with the same identifier value was already associated with the session: [com.work.hn.model.User#1]异常
         * */
        session.save(user0);
//        session.save(user1);
        /**
         * 进行merge,对user1进行merge
         * */
        User oldUser = (User)session.merge(user1);
        System.out.println(oldUser);
        tx.commit();
        session.close();
    }
}
