package xiyu.unittestforspringboot.DAO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import xiyu.unittestforspringboot.Entity.Item;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ItemRepositoryTest {
    @Autowired
    //@Qualifier("real")
    private ItemRepository itemRepository;
    @Test
    public void findAllShouldWork(){
        List<Item> items=itemRepository.findAll();
        assertEquals(5,items.size());
    }
}
