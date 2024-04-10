package com.tohir.orderservice.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tohir.basedomains.dto.Order;
import com.tohir.basedomains.dto.OrderEvent;
import com.tohir.orderservice.service.kafka.OrderProducer;
import com.tohir.orderservice.utils.constant.KafkaConstants;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderProducer orderProducer;

    @PostMapping()
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setOrder(order);

        orderProducer.sendMessage(KafkaConstants.TOPIC_ORDERS, LocalDateTime.now().toString(), orderEvent);

        return ResponseEntity.ok("Order placed successfully ...");
    }

}
