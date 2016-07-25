//
//  Loader.h
//  WebView
//
//  Created by Alexandre Oliveira on 18/05/11.
//  Copyright 2011 BRQ IT Services. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "InvokedUrlCommand.h"

@interface Loader : NSObject {

	UIWebView* webview;
	NSDictionary *settings;
    NSMutableDictionary *commandObjects;
	
}

@property(nonatomic,assign) UIWebView* webview;

- (BOOL) execute:(InvokedUrlCommand*)command;
- (id)initWithView:(UIWebView *)webView usingFile:(NSString *)file inDirectory:(NSString *)directory;


@end
