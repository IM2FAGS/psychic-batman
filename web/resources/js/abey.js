var abey = {
    
    init: function() {
        $('#show-login').click(function() {
            abey.showLogin();
        });
    },
    
    showLogin: function() {
        $('#login-form').show();
        $('#show-login-li').hide();
    }
    
};

$(document).ready(abey.init);

