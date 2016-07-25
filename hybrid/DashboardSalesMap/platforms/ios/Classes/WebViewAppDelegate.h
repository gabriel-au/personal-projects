//
//  WebViewAppDelegate.h
//  WebView
//
//  Created by Alexandre Oliveira on 5/10/11.
//  Copyright (c) 2011 BRQ IT Services. All rights reserved.
//


#import <UIKit/UIKit.h>

@class WebViewViewController;

@interface WebViewAppDelegate : NSObject <UIApplicationDelegate> {

    UIWindow *window;
    WebViewViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet WebViewViewController *viewController;

@end

