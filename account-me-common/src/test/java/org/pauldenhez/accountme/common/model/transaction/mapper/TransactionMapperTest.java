package org.pauldenhez.accountme.common.model.transaction.mapper;

import org.junit.jupiter.api.Test;
import org.pauldenhez.accountme.common.model.transaction.AdditionalInformation;
import org.pauldenhez.accountme.common.model.transaction.Transaction;
import org.pauldenhez.accountme.common.model.transaction.TransactionMethod;
import org.pauldenhez.accountme.common.model.transaction.TransactionType;
import org.pauldenhez.accountme.common.model.transaction.dto.TransactionResponse;
import org.pauldenhez.accountme.common.model.transaction.vo.PositiveAmount;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionMapperTest {

    @Test
    void shouldMapDtoToEntityCorrectly() {
        // Given
        TransactionResponse dto = new TransactionResponse(
                "txn-001",
                new Date(),
                TransactionMethod.PAYMENT,
                "Comment",
                "Label",
                "Full comment",
                TransactionType.CREDIT,
                123.45,
                new AdditionalInformation("category", "subcategory", true, "Checking Account"),
                List.of("tag1", "tag2"), null, null
        );

        Transaction entity = TransactionMapper.fromDto(dto);

        assertThat(entity).isNotNull();
        assertThat(entity.getId()).isEqualTo(dto.id());
        assertThat(entity.getDate()).isEqualTo(dto.date());
        assertThat(entity.getMethod()).isEqualTo(dto.method());
        assertThat(entity.getComment()).isEqualTo(dto.comment());
        assertThat(entity.getLabel()).isEqualTo(dto.label());
        assertThat(entity.getFullComment()).isEqualTo(dto.fullComment());
        assertThat(entity.getType()).isEqualTo(dto.type());
        assertThat(entity.getAmount()).isNotNull();
        assertThat(entity.getAmount().getValue()).isEqualTo(dto.amount());
        assertThat(entity.getAdditionalInformation()).isEqualTo(dto.additionalInformation());
        assertThat(entity.getTags()).containsAll(dto.tags());
    }

    @Test
    void shouldMapEntityToDtoCorrectly() {
        // Given
        Transaction entity = new Transaction(
                "txn-002",
                new Date(),
                TransactionMethod.PAYMENT,
                "Some comment",
                "Some label",
                "Full transaction comment",
                TransactionType.DEBIT,
                new PositiveAmount(789.01),
                new AdditionalInformation("category", "subcategory", true, "Checking Account"),
                List.of("tagA", "tagB")
        );

        TransactionResponse dto = TransactionMapper.toDto(entity, null, null);

        assertThat(dto).isNotNull();
        assertThat(dto.id()).isEqualTo(entity.getId());
        assertThat(dto.date()).isEqualTo(entity.getDate());
        assertThat(dto.method()).isEqualTo(entity.getMethod());
        assertThat(dto.comment()).isEqualTo(entity.getComment());
        assertThat(dto.label()).isEqualTo(entity.getLabel());
        assertThat(dto.fullComment()).isEqualTo(entity.getFullComment());
        assertThat(dto.type()).isEqualTo(entity.getType());
        assertThat(dto.amount()).isEqualTo(entity.getAmount().getValue());
        assertThat(dto.additionalInformation()).isEqualTo(entity.getAdditionalInformation());
        assertThat(dto.tags()).containsExactlyElementsOf(entity.getTags());
    }
}