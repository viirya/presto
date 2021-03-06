/*
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
package com.facebook.presto.byteCode.expression;

import com.facebook.presto.byteCode.Block;
import com.facebook.presto.byteCode.ByteCodeNode;
import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.facebook.presto.byteCode.ParameterizedType.type;
import static com.google.common.base.Preconditions.checkNotNull;

class PopByteCodeExpression
        extends ByteCodeExpression
{
    private final ByteCodeExpression instance;

    PopByteCodeExpression(ByteCodeExpression instance)
    {
        super(type(void.class));
        this.instance = checkNotNull(instance, "instance is null");
    }

    @Override
    public ByteCodeNode getByteCode()
    {
        return new Block(null)
                .append(instance.getByteCode())
                .pop(instance.getType());
    }

    @Override
    protected String formatOneLine()
    {
        return instance.toString();
    }

    @Override
    public List<ByteCodeNode> getChildNodes()
    {
        return ImmutableList.<ByteCodeNode>of(instance);
    }
}
