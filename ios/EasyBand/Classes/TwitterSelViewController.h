//
//  TwitterSelViewController.h
//  EasyBand
//
//  Created by André Máximo on 27/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface TwitterSelViewController : UINavViewController <UIWebViewDelegate> {
	
	UIWebView *web;
	UILoading *loading;
	NSURL *urlLocal;
}

@property (nonatomic,retain) UIWebView *web;
@property (nonatomic,retain) UILoading *loading;
@property (nonatomic,retain) NSURL *urlLocal;

@end
