package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.OrderItem;

@Repository("orderItemDao")
@Mapper
public interface OrderItemDao {

	public void addOrderItem(OrderItem orderItem);

	public List<OrderItem> orderitem(String order_id);

	public void delorderitem(String id);

}
