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

    function init(element) {
        var elementData = getData(element);
        dataLayer.push({
            data: {
                component: getComponentObject(elementData)
            }
        });
        trackClick(element, true); // TODO: need to track click events only for clickable elements
    }

    function trackClick(element, clickable) {
        if (clickable) {
            element.addEventListener("click", addClickToDataLayer);
        }
    }

    function getComponentObject(elementData) {
        var component = {};
        component[elementData.type] = {};
        component[elementData.type][generateUniqueID()] = elementData;
        return component;
    }

    function addClickToDataLayer(event) {
        var element = event.currentTarget;
        var elementData = getData(element);
        dataLayer.push({
            event: elementData.type + " clicked",
            info: {
                id: elementData.id
            }
        });
    }

    function generateUniqueID() {
        return Date.now() + "" + Math.trunc(Math.random() * 1000);
    }

    function getData(element) {
        var dataLayerJson = element.getAttribute("data-cmp-data-layer");
        return JSON.parse(dataLayerJson);
    }

    function onDocumentReady() {
        var elements = document.querySelectorAll("[data-cmp-data-layer]");
        for (var i = 0; i < elements.length; i++) {
            init(elements[i]);
        }
    }

    if (document.readyState !== "loading") {
        onDocumentReady();
    } else {
        document.addEventListener("DOMContentLoaded", onDocumentReady);
    }

}());
