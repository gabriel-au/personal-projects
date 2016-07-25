//
//  EasyTabBarController.m
//  EasyBand
//
//  Created by Eduardo Carminati on 23/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "EasyTabBarController.h"

#define degreesToRadian(x) (M_PI * (x) / 180.0)
#define radianToDegrees(x) ((x) * 180.0/M_PI)
@implementation EasyTabBarController

- (BOOL) shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation) io {
	[Const sharedInstance].orient = io;

	BOOL orient = (io == UIInterfaceOrientationPortrait);
	BOOL activeTab = [Const sharedInstance].photoActiveTab;
	return orient || activeTab;
}

- (void) forcePortrait {

}

- (void) viewWillAppear:(BOOL)animated {
}
- (void)tabBarController:(UITabBarController *)tabBarController didSelectViewController:(UIViewController *)viewController {
	if ([Const sharedInstance].orient != UIInterfaceOrientationPortrait)
		[self forcePortrait];
}
@end
