package exo.rest.service;
import javax.ws.rs.*;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import javax.annotation.security.RolesAllowed;

import io.swagger.annotations.ApiParam;
import org.exoplatform.addons.Dao.FavouriteActivityDao;
import org.exoplatform.addons.entity.Calendar;
import org.exoplatform.addons.entity.FavoriteActivity;
import org.exoplatform.commons.utils.ListAccess;
import org.exoplatform.container.ExoContainer;
import org.exoplatform.container.ExoContainerContext;
import org.exoplatform.services.organization.OrganizationService;
import org.exoplatform.services.organization.User;
import org.exoplatform.services.organization.UserHandler;
import org.exoplatform.services.rest.resource.ResourceContainer;
import org.exoplatform.services.security.Identity;
import org.exoplatform.services.security.IdentityRegistry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Rest User Service!
 */
@Path("/demo")
@Produces("application/json")
public class RestUserService implements ResourceContainer {

    private FavouriteActivityDao dao = new FavouriteActivityDao();
    @GET
    @Path("/hello/{name}")
    public String hello(@PathParam("name")
                                String name,@Context  SecurityContext sc) throws JSONException {

        return "Hello " + name;

    }
    //@GET
    /*@Path("/listusers/{offset}")
    public Response getListUserName(@Context  SecurityContext sc,@PathParam("offset") Integer offset) throws JSONException {
        JSONArray list = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        String groupToCheck = "/platform/administrators";
        CacheControl cacheControl = new CacheControl();       cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);

        if (sc.getUserPrincipal() == null || !this.isMemberOf(sc.getUserPrincipal().getName(), groupToCheck)) {

            jsonObject.put("rights","NOT-ALLOWED");
            list.put(jsonObject);

        } else {

            OrganizationService organizationService = (OrganizationService) ExoContainerContext.getCurrentContainer()
                    .getComponentInstanceOfType(OrganizationService.class);
            UserHandler userHandler = organizationService.getUserHandler();
            try {
                ListAccess<User> allUsers = userHandler.findAllUsers();

                if(offset == null || offset < 0)
                    offset = 0;
                int limit = 1000;
                int total = limit + offset;
                int totalUsers = allUsers.getSize();

                if(offset < totalUsers && total > totalUsers){
                    total = totalUsers;
                }
                User[] users = null;

                for (int i = offset; i < total; i++) {
                    users = allUsers.load(i,1);
                    jsonObject = new JSONObject();
                    jsonObject.put("username", users[0].getUserName());
                    list.put(jsonObject);
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        return Response.ok(list.toString(), MediaType.APPLICATION_JSON).cacheControl(cacheControl).build();

    }*/
    @POST
    @Path("/create")
    @Consumes("application/json")
    public Response createActivity(@ApiParam(value = "act", required = true)FavoriteActivity activity) {
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);
       // JSONArray list = new JSONArray();
        //JSONObject jsonObject = new JSONObject();
            //    try{
                    activity.setFavoriteDate(Calendar.getInstance());
                    this.dao.insertWithEntityManager(activity);

                    //jsonObject.put("activity id",act.getId());
                   // jsonObject.put("activity title",act.getActivityTitle());
                   // jsonObject.put("activity date",act.getFavoriteDate());


                    //list.put(jsonObject);
               // }
               // catch (Exception e) {
                 //   // TODO Auto-generated catch block
                  //  e.printStackTrace();
               // }
        return Response.ok("ok", MediaType.APPLICATION_JSON).cacheControl(cacheControl).build();

    }

    @GET
    @Path("/GetActivity")
    public Response GetActivities() throws JSONException {
        JSONArray list = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        CacheControl cacheControl = new CacheControl();
        cacheControl.setNoCache(true);
        cacheControl.setNoStore(true);
        List<FavoriteActivity> activitylist= this.dao.findAllActs();
          if(activitylist.isEmpty()){
              jsonObject.put("list is" , "empty");
              list.put(jsonObject);
          }
       // try{
           for(int i=0;i<activitylist.size();i++){
               jsonObject = new JSONObject();
               jsonObject.put(String.valueOf(i), activitylist.get(i));
               list.put(jsonObject);
           }
      //  }
       // catch (Exception e) {
            // TODO Auto-generated catch block
           // e.printStackTrace();
        //}
        return Response.ok(list.toString(), MediaType.APPLICATION_JSON).cacheControl(cacheControl).build();


    }


    private boolean isMemberOf(String username, String role) {
        ExoContainer container = ExoContainerContext.getCurrentContainer();
        IdentityRegistry identityRegistry = (IdentityRegistry) container.getComponentInstanceOfType(IdentityRegistry.class);
        Identity identity = identityRegistry.getIdentity(username);
        return identity.isMemberOf(role);
    }

    public RestUserService() {
    }

    public RestUserService(FavouriteActivityDao dao) {
        this.dao=dao;
    }
}