package org.exoplatform.addons.entity;

import org.exoplatform.commons.api.persistence.ExoEntity;
import org.exoplatform.social.core.jpa.storage.entity.ActivityEntity;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@ExoEntity
@Table(name = "ADDON_FAVORITE_ACTIVITY")
public class FavoriteActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    private String ActivityTitle;
    @ManyToOne
    @JoinColumn(name = "TargetActivity")
    private ActivityEntity TargetActivity;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "FAVOURITE_DATE")
    private Calendar FavoriteDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityTitle() {
        return ActivityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.ActivityTitle = activityTitle;
    }

    public ActivityEntity getTargetActivity() {
        return TargetActivity;
    }

    public void setTargetActivity(ActivityEntity targetActivity) {
        this.TargetActivity = targetActivity;
    }

    public Calendar getFavoriteDate() {
        return FavoriteDate;
    }

    public void setFavoriteDate(Calendar favoriteDate) {
        this.FavoriteDate = favoriteDate;
    }

    public FavoriteActivity(Long id, String activityTitle, ActivityEntity targetActivity, Calendar favoriteDate) {
        this.id = id;
        this.ActivityTitle = activityTitle;
        this.TargetActivity = targetActivity;
        this.FavoriteDate = favoriteDate;
    }

    public FavoriteActivity() {
    }
}
