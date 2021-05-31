package com.munnicha.voucher.health;

import com.munnicha.voucher.repository.RedemptionRepository;
import com.munnicha.voucher.repository.VoucherRepository;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 *
 * @author munnicha
 * API Documentation: http://{host}/{context-path}/actuator/health
 */
@Component("database")
public class DbHealthIndicator implements HealthIndicator {
    
    private static final Logger LOG = Logger.getLogger(DbHealthIndicator.class.getName());

    @Autowired
    VoucherRepository voucherRepo;
    
    @Autowired
    RedemptionRepository redRepo;
    
    @Override
    public Health health() {
        
        try{
            long countVoucher=voucherRepo.count();
            long countRedemption=redRepo.count();
            if(countVoucher==countRedemption){
                return Health.up().withDetail("connection","ok").withDetail("countVoucher", countVoucher).withDetail("countRedemption", countRedemption).build();
            }else{
                return Health.up().withDetail("connection","ok").withDetail("warning", "counts should match").withDetail("countVoucher", countVoucher).withDetail("countRedemption", countRedemption).build();
            }
        }catch (Exception e){
            LOG.log(Level.SEVERE, "There is an issue with the database: {0}", e.getMessage());
            return Health.down().withDetail("error", e.getMessage()).build();
        }
    }
    
}
