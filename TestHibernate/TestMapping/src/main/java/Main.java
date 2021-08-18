import dao.DAO;
import dao.StudentsDAO;
import org.hibernate.SessionFactory;


public class Main {

  public static void main(String[] args) {

    SessionFactory sessionFactory = DAO.DefaultSessionFactory.getDefaultSessionFactory("hibernate.cfg.xml");
    StudentsDAO studentsDAO = new StudentsDAO(sessionFactory);
    System.out.println(studentsDAO.read(4));

  }

}
