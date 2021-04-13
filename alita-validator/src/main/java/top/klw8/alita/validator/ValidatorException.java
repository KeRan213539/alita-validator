/*
 * Copyright 2018-2021, ranke (213539@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.klw8.alita.validator;


/**
 * 表单验证, 业务异常
 * 2018年9月17日 13:07:33
 */
public class ValidatorException extends RuntimeException {

    private static final long serialVersionUID = 5968495544349839830L;

    private String statusCode;

    private String errorMsg;

    private Class<?> methodReturnType;

    public ValidatorException(String statusCode, String errorMsg) {
        super("【" + statusCode + "】" + errorMsg);
        this.statusCode = statusCode;
        this.errorMsg = errorMsg;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setMethodReturnType(Class<?> methodReturnType){
        this.methodReturnType = methodReturnType;
    }

    public Class<?> getMethodReturnType(){
        return this.methodReturnType;
    }

}
