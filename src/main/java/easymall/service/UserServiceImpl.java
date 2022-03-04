package easymall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import easymall.dao.UserDao;
import easymall.po.User;

@Service("userService")
//@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public User login(String username){
		return userDao.login(username);
	}
	@Override
	public int regist(User user) {
		// TODO Auto-generated method stub
		int n=userDao.regist(user);
		return n;
	}

	@Override
	public void activation(Integer id) {
		userDao.activation(id);
	}

}
