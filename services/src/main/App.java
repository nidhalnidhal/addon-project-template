package org.exoplatform.addons.services;
package org.exoplatform
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;
import org.picocontainer.Startable;
import org.exoplatform.services.log.*;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    PortalContainer portalContainer = (PortalContainer) ExoContainerContext.getCurrentContainer);
	    MyNewStartableService mynewStartableService = (MyNewStartableService) portalContainer.getComponentInstance(MyNewStartableService.class);
	    myNewStartableService.start();
	}
      
}
