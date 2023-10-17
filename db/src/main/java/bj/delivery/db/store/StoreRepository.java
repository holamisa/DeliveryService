package bj.delivery.db.store;

import bj.delivery.db.store.enums.StoreCategory;
import bj.delivery.db.store.enums.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {

    // select * from store where id = ? and status = ? order by id desc limit 1
    Optional<StoreEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus status);

    // select * from store where status = ? order by id desc
    List<StoreEntity> findAllByStatusOrderByIdDesc(StoreStatus status);

    // select * from store where category = ? and status = ? order by id desc
    List<StoreEntity> findAllByCategoryAndStatusOrderByIdDesc(StoreCategory category, StoreStatus status);
}
