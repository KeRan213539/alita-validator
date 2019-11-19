package top.klw8.alita.validator.annotations.impl;

import top.klw8.alita.utils.DateTimeUtil;
import top.klw8.alita.utils.LocalDateTimeUtil;
import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.DateRange;

import java.lang.annotation.Annotation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xp
 * @ClassName: DateRangeImpl
 * @Description: 日期格式/时间范围验证器，支持Date/LocalDate/LocalDateTime
 * @date 2019/11/19 9:18
 */
@ValidatorImpl(validator = DateRange.class)
public class DateRangeImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
        DateRange dr = (DateRange) annotation;
        Integer before = dr.beforeNowDays();
        Integer after = dr.afterNowDays();
        if (before < 0 || after < 0) {
            throw new ValidatorException(dr.responseStatusCode(), "起始结束天，范围不合理");
        }
        if (!LocalDateTimeUtil.isDateWithinDaysRange(object, before, after)) {
            throw new ValidatorException(dr.responseStatusCode(), dr.validatFailMessage());
        }
    }

}