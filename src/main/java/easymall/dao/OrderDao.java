package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Repository;

import easymall.po.Orders;

@Repository("orderDao")
@Mapper
public interface OrderDao {
	public void addOrder(Orders myOrder);
	List<Orders> findOrderByUserId(Integer user_id);
	public void delorder(String id);
	public void payorder(String id);
	public void setstate(String id);
	public void sendstate(String id);
	public List<Orders> selectAll();
	public List<Orders> selectSend();
}
