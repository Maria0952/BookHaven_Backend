package com.bookhaven.ecom.services.admin.adminOrder;
 
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bookhaven.ecom.dto.OrderDto;
import com.bookhaven.ecom.entity.Order;
import com.bookhaven.ecom.enums.OrderStatus;
import com.bookhaven.ecom.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
 
@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {
 
	private final OrderRepository orderRepository;
	
	public List<OrderDto> getAllPlacedOrders(){
		List<Order> orderList=orderRepository.findAllByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.Shipped,OrderStatus.Delivered));
			return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());
	}
	public OrderDto changeOrderStatus(Long orderId,String status) {
		Optional<Order> optionalOrder=orderRepository.findById(orderId);
		if(optionalOrder.isPresent()) {
			Order order=optionalOrder.get();
			if(Objects.equals(status,"Shipped")) {
				order.setOrderStatus(OrderStatus.Shipped);
		}else if(Objects.equals(status,"Delivered")) {
			order.setOrderStatus(OrderStatus.Delivered);
		}
			return orderRepository.save(order).getOrderDto();
		}
		return null;
	}
}