import com.work.hn.model.Klo;
import com.work.hn.model.Mjoer;
import com.work.hn.model.Question;
import com.work.hn.model.User;
import com.work.hn.util.ParseHqlHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.*;

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

    /**
     * test map
     * */
    @Test
    public void testHibernateMap(){
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

        /** Add:=======================================
         * Map<String,String> map0 = new HashMap<String,String>();
        Map<String,String> map1 = new HashMap<String,String>();
        map0.put("xxxxxxxxxxxxxxxxxxxxxxx_1","ddx");
        map1.put("XXXXXXXXXXXXXXXXXXXXXXX_1","ddd");
        Mjoer mjoer0 = new Mjoer("ddx","123456",map0);
        Mjoer mjoer1 = new Mjoer("ddd","654321",map1);
        mjoer0.setId("1");
        mjoer1.setId("2");
        session.save(mjoer0);
        session.save(mjoer1);
         */


        /**
         * Query
         * */
        Mjoer mjoer2 = (Mjoer) session.get(Mjoer.class,"1");
        tx.commit();
        Set<Map.Entry<String,String>> entries = mjoer2.getAnswers().entrySet();
        Iterator iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String,String> ele = (Map.Entry<String,String>)iterator.next();
            System.out.println("[key: "+ele.getKey()+",  value:"+ele.getValue()+"]");
        }
        session.close();
    }

    @Test
    public void testManyToManyWithMap(){
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


        Map<String,Klo> map0 = new HashMap<String,Klo>();
        Map<String,Klo> map1 = new HashMap<String,Klo>();
        Klo klo0 =new Klo("1","12","@163");
        Klo klo1 =new Klo("2","22","@136");

        map0.put("1",klo0);
        map0.put("2",klo1);
        map1.put("2",klo1);
        map1.put("1",klo0);

        Question question0 = new Question("1","12",map0);
        Question question1 = new Question("2","22",map1);

        session.save(question0);
        session.save(question1);

        tx.commit();
        session.close();
    }
}
