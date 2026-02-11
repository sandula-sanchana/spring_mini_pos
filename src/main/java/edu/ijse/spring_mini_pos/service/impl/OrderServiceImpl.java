package edu.ijse.spring_mini_pos.service.impl;

import edu.ijse.spring_mini_pos.dto.OrderDTO;
import edu.ijse.spring_mini_pos.dto.OrderDetailDTO;
import edu.ijse.spring_mini_pos.entity.Item;
import edu.ijse.spring_mini_pos.entity.OrderDetail;
import edu.ijse.spring_mini_pos.entity.Orders;
import edu.ijse.spring_mini_pos.respository.CustomerRepository;
import edu.ijse.spring_mini_pos.respository.ItemRepository;
import edu.ijse.spring_mini_pos.respository.OrderDetailRepository;
import edu.ijse.spring_mini_pos.respository.OrderRepository;
import edu.ijse.spring_mini_pos.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final OrderDetailRepository orderDetailRepo;
    private final CustomerRepository customerRepo;
    private final ItemRepository itemRepo;

    public OrderServiceImpl(OrderRepository orderRepo,
                            OrderDetailRepository orderDetailRepo,
                            CustomerRepository customerRepo,
                            ItemRepository itemRepo) {
        this.orderRepo = orderRepo;
        this.orderDetailRepo = orderDetailRepo;
        this.customerRepo = customerRepo;
        this.itemRepo = itemRepo;
    }

    @Override
    @Transactional
    public void placeOrder(OrderDTO orderDTO) {

        // 1) basic validation
        if (orderDTO.getOrderId() == null || orderDTO.getOrderId().trim().isEmpty()) {
            throw new RuntimeException("Order ID is required");
        }
        if (orderDTO.getCustomerId() == null || orderDTO.getCustomerId().trim().isEmpty()) {
            throw new RuntimeException("Customer ID is required");
        }
        if (orderDTO.getItems() == null || orderDTO.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        if (!customerRepo.existsById(orderDTO.getCustomerId())) {
            throw new RuntimeException("Customer not found: " + orderDTO.getCustomerId());
        }

        if (orderRepo.existsById(orderDTO.getOrderId())) {
            throw new RuntimeException("Order already exists: " + orderDTO.getOrderId());
        }

        // 2) save Orders header
        Orders order = new Orders(
                orderDTO.getOrderId(),
                orderDTO.getCustomerId(),
                LocalDate.now(),
                orderDTO.getTotal()
        );
        orderRepo.save(order);

        // 3) details + stock update
        for (OrderDetailDTO d : orderDTO.getItems()) {

            if (d.getQty() <= 0) {
                throw new RuntimeException("Invalid qty for item: " + d.getItemId());
            }

            Item item = itemRepo.findById(d.getItemId())
                    .orElseThrow(() -> new RuntimeException("Item not found: " + d.getItemId()));

            if (item.getQty() < d.getQty()) {
                throw new RuntimeException("Not enough stock for " + d.getItemId()
                        + " (available=" + item.getQty() + ", requested=" + d.getQty() + ")");
            }

            // reduce stock
            item.setQty(item.getQty() - d.getQty());
            itemRepo.save(item);

            // save order detail row
            OrderDetail detail = new OrderDetail(
                    null, // auto id
                    orderDTO.getOrderId(),
                    d.getItemId(),
                    d.getQty(),
                    d.getUnitPrice()
            );
            orderDetailRepo.save(detail);
        }

        // Any exception above -> rollback everything ✅ (@Transactional)
    }
}
