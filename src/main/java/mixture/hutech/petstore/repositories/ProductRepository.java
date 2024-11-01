package mixture.hutech.petstore.repositories;

import hutech.mixture.petstore.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.category.id = :id AND p.isDeleted = false")
    Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.isDeleted = false")
    Page<Product> findAllByDeletedFalse(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category.parent.id = :categoryParentId and p.isDeleted = false and p.category.isDeleted = false ")
    Page<Product> findByCategoryParentId(@Param("categoryParentId") Long categoryParentId, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE " +
            "(:categoryParentId IS NULL OR p.category.parent.id = :categoryParentId) AND " +
            "(:minPrice IS NULL OR p.promotionPrice >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.promotionPrice < :maxPrice)")
    Page<Product> searchByPriceAndCatoParent(
            @Param("categoryParentId") Long categoryParentId,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            Pageable pageable);
    ///////
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) AND p.isDeleted = false AND p.category.isDeleted = false ")
    Page<Product> search(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) ")
    Page<Product> searchForAdmin(@Param("name") String name, Pageable pageable);

    // search auto
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% AND p.isDeleted = false AND p.category.isDeleted = false ")
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryId(Long categoryId);
}
