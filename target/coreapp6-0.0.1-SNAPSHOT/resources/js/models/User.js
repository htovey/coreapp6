/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

define([
  'underscore',
  'backbone'
], function (_, Backbone) {
    var User = Backbone.Model.extend({
        //create set of values for determining login status
        defaults:{
           // loggedIn: null,
            'userId' : '',
            'password' : ''
           // rememberMe: false
        },
        isNew: function(){
            return false;
        },
     
        url: document.URL+'login'
    });
    
    return new User(); 
});
