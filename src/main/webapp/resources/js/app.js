//use require js to pull the rest of the js files together
require.config({
    
  /**
   * Require 2.0 introduced shim config which allows to configure dependencies for
   * scripts that do not call define() to register a module
   */
  shim:{
    "underscore":{
      exports:"_"
    },
    "backbone":{
      deps:[
        "underscore",
        "jquery"
      ],
      exports:"Backbone"
    },
    "bootstrap":{
      deps:[
        "jquery"
      ],
      exports:"jQuery"
    }
  },
  /**
   * Shortcut configuration for libs
   */
  paths:{
    jquery:"./libs/jquery-1.8.0/jquery",
    bootstrap:"./libs/bootstrap-2.1.0/js/bootstrap.min",
    underscore:"./libs/underscore-1.8.3/underscore",
    backbone:"./libs/backbone-1.3.3/backbone",
    text:"./libs/require-2.0.6/text"
  }
});

require(['jquery', 'backbone', 'routers/loginRouter'], function ($, Backbone, Router) {
    Backbone.emulateHTTP = true;
    var router = new Router();
    Backbone.history.start();
});

//login

//homepage