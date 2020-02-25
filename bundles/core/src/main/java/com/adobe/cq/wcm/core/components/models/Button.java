/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ Copyright 2019 Adobe
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

import org.jetbrains.annotations.NotNull;
import org.osgi.annotation.versioning.ConsumerType;

import com.adobe.cq.export.json.ComponentExporter;

/**
 * Defines the {@code Button} Sling Model used for the {@code /apps/core/wcm/components/button} component.
 *
 * @since com.adobe.cq.wcm.core.components.models 12.8.0
 */
@ConsumerType
public interface Button extends ComponentExporter, DataLayerProvider {

    /**
     * Returns the button text.
     *
     * @return the button text
     * @since com.adobe.cq.wcm.core.components.models 12.8.0
     */
    default String getText() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the button link.
     *
     * @return the button link
     * @since com.adobe.cq.wcm.core.components.models 12.8.0
     */
    default String getLink() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the button icon identifier.
     *
     * @return the button icon identifier
     * @since com.adobe.cq.wcm.core.components.models 12.8.0
     */
    default String getIcon() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns an accessibility label for the button.
     *
     * @return an accessibility label for the button
     * @since com.adobe.cq.wcm.core.components.models 12.9.0
     */
    default String getAccessibilityLabel() {
        throw new UnsupportedOperationException();
    }

    /**
     * @see ComponentExporter#getExportedType()
     * @since com.adobe.cq.wcm.core.components.models 12.8.0
     */
    @NotNull
    @Override
    default String getExportedType() {
        throw new UnsupportedOperationException();
    }

}
