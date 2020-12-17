package org.exoplatform.addons.services;

import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;


public class MyNewService {
	/**
	   * The logger.
	   */
	  private static final Log LOG = ExoLogger.getExoLogger(MyNewService.class);
	  
	  public MyNewService() {
		     LOG.info("##################### Simple New service started successfully !!");
		  }

		  public int  call() {
		    LOG.info("######################  call function invoked !!");
		    return(1);
		  }
}
