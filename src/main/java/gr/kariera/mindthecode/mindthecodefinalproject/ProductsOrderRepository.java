package gr.kariera.mindthecode.mindthecodefinalproject;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface ProductsOrderRepository extends CrudRepository<ProductsOrder, Long> {
    public List<ProductsOrder> findAllByCreationDateTimeBetween(Date publicationTimeStart, Date publicationTimeEnd);

}
