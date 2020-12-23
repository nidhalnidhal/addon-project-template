package org.exoplatform.addons.services;
import org.exoplatform.container.PortalContainer;
import org.picocontainer.Startable;


import org.exoplatform.container.ExoContainerContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
	 * Created by eXo Platform SAS.
	 *
	 */

	public class MyNewStartableService implements Startable {
	  /**
	   * The logger.
	   */
	  private static final Logger LOG = LogManager.getLogger(String.valueOf(MyNewStartableService.class));


	  public void start() {
	    LOG.info("&&&&&&&&&&&& My  Brand NewStartable service started !");
	    PortalContainer portalContainer = (PortalContainer) ExoContainerContext.getCurrentContainer();
	    LOG.info("&&&&&&&&&&&& Getting an instance of MyNEWService !");
	    MyService myNewService = (MyService) portalContainer.getComponentInstance(MyService.class);
	    LOG.info("&&&&&&&&&&&& Calling function call of MyNewService !");
	    myNewService.call();
	  }

	 
	  public void stop() {
		  LOG.info("&&&&&&&&&&&& My Brand New Startable service stopped !");
	  }
	  

}
