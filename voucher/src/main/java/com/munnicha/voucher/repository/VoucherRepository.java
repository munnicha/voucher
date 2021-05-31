package com.munnicha.voucher.repository;

import com.munnicha.voucher.model.Voucher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author munnicha
 */
@Repository
public interface VoucherRepository extends CrudRepository<Voucher, Long>{
    
}
