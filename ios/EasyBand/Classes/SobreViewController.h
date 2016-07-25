//
//  SobreViewController.h
//  EasyBand
//
//  Created by André Máximo on 27/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "UINavViewController.h"


@interface SobreViewController : UINavViewController <UIWebViewDelegate> {

	UIWebView *web;
	UILoading *load;
	
}

@property (nonatomic,retain) UIWebView *web;
@property (nonatomic,retain) UILoading *load;

@end
