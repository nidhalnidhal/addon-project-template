package org.exoplatform.addons.services;

import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.jcr.RepositoryService;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.services.jcr.ext.common.SessionProvider;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.jcr.*;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import java.util.Date;

/**
 * Created by eXo Platform SAS.
 *
 * @author Ali Hamdi <ahamdi@exoplatform.com>
 * @since 13/09/18 15:12
 */

public class MyService {
  /**
   * The logger.
   */
  private static final Log LOG = ExoLogger.getExoLogger(MyService.class);


  public MyService() {
     LOG.info("##################### Simple service started successfully !!");
  }

  public void call() {
    LOG.info("######################  call function invoked !!");
  }
  public int createActivityJSR(String title, String link, Date date,String name) throws RepositoryException {
    LOG.info("######################  Creation method is invoked !!");
    ExoContainer myContainer = ExoContainerContext.getCurrentContainer();
    RepositoryService repositoryService = (RepositoryService) myContainer. getComponentInstanceOfType(RepositoryService.class);
    Repository repository =   repositoryService.getCurrentRepository();
    Session jcrSession = ((ManageableRepository) repository).getSystemSession("collaboration");
    Node root = jcrSession.getRootNode();

    Value titleValue = jcrSession.getValueFactory().createValue(title);
    Value linkValue = jcrSession.getValueFactory().createValue(link);
    String NewDate= String.valueOf(date);
    Value dateValue = jcrSession.getValueFactory().createValue(NewDate);
    Node newNode = root.addNode(name, "exo:FavoriteActivity");
    newNode.setProperty("exo:Title", titleValue);
    newNode.setProperty("exo:Link", linkValue);
    newNode.setProperty("exo:Publication Date", dateValue);
    newNode.save();
    return(1);
  }

  public int ListAll() throws RepositoryException {

    ExoContainer myContainer = ExoContainerContext.getCurrentContainer();
    RepositoryService repositoryService = (RepositoryService) myContainer. getComponentInstanceOfType(RepositoryService.class);
    Repository repository =   repositoryService.getCurrentRepository();

    Session jcrSession = ((ManageableRepository) repository).getSystemSession("collaboration");

     Node root = jcrSession.getRootNode();
    QueryManager qm = jcrSession.getWorkspace().getQueryManager();
    Query q = qm.createQuery("select * from exo:FavoriteActivity", Query.SQL);
    NodeIterator ni = q.execute().getNodes();
    while (ni.hasNext()) {
      Node iterNode= ni.nextNode();
      System.out.println( iterNode.getName() + ""
              + root.getProperty("Title").getValue().getString() );
    }
    return(1);
  }
  public void delete(String title) throws RepositoryException {
    ExoContainer myContainer = ExoContainerContext.getCurrentContainer();
    RepositoryService repositoryService = (RepositoryService) myContainer. getComponentInstanceOfType(RepositoryService.class);
    Repository repository =   repositoryService.getCurrentRepository();
    Session jcrSession = ((ManageableRepository) repository).getSystemSession("collaboration");
    Node root = jcrSession.getRootNode();
    QueryManager qm = jcrSession.getWorkspace().getQueryManager();
    Query q = qm.createQuery("delete  from exo:FavoriteActivity where Title = '${title}'", Query.SQL);
    q.execute().getNodes();
  }
  }
