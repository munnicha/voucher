package com.munnicha.voucher.repository;

import com.munnicha.voucher.model.Redemption;
import java.util.Optional;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author munnicha
 */
@Repository
public interface RedemptionRepository extends CrudRepository<Redemption, Long>{
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Optional<Redemption> findById(Long redemptionId);
    
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Redemption save(Redemption red);
}
