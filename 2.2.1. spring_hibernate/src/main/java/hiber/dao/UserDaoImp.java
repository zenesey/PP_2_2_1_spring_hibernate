package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserCar(String model, int series) {
      TypedQuery<User> query = sessionFactory
              .getCurrentSession().createQuery("from User where userCar.model= :model AND userCar.series = :series")
              .setParameter("model", model).setParameter("series", series);
      return query.getSingleResult();
   }
}


//   public User getUserCar(Car car) {
//      TypedQuery<User> query = sessionFactory
//              .getCurrentSession().createQuery("from Car where series =: series and model =: model")
//              .setParameter("series", car.getSeries()).setParameter("model", car.getModel());
//
//      return query.getSingleResult();

