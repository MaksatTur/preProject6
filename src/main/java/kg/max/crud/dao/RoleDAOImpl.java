package kg.max.crud.dao;

import kg.max.crud.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<String> getAllRolesName() {
        TypedQuery<String> query = sessionFactory.getCurrentSession().createQuery("select name from Role");
        return query.getResultList();
    }
}
