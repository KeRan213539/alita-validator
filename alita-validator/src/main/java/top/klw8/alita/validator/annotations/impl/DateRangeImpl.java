package top.klw8.alita.validator.annotations.impl;

import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.DateRange;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        if (!isDateWithinDaysRange(object, before, after)) {
            throw new ValidatorException(dr.responseStatusCode(), dr.validatFailMessage());
        }
    }
    
    /**
     * @author xp
     * @Description: 判断日期是否在范围内，以当前日期为参照往前多少天，往后多少天<BR/>支持Date,LocalDate,LocalDateTime
     * @Date 2019/11/19 14:12
     * @param: object (Date|LocalDate|LocalDateTime)
     * @param: before
     * @param: after
     * @return boolean
     */
    public boolean isDateWithinDaysRange(Object object, Integer before, Integer after) {
        if(object instanceof Date){
            Date targetDate=(Date)object;
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE,-before);
            Date beginDate=calendar.getTime();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE,after);
            Date endDate=calendar.getTime();
            return isDateWithinRange(targetDate,beginDate,endDate);
        }else if(object instanceof LocalDate){
            LocalDate targetDate=(LocalDate) object;
            LocalDate beginDate=LocalDate.now().plusDays(-before);
            LocalDate endDate=LocalDate.now().plusDays(after);
            return isLocalDateWithinRange(targetDate,beginDate,endDate);
        }else if(object instanceof LocalDateTime){
            LocalDateTime targetDate=(LocalDateTime) object;
            LocalDateTime beginDate=LocalDateTime.now().plusDays(-before);
            LocalDateTime endDate=LocalDateTime.now().plusDays(after);
            return isLocalDateTimeWithinRange(targetDate,beginDate,endDate);
        }else{
            return false;
        }
    }
    
    /**
     * @author xp
     * @Description: 判断日期是否在指定日期范围之内
     * @Date 2019/11/19 10:12
     * @param: date
     * @param: beginDate
     * @param: endDate
     * @return boolean
     */
    public boolean isDateWithinRange(Date date,Date beginDate,Date endDate){
        if (date.after(beginDate) && date.before(endDate)) {
            return true;
        }else if(date.compareTo(beginDate)==0 || date.compareTo(endDate) == 0 ){
            return true;
        }else {
            return false;
        }
    }
    
    /**
     * @author xp
     * @Description: 判断LocalDate是否在指定日期范围之内
     * @Date 2019/11/19 10:32
     * @param: date
     * @param: beginDate
     * @param: endDate
     * @return boolean
     */
    public static boolean isLocalDateWithinRange(LocalDate date,LocalDate beginDate,LocalDate endDate){
        if (date.isAfter(beginDate) && date.isBefore(endDate)) {
            return true;
        }else if(date.isEqual(beginDate) || date.isEqual(endDate)){
            return true;
        }else {
            return false;
        }
    }
    
    /**
     * @author xp
     * @Description: 判断LocalDateTime是否在指定日期范围之内
     * @Date 2019/11/19 10:50
     * @param: targetDate
     * @param: beginDate
     * @param: endDate
     * @return boolean
     */
    private boolean isLocalDateTimeWithinRange(LocalDateTime targetDate, LocalDateTime beginDate, LocalDateTime endDate) {
        if (targetDate.isAfter(beginDate) && targetDate.isBefore(endDate)) {
            return true;
        }else if(targetDate.isEqual(beginDate) || targetDate.isEqual(endDate)){
            return true;
        }else {
            return false;
        }
    }

}