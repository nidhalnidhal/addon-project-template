package org.exoplatform.addons.services;
import org.exoplatform.container.PortalContainer;
import org.picocontainer.Startable;
import org.exoplatform.services.log.*;


import org.exoplatform.container.ExoContainerContext;

	
	/**
	 * Created by eXo Platform SAS.
	 *
	 */

	public class MyNewStartableService implements Startable {
	  /**
	   * The logger.
	   */
	  private static final Log LOG = ExoLogger.getExoLogger(MyNewStartableService.class);

	  @Override
	  public void start() {
	    LOG.info("&&&&&&&&&&&& My  Brand NewStartable service started !");
	    PortalContainer portalContainer = (PortalContainer) ExoContainerContext.getCurrentContainer();
	    LOG.info("&&&&&&&&&&&& Getting an instance of MyNEWService !");
	    MyNewService myNewService = (MyNewService) portalContainer.getComponentInstance(MyNewService.class);
	    LOG.info("&&&&&&&&&&&& Calling function call of MyNewService !");
	    myNewService.call();
	  }

	  @Override
	  public void stop() {
	    LOG.info("&&&&&&&&&&&& My Brand New Startable service stopped !");
	  }

}
