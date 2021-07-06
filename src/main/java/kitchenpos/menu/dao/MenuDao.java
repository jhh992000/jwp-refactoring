package kitchenpos.menu.dao;

import java.util.List;
import kitchenpos.menu.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuDao extends JpaRepository<Menu, Long> {

    long countByIdIn(List<Long> menuIds);
}
