package com.munnicha.voucher.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author munnicha
 */
@Entity
@Table(name = "vouchers")
public class Voucher implements Serializable {
    
    @Id
    @Column(name = "redemption_id")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "redemption_id")
    private Redemption redemption;

    public Voucher() {
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Redemption getRedemption() {
        return redemption;
    }

    public void setRedemption(Redemption redemption) {
        this.redemption = redemption;
    }
    
    public boolean voucherEligible(){
        if(name==null ||
           "".equalsIgnoreCase(name) ||
           redemption==null ||
           !redemption.redemptionEligible()){
            return false;
        }
        return true;
    }
    
}
