
package appbackend.back.controller;


import appbackend.back.model.UserModel;
import appbackend.back.service.UserService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "/vdt")
public class UserController {

    private final UserService userService;
    private final Counter createUserCounter;
    private final Counter getAllUserCounter;
    private final Counter updateUserCounter;
    private final Counter deleteUserCounter;
    private final Timer createUserTimer;
    private final Timer getAllUserTimer;
    private final Timer updateUserTimer;
    private final Timer deleteUserTimer;
    private final Bucket bucket;

    @Autowired
    public UserController(UserService userService, MeterRegistry meterRegistry) {
        this.userService = userService;
        this.createUserCounter = meterRegistry.counter("user.create.counter");
        this.getAllUserCounter = meterRegistry.counter("user.get_all.counter");
        this.updateUserCounter = meterRegistry.counter("user.update.counter");
        this.deleteUserCounter = meterRegistry.counter("user.delete.counter");
        this.createUserTimer = meterRegistry.timer("user.create.timer");
        this.getAllUserTimer = meterRegistry.timer("user.get_all.timer");
        this.updateUserTimer = meterRegistry.timer("user.update.timer");
        this.deleteUserTimer = meterRegistry.timer("user.delete.timer");
        Bandwidth limit = Bandwidth.classic(10, Refill.greedy(10, Duration.ofSeconds(60)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }



    @CrossOrigin(origins = "http://52.221.204.55:5500")
    @GetMapping("/")
    public ResponseEntity<String> hello() {
        if (bucket.tryConsume(1)) {
            return ResponseEntity.ok("API call successful");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Rate limit exceeded");
        }
    }

    @CrossOrigin(origins = "http://52.221.204.55:5500")
    @GetMapping("/all")
    public ResponseEntity<Object> getAllUser() {
        if (bucket.tryConsume(1)) {
             getAllUserCounter.increment();
            return ResponseEntity.status(HttpStatus.OK).body(getAllUserTimer.record(userService::getAllUser));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Rate limit exceeded");
        }
    }

    @CrossOrigin(origins = "http://52.221.204.55:5500")
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
        if (bucket.tryConsume(1)) {
            createUserCounter.increment();
            return createUserTimer.record(() -> userService.addUser(userModel));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Rate limit exceeded");
        }
    }

    @CrossOrigin(origins = "http://52.221.204.55:5500")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "id") int id, @RequestBody UserModel userModel) {
        if (bucket.tryConsume(1)) {
            updateUserCounter.increment();
            return  updateUserTimer.record(() -> userService.updateUser(id, userModel));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Rate limit exceeded");
        }
    }


    @CrossOrigin(origins = "http://52.221.204.55:5500")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") int id) {
        if (bucket.tryConsume(1)) {
            deleteUserCounter.increment();
            return  deleteUserTimer.record(() -> userService.deleteUser(id));
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Rate limit exceeded");
        }
    }
}