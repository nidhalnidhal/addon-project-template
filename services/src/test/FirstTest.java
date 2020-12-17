package org.exoplatform.addons.services;

import static org.junit.Assert.*;  
import static org.junit.Assert.assertEquals;  
import org.junit.After;  
import org.junit.AfterClass;  
import org.junit.Before;  
import org.junit.BeforeClass; 
import org.junit.Test;  


import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

 public class FirstTest {
	 
	 @Test
	     public void logInfoMessagesforMyNewStartableService() {

	         String expectedInfoMessage = "&&&&&&&&&&&& My  Brand NewStartable service started !";
	         String expectedInfoMessage1 = "&&&&&&&&&&&& Getting an instance of MyNEWService !";
	         String expectedInfoMessage2 = "&&&&&&&&&&&& Calling function call of MyNewService !";


	         LogCaptor<MyNewStartableService> logCaptor = LogCaptor.forClass(MyNewStartableService.class);
	         MyNewStartableService mynewstartableservice = new MyNewStartableService();
	         mynewstartableservice.start();
	         assertThat(logCaptor.getLogs())
	                         .hasSize(3)
	                         .containsExactly(expectedInfoMessage,expectedInfoMessage1,expectedInfoMessage2 );	 
	     }
	 
	 @Test
     public void logInfoMessagesforMyNewService() {

         String expectedInfoMessage = "######################  call function invoked !!";
         
         LogCaptor<MyNewStartableService> logCaptor = LogCaptor.forClass(MyNewService.class);
         MyNewService mynewservice = new MyNewService();
         mynewservice.start();
         assertThat(logCaptor.getLogs())
                         .hasSize(1)
                         .containsExactly(expectedInfoMessage);	 
     }
	


}
