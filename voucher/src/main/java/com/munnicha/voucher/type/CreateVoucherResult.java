package com.munnicha.voucher.type;

import com.munnicha.voucher.model.Voucher;

/**
 *
 * @author munnicha
 */
public class CreateVoucherResult {
    private boolean created;
    private String message;
    private Voucher voucher;

    public CreateVoucherResult() {
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }
    
}
