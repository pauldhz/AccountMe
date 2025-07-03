package org.pauldenhez.accountme.common.model.transaction.dto;

import org.pauldenhez.accountme.common.model.hateoas.Links;
import org.pauldenhez.accountme.common.model.hateoas.Metadata;

import java.util.List;

public record TransactionResponse(List<TransactionDTO> transactions, Links links, Metadata metadata) {}
