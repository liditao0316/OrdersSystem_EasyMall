package easymall.pojo;
import java.util.Map;
import easymall.po.Orders;
import easymall.po.Products;
public class OrderInfo {
	private Orders order;//订单信息
	private Map<Products, Integer> map;//该订单中的所有订单项信息
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Map<Products, Integer> getMap() {
		return map;
	}
	public void setMap(Map<Products, Integer> map) {
		this.map = map;
	}

}
