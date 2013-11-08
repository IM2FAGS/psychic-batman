var abey = {
    
    init: function() {
        $('#show-login').click(function() {
            abey.showLogin();
        });
    },
    
    showLogin: function() {
        $('#login-form').show().animate({width: 415});
        $('#show-login-li').hide();
    }
    
};

$(document).ready(abey.init);

