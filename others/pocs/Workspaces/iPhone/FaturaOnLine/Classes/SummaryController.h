//
//  SummaryController.h
//  FaturaOnLine
//
//  Created by Gabriel Silva on 15/07/10.
//  Copyright 2010 Prime Mobile Solutions. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface SummaryController : UIViewController <UIWebViewDelegate> {
	IBOutlet UIWebView *wvSummary;
	UIView *vwCarregando;

}

@property (nonatomic, retain) IBOutlet UIWebView *wvSummary;

- (NSString *) getPath;

@end
