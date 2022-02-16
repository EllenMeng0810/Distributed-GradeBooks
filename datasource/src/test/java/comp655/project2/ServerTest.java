package comp655.project2;

import comp655.project2.entities.GradeBook;
import comp655.project2.repository.GradeBookRepository;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;
import java.util.List;


public class ServerTest {

    @Test
    public void list() throws Exception{
        /**
         *     Properties p = System.getProperties();
         *             p.setProperty("org.omg.CORBA.ORBInitialHost", "glassfish运行的IP地址");
         *             InitialContext ic =new InitialContext();
         *             HelloRemote sayhello = (HelloRemote) ic.lookup("java:global/hello/Hello");
         *             String result = sayhello.hi();
         *             System.out.println(result);
         */
        Hashtable<String, String> jndiProperties = new Hashtable<>();
        //=com.sun.enterprise.naming.impl.SerialInitContextFactory,
        //jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
        Context ic = new InitialContext(jndiProperties);
          /*  final String fullName = "ejb:/" + moduleName + "/JPA"
                    + clazz.getSimpleName() + "Impl" + "!" + clazz.getName();*/
        GradeBookRepository bookRepository = (GradeBookRepository) ic.lookup("java:global/gradbook-ear/datasource/GradeBookRepositoryImpl");
        List<GradeBook> list = bookRepository.listAll("primary");
        for (GradeBook gradeBook : list) {
            System.out.println(gradeBook);
        }
    }
}
