package org.pauldenhez.accountme.common.model.transaction;

public enum TransactionMethod {
    // Virement
    PAYMENT,
    // Carte Bancaire
    BANK_CARD,
    // Prélèvement
    BANK_WITHDRAWAL,
    //Chèque
    CHECK,
    // Remise de chèque
    CHECK_DELIVERY,
    // Retrait d'espèce
    CASH_WITHDRAWAL,
    // Dépot d'expère
    CASH_DEPOSIT
}
