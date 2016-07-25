//
//  VideoWebViewController.h
//  EasyBand
//
//  Created by Eduardo Carminati on 20/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UINavViewController.h"

@interface VideoWebViewController : UINavViewController {
	NSString *url;
	NSString *tit;
}

@property (nonatomic,retain) NSString *url;
@property (nonatomic,retain) NSString *tit;

@end
