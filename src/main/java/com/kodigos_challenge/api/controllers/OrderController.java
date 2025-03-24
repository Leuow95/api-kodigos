package com.kodigos_challenge.api.controllers;

import com.kodigos_challenge.api.config.JwtUtil;
import com.kodigos_challenge.api.entities.Order;
import com.kodigos_challenge.api.entities.OrderRequestDto;
import com.kodigos_challenge.api.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(
            @RequestBody OrderRequestDto orderRequest,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        try {
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido ou ausente");
            }

            Order order = new Order();
            order.setDescription(orderRequest.description());
            order.setChecklist(orderRequest.checklist());
            order.setPhotoUrl(orderRequest.photoData());

            orderRepository.save(order);

            return ResponseEntity.ok("Ordem criada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar ordem: " + e.getMessage());
        }
    }

    @GetMapping("/list")
    public List<Order> listOrders() {
        // Retorna todas as ordens do banco de dados
        return orderRepository.findAll();
    }
}
