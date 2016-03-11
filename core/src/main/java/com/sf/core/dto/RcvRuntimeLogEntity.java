package com.sf.core.dto;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述:
 * <p/>
 * Created by 828477[JAX] on 2016/3/11 15:46.
 */
@Entity
@Table(name = "tt_rcv_runtime_log", schema = "fvpbasedev", catalog = "")
public class RcvRuntimeLogEntity {
    private long id;
    private String wayBillNo;
    private String opCode;
    private String beanObj;
    private Date barUploadTm;
    private Date barScanTm;
    private Date createTime;
    private String zoneCode;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "wayBillNo")
    public String getWayBillNo() {
        return wayBillNo;
    }

    public void setWayBillNo(String wayBillNo) {
        this.wayBillNo = wayBillNo;
    }

    @Basic
    @Column(name = "opCode")
    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode;
    }

    @Basic
    @Column(name = "beanObj")
    public String getBeanObj() {
        return beanObj;
    }

    public void setBeanObj(String beanObj) {
        this.beanObj = beanObj;
    }

    @Basic
    @Column(name = "barUploadTm")
    public Date getBarUploadTm() {
        return barUploadTm;
    }

    public void setBarUploadTm(Date barUploadTm) {
        this.barUploadTm = barUploadTm;
    }

    @Basic
    @Column(name = "barScanTm")
    public Date getBarScanTm() {
        return barScanTm;
    }

    public void setBarScanTm(Date barScanTm) {
        this.barScanTm = barScanTm;
    }

    @Basic
    @Column(name = "createTime")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "zoneCode")
    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RcvRuntimeLogEntity that = (RcvRuntimeLogEntity) o;

        if (id != that.id) return false;
        if (wayBillNo != null ? !wayBillNo.equals(that.wayBillNo) : that.wayBillNo != null) return false;
        if (opCode != null ? !opCode.equals(that.opCode) : that.opCode != null) return false;
        if (beanObj != null ? !beanObj.equals(that.beanObj) : that.beanObj != null) return false;
        if (barUploadTm != null ? !barUploadTm.equals(that.barUploadTm) : that.barUploadTm != null) return false;
        if (barScanTm != null ? !barScanTm.equals(that.barScanTm) : that.barScanTm != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (zoneCode != null ? !zoneCode.equals(that.zoneCode) : that.zoneCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (wayBillNo != null ? wayBillNo.hashCode() : 0);
        result = 31 * result + (opCode != null ? opCode.hashCode() : 0);
        result = 31 * result + (beanObj != null ? beanObj.hashCode() : 0);
        result = 31 * result + (barUploadTm != null ? barUploadTm.hashCode() : 0);
        result = 31 * result + (barScanTm != null ? barScanTm.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (zoneCode != null ? zoneCode.hashCode() : 0);
        return result;
    }
}
