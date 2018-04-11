package cn.hxy.bbs.model;

import java.util.Date;

public class Log {
    private Integer adminId;

    private String module;

    private String mehtod;

    private Date date;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public String getMehtod() {
        return mehtod;
    }

    public void setMehtod(String mehtod) {
        this.mehtod = mehtod == null ? null : mehtod.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}