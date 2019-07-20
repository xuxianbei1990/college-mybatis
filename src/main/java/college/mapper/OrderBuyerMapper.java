package college.mapper;

import college.model.OrderBuyer;

import java.util.List;

public interface OrderBuyerMapper {
    int deleteByPrimaryKey(Long forderBuyerId);

    int insert(OrderBuyer record);

    int insertSelective(OrderBuyer record);

    OrderBuyer selectByPrimaryKey(Long forderBuyerId);

    int updateByPrimaryKeySelective(OrderBuyer record);

    int updateByPrimaryKey(OrderBuyer record);

    List<OrderBuyer> selectByPrimaryKeys(List<Long> forderBuyerIds);
}