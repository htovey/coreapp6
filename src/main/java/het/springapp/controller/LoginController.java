package het.springapp.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import het.springapp.service.LoginService;
import het.springapp.service.impl.LoginServiceImpl;
import het.springapp.model.Person;
import het.springapp.model.User;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

public class LoginController {
	
	@Autowired
        LoginService loginService;
	@RequestMapping(value="/login", method=RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public @ResponseBody String authenticate(@RequestBody User user) {
            String result = "error";
            try {
                User loggedInUser = loginService.login(user.getUserId(), user.getPassword());
                if (loggedInUser != null) {
                        result = "success";
                }
            } catch (Exception ex) {
                    ex.printStackTrace();
            }
            
            return result;
	}
}