<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Bookie - Reset Password</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }

        .bg-color {
            background-color: #000;
        }

        .login-panel {
            width: 400px;
            margin: 150px auto;
        }

        .panel {
            margin-bottom: 20px;
            background-color: #fff;
            border: 1px solid transparent;
            border-radius: 4px;
            -webkit-box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
        }

        .panel > .panel-heading {
            color: #333;
            background-color: #f5f5f5;
            border-color: #ddd;
        }

        .panel-heading {
            padding: 10px 15px;
            border-bottom: 1px solid transparent;
            border-top-left-radius: 3px;
            border-top-right-radius: 3px;
        }

        .panel-title {
            font-size: 17px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-section {
            padding: 20px 10px;
        }

        .form-control {
            display: block;
            width: 100%;
            height: 50px;
            text-indent: 10px;
            font-size: 14px;
            line-height: 1.42857143;
            color: #555;
            background-color: #fff;
            border: 1px solid #e8e8e8;
            border-radius: 4px;
        }

        .btn-login {
            background-color: #50b748;
            border: none;
            text-transform: uppercase;
            letter-spacing: 1px;
            font-size: 14px;
            margin: 20px 0;
            padding: 10px 0;
            text-decoration: none;
            color: #fff;
            border-radius: 5px;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            -o-border-radius: 5px;
            text-indent: 10px;
            text-align: center;
        }

        .btn-block {
            display: block;
            width: 100%;
        }

        .btn-login:hover {
            background-color: #449d44;
        }
    </style>
</head>

<body ng-app="myApp" class="bg-color">
<div class="login-panel panel">
    <div class="panel-heading">
        <h3 class="panel-title">Reset Password</h3>
    </div>
    <form class="form-horizontal" ng-submit="submit()" ng-controller="customersCtrl" name="myform" role="form">
        <div class="form-section">
            <div class="form-group">
                <p>{{successlog}}</p>
                <input type="password" class="form-control" id="newpassword"
                       required ng-model="Pwd.newpassword" placeholder="New Password">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="confirmnewpassword"
                       required ng-model="Pwd.confirmnewpassword"
                       placeholder="Confirm Password">
            </div>
            <input class="btn-login  btn-block" type="submit" id="submit" value="Reset Password"/>
        </div>
    </form>
</div>

<script>
    var app = angular.module("myApp", []);
    app.controller("customersCtrl", function ($scope, $http) {
        $scope.submit = function () {

            console.log($scope.Pwd.confirmnewpassword.length);
            if ($scope.Pwd.newpassword.length < 6) {
                $scope.successlog = "New Password require minimum 6 character";
                return;
            }

            if ($scope.Pwd.confirmnewpassword.length < 6) {
                $scope.successlog = "Confirm Password require minimum 6 character";
                return;
            }

            if ($scope.Pwd.confirmnewpassword == $scope.Pwd.newpassword) {
                $http.post(document.location.origin + "/bookie/api/v1/auth/updatePassword?newPassword=" + $scope.Pwd.newpassword, JSON.stringify({"password": $scope.Pwd.newpassword}))
                        .success(function (response) {
                            //var w = window.open();
                            document.write(response);
                            $scope.successlog = "";
                        });
            }
            else {
                $scope.successlog = "Password do not match";
                return;
            }

        }

    });
</script>

</body>
</html>