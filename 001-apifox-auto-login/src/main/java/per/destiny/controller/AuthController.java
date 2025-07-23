package per.destiny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import per.destiny.dto.R;
import per.destiny.entity.User;
import per.destiny.service.UserService;
import per.destiny.util.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/auth/login1")
    public ResponseEntity<R<?>> login1(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return login2(user);
    }

    @PostMapping("/auth/login2")
    public ResponseEntity<R<?>> login2(@RequestBody User user) {
        User authenticatedUser = userService.authenticate(user.getUsername(), user.getPassword());
        if (authenticatedUser != null) {
            Map<String, Object> map = jwtUtil.generateToken(authenticatedUser.getUsername());
            return ResponseEntity.ok().header("Authorization", "Bearer " + map.get("token")).body(R.ok(map));
        } else {
            return ResponseEntity.status(401).body(R.fail(-1, "Invalid credentials"));
        }
    }


    @PostMapping("/info")
    public R<?> info(@RequestHeader("Authorization") String token) {
        String username = jwtUtil.getUsernameFromToken(token.replace("Bearer ", ""));
        User user = new User();
        user.setUsername(username);
        return R.ok(user);
    }
}
