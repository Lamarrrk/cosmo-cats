package com.cosmocats.mapper;

import com.cosmocats.domain.Order;
import com.cosmocats.dto.order.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDTO toDTO(Order order);

    Order toDomain(OrderDTO orderDTO);
}


