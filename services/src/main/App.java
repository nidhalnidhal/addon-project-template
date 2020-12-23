package src.main;

import org.exoplatform.addons.services.MyNewStartableService;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.container.PortalContainer;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    PortalContainer portalContainer = (PortalContainer) ExoContainerContext.getCurrentContainer();
	    MyNewStartableService mynewStartableService = (MyNewStartableService) portalContainer.getComponentInstance(MyNewStartableService.class);
	    mynewStartableService.start();
	}

}
