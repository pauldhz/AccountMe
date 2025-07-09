package org.pauldenhez.accountme.common.model.transaction.mapper;

import org.pauldenhez.accountme.common.model.transaction.dto.TransactionDTO;
import org.pauldenhez.accountme.common.model.transaction.vo.PositiveAmount;

public class TransactionMapper {

    public static TransactionDTO toDto(org.pauldenhez.accountme.common.model.transaction.Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        return new TransactionDTO(
                transaction.getId(),
                transaction.getDate(),
                transaction.getMethod(),
                transaction.getComment(),
                transaction.getLabel(),
                transaction.getFullComment(),
                transaction.getType(),
                transaction.getAmount().getValue(),
                transaction.getAdditionalInformation(),
                transaction.getTags());
    }

    public static org.pauldenhez.accountme.common.model.transaction.Transaction fromDto(TransactionDTO dto) {
        if (dto == null) {
            return null;
        }

        return new org.pauldenhez.accountme.common.model.transaction.Transaction(
                dto.id(),
                dto.date(),
                dto.method(),
                dto.comment(),
                dto.label(),
                dto.fullComment(),
                dto.type(),
                new PositiveAmount(dto.amount()),
                dto.additionalInformation(),
                dto.tags()
        );
    }
}