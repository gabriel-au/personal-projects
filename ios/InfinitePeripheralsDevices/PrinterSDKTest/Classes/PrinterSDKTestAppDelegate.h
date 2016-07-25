#import <UIKit/UIKit.h>

@class PrinterSDKTestViewController;

@interface PrinterSDKTestAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    PrinterSDKTestViewController *viewController;
}

@property (nonatomic, strong) IBOutlet UIWindow *window;
@property (nonatomic, strong) IBOutlet PrinterSDKTestViewController *viewController;

@end

