package com.zaurfarrukhzada.myebayclone;

public class RewardsModel {

    private String title;
    private String rewardsDate;
    private String rewardsBody;

    public RewardsModel(String title, String rewardsDate, String rewardsBody) {
        this.title = title;
        this.rewardsDate = rewardsDate;
        this.rewardsBody = rewardsBody;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRewardsDate() {
        return rewardsDate;
    }

    public void setRewardsDate(String rewardsDate) {
        this.rewardsDate = rewardsDate;
    }

    public String getRewardsBody() {
        return rewardsBody;
    }

    public void setRewardsBody(String rewardsBody) {
        this.rewardsBody = rewardsBody;
    }
}
