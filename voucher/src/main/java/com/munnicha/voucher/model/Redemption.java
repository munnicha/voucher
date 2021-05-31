package com.munnicha.voucher.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.munnicha.voucher.exception.InvalidVoucherException;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author munnicha
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
    @Type(value=NumberRedemption.class, name = "number"),
    @Type(value=DateRedemption.class, name = "date")
})
@Entity
@Table(name = "redemption")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Redemption implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @JsonIgnore
    @Column(name="numberOfRedemptions")
    private int numberOfRedemptions=0;
    
    @JsonIgnore
    @OneToOne(mappedBy = "redemption", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Voucher voucher;
    
    public boolean redemptionEligible(){
        return false;
    }
    
    public final Redemption redeem() throws InvalidVoucherException {
        if(!redemptionEligible()){
            throw new InvalidVoucherException("The voucher is not eligible.");
        }
        this.numberOfRedemptions++;
        return this;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public void setVoucher(Voucher voucher) {
        this.voucher = voucher;
    }

    public int getNumberOfRedemptions() {
        return numberOfRedemptions;
    }

}
