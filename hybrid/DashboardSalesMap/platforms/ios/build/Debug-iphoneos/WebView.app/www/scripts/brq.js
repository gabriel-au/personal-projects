// ############ BRQ MOBILE FRAMEWORK ######################


jQuery.ajaxCal = function() {
    var args = arguments[0] || {}; 
    var url = args.url;
    var callback = args.callback;
    window.location = "brq://network.request/" + callback + "/" + url;
};

// ############ BRQ MOBILE FRAMEWORK ######################
