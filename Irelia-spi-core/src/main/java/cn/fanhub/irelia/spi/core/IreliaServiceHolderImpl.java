/**
 *    Copyright 2018 chengfan(fanhub.cn)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.fanhub.irelia.spi.core;

import cn.fanhub.irelia.core.model.IreliaBean;
import cn.fanhub.irelia.core.IreliaServiceHolder;
import cn.fanhub.irelia.core.model.MethodInfo;
import cn.fanhub.irelia.spi.core.annotation.Rpc;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Method;
import java.util.Map;

/**
 *
 * @author chengfan
 * @version $Id: IreliaServiceHolderImpl.java, v 0.1 2018年04月25日 下午10:40 chengfan Exp $
 */
public class IreliaServiceHolderImpl implements IreliaServiceHolder {

    private final Map<String, IreliaBean> IreliaBeansMap = Maps.newConcurrentMap();

    public void loadRpc(Object rpcBean) {
        Method[] methods = MethodUtils.getMethodsWithAnnotation(rpcBean.getClass(), Rpc.class);

        for (Method method : methods) {
            Rpc annotation = method.getAnnotation(Rpc.class);

            Class<?>[] parameterTypes = method.getParameterTypes();
            String[] paramNames = new String[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                paramNames[i] = parameterTypes[i].getName();
            }
            MethodInfo methodInfo = MethodInfo
                    .builder()
                    .paramTypes(parameterTypes)
                    .paramNames(paramNames)
                    .returnType(method.getReturnType())
                    .build();

            IreliaBean ireliaBean = IreliaBean
                    .builder()
                    .rpc(annotation.value())
                    .rpcName(annotation.name())
                    .des(annotation.desc())
                    .methodInfo(methodInfo)
                    .build();

            IreliaBeansMap.put(annotation.value(), ireliaBean);
        }

    }

    public IreliaBean getIreliaBean(String rpcValue) {
        return IreliaBeansMap.get(rpcValue);
    }

}