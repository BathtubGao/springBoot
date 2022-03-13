package com.bathtub.service;

import com.bathtub.entity.InventoryMainInfo;
import com.bathtub.util.CallHttpUtil;
import com.bathtub.util.CallHttpUtil.HttpResult;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.http.client.protocol.HttpClientContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class InventoryMainInfoServiceImpl implements InventoryMainInfoService{

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier("eighthJdbcTemplate")
    private JdbcTemplate eighthJdbcTemplate;

    @Override
    @Transient
    public int[] batchInsert(String warehouseCode, final List<InventoryMainInfo> list) {
        // 获取分库分表
        CallHttpUtil callHttpUtil = new CallHttpUtil();
        Map<String, Object> dbResult =  callHttpUtil.getDbTableIndex(warehouseCode, 256);
        long dbIndex = MapUtils.getLongValue(dbResult, "dbIndex");
        String sql = "INSERT INTO INVENTORY_MAIN_INFO_" + dbResult.get("tableIndex") +
                " (warehouse_code, storehouse_code, zone_code, location_code, " +
                "owner_code, sku_code, business_mode, sku_prop, product_date, due_date, total_quantity, " +
                "lock_quantity, occupy_quantity, check_quantity, gmt_create, gmt_modified) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), now())";


        return eighthJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, list.get(i).getWarehouseCode());
                ps.setString(2, list.get(i).getStorehouseCode());
                ps.setString(3, list.get(i).getZoneCode());
                ps.setString(4, list.get(i).getLocationCode());
                ps.setString(5, list.get(i).getOwnerCode());
                ps.setString(6, list.get(i).getSkuCode());
                ps.setInt(7, list.get(i).getBusinessMode());
                ps.setInt(8, list.get(i).getSkuProp());
                ps.setString(9, list.get(i).getProductDate());
                ps.setString(10, list.get(i).getDueDate());
                ps.setLong(11, list.get(i).getTotalQuantity());
                ps.setLong(12, list.get(i).getLockQuantity());
                ps.setLong(13, list.get(i).getOccupyQuantity());
                ps.setLong(14, list.get(i).getCheckQuantity());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }

    @Override
    @Transient
    public void autoInsertInventory() {
//        String[] warehouseCodeS = new String[] {
//                "70E0"
//        };

        CallHttpUtil callHttpUtil = new CallHttpUtil();
        Map<String, Object> dbResult =  callHttpUtil.getDbTableIndex("9061", 256);

        // 查询前场仓位
        String sql = "SELECT l.location_code FROM base_location_"+ dbResult.get("tableIndex") +" l " +
                "LEFT JOIN base_zone z ON  l.zone_id = z.id " +
                "LEFT JOIN base_warehouse w ON z.warehouse_id = w.id AND l.warehouse_id = w.id " +
                "WHERE z.storeage_type = 'QC' AND w.warehouse_code = ?";
        List<Map<String, Object>> results = primaryJdbcTemplate.queryForList(sql, "9061");
        logger.info("结果：{}", results);

        // 查询库存记录

    }

}
