package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

   private final UserDao userDao;

   @Override
   @Transactional
   public void add(User user) {
      userDao.add(user);
   }


   @Override
   @Transactional(readOnly = true)
   public List<User> listUsers() {
      return userDao.listUsers();
   }


   @Override
   @Transactional(readOnly = true)
   public User getUser(String model, int series) {
      return userDao.getUser(model, series);
   }
}
