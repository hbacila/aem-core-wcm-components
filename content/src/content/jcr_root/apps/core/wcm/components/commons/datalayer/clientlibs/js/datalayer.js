/*******************************************************************************
 * Copyright 2020 Adobe
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
 ******************************************************************************/
(function() {
    "use strict";

    var dataLayer = window.dataLayer = window.dataLayer || [];

    dataLayer.push({
        on: "clicked:listItem",
        handler: function(event) {
            console.log(event)
        }
    });

    dataLayer.push({
        on: "accordion:expanded",
        handler: function(event) {
            console.log(event)
        }
    });

    dataLayer.push({
        on: "accordion:collapsed",
        handler: function(event) {
            console.log(event)
        }
    });

    function addComponentToDataLayer(component) {
        var componentData = getComponentData(component);
        dataLayer.push({
            data: {
                component: getComponentObject(componentData)
            }
        });
    }

    function attachClickEventListener(element) {
        element.addEventListener("click", addClickToDataLayer);
    }

    function getComponentObject(elementData) {
        var component = {};
        component[elementData.type] = {};
        component[elementData.type][generateUniqueID()] = elementData;
        return component;
    }

    function addClickToDataLayer(event) {
        var element = event.currentTarget;
        var elementData = getClickData(element);

        console.log("addClickToDataLayer", elementData);

        dataLayer.push({
            event: 'clicked:' + elementData.type,
            info: elementData
        });
    }

    function generateUniqueID() {
        return Date.now() + "" + Math.trunc(Math.random() * 1000);
    }

    function getComponentData(element) {
        var dataLayerJson = element.dataset.cmpDataLayer;
        return JSON.parse(dataLayerJson);
    }

    function getClickData(element) {
        var clickDataJson = element.dataset.cmpClickable;
        return JSON.parse(clickDataJson);
    }

    function onDocumentReady() {
        var components = document.querySelectorAll("[data-cmp-data-layer]");
        var clickableElements = document.querySelectorAll("[data-cmp-clickable]");

        components.forEach(function (component) {
            addComponentToDataLayer(component)
        });

        clickableElements.forEach(function (element) {
            attachClickEventListener(element)
        });
    }

    if (document.readyState !== "loading") {
        onDocumentReady();
    } else {
        document.addEventListener("DOMContentLoaded", onDocumentReady);
    }

}());
