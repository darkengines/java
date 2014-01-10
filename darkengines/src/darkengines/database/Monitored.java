package darkengines.database;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

@SuppressWarnings("serial")
public class Monitored implements SaveOrUpdateEventListener  {

	@Override
	public void onSaveOrUpdate(SaveOrUpdateEvent arg0)
			throws HibernateException {
		if (arg0.getEntity() instanceof MonitoredEntity) {
			try {
				MonitoredEntity.class.getField("UpdatedOn").set(arg0.getEntity(), new Date());
			} catch (IllegalArgumentException | IllegalAccessException
					| NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
