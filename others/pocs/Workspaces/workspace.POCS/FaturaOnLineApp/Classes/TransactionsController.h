//
//  TransactionsController.h
//  FaturaOnLine
//
//  Created by Gabriel Silva on 02/08/10.
//  Copyright 2010 Prime Mobile Solutions. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface TransactionsController : UIViewController <UIWebViewDelegate> {
	IBOutlet UIWebView *wvTransactions;
}

@property (nonatomic, retain) IBOutlet UIWebView *wvTransactions;

@end
