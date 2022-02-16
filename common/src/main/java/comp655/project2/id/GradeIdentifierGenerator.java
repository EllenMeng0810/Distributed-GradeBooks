package comp655.project2.id;

import comp655.project2.entities.GradeBook;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.enhanced.TableGenerator;

import java.io.Serializable;

public class GradeIdentifierGenerator extends TableGenerator {
    @Override
    public Serializable generate(SessionImplementor sessionImplementor, Object o) throws HibernateException {

        if (null == o) {
            return super.generate(sessionImplementor, o);
        }
        if (o instanceof GradeBook) {
            try {
                GradeBook source = (GradeBook) o;
                if (source.getId() != 0) {
                    return source.getId();
                }
            } catch (Exception e) {

            }
        }
        return super.generate(sessionImplementor, o);
    }
}
