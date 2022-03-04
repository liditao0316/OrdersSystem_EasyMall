package easymall.service;

import easymall.dao.CategoryDao;
import easymall.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public void delete(String name) {
        categoryDao.delete(name);
    }

    @Override
    public void update(Category category) {
        categoryDao.update(category);
    }

    @Override
    public List<Category> selectAll() {
        return categoryDao.selectAll();
    }

    @Override
    public void insert(Category category) {
        categoryDao.insert(category);
    }

    @Override
    public Category selectByName(String name) {
        return categoryDao.selectByName(name);
    }
}
