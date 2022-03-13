package com.bathtub.controller;

import com.bathtub.entity.InventoryMainInfo;
import com.bathtub.service.InventoryMainInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/jdbc")
public class JdbcController {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private InventoryMainInfoService inventoryMainInfoService;

    @RequestMapping("/insertInventory")
    public String insertInventory() {
        logger.info("开始执行");
        String[] skuCodeS = new String[] {"000000000000000001", "000000000000000002", "000000000000000003", "000000000000000004",
                "000000000000000005", "000000000000000006", "000000000000000007", "000000000000000008", "000000000000000009",
                "000000000000000010", "000000000000000011", "000000000000000012", "000000000000000013", "000000000000000014",
                "000000000000000015", "000000000000000016", "000000000000000017", "000000000000000018", "000000000000000019",
                "000000000000000020", "000000000000000021", "000000000000000022", "000000000000000023", "000000000000000024",
                "000000000000000025", "000000000000000026", "000000000000000027", "000000000000000028", "000000000000000029"
        };

        String[] ownerCodeS = new String[] {"R5400", "R5401", "R5402", "R5403", "R5404", "R5405", "R5406", "R5407", "R5408", "R5409",
                "R5410", "R5411", "R5412", "R5413", "R5414", "R5415", "R5416", "R5417", "R5418", "R5419", "R5420", "R5421", "R5422",
                "R5423", "R5424", "R5425"
        };

        String[] locationCodeS = new String[] {"TSET-001-0101", "TSET-001-0102", "TSET-001-0103", "TSET-001-0104", "TSET-001-0105",
                "TSET-001-0106", "TSET-001-0107", "TSET-001-0108", "TSET-001-0109", "TSET-001-0201", "TSET-001-0202", "TSET-001-0203",
                "TSET-001-0204", "TSET-001-0205", "TSET-001-0206", "TSET-001-0207", "TSET-001-0208", "TSET-001-0209"
        };

        String[] warehouseCodeS = new String[] {"891U", "7610", "7619", "9345", "C165", "C161", "1200", "1237", "1201", "C170", "C171", "CK1",
        "CK2", "CK3", "C173", "1111", "C180", "CK5", "910A", "1102", "1103", "SP01", "7612", "7522", "4334", "4335", "4336", "2221", "2223",
        "2224", "2225", "7613", "910U", "910V", "222C", "L040", "70E0", "9342", "7010", "70E1"};

        InventoryMainInfo info;
        for(String warehouseCode : warehouseCodeS) {
            logger.info("开始插入门店{}", warehouseCode);
            List<InventoryMainInfo> inserList = new ArrayList<InventoryMainInfo>();
            for(int i = 0;i<3000; i++) {
                info = new InventoryMainInfo();
                info.setWarehouseCode(warehouseCode);
                info.setStorehouseCode("1");
                info.setZoneCode("TEST");
                info.setLocationCode(locationCodeS[(int)(Math.random()*17)]);
                info.setOwnerCode(ownerCodeS[(int)(Math.random()*24)]);
                info.setSkuCode(skuCodeS[(int)(Math.random()*28)]);
                info.setBusinessMode(0);
                info.setSkuProp(0);
                info.setProductDate("2019-01-01");
                info.setDueDate("2019-12-01");
                info.setTotalQuantity((long)(Math.random()*5000));
                info.setLockQuantity(0L);
                info.setOccupyQuantity(0L);
                info.setCheckQuantity(0L);
                inserList.add(info);
            }
            inventoryMainInfoService.batchInsert(warehouseCode, inserList);
            logger.info("门店{}数据插入完成", warehouseCode);
        }
        logger.info("执行完成");
        return "SUCCESS";
    }

    @RequestMapping("/autoInsertInventory")
    public String autoInsertInventory() {

        inventoryMainInfoService.autoInsertInventory();
        return "SUCCESS";
    }
}
