package org.pauldenhez.accountme.common.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.pauldenhez.accountme.common.model.transaction.vo.PositiveAmount;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

@Document(indexName = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    private String id;

    @Field(type = FieldType.Date)
    private Date date;

    @Field(type = FieldType.Keyword)
    private TransactionMethod method;

    @Field(type = FieldType.Keyword)
    private String comment;

    @Field(type = FieldType.Text)
    private String label;

    @Field(type = FieldType.Text)
    private String fullComment;

    @Field(type = FieldType.Keyword)
    private TransactionType type;

    @Field(type = FieldType.Double)
    private PositiveAmount amount;

    @Field(type = FieldType.Object)
    private AdditionalInformation additionalInformation;

    @Field(type = FieldType.Keyword)
    private List<String> tags;
}
