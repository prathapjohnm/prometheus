define(['../config/ServiceEndpoints'], function (service) {
    function SearchService() {
        this.fetchOptions = function (context) {
            return $.ajax({
                url: service.FREQUENT_SEARCH_TERMS
            }).then(function (response) {
                return response.map(function (element) {
                    return {value: element.keyword, label: element.keyword};
                }).filter(function (element) {
                    return element.label.toLowerCase().indexOf(context.term.toLowerCase()) !== -1;
                });
            });
        }
    }

    return new SearchService();
});