package com.mtit.restaurantchef_service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ServiceActivator implements BundleActivator {

	ServiceRegistration publishServiceRegistration;

	public void start(BundleContext context) throws Exception {
		System.out.println("============ResturantChef service started.============");
		ChefPublish chefService = new ChefPublishImpl();
		publishServiceRegistration = context.registerService(ChefPublish.class.getName(), chefService, null);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("==============ResturantChef service closed.=============");
		publishServiceRegistration.unregister();
	}

}
