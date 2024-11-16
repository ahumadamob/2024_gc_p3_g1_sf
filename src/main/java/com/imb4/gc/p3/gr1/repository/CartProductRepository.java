package com.imb4.gc.p3.gr1.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.imb4.gc.p3.gr1.entity.CartProduct;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
	Optional<CartProduct> findByCartIdAndProductId(Long cartId, Long productId);
}
