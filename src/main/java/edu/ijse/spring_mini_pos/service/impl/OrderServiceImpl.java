package edu.ijse.spring_mini_pos.service.impl;

import edu.ijse.spring_mini_pos.dto.OrderDTO;
import edu.ijse.spring_mini_pos.dto.OrderDetailDTO;
import edu.ijse.spring_mini_pos.entity.Item;
import edu.ijse.spring_mini_pos.entity.OrderDetail;
import edu.ijse.spring_mini_pos.entity.Orders;
import edu.ijse.spring_mini_pos.exception.BadRequestException;
import edu.ijse.spring_mini_pos.exception.ResourceNotFoundException;
import edu.ijse.spring_mini_pos.respository.CustomerRepository;
import edu.ijse.spring_mini_pos.respository.ItemRepository;
import edu.ijse.spring_mini_pos.respository.OrderDetailRepository;
import edu.ijse.spring_mini_pos.respository.OrderRepository;
import edu.ijse.spring_mini_pos.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final OrderDetailRepository orderDetailRepo;
    private final CustomerRepository customerRepo;
    private final ItemRepository itemRepo;

    @Override
    public void placeOrder(OrderDTO orderDTO) {

        // 1) basic validation (BAD REQUEST 400)
        if (orderDTO == null) {
            throw new BadRequestException("Request body is missing");
        }
        if (orderDTO.getOrderId() == null ) {
            throw new BadRequestException("Order ID is required");
        }
        if (orderDTO.getCustomerId() == null) {
            throw new BadRequestException("Customer ID is required");
        }
        if (orderDTO.getItems() == null || orderDTO.getItems().isEmpty()) {
            throw new BadRequestException("Cart is empty");
        }

        // customer must exist (NOT FOUND 404)
        if (!customerRepo.existsById(orderDTO.getCustomerId())) {
            throw new ResourceNotFoundException("Customer not found: " + orderDTO.getCustomerId());
        }

        // order id must be unique (BAD REQUEST 400)
        if (orderRepo.existsById(orderDTO.getOrderId())) {
            throw new BadRequestException("Order already exists: " + orderDTO.getOrderId());
        }

        // 2) save order header
        Orders order = new Orders(
                orderDTO.getOrderId(),
                orderDTO.getCustomerId(),
                LocalDate.now(),
                orderDTO.getTotal()
        );
        orderRepo.save(order);

        // 3) details + stock update
        for (OrderDetailDTO d : orderDTO.getItems()) {

            if (d == null) {
                throw new BadRequestException("Order item is missing");
            }
            if (d.getItemId() == null ) {
                throw new BadRequestException("Item ID is required in order detail");
            }
            if (d.getQty() <= 0) {
                throw new BadRequestException("Invalid qty for item: " + d.getItemId());
            }
            if (d.getUnitPrice() <= 0) {
                throw new BadRequestException("Invalid unit price for item: " + d.getItemId());
            }

            Item item = itemRepo.findById(d.getItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Item not found: " + d.getItemId()));

            if (item.getQty() < d.getQty()) {
                throw new BadRequestException(
                        "Not enough stock for " + d.getItemId() +
                                " (available=" + item.getQty() + ", requested=" + d.getQty() + ")"
                );
            }

            // reduce stock (managed entity inside @Transactional)
            item.setQty(item.getQty() - d.getQty());

            // save order detail
            OrderDetail detail = new OrderDetail(
                    null, // auto id
                    orderDTO.getOrderId(),
                    d.getItemId(),
                    d.getQty(),
                    d.getUnitPrice()
            );
            orderDetailRepo.save(detail);
        }

        // any exception => rollback all ✅
    }
}
