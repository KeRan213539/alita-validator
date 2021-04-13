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
 * 返回消息生成器的接口,由于验证器的切面逻辑可以共用,
 * 但是具体使用的项目的返回消息不统一,所以对应具体项目的返回消息需要项目自己实现
 * 2018年9月17日 上午11:16:48
 */
public interface IResponseMsgGenerator {

    Object generatorResponse(String code, String message, ValidatorException ex);
    
}
