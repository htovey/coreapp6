/** 
 *   LOGIN VIEW
 */

define(
    [
        'bootstrap',
        'underscore',
        'backbone',
        'models/User'
    ],
    function($, _, Backbone, User) {
        window.IndexView = Backbone.View.extend({
           
            el: '#login-modal',
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
                    $("#login-modal").hide();
                },
//                loginStateChange: function() {
//                   if(User.get('loggedIn')) {
//                        this.$el.modal('hide');
//                        if (this.callback) {
//                            this.callback();
//                        }
//                    } else {
//                        this.$('form input').val(null);
//                        this.$el.modal('show');
//                    }
//                },
                submit: function () {
//                  do stuff
                },
                cancel: function () {
                    //this.$el.modal('hide');
                },
                setToken: function() {
                    var cookie = document.cookie;
                    var $form = $("#login-form");
                    var tokenName = cookie.split("=")[0]
                    var token = cookie.split("=")[1];
                    $('<input>').attr({type: 'hidden', name: tokenName, value: token}).appendTo($form);
                 }
            });
        
        return IndexView;
    }
);
