package easymall.dao;

import java.util.List;
import java.util.Map;

import easymall.po.Salas;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Products;

@Repository("productsDao")
@Mapper
public interface ProductsDao {
	public List<String> allcategories();
	public List<Products> prodlist(Map<String,Object> map);
	public Products oneProduct(String pid);
	public List<Products> prodclass(String category);
	public Object findByImgurl(String imgurl);
	public void save(Products products);
	public void delprod(String id);
	public void updateSalas(Salas salas);
	public List<Products> findSalasTop();
	public void updateProduct(Products products);
}
