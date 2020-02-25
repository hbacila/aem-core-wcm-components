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
package com.adobe.cq.wcm.core.components.internal;

import com.adobe.cq.wcm.core.components.models.DataLayerProvider;
import com.day.cq.dam.api.Asset;
import org.apache.sling.api.resource.Resource;

import javax.json.*;

public class DataLayerFactory {

    static public String build(DataLayerProvider provider) {
        JsonObjectBuilderWrapper data = new JsonObjectBuilderWrapper();
        data.add("id", provider.getDataLayerId());
        data.add( "type", provider.getDataLayerType());
        data.add( "name", provider.getDataLayerName());
        data.add( "title", provider.getDataLayerTitle());
        data.add( "template", provider.getDataLayerTemplate());
        data.add( "src", provider.getDataLayerSrc());
        data.add( "text", provider.getDataLayerText());
        data.add( "tags", provider.getDataLayerTags());
        data.add( "asset", getAssetMetadata(provider));
        data.add( "linkUrl", provider.getDataLayerLinkUrl());
        data.add( "language", provider.getDataLayerLanguage());
        data.add( "itemsCount", provider.getDataLayerItemsCount());
        data.add( "activeItem", provider.getDataLayerActiveItem());
        data.add( "expandedItems", getExpandedItems(provider));
        return  data.build().toString();
    }

    static private JsonObject getAssetMetadata(DataLayerProvider provider) {
        JsonObject assetMetadataObject = null;
        Resource assetResource = provider.getAssetResource(); //getResource().getResourceResolver().getResource(fileReference);
        if (assetResource != null) {
            Asset asset = assetResource.adaptTo(Asset.class);
            if (asset != null) {
                JsonObjectBuilderWrapper assetMetadata = new JsonObjectBuilderWrapper();
                assetMetadata.add("id", asset.getID());
                assetMetadata.add("name", asset.getName());
                assetMetadata.add("path", asset.getPath());
                assetMetadata.add("type", asset.getMimeType());
                assetMetadata.add("url", "https://"); // aboslute URL
                assetMetadata.add("tags", getAssetTags(asset));
                assetMetadataObject = assetMetadata.build();
            }
        }
        return assetMetadataObject;
    }

    static private JsonObject getAssetTags(Asset asset) {
        JsonObjectBuilder assetTags = Json.createObjectBuilder();
        String tagsValue = asset.getMetadataValueFromJcr("cq:tags");
        if (tagsValue != null) {
            String[] tags = tagsValue.split(",");
            for (String tag : tags) {
                assetTags.add(tag, 1);
            }
        }
        return assetTags.build();
    }

    static private JsonArray getExpandedItems(DataLayerProvider provider) {
        JsonArray obj = null;
        String[] expandedItems = provider.getDataLayerExpandedItems();

        if (expandedItems != null) {
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for (String expandedItem : expandedItems) {
                arrayBuilder.add(expandedItem);
            }
            obj = arrayBuilder.build();
        }
        return obj;
    }

    static class JsonObjectBuilderWrapper {
        private JsonObjectBuilder objectBuilder;

        public JsonObjectBuilderWrapper() {
            objectBuilder = Json.createObjectBuilder();
        }

        void add(String propName, String propValue) {
            if (propValue != null) {
                objectBuilder.add(propName, propValue);
            }
        }

        void add(String propName, JsonObject propValue) {
            if (propValue != null) {
                objectBuilder.add(propName, propValue);
            }
        }

        void add(String propName, JsonArray propValue) {
            if (propValue != null) {
                objectBuilder.add(propName, propValue);
            }
        }

        void add(String propName, int propValue) {
            if (propValue >= 0) {
                objectBuilder.add(propName, propValue);
            }
        }

        JsonObject build() {
            return objectBuilder.build();
        }
    }
}
