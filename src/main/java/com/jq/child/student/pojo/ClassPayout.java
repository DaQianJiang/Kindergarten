package com.jq.child.student.pojo;

import java.util.Date;

public class ClassPayout {
    private Integer payoutId;

    private String classNum;

    private String outType;

    private Integer outNum;

    private Date optTime;

    private String optParent;

    private String outInvoice;

    private String outDetail;

    private String ext1;

    private String ext2;

    private String ext3;

    public Integer getPayoutId() {
        return payoutId;
    }

    public void setPayoutId(Integer payoutId) {
        this.payoutId = payoutId;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum == null ? null : classNum.trim();
    }

    public String getOutType() {
        return outType;
    }

    public void setOutType(String outType) {
        this.outType = outType == null ? null : outType.trim();
    }

    public Integer getOutNum() {
        return outNum;
    }

    public void setOutNum(Integer outNum) {
        this.outNum = outNum;
    }

    public Date getOptTime() {
        return optTime;
    }

    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public String getOptParent() {
        return optParent;
    }

    public void setOptParent(String optParent) {
        this.optParent = optParent == null ? null : optParent.trim();
    }

    public String getOutInvoice() {
        return outInvoice;
    }

    public void setOutInvoice(String outInvoice) {
        this.outInvoice = outInvoice == null ? null : outInvoice.trim();
    }

    public String getOutDetail() {
        return outDetail;
    }

    public void setOutDetail(String outDetail) {
        this.outDetail = outDetail == null ? null : outDetail.trim();
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