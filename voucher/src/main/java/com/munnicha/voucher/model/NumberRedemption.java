package com.munnicha.voucher.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author munnicha
 */
@Entity
public class NumberRedemption extends Redemption{
    
    @Column(name = "maxNumberOfRedemptions")
    private int maxNumberOfRedemptions; //if -1, then never expires

    public NumberRedemption() {
        super();
    }

    @Override
    public boolean redemptionEligible() {
        if(maxNumberOfRedemptions==0){ //once should be eligible
            return false;
        }
        if(maxNumberOfRedemptions==-1){ //multiple
            return true;
        }
        if(getNumberOfRedemptions()<maxNumberOfRedemptions){ //N time redemption
            return true;
        }
        return false;
    }

    public int getMaxNumberOfRedemptions() {
        return maxNumberOfRedemptions;
    }

    public void setMaxNumberOfRedemptions(int maxNumberOfRedemptions) {
        this.maxNumberOfRedemptions = maxNumberOfRedemptions;
    }
    
    
    
}
