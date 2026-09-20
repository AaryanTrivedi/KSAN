package in.ksan.controller;

import in.ksan.models.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity userEntity) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<UserEntity> login(@RequestBody UserEntity userEntity) {
        return null;
    }

    @GetMapping("/details")
    public ResponseEntity<UserEntity> details() {
        return null;
    }

    @PatchMapping("/update")
    public ResponseEntity<UserEntity> update(@RequestBody UserEntity userEntity) {
        return null;
    }

    @PatchMapping("/change-password")
    public ResponseEntity<UserEntity> changePassword(@RequestBody UserEntity userEntity) {
        return null;
    }
}
