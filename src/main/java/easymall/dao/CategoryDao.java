package easymall.dao;

import easymall.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryDao")
@Mapper
public interface CategoryDao {
    public void delete(String name);
    public void update(Category category);
    public List<Category> selectAll();
    public void insert(Category category);
    public Category selectByName(String name);
}
