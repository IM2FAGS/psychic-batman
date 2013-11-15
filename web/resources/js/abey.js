var abey = {
    
    init: function() {
        $('#show-login').click(function() {
            abey.showLogin();
        });
    },
    
    showLogin: function() {
        $('#login-form').show().width(65).animate({width: 450}, 150);
        $('[id$=":nom"]').focus();
        $('#show-login-li').hide();
    }
    
};

$(document).ready(abey.init);

