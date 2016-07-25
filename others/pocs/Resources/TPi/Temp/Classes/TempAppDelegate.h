//
//  TempAppDelegate.h
//  Temp
//
//  Created by Gabriel Silva on 20/07/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>

@class TempViewController;

@interface TempAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    TempViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet TempViewController *viewController;

@end

