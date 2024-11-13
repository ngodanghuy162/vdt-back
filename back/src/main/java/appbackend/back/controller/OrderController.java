package appbackend.back.controller;
import appbackend.back.model.OrderModel;
import appbackend.back.model.UserModel;
import appbackend.back.service.OrderService;
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
@RequestMapping(path = "/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public List<OrderModel> getAllUser() {
            return orderService.getAllOrder();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody OrderModel orderModel) {
            return orderService.addOrder(orderModel);
        }
    }
