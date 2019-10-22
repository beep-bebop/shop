package org.shop.domain;

import java.io.Serializable;

public class LineItemPK implements Serializable {
    private Integer orderId;
    private Integer lineNum;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getLineNum() {
        return lineNum;
    }

    public void setLineNum(int lineNum) {
        this.lineNum = lineNum;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result + ((this.orderId == null) ? 0 : this.orderId.hashCode());
        result = result + ((this.lineNum == null) ? 0 : this.lineNum.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof LineItemPK){
            LineItemPK lineItemPK = (LineItemPK) obj;
            if(this.orderId == lineItemPK.orderId && this.lineNum == lineItemPK.lineNum){
                return true;
            }
        }
        return false;
    }
}
