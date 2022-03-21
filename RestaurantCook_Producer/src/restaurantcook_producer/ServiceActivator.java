package restaurantcook_producer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	private static BundleContext context;
	ServiceRegistration publishServiceRegistration;


	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext context) throws Exception {
		System.out.println("============Resturant Cook Service Started.============");
		CookPublish chefService = new CookPublishImpl();
		publishServiceRegistration = context.registerService(CookPublish.class.getName(), chefService, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("==============Resturant Cook Service Closed.=============");
		publishServiceRegistration.unregister();
		
	}

}
