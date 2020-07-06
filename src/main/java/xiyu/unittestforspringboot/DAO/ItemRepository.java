package xiyu.unittestforspringboot.DAO;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import xiyu.unittestforspringboot.Entity.Item;
//@Qualifier("real")
@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item,Long> {
}
