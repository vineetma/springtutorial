angular.module('app', [])
  .controller('home', ['$scope', '$http', function($scope, $http) {
    $scope.greeting = {id: 'xxx', content: 'Hello World!'}
    $scope.addStore = function() {
    	$http.post('http://localhost:8080/StoresWithMaven/v1/master/stores')
    		.then(function(data){
    			console.log(data);
    		});
    }
    $scope.addStoreItemMaster = function() {
    	$http.post('http://localhost:8080/StoresWithMaven/v1/master/items')
		.then(function(data){
			console.log(data);
		});
    }
    $scope.addStoreItemQtyToStore = function() {
    	$http.post('http://localhost:8080/StoresWithMaven/v1/stores/1/item/12')
		.then(function(data){
			console.log("Adding qty from store, for item", data);
		});    	
    }
    $scope.removeStoreItemQtyFromStore = function() {
    	$http.delete('http://localhost:8080/StoresWithMaven/v1/stores/1/item/12')
		.then(function(data){
			console.log("Removing qty from store, for item", data);
		});    	
    }
    $scope.updateStoreItemStockToStore = function() {
    	$http.put('http://localhost:8080/StoresWithMaven/v1/stores/1/item/12')
		.then(function(data){
			console.log("Updating stock of item in store", data);
		}); 
    }
}])