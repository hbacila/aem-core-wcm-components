/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2020 Adobe
 ~
 ~ Licensed under the Apache License, Version 2.0 (the "License");
 ~ you may not use this file except in compliance with the License.
 ~ You may obtain a copy of the License at
 ~
 ~     http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ Unless required by applicable law or agreed to in writing, software
 ~ distributed under the License is distributed on an "AS IS" BASIS,
 ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ~ See the License for the specific language governing permissions and
 ~ limitations under the License.
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
package com.adobe.cq.wcm.core.components.internal.models.v1;

import com.adobe.cq.wcm.core.components.internal.DataLayerFactory;
import com.adobe.cq.wcm.core.components.models.DataLayerProvider;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.json.JsonObject;
import java.util.Map;

public abstract class AbstractDataLayerProvider implements DataLayerProvider {

    @Override
    public boolean isDataLayerEnabled() {
        return true;
    }

    @Override
    public Map<String, Object> getDataLayerJson() {
        if (isDataLayerEnabled()) {
            return DataLayerFactory.build(this);
        }
        throw new UnsupportedOperationException();
    }

    @Override
    @JsonIgnore
    public String getDataLayerString() {
        return getDataLayerJson().toString();
    }
}
