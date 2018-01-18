package com.jq.child.admin.pojo;

import java.util.Date;

public class ParentDuty {
    private Date parentdutyTime;

    private String dutyClass;

    private String dutyParentname;

    private String ext1;

    private String ext2;

    private String ext3;

    public Date getParentdutyTime() {
        return parentdutyTime;
    }

    public void setParentdutyTime(Date parentdutyTime) {
        this.parentdutyTime = parentdutyTime;
    }

    public String getDutyClass() {
        return dutyClass;
    }

    public void setDutyClass(String dutyClass) {
        this.dutyClass = dutyClass == null ? null : dutyClass.trim();
    }

    public String getDutyParentname() {
        return dutyParentname;
    }

    public void setDutyParentname(String dutyParentname) {
        this.dutyParentname = dutyParentname == null ? null : dutyParentname.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3 == null ? null : ext3.trim();
    }
}