package com.munnicha.voucher;

import com.munnicha.voucher.type.CreateVoucherResult;
import java.util.List;

/**
 *
 * @author munnicha
 */
public class CreateVoucherResponse {
    
    private String requestId;
    List<CreateVoucherResult> voucherResponses;

    public CreateVoucherResponse() {
        
    }

    public CreateVoucherResponse(String requestId, List<CreateVoucherResult> voucherResponses) {
        this.requestId = requestId;
        this.voucherResponses = voucherResponses;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public List<CreateVoucherResult> getVoucherResponses() {
        return voucherResponses;
    }

    public void setVoucherResponses(List<CreateVoucherResult> voucherResponses) {
        this.voucherResponses = voucherResponses;
    }

}
