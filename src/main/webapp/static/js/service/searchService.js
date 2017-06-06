'use strict';

angular.module('myApp').factory('SearchService', ['$http', '$q', function($http, $q){

    var REST_SERVICE_URI = 'http://localhost:8080/prometheus/searchResults/';

    var factory = {
        fetchAllSearchResults: fetchAllSearchResults
    };

    return factory;

    function fetchAllSearchResults() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
