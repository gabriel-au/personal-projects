#import "PrinterSDKTestAppDelegate.h"
#import "PrinterSDKTestViewController.h"

@implementation PrinterSDKTestAppDelegate

@synthesize window;
@synthesize viewController;


- (void)applicationDidFinishLaunching:(UIApplication *)application {    
    // Override point for customization after app launch    
    [window addSubview:viewController.view];
    [window makeKeyAndVisible];
}




@end
