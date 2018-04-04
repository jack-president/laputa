package com.laputa.foundation.web.generate.annotae;

/**
 * Created by JiangDongPing on 2016/11/10.
 */
public class Annotae {
    Integer indexNumber;

    String cname;

    Boolean showable;

    Integer showwidth;

    String descript;

    Boolean updatable;


    public Boolean getShowable() {
        return showable;
    }

    public void setShowable(Boolean showable) {
        this.showable = showable;
    }

    public Integer getShowwidth() {
        return showwidth;
    }

    public void setShowwidth(Integer showwidth) {
        this.showwidth = showwidth;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Boolean getUpdatable() {
        return updatable;
    }

    public void setUpdatable(Boolean updatable) {
        this.updatable = updatable;
    }
}
