//
//  BioViewController.h
//  EasyBand
//
//  Created by André Máximo on 23/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UINavViewController.h"

@interface BioViewController : UINavViewController <UIWebViewDelegate> {
	UIWebView *wv;
	UILoading *l;
}

@property (nonatomic,retain) UIWebView *wv;
@property (nonatomic,retain) UILoading *l;

- (void)webViewDidStartLoad:(UIWebView *)webView;
- (void)webViewDidFinishLoad:(UIWebView *)webView;
@end
