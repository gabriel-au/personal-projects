//
//  PDFController.h
//  FaturaOnLine
//
//  Created by Gabriel Silva on 02/08/10.
//  Copyright 2010 Prime Mobile Solutions. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface PDFController : UIViewController <UIWebViewDelegate> {
	IBOutlet UIWebView *wvPDF;
}

@property (nonatomic, retain) IBOutlet UIWebView *wvPDF;

@end
