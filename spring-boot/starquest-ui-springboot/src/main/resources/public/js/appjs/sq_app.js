var applog = angular.module('myApp',['ngRoute']);

applog.config([ '$routeProvider', '$locationProvider',
    function($routeProvider, $locationProvider) {
        $routeProvider.when('/home', {
            templateUrl : '../../sqhome.html',
            controller : 'HomeController'
        })
        $routeProvider.when('/', {
            templateUrl : '../../login.html',
            controller : 'LoginController'
        }).otherwise({
            redirectTo : 'index.html'
        });
        $locationProvider.html5Mode(true); //Remove the '#' from URL.
    }
]);

applog.controller("LoginController", function($scope, $location, $http) {
    $scope.login = function() {
        var username = $scope.user.name;
        var password = $scope.user.password;
        
        var loginReqData = {
                emailAddress:username,
                password:password
            };
        console.log('loginReqData:', loginReqData);
        
        var postRegReq = $http.post('http://localhost:8383/sq/userlogin/submitLogin',loginReqData);
        postRegReq.success(function(data, status, headers, config){
            this.message = data;
            console.log('received data:', data);
            $location.path("home" );
        });    
        postRegReq.error(function(data, status, headers, config){
            console.log('All Screwed.... data');
            //self.serverResponse = 'Oopps!! Something went wrong!! Pl. try later...';
            alert('invalid username and password');
        }); 
    };
});

applog.controller("HomeController", function($scope, $location) {

});