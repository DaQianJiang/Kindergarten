package com.jq.child.admin.pojo;

import java.util.Date;

public class ChildRemarks {
    private String sno;

    private String sname;

    private Date creatTime;

    private String classBehavior;

    private String totalRemark;

    private String teacherOpinion;

    private String ext1;

    private String ext2;

    private String ext3;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname == null ? null : sname.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getClassBehavior() {
        return classBehavior;
    }

    public void setClassBehavior(String classBehavior) {
        this.classBehavior = classBehavior == null ? null : classBehavior.trim();
    }

    public String getTotalRemark() {
        return totalRemark;
    }

    public void setTotalRemark(String totalRemark) {
        this.totalRemark = totalRemark == null ? null : totalRemark.trim();
    }

    public String getTeacherOpinion() {
        return teacherOpinion;
    }

    public void setTeacherOpinion(String teacherOpinion) {
        this.teacherOpinion = teacherOpinion == null ? null : teacherOpinion.trim();
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