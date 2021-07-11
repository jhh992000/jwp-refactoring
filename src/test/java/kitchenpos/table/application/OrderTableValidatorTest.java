package kitchenpos.table.application;

import static kitchenpos.order.domain.OrderTest.주문_항목_목록;
import static kitchenpos.order.domain.OrderTest.주문테이블;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import kitchenpos.order.domain.Order;
import kitchenpos.order.domain.OrderRepository;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.table.exception.CannotChangeTableEmptyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("주문테이블 유효성검증 테스트")
@ExtendWith(MockitoExtension.class)
public class OrderTableValidatorTest {

    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderTableValidator orderTableValidator;

    @DisplayName("진행중(조리 or 식사)인 경우 빈 테이블로 변경이 불가능하다.")
    @Test
    void validationChangeEmpty() {
        Order 주문 = new Order(1L, 주문테이블.getId(), OrderStatus.COOKING, LocalDateTime.now(), 주문_항목_목록);
        given(orderRepository.findAllByOrderTableId(any())).willReturn(new ArrayList<>(Arrays.asList(주문)));

        // When
        assertThatThrownBy(() -> orderTableValidator.validationChangeEmpty(1L))
            .isInstanceOf(CannotChangeTableEmptyException.class);
    }

}