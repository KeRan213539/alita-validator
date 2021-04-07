/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.klw8.alita.validator.annotations.impl;

import java.lang.annotation.Annotation;

import top.klw8.alita.validator.IAnnotationsValidator;
import top.klw8.alita.validator.ValidatorException;
import top.klw8.alita.validator.ValidatorImpl;
import top.klw8.alita.validator.annotations.DoubleRange;
import top.klw8.alita.validator.annotations.FloatRange;
import top.klw8.alita.validator.annotations.GeoLatitude;
import top.klw8.alita.validator.annotations.GeoLongitude;
import top.klw8.alita.validator.annotations.IntegerRange;
import top.klw8.alita.validator.annotations.LongRange;
import top.klw8.alita.validator.utils.ValidateUtil;

/**
 * @ClassName: NumberRangeImpl
 * @Description: 数字范围验证器实现
 * @author klw
 * @date 2019年1月26日 下午2:35:51
 */
@ValidatorImpl(validator = {DoubleRange.class, FloatRange.class, IntegerRange.class, LongRange.class, GeoLongitude.class, GeoLatitude.class})
public class NumberRangeImpl implements IAnnotationsValidator {

    @Override
    public void doValidator(Object object, Annotation annotation) throws ValidatorException {
	if(object == null) {
	    // 不为null才验证
	    return;
	}
	if(annotation instanceof GeoLongitude) {
	    GeoLongitude geoLongitude = (GeoLongitude)annotation;
	    if(!ValidateUtil.checkLongitudeRange((Double)object)) {
		throw new ValidatorException(geoLongitude.responseStatusCode(), geoLongitude.validatFailMessage());
	    }
	} else if(annotation instanceof GeoLatitude) {
	    GeoLatitude geoLatitude = (GeoLatitude)annotation;
	    if(!ValidateUtil.checkLatitudeRange((Double)object)) {
		throw new ValidatorException(geoLatitude.responseStatusCode(), geoLatitude.validatFailMessage());
	    }
	} else if(annotation instanceof DoubleRange) {
	    DoubleRange doubleRange = (DoubleRange)annotation;
	    double value = ((Double)object).doubleValue();
	    if (doubleRange.min() > value || doubleRange.max() < value) {
		throw new ValidatorException(doubleRange.responseStatusCode(), doubleRange.validatFailMessage());
	    }
	} else if(annotation instanceof FloatRange) {
	    FloatRange floatRange = (FloatRange)annotation;
	    float value = ((Float)object).floatValue();
	    if (floatRange.min() > value || floatRange.max() < value) {
		throw new ValidatorException(floatRange.responseStatusCode(), floatRange.validatFailMessage());
	    }
	} else if(annotation instanceof IntegerRange) {
	    IntegerRange integerRange = (IntegerRange)annotation;
	    int value = ((Integer)object).intValue();
	    if (integerRange.min() > value || integerRange.max() < value) {
		throw new ValidatorException(integerRange.responseStatusCode(), integerRange.validatFailMessage());
	    }
	} else if(annotation instanceof LongRange) {
	    LongRange longRange = (LongRange)annotation;
	    long value = ((Long)object).longValue();
	    if (longRange.min() > value || longRange.max() < value) {
		throw new ValidatorException(longRange.responseStatusCode(), longRange.validatFailMessage());
	    }
	}
	
    }

}
