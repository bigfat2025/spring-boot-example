package per.destiny.service;

import org.springframework.stereotype.Service;
import per.destiny.entity.User;

@Service
public class UserService {

    public User authenticate(String username, String password) {
        if ("admin".equals(username) && "asdasd".equals(password)) {
            return new User(1L, "admin", "asdasd");
        }
        return null;
    }
}
