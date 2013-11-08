package darkengines.database;

import java.util.Set;

import javax.persistence.Entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.reflections.Reflections;

import server.User;
import darkengines.reflexion.ReflectionsFactory;

public class DBSessionFactory {
	private static SessionFactory sessionFactory = null;
	public static Session GetSession() {
		if (sessionFactory == null) {
			sessionFactory = CreateSessionFactory();
		}
		return sessionFactory.openSession();
	}
	public static SessionFactory CreateSessionFactory() {
		Configuration configuration = new Configuration();
	    configuration.configure();
	    Reflections reflections = ReflectionsFactory.getReflections();
	    Set<Class<?>> entities = reflections.getTypesAnnotatedWith(Entity.class);
	    for (Class<?> c: entities) {
	    	configuration.addAnnotatedClass(c);
	    }
	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
}
