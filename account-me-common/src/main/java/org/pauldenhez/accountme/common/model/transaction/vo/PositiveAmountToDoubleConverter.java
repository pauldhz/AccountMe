package org.pauldenhez.accountme.common.model.transaction.vo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class PositiveAmountToDoubleConverter implements Converter<Double, PositiveAmount> {

    @Override
    public PositiveAmount convert(Double source) {
        return new PositiveAmount(source);
    }
}
