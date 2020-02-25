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
package com.adobe.cq.wcm.core.components.models;

import com.adobe.cq.wcm.core.components.internal.DataLayerFactory;
import org.apache.sling.api.resource.Resource;
import org.osgi.annotation.versioning.ConsumerType;

@ConsumerType
public interface DataLayerProvider {

    default boolean isDataLayerEnabled() {
        return true;
    }

    default Resource getAssetResource() {
        return null;
    }

    default String getDataLayerId() {
        return null;
    }

    default String getDataLayerType() {
        return null;
    }

    default String getDataLayerSrc() {
        return null;
    }

    default String getDataLayerName() {
        return null;
    }

    default String getDataLayerTitle() {
        return null;
    }

    default String getDataLayerText() {
        return null;
    }

    default String getDataLayerTags() {
        return null;
    }

    default String getDataLayerLinkUrl() {
        return null;
    }

    default String getDataLayerTemplate() {
        return null;
    }

    default String getDataLayerLanguage() {
        return null;
    }

    default String[] getDataLayerExpandedItems() {
        return null;
    }

    default String getDataLayerActiveItem() {
        return null;
    }

    default int getDataLayerItemsCount() {
        return -1;
    }

    default String getDataLayerJson() {
        if (isDataLayerEnabled()) {
            return DataLayerFactory.build(this);
        }
        throw new UnsupportedOperationException();
    }
}
