package het.springapp.dao.impl;

import het.springapp.model.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Repository;
import het.springapp.dao.UserDao;
import org.hibernate.Query;
import org.hibernate.Session;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired(required = true)
    SessionFactory sessionFactory;
    
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    public User login(String userId, String password) {
     
        String hashedPassword = passwordEncoder.encode(password);
        
        Session session = sessionFactory.getCurrentSession();
        Query query = session.getNamedQuery("User.authenticate");
        query.setParameter("USER_ID", userId);
        query.setParameter("PASSWORD", password);
        
        User user = (User) query.uniqueResult();
        
        return user;
    }
    
    public User getUser(String userId) {
        return (User) sessionFactory.getCurrentSession().get(User.class, userId);
    }
}
