//
//  RevistaSTJAppDelegate.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 26/03/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "IndiceViewController.h";
#import "SplashView.h"
#import <sqlite3.h>

@interface RevistaSTJAppDelegate : NSObject <UIApplicationDelegate, UITabBarControllerDelegate> {
    UIWindow *window;
    UITabBarController *tabBarController;
	IBOutlet IndiceViewController *indiceVc;
	UIImageView *imgView;

	SplashView *viewController;
}

@property (nonatomic, retain) IBOutlet SplashView *viewController;
@property (nonatomic, retain) IBOutlet IndiceViewController *indiceVc;
@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet UITabBarController *tabBarController;


- (void) copyDatabaseIfNeeded;

@end
