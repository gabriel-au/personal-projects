package org.apache.cordova.splashscreen;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;

public class SplashScreen extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (action.equals("hide")) {
            this.webView.postMessage("splashscreen", "hide");
        } else if (action.equals("show")){
            this.webView.postMessage("splashscreen", "show");
        }
        else {
            return false;
        }

        callbackContext.success();
        return true;
    }

}
