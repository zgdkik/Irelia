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
package cn.fanhub.irelia.server.handler.limit;

import cn.fanhub.irelia.core.handler.AbstractPreHandler;
import cn.fanhub.irelia.core.model.LimitConfig;
import io.netty.channel.ChannelHandlerContext;

/**
 *
 * @author chengfan
 * @version $Id: AbstractLimitHandler.java, v 0.1 2018年05月13日 下午1:39 chengfan Exp $
 */
public abstract class AbstractLimitHandler extends AbstractPreHandler {

    @Override
    public final void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public int order() {
        return 110;
    }

    abstract boolean shouldLimit(LimitConfig limitConfig);
}