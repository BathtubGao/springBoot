package com.bathtub.service;

import com.bathtub.entity.InventoryMainInfo;

import java.util.List;

public interface InventoryMainInfoService {
    int[] batchInsert(String warehouseCode, List<InventoryMainInfo> list);

    void autoInsertInventory();
}
