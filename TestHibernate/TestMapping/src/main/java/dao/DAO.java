package dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public interface DAO<Entity, Key> {

  Entity read(Key key);

   class  DefaultSessionFactory
  {
    public static SessionFactory getDefaultSessionFactory(String hibernateCfgFile)
    {
      StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
          .configure(hibernateCfgFile).build();
      Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
      return metadata.getSessionFactoryBuilder().build();
    }
  }
}
