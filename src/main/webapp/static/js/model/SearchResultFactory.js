define(['ojs/ojcore', '../config/ServiceEndpoints'], function (oj, services) {

    function SearchResultsFactory() {
        this.resourceURI = services.SEARCH_SERVICE;

        this.fieldMap = {
            "SearchResultID": "id",
            "ResultTitle": "title",
            "Description": "description",
            "PageURI": "url"
        };

        this.parseSearchResult = function (response) {
            var parsedSearchResult = {};
            for (var field in this.fieldMap) {
                if (this.fieldMap.hasOwnProperty(field)) {
                    parsedSearchResult[field] = response[this.fieldMap[field]]
                }
            }
            return parsedSearchResult;
        }.bind(this);

        this.createSearchResultModel = function () {
            var SearchResult = oj.Model.extend({
                urlRoot: services.SEARCH_SERVICE,
                parse: this.parseSearchResult,
                idAttribute: 'SearchResultID'
            });

            return new SearchResult();
        }.bind(this);

        this.createSearchResultCollection = function (keyword) {

            var customPaginationURLCallback = function (operation, collection, options) {
                var page = Math.floor(options.startIndex / collection.fetchSize + 1);

                return {
                    url: this.resourceURI + '?keyword=' + keyword + '&pageId=' + page
                };
            }.bind(this);

            var customPagingOptionsCallback = function (response) {
            	return {
            		'totalResults': response.searchResultInfo.totalCount,
            	    'limit': this.fetchSize,
            	    'count': response.searchResults.length,
            	    'offset': (response.searchResultInfo.pageId - 1) * this.fetchSize,
            	    'hasMore': response.searchResultInfo.pageId < (response.searchResultInfo.totalCount / this.fetchSize) + 1
            	}
            };

            var SearchResults = oj.Collection.extend({
                url: services.SEARCH_SERVICE + '?keyword=' + keyword,
                customURL: customPaginationURLCallback,
                customPagingOptions: customPagingOptionsCallback,
                model: this.createSearchResultModel(),
                fetchSize: 20
            });

            return new SearchResults();
        }.bind(this);
    }

    return new SearchResultsFactory();

});