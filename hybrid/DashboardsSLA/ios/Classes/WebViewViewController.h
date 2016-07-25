//
//  WebViewViewController.h
//  WebView
//
//  Created by Alexandre Oliveira on 5/10/11.
//  Copyright (c) 2011 BRQ IT Services. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "InvokedUrlCommand.h"
#import "Loader.h"

@interface WebViewViewController : UIViewController <UIWebViewDelegate>{

}

@property (nonatomic, retain) IBOutlet UIWebView *webView;
@property (nonatomic,retain) Loader* loader;


@end

