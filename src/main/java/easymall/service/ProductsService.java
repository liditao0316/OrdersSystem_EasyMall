package easymall.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import easymall.po.Products;
import easymall.po.Salas;
import easymall.pojo.MyProducts;
import easymall.pojo.UpdateProduct;

public interface ProductsService {
	public List<String> allcategories();
	public List<Products> prodlist(Map<String,Object> map);
	public Products oneProduct(String pid);
	public List<Products> prodclass(String prodclass);
	public String save(MyProducts myproducts, HttpServletRequest request);
	public void delprod(String id);
	public void updateSalas(Salas salas);
	public List<Products> findSalasTop();
	public String update(UpdateProduct updateProducts, HttpServletRequest request);
}
