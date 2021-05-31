package com.munnicha.voucher;

import com.munnicha.voucher.model.Voucher;
import java.util.List;

/**
 *
 * @author munnicha
 */
public class CreateVoucherRequest {
    
    private String requestId;
    private List<Voucher> vouchers;

    public CreateVoucherRequest(String requestId, List<Voucher> vouchers) {
        this.requestId = requestId;
        this.vouchers = vouchers;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<Voucher> getVouchers() {
        return vouchers;
    }

    public void setVouchers(List<Voucher> vouchers) {
        this.vouchers = vouchers;
    }

}
