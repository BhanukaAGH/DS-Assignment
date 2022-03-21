package tablemanagerproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class TableManagerProducerActivator implements BundleActivator {

	ServiceRegistration producerRegister;

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println(".-------------------------------Resturant Table Manager Producer started.-------------------------------");
		TableManagerProduce tableService = new TableManagerProducerImpl();
		producerRegister=bundleContext.registerService(TableManagerProduce.class.getName(),tableService,null);//register producer bundle
		//TableManagerProducerActivator.context = bundleContext;
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println(".-------------------------------Resturant Table Manager Producer stopped.-------------------------------");
		producerRegister.unregister();//unregister producer bundle
		
		//TableManagerProducerActivator.context = null;
	}

}
