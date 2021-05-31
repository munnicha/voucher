package com.munnicha.voucher.model;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.persistence.Column;
import javax.persistence.Entity;


/**
 *
 * @author munnicha
 */
@Entity
public class DateRedemption extends Redemption{
    
    @Column(name = "redemptionByDate")
    private String redemptionByDate;  //use unix format for checking eligibiity

    public DateRedemption() {
        super();
    }

    public String getRedemptionByDate() {
        return redemptionByDate;
    }

    public void setRedemptionByDate(String redemptionByDate) {
        this.redemptionByDate = redemptionByDate;
    }

    @Override
    public boolean redemptionEligible() {
        if(redemptionByDate!=null /*&& getNumberOfRedemptions()==0*/){ //can be redeemed multiple time before max date
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
                ZonedDateTime zonedDateTime = ZonedDateTime.parse(redemptionByDate, formatter);
                long currentUnixTime = System.currentTimeMillis() / 1000L;
                long maxUnixTime = zonedDateTime.toEpochSecond();
                if(maxUnixTime>=currentUnixTime){
                    return true;
                }else{
                    return false;
                }
            }catch(DateTimeParseException e){
                return false;
            }
            
        }
        return false;
    }
    
}
