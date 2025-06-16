package org.pauldenhez.accountme.common.model.transaction.vo;

import org.pauldenhez.accountme.common.model.transaction.vo.PositiveAmount;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class DoubleToPositiveAmountConverter implements Converter<PositiveAmount, Double> {

    @Override
    public Double convert(PositiveAmount source) {
        return source.getValue();
    }
}
