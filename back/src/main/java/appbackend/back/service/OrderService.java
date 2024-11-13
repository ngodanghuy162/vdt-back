package appbackend.back.service;

import appbackend.back.model.OrderModel;
import appbackend.back.model.UserModel;
import appbackend.back.repository.OrderRepository;
import appbackend.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderModel> getAllOrder() {
        return orderRepository.findAll();
    }

    public ResponseEntity<String> addOrder(OrderModel orderModel) {
        try {
            orderRepository.save(orderModel);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }
}
