//
//  Loader.h
//  WebView
//
//  Created by Alexandre Oliveira on 18/05/11.
//  Copyright 2011 BRQ IT Services. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "InvokedUrlCommand.h"
#import "Network.h"



@interface Loader : NSObject <NetworkRequestDelegate> {
    
	UIWebView* webview;
	NSDictionary *settings;
    NSMutableDictionary *commandObjects;
    Network* network;
    InvokedUrlCommand* sharedCommand;
    NSString* json_string;
}

@property(nonatomic,retain) UIWebView* webview;
@property(nonatomic,retain) InvokedUrlCommand* sharedCommand ;

- (void) execute:(InvokedUrlCommand*)command usingView:(UIView*)view;
- (id)initWithView:(UIWebView *)webView2 usingFile:(NSString *)file inDirectory:(NSString *)directory;


@end