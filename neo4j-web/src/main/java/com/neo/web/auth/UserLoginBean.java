package com.neo.web.auth;

import com.neo.domain.User;
import com.neo.services.UserService;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import javax.faces.bean.SessionScoped;

/**
 *
 * @author
 */
@ManagedBean(name = "userLoginBean")
@SessionScoped
public class UserLoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{userService}")
    private UserService userService = null;

    public UserLoginBean() {
    }

    public String doCall() {
        try {
            User user1 = new User("Carla", "Red");
            User user2 = new User("Nunka", "Clear");
            User retrievedSallyUser = userService.saveUser(user1, user2);
            System.out.println("@@@@get user!!!!! " + retrievedSallyUser.getName() + " " + retrievedSallyUser.getUserId() + " " + retrievedSallyUser.getNodeId());
            Long count = userService.getUserCount();
            System.out.println("@First count is " + count);
            List<User> userList = userService.getUserList();
            for (User item : userList) {
                System.out.println("Item from User List " + item.getName() + " " + item.getUserId() + " " + item.getNodeId());
            }
            user1 = new User("Armen", "Like");
            user1 = userService.saveTemplate(user1);
            System.out.println("Ret user: " + user1.getName() + " " + user1.getUserId() + " " + user1.getNodeId());
            List<User> neoUserList = userService.getNeoUserList();
            for (User item : neoUserList) {
                System.out.println("neo Items " + item.getName() + " " + item.getUserId() + " " + item.getNodeId());
            }
            count = userService.getUserCount();
            System.out.println("Final count is " + count);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
