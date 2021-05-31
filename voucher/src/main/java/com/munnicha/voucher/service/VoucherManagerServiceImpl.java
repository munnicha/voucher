package com.munnicha.voucher.service;

import com.munnicha.voucher.type.CreateVoucherResult;
import com.munnicha.voucher.RedeemVoucherResponse;
import com.munnicha.voucher.exception.InvalidVoucherException;
import com.munnicha.voucher.model.Redemption;
import com.munnicha.voucher.model.Voucher;
import com.munnicha.voucher.repository.RedemptionRepository;
import com.munnicha.voucher.repository.VoucherRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author munnicha
 */
@Service
public class VoucherManagerServiceImpl extends VoucherManagerService{

    private static final Logger LOG = Logger.getLogger(VoucherManagerServiceImpl.class.getName());

    @Autowired
    VoucherRepository voucherRepo;
    
    @Autowired
    RedemptionRepository redRepo;

    @Override
    public List<CreateVoucherResult> createVouchers(List<Voucher> vouchers) {
        List<CreateVoucherResult> response = new ArrayList<>();
        for(Voucher v: vouchers){
            CreateVoucherResult voucherResponse=new CreateVoucherResult();
            if(v.voucherEligible()){
                try{
                    Voucher createdVoucher=voucherRepo.save(v);
                    voucherResponse.setCreated(true);
                    voucherResponse.setMessage("Voucher has been sucesfully created.");
                    voucherResponse.setVoucher(createdVoucher);
                    response.add(voucherResponse);
                }catch (Exception e){
                    LOG.log(Level.SEVERE, "Unexpected error during voucher creation: {0}", new Object[]{e.getMessage()});
                    voucherResponse.setCreated(false);
                    voucherResponse.setMessage("Unexpected error during voucher creation.");
                    voucherResponse.setVoucher(v);
                    response.add(voucherResponse);
                }
            }else{
                voucherResponse.setCreated(false);
                voucherResponse.setMessage("Voucher is invalid.");
                voucherResponse.setVoucher(v);
                response.add(voucherResponse);
            }
        }
        return response;
    }
    
    @Override
    public RedeemVoucherResponse redeemVoucher(Long voucherId) {
        RedeemVoucherResponse response = new RedeemVoucherResponse();
        response.setVoucherId(voucherId);
        try {
            Optional<Redemption> redOpt = redRepo.findById(voucherId);
            Redemption red;
            if(redOpt.isPresent()){
                red=redOpt.get();
                red=red.redeem();
                redRepo.save(red);
                response.setRedeemed(true);
                response.setMessage("Successfully redeemed the vocuher.");
            }else{
                response.setRedeemed(false);
                response.setMessage("The voucher does not exist.");
            }
        } catch (InvalidVoucherException ex) {
            response.setRedeemed(false);
            response.setMessage("The voucher is invalid.");
            return response;
        } catch (Exception e){
            LOG.log(Level.SEVERE, "Unexpected error while processing voucher: {0}:\n{1}", new Object[]{voucherId, e.getMessage()});
            response.setRedeemed(false);
            response.setMessage("Could not redeem the voucher.");
            return response;
        }
        
        return response;
    }
    
}
