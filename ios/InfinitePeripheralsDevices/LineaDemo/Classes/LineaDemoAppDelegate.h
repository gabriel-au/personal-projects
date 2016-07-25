#import <UIKit/UIKit.h>

@class LineaDemoViewController;

@interface LineaDemoAppDelegate : NSObject {
    UIWindow *window;
    UIViewController *viewController;
}

@property (nonatomic, strong) IBOutlet UIWindow *window;
@property (nonatomic, strong) IBOutlet UIViewController *viewController;

@end

