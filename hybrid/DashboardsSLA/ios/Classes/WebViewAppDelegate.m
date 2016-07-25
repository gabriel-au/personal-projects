//
//  WebViewAppDelegate.m
//  WebView
//
//  Created by Alexandre Oliveira on 5/10/11.
//  Copyright (c) 2011 BRQ IT Services. All rights reserved.
//


#import "WebViewAppDelegate.h"

#import "WebViewViewController.h"

@implementation WebViewAppDelegate


@synthesize window;

@synthesize viewController;

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions {

	self.viewController = [[WebViewViewController alloc] initWithNibName:@"WebViewViewController" bundle:[NSBundle mainBundle]];

     
    [window addSubview:viewController.view];
    [window makeKeyAndVisible];
    return YES;
}

- (void)applicationWillTerminate:(UIApplication *)application {

    // Save data if appropriate.
}

- (void)dealloc {

    [window release];
    [viewController release];
    [super dealloc];
}

@end

