package com.mtit.restaurantcashir_service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	ServiceRegistration publishServiceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("============Supermarket Cashier service started.============");
		CashierPublish cashierService = new CashierPublishImpl();
		publishServiceRegistration = context.registerService(CashierPublish.class.getName().toString(), cashierService,
				null);// registering the cashierService
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("==============Supermarket service closed.=============");
		publishServiceRegistration.unregister();
	}

}
