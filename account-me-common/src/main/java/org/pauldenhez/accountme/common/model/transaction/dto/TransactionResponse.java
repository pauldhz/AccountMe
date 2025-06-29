package org.pauldenhez.accountme.common.model.transaction.dto;

import org.pauldenhez.accountme.common.model.hateoas.Links;
import org.pauldenhez.accountme.common.model.hateoas.Metadata;
import org.pauldenhez.accountme.common.model.transaction.AdditionalInformation;
import org.pauldenhez.accountme.common.model.transaction.TransactionMethod;
import org.pauldenhez.accountme.common.model.transaction.TransactionType;

import java.util.Date;
import java.util.List;

public record TransactionResponse(
        String id,
        Date date,
        TransactionMethod method,
        String comment,
        String label,
        String fullComment,
        TransactionType type,
        double amount,
        AdditionalInformation additionalInformation,
        List<String> tags,
        Metadata metadata,
        Links links
) {
}
