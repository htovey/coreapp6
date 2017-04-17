define(function (require){
    "use strict";
    var $ = require('jquery'),
    Backbone = require('backbone'),
    IndexView = require('../views/IndexView'),
    indexView = new IndexView();
    
    return Backbone.Router.extend({
        routes: {
            "index" : "index"
        },
        index: function () {
            indexView.delegateEvents();
            indexView.render();
        }
    });
    
});