package com.nbnfsoft.admin.domain.model;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 19:09 2019-11-25
 */
public class FifoData {

    public FifoData() {
    }

    public FifoData(Double totalQty, Double totalAmount) {
        this.totalAmount = totalAmount;
        this.totalQty = totalQty;
    }

    private Double totalQty;

    private Double totalAmount;

    public Double getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Double totalQty) {
        this.totalQty = totalQty;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
