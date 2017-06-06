'use strict';

angular.module('myApp').controller('searchController', ['$scope', 'UserService', function($scope, SearchService) {
    var self = this;
    self.results=[];
    

    fetchAllSearchResults();

    function fetchAllSearchResults(){
    	SearchService.fetchAllSearchResults()
            .then(
            function(d) {
                self.results = d;
            },
            function(errResponse){
                console.error('Error while fetching Users');
            }
        );
    }
}]);
