package org.exoplatform.addons.Dao;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.exoplatform.addons.entity.FavoriteActivity;
import org.exoplatform.commons.api.persistence.ExoTransactional;
import org.exoplatform.commons.persistence.impl.GenericDAOJPAImpl;
import org.exoplatform.services.jcr.core.ManageableRepository;
import org.exoplatform.social.core.jpa.storage.entity.ActivityEntity;
import org.exoplatform.social.core.jpa.storage.entity.IdentityEntity;

import javax.jcr.Session;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FavouriteActivityDao  extends GenericDAOJPAImpl<FavoriteActivity, Long> {


    public List<FavoriteActivity> getActivitiesOwnedBy(ActivityEntity entity) {
        String identity = entity.getAppId();
        List<FavoriteActivity> list  = getEntityManager().createQuery("select * from ADDON_FAVOURITE_ACTIVITY where TargetActivity=" +identity).getResultList();
         return(list);
    }
    public List<FavoriteActivity> getActivitiesByTitle(String title) {
        List<FavoriteActivity> list  = getEntityManager().createQuery("select * from ADDON_FAVOURITE_ACTIVITY where TargetActivity=" +title).getResultList();
        return(list);
    }
    public List<FavoriteActivity> getActivitiesByDate(Calendar calendar) {
       // Date RealDate = calendar.getTime();
        String date = String.valueOf(calendar);
        List<FavoriteActivity> list = getEntityManager().createQuery("select * from ADDON_FAVOURITE_ACTIVITY where FAVOURITE_DATE="+date).getResultList();
        return(list);
    }

    public FavoriteActivity getActivitiesById(long id) {
            Object act = getEntityManager().createQuery("select * from ADDON_FAVOURITE_ACTIVITY where ID=" +id).getSingleResult();
        return(FavoriteActivity) act;
    }
    public void insertWithEntityManager(FavoriteActivity activity) {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
        em.persist(activity);
         em.getTransaction().commit();
    }
    public List<FavoriteActivity> findAllActs() {
        EntityManager em= getEntityManager() ;
        em.getTransaction().begin();
      // LOG.info("EM propbreties :" +em.getProperties());
        Query q = em.createQuery("select f from FavoriteActivityEntity f ");
        //LOG.info("query :"+q.toString());
        List<FavoriteActivity> l = q.getResultList();
       // LOG.info("result list : "+q.getResultList());
        em.getTransaction().commit();
        return l;
    }
    public Boolean deleteWithEntityManager(FavoriteActivity activity) {
        getEntityManager().remove(activity);
        return(true);
    }

}
