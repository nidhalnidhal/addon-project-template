package org.exoplatform.addons.services;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import nl.altindag.log.LogCaptor;
import org.exoplatform.addons.Dao.FavouriteActivityDao;
import org.exoplatform.addons.entity.FavoriteActivity;
import org.exoplatform.addons.entity.Calendar;
import org.junit.Test;

import javax.jcr.RepositoryException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.function.Predicate;
public class FirstTest {

    @Test
    public void logInfoMessagesforMyNewStartableService() {

        /*String expectedInfoMessage = "&&&&&&&&&&&& My  Brand NewStartable service started !";
        String expectedInfoMessage1 = "&&&&&&&&&&&& Getting an instance of MyNEWService !";
        String expectedInfoMessage2 = "&&&&&&&&&&&& Calling function call of MyNewService !";

        LogCaptor<MyNewStartableService> logCaptor = LogCaptor.forClass(MyNewStartableService.class);
        MyNewStartableService mynewstartableservice = new MyNewStartableService();
        mynewstartableservice.start();
        assertThat(logCaptor.getLogs()).hasSize(3).containsExactly(expectedInfoMessage, expectedInfoMessage1, expectedInfoMessage2);*/
    }

    @Test
    public void logInfoMessagesforMyNewService() {

      /* String expectedInfoMessage = "######################  call function invoked !!";

        LogCaptor<MyNewStartableService> logCaptor = LogCaptor.forClass(MyNewStartableService.class);
        MyNewService mynewservice = new MyNewService();
        mynewservice.call();
        assertThat(logCaptor.getLogs())
                .hasSize(1)
                .containsExactly(expectedInfoMessage);*/
    }

    @Test
    public void MyNewServiceCreationTest() throws RepositoryException {
        MyService service =  new MyService();
        assertEquals(1,service.createActivityJSR("Tennis","TennisLink",new Date(),"NewDate"));

    }
    @Test
    public void ListAllTest() throws RepositoryException {
        MyService service = new MyService();
        assertEquals(1, service.ListAll());
    }
    @Test
    public void ListingActivitiesByTitleTest() throws RepositoryException {
        FavouriteActivityDao dao = new FavouriteActivityDao();
        List<FavoriteActivity> list = dao.getActivitiesByTitle("sport");
        assertFalse(list.isEmpty());
    }
    @Test
    public void ListingActivitiesByDateTest() throws RepositoryException {
        FavouriteActivityDao dao = new FavouriteActivityDao();
        Calendar c = new Calendar();
        List<FavoriteActivity> list = dao.getActivitiesByDate(c);
        assertFalse(list.isEmpty());
    }
    @Test
    public void SaveTest() throws RepositoryException {
        FavouriteActivityDao dao = new FavouriteActivityDao();
        FavoriteActivity act = new FavoriteActivity();
        assertEquals(1,dao.insertWithEntityManager(act));


    }
    @Test
    public void DeleteTest() throws RepositoryException {
        FavouriteActivityDao dao = new FavouriteActivityDao();
        FavoriteActivity act = new FavoriteActivity();
        assertEquals(1,dao.deleteWithEntityManager(act));


    }








}
