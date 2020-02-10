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
package com.adobe.cq.wcm.core.components.internal.models.v1;

import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.jetbrains.annotations.NotNull;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.adobe.cq.wcm.core.components.internal.Utils;
import com.adobe.cq.wcm.core.components.models.Button;
import com.day.cq.commons.jcr.JcrConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(
    adaptables = SlingHttpServletRequest.class,
    adapters = {Button.class, ComponentExporter.class},
    resourceType = ButtonImpl.RESOURCE_TYPE
)
@Exporter(
    name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
    extensions = ExporterConstants.SLING_MODEL_EXTENSION
)
public class ButtonImpl implements Button {

    public static final String RESOURCE_TYPE = "core/wcm/components/button/v1/button";

    private String linkURL;

    @Self
    private SlingHttpServletRequest request;

    @ScriptVariable
    private PageManager pageManager;

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    @Named(JcrConstants.JCR_TITLE)
    private String text;

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String link;

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    private String icon;

    @ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
    protected String accessibilityLabel;

    @Override
    public String getText() {
        return text;
    }

    @Override
    public String getLink() {
        if (linkURL == null) {
            Page targetPage = pageManager.getPage(link);
            if (targetPage != null) {
                linkURL = Utils.getURL(request, targetPage);
            } else {
                linkURL = link;
            }
        }
        return linkURL;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    @Override
    public String getAccessibilityLabel() {
        return accessibilityLabel;
    }

    @NotNull
    @Override
    public String getExportedType() {
        return request.getResource().getResourceType();
    }

    @Override
    public String getDataLayerJson() {
        JsonObjectBuilder data = Json.createObjectBuilder();
        data.add("id", request.getResource().getPath());
        data.add("type", "button");
        data.add("text", getText());

        if (getLink() != null)
            data.add("linkUrl", getLink());

        return  data.build().toString();
    }
}
