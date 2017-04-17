/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package het.springapp.service.impl;


import het.springapp.dao.UserDao;
import het.springapp.model.User;
import het.springapp.security.UserDetailsAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author heather
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;
    
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userDao.getUser(userId);
        return buildUserForAuthentication(user);
    }
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    private UserDetailsAdapter buildUserForAuthentication(User user) {
        String userId = user.getUserId();
        String password = user.getPassword();
        user = userDao.login(userId, password);
        return new UserDetailsAdapter(user);
    }
}
