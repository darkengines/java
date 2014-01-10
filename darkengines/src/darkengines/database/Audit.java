package darkengines.database;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class Audit implements Integrator {

	@Override
	public void disintegrate(SessionFactoryImplementor arg0,
			SessionFactoryServiceRegistry arg1) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public void integrate(Configuration cfg, SessionFactoryImplementor arg1,
			SessionFactoryServiceRegistry serviceRegistry) {
		EventListenerRegistry service = serviceRegistry.getService(org.hibernate.event.service.spi.EventListenerRegistry.class);
		service.appendListeners(EventType.UPDATE, Monitored.class);
	}

	@Override
	public void integrate(MetadataImplementor arg0,
			SessionFactoryImplementor arg1, SessionFactoryServiceRegistry arg2) {
		// TODO Auto-generated method stub
		
	}

}
