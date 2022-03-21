package waiterproducer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;


public class Activator implements BundleActivator {

	ServiceRegistration producerRegister;

	
	

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println(".-------------------------------Waiter  Producer started.-------------------------------");
		waiterproduce waiter = new waiterproduceimpl();
		producerRegister=bundleContext.registerService(waiterproduce.class.getName(),waiter,null);//register producer bundle
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println(".-------------------------------Waiter  Producer stopped.-------------------------------");
		producerRegister.unregister();//unregister producer bundle
		
	}

}
