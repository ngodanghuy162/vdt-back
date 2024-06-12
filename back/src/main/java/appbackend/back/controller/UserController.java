//package appbackend.back.controller;
//
//import appbackend.back.model.UserModel;
//import appbackend.back.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping(path = "/vdt")
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @CrossOrigin(origins = "http://192.168.1.100:5500")
//    @GetMapping("/")
//    public String hello() {
//        return "Hello";
//    }
//
//    @CrossOrigin(origins = "http://192.168.1.100:5500")
//    @GetMapping("/all")
//    public List<UserModel> getAllUser() {
//        return userService.getAllUser();
//    }
//
//    @CrossOrigin(origins = "http://192.168.1.100:5500")
//    @PostMapping("/create")
//    public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
//        return userService.addUser(userModel);
//    }
//
//    @CrossOrigin(origins = "http://192.168.1.100:5500")
//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> createUser(@PathVariable(name = "id") int id, @RequestBody UserModel userModel) {
//        return userService.updateUser(id,userModel);
//    }
//
//    @CrossOrigin(origins = "http://192.168.1.100:5500")
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") int id ) {
//        return userService.deleteUser(id);
//    }
//}
package appbackend.back.controller;

import appbackend.back.model.UserModel;
import appbackend.back.service.UserService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    }

    @CrossOrigin(origins = "http://192.168.1.100:5500")
    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

    @CrossOrigin(origins = "http://192.168.1.100:5500")
    @GetMapping("/all")
    public List<UserModel> getAllUser() {
        getAllUserCounter.increment();
        return getAllUserTimer.record(() -> userService.getAllUser());
    }

    @CrossOrigin(origins = "http://192.168.1.100:5500")
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserModel userModel) {
        createUserCounter.increment();
        return createUserTimer.record(() -> userService.addUser(userModel));
    }

    @CrossOrigin(origins = "http://192.168.1.100:5500")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable(name = "id") int id, @RequestBody UserModel userModel) {
        updateUserCounter.increment();
        return updateUserTimer.record(() -> userService.updateUser(id, userModel));
    }

    @CrossOrigin(origins = "http://192.168.1.100:5500")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") int id) {
        deleteUserCounter.increment();
        return deleteUserTimer.record(() -> userService.deleteUser(id));
    }
}
