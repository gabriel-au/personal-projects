//
//  RevistaSTJAppDelegate.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 26/03/10.
//  Copyright __MyCompanyName__ 2010. All rights reserved.
//

#import "RevistaSTJAppDelegate.h"
#import "RootViewController.h"
#import "IndiceViewController.h";
#import <sqlite3.h>

@implementation RevistaSTJAppDelegate

@synthesize window;
@synthesize viewController;
@synthesize tabBarController,
indiceVc;

#pragma mark -
#pragma mark Application lifecycle
- (void)applicationDidFinishLaunching:(UIApplication *)application {
	[self copyDatabaseIfNeeded];
    // Add the tab bar controller's current view as a subview of the window
    [window addSubview:tabBarController.view];
    [window makeKeyAndVisible];
	
	imgView = [[UIImageView alloc] initWithFrame:CGRectMake(0, 0, 320, 480)];
	imgView.image = [UIImage imageNamed:@"splash.png"];
	[window addSubview:imgView];
	[window bringSubviewToFront:imgView];
	[self performSelector:@selector(removeSplash) withObject:nil afterDelay: TEMPO_SPLASH];
}

-(void)removeSplash;
{
	[imgView removeFromSuperview];
	[imgView release];
}

/**
 applicationWillTerminate: saves changes in the application's managed object context before the application terminates.
 */
- (void)applicationWillTerminate:(UIApplication *)application {


}

- (void) copyDatabaseIfNeeded {
	NSFileManager *fileManager = [NSFileManager defaultManager];
	NSError *error;
	NSString *dbPath = [TemplateDAO getDBPath];
	BOOL success = [fileManager fileExistsAtPath:dbPath];
	
	if(!success) {
		
		NSString *defaultDBPath = [[[NSBundle mainBundle] resourcePath] stringByAppendingPathComponent:@"revistastj.sqlite"];
		success = [fileManager copyItemAtPath:defaultDBPath toPath:dbPath error:&error];
		
		if (!success)
			NSAssert1(0, @"Failed to create writable database file with message '%@'.", [error localizedDescription]);
	}
}

/*
// Optional UITabBarControllerDelegate method
- (void)tabBarController:(UITabBarController *)tabBarController didSelectViewController:(UIViewController *)viewController {
}
*/

/*
// Optional UITabBarControllerDelegate method
- (void)tabBarController:(UITabBarController *)tabBarController didEndCustomizingViewControllers:(NSArray *)viewControllers changed:(BOOL)changed {
}
*/

- (void)dealloc {
	//
    [tabBarController release];
    [window release];
    [super dealloc];
}



@end

