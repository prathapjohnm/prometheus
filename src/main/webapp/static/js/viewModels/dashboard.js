/**
 * Copyright (c) 2014, 2017, Oracle and/or its affiliates.
 * The Universal Permissive License (UPL), Version 1.0
 */
/*
 * Your dashboard ViewModel code goes here
 */
define(['ojs/ojcore', 'knockout', 'jquery', '../model/SearchResultFactory', '../service/searchService', 'ojs/ojknockout', 'ojs/ojselectcombobox', 'ojs/ojlistview'],
    function (oj, ko, $, resultFactory, searchService) {

        function DashboardViewModel() {
            this.value = ko.observable("");
            this.resultCollection;
            this.results = ko.observable(null);
            this.currentCount = ko.observable(0);
            this.totalCount = ko.observable(0);
            this.responseTime = ko.observable();

            var smQuery = oj.ResponsiveUtils.getFrameworkQuery(oj.ResponsiveUtils.FRAMEWORK_QUERY_KEY.SM_ONLY);
            this.smScreen = oj.ResponsiveKnockoutUtils.createMediaQueryObservable(smQuery);
            // Below are a subset of the ViewModel methods invoked by the ojModule binding
            // Please reference the ojModule jsDoc for additionaly available methods.

            this.refreshOptions = function (optionContext) {
                return searchService.fetchOptions(optionContext);
            };

            this.keywordChanged = function (event, ui) {
                if (ui.option === 'value')
                    if (this.value()[0]) {
                        //trigger search
                        var startTime = new Date().getTime();
                        this.resultCollection = resultFactory.createSearchResultCollection(this.value()[0]);
                        this.results(new oj.CollectionTableDataSource(this.resultCollection));

                        this.resultCollection.on('request', function () {
                            startTime = new Date().getTime();
                        }.bind(this));

                        this.resultCollection.on('ready', function (collection) {
                            this.responseTime(new Date().getTime() - startTime);
                            this.totalCount(collection.length);
                            this.currentCount(collection.offset + collection.lastFetchCount);
                        }.bind(this));
                    }
                    else {
                        this.results(null);
                    }
            }.bind(this);

            this.highlightTerms = function (htmlText) {
                if (this.value()[0]) {
                    var initialText = htmlText;
                    var outputText = "";
                    var keywords = this.value()[0].split(' ');

                    for (var key in keywords) {
                        outputText = "";
                        var keyword = keywords[key];
                        do {
                            var match = initialText.match(new RegExp(keyword, 'i'));
                            if (match) {
                                outputText += initialText.substring(0, match.index) + '<b>' + match[0] + '</b>';
                                initialText = initialText.substring(match.index + keyword.length);
                            } else {
                                //end case no match but there was still content
                                outputText += initialText;
                                initialText = "";
                            }
                        } while (initialText.length > 0);
                        initialText = outputText;
                    }
                    return outputText;
                }
            };

            /**
             * Optional ViewModel method invoked when this ViewModel is about to be
             * used for the View transition.  The application can put data fetch logic
             * here that can return a Promise which will delay the handleAttached function
             * call below until the Promise is resolved.
             * @param {Object} info - An object with the following key-value pairs:
             * @param {Node} info.element - DOM element or where the binding is attached. This may be a 'virtual' element (comment node).
             * @param {Function} info.valueAccessor - The binding's value accessor.
             * @return {Promise|undefined} - If the callback returns a Promise, the next phase (attaching DOM) will be delayed until
             * the promise is resolved
             */
            this.handleActivated = function (info) {
                // Implement if needed
            };

            /**
             * Optional ViewModel method invoked after the View is inserted into the
             * document DOM.  The application can put logic that requires the DOM being
             * attached here.
             * @param {Object} info - An object with the following key-value pairs:
             * @param {Node} info.element - DOM element or where the binding is attached. This may be a 'virtual' element (comment node).
             * @param {Function} info.valueAccessor - The binding's value accessor.
             * @param {boolean} info.fromCache - A boolean indicating whether the module was retrieved from cache.
             */
            this.handleAttached = function (info) {
                // Implement if needed
            };


            /**
             * Optional ViewModel method invoked after the bindings are applied on this View.
             * If the current View is retrieved from cache, the bindings will not be re-applied
             * and this callback will not be invoked.
             * @param {Object} info - An object with the following key-value pairs:
             * @param {Node} info.element - DOM element or where the binding is attached. This may be a 'virtual' element (comment node).
             * @param {Function} info.valueAccessor - The binding's value accessor.
             */
            this.handleBindingsApplied = function (info) {
                // Implement if needed
            };

            /*
             * Optional ViewModel method invoked after the View is removed from the
             * document DOM.
             * @param {Object} info - An object with the following key-value pairs:
             * @param {Node} info.element - DOM element or where the binding is attached. This may be a 'virtual' element (comment node).
             * @param {Function} info.valueAccessor - The binding's value accessor.
             * @param {Array} info.cachedNodes - An Array containing cached nodes for the View if the cache is enabled.
             */
            this.handleDetached = function (info) {
                // Implement if needed
            };
        }

        /*
         * Returns a constructor for the ViewModel so that the ViewModel is constrcuted
         * each time the view is displayed.  Return an instance of the ViewModel if
         * only one instance of the ViewModel is needed.
         */
        return new DashboardViewModel();
    }
);
