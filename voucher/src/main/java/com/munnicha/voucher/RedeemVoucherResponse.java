package com.munnicha.voucher;

/**
 *
 * @author munnicha
 */
public class RedeemVoucherResponse {
    private Long voucherId;
    private boolean redeemed;
    private String message;

    public RedeemVoucherResponse() {
    }

    public Long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Long voucherId) {
        this.voucherId = voucherId;
    }

    public boolean isRedeemed() {
        return redeemed;
    }

    public void setRedeemed(boolean redeemed) {
        this.redeemed = redeemed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
