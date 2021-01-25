package kitchenpos.dto;

import kitchenpos.domain.table.OrderTableGroup;

import java.util.List;

public class TableGroupResponse {
    private Long id;
    private List<TableResponse> tableResponses;

    public TableGroupResponse(Long id, List<TableResponse> tableResponses) {
        this.id = id;
        this.tableResponses = tableResponses;
    }

    public static TableGroupResponse of(OrderTableGroup orderTableGroup) {
        return new TableGroupResponse(orderTableGroup.getId(), TableResponse.ofList(orderTableGroup.getOrderTables()));
    }

    public Long getId() {
        return id;
    }

    public List<TableResponse> getTableResponses() {
        return tableResponses;
    }
}
