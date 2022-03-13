package com.bathtub.entity;

import java.util.Date;

public class InventoryMainInfo {
    private Long id;
    private String warehouseCode;
    private String warehouseNo;
    private String storehouseCode;
    private String storehouseName;
    private String zoneName;
    private String zoneCode;
    private String locationCode;
    private String ownerCode;
    private String skuCode;
    private Integer businessMode;
    private Integer skuProp;
    private String skuPropShow;
    private String productDate;
    private String dueDate;

    private Long totalQuantity;

    private Long lockQuantity;
    private Long occupyQuantity;
    private Long checkQuantity; // 质检数量
    private Long realQuantity;  //可用库存
    private Long countQuantity;  //库存总数
    private Date gmtCreate;
    private Date gmtModified;
    private Integer version;
    private String cmmdtyName;
    private String shelfLifePhase; // 保质期阶段
    private String skuDesc;// 商品描述
    private String supplyChainZoneType;// 库存地点

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getWarehouseNo() {
        return warehouseNo;
    }

    public void setWarehouseNo(String warehouseNo) {
        this.warehouseNo = warehouseNo;
    }

    public String getStorehouseCode() {
        return storehouseCode;
    }

    public void setStorehouseCode(String storehouseCode) {
        this.storehouseCode = storehouseCode;
    }

    public String getStorehouseName() {
        return storehouseName;
    }

    public void setStorehouseName(String storehouseName) {
        this.storehouseName = storehouseName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Integer getBusinessMode() {
        return businessMode;
    }

    public void setBusinessMode(Integer businessMode) {
        this.businessMode = businessMode;
    }

    public Integer getSkuProp() {
        return skuProp;
    }

    public void setSkuProp(Integer skuProp) {
        this.skuProp = skuProp;
    }

    public String getSkuPropShow() {
        return skuPropShow;
    }

    public void setSkuPropShow(String skuPropShow) {
        this.skuPropShow = skuPropShow;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Long getLockQuantity() {
        return lockQuantity;
    }

    public void setLockQuantity(Long lockQuantity) {
        this.lockQuantity = lockQuantity;
    }

    public Long getOccupyQuantity() {
        return occupyQuantity;
    }

    public void setOccupyQuantity(Long occupyQuantity) {
        this.occupyQuantity = occupyQuantity;
    }

    public Long getCheckQuantity() {
        return checkQuantity;
    }

    public void setCheckQuantity(Long checkQuantity) {
        this.checkQuantity = checkQuantity;
    }

    public Long getRealQuantity() {
        return realQuantity;
    }

    public void setRealQuantity(Long realQuantity) {
        this.realQuantity = realQuantity;
    }

    public Long getCountQuantity() {
        return countQuantity;
    }

    public void setCountQuantity(Long countQuantity) {
        this.countQuantity = countQuantity;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCmmdtyName() {
        return cmmdtyName;
    }

    public void setCmmdtyName(String cmmdtyName) {
        this.cmmdtyName = cmmdtyName;
    }

    public String getShelfLifePhase() {
        return shelfLifePhase;
    }

    public void setShelfLifePhase(String shelfLifePhase) {
        this.shelfLifePhase = shelfLifePhase;
    }

    public String getSkuDesc() {
        return skuDesc;
    }

    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
    }

    public String getSupplyChainZoneType() {
        return supplyChainZoneType;
    }

    public void setSupplyChainZoneType(String supplyChainZoneType) {
        this.supplyChainZoneType = supplyChainZoneType;
    }
}
