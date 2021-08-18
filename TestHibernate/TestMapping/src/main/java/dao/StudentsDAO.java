package dao;

import com.sun.istack.NotNull;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import students.Student;

public class StudentsDAO implements DAO<Student, Integer> {


  private final SessionFactory factory;

  public StudentsDAO(@NotNull final SessionFactory factory) {
    this.factory = factory;
  }

  @Override
  public Student read(@NotNull final Integer id) {
    try (final Session session = factory.openSession()) {
      final Student result = session.get(Student.class, id);
      return Optional.of(result).orElse(new Student());
    }
  }


}