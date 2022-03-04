package easymall.service;

import easymall.pojo.Category;

import java.util.List;

public interface CategoryService {
    public void delete(String name);
    public void update(Category category);
    public List<Category> selectAll();
    public void insert(Category category);
    public Category selectByName(String name);
}
