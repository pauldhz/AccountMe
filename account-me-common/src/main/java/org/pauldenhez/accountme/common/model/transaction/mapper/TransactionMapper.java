package org.pauldenhez.accountme.common.model.transaction.mapper;

import org.pauldenhez.accountme.common.model.transaction.Transaction;
import org.pauldenhez.accountme.common.model.hateoas.Links;
import org.pauldenhez.accountme.common.model.hateoas.Metadata;
import org.pauldenhez.accountme.common.model.transaction.dto.TransactionResponse;
import org.pauldenhez.accountme.common.model.transaction.vo.PositiveAmount;

public class TransactionMapper {

    public static TransactionResponse toDto(Transaction transaction, Links links, Metadata metadata) {
        if (transaction == null) {
            return null;
        }

        return new TransactionResponse(
                transaction.getId(),
                transaction.getDate(),
                transaction.getMethod(),
                transaction.getComment(),
                transaction.getLabel(),
                transaction.getFullComment(),
                transaction.getType(),
                transaction.getAmount().getValue(),
                transaction.getAdditionalInformation(),
                transaction.getTags(),
                metadata,
                links);
    }

    public static Transaction fromDto(TransactionResponse dto) {
        if (dto == null) {
            return null;
        }

        return new Transaction(
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