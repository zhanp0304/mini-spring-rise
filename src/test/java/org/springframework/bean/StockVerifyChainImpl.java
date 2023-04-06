package org.springframework.bean;

/**
 * 库存校验链
 *
 * @author zhanpeng.jiang@hand-china.com 2023/4/6
 */
public class StockVerifyChainImpl {

    private StockVerifier stockVerifier;

    public void verify(String anything) {
        stockVerifier.verify(anything);
    }

    public StockVerifier getStockVerifier() {
        return stockVerifier;
    }

    public void setStockVerifier(StockVerifier stockVerifier) {
        this.stockVerifier = stockVerifier;
    }
}
