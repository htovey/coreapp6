define(function (require){
    "use strict";
    var $ = require('jquery'),
    Backbone = require('backbone'),
    UserView = require('../views/UserView'),
    userView = new UserView();
    
    return Backbone.Router.extend({
        routes: {
            "login" : "login",
            "index" : "index"
        },
        login: function () {
            userView.delegateEvents();
            userView.render();
        }
    });
    
});