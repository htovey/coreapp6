/** 
 *   LOGIN VIEW
 */

define(
    [
        'bootstrap',
        'underscore',
        'backbone',
        'models/User',
        'views/IndexView'
    ],
    function($, _, Backbone, User, IndexView) {
        window.UserView = Backbone.View.extend({
           
            el: '#login-form',
                events: {
                    'click .button.submit' : 'submit',
                    'click .button.cancel' : 'cancel'
                },
                 // View initialization with logout outside if the view and listening on model
                initialize: function (callback) {
                    this.callback = callback;
                    $("a.logout").click(this.logout);
                    User.on('change:loggedIn', this.loggedInChange, this);
                    this.setToken();
                    $.ajaxPrefilter( function (options, jqXHR) {
                          options.cache = true;
                          var inputToken = document.getElementsByName("X-CSRF-TOKEN")[0]
                          var tokenName = inputToken.name;
                          var token = inputToken.value;
                          options.beforeSend = function (xhr) {
                              xhr.setRequestHeader(tokenName, token);
                          }
                    });
                },
                loginStateChange: function() {
                   if(User.get('loggedIn')) {
                        this.$el.modal('hide');
                        if (this.callback) {
                            this.callback();
                        }
                    } else {
                        this.$('form input').val(null);
                        this.$el.modal('show');
                    }
                },
                submit: function () {
//                    User.set({
//                        userId: this.$("#userId").val(),
//                        password: this.$("#password").val(),
//                       // rememberMe: this.$("#remeberMe:checked").length > 0
//                    });
                    
                    User.save({userId: this.$("#userId").val(),password: this.$("#password").val()}, {
                        dataType: "text",
                        success: function(mod, res){
                            console.log("SUCCESS", mod, res);
                            var index = new IndexView();
                            index.render();
                        },
                        error: function(err){
                            console.log("ERROR", err);
                        }
                    });
                },
                cancel: function () {
                    this.$el.modal('hide');
                },
                setToken: function() {
                    var cookie = document.cookie;
                    var $form = $("#login-form");
                    var tokenName = cookie.split("=")[0]
                    var token = cookie.split("=")[1];
                    $('<input>').attr({type: 'hidden', name: tokenName, value: token}).appendTo($form);
                 }
            });
        
        return UserView;
    }
);
