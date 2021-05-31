package com.munnicha.voucher.service;

import com.munnicha.voucher.type.CreateVoucherResult;
import com.munnicha.voucher.RedeemVoucherResponse;
import com.munnicha.voucher.model.Voucher;
import java.util.List;

/**
 *
 * @author munnicha
 */
public abstract class VoucherManagerService {
    
    public abstract List<CreateVoucherResult> createVouchers(List<Voucher> vouchers);
    
    public abstract RedeemVoucherResponse redeemVoucher(Long voucherId);
    
}
