package org.springframework.test;

import java.text.MessageFormat;

/**
 * 库存校验器
 *
 * @author zhanpeng.jiang@hand-china.com 2023/3/19
 */
public class StockVerifier {
    public static final String FIELD_WAREHOUSE_CODE = "warehouseCode";
    public static final String FIELD_SKU_CODE = "skuCode";

    private String warehouseCode;
    private String skuCode;

    public void verify(String anything) {
        System.out.println(MessageFormat.format("verify: {0}, warehouseCode:{1}, skuCode:{2}", anything, warehouseCode, skuCode));
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
}
