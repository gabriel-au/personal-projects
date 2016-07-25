//
//  AgendaDetailViewController.h
//  EasyBand
//
//  Created by Eduardo Carminati on 22/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Agenda.h"

@interface AgendaDetailViewController : UINavViewController <UIWebViewDelegate> {
	Agenda *agenda;
	UIWebView *webView;
	NSMutableString *string;
	UILoading *loading;
}

@property (nonatomic,retain) 	Agenda *agenda;
@property (nonatomic,retain) 	NSMutableString *string;
@property (nonatomic,retain) 	IBOutlet UIWebView *webView;
@property (nonatomic,retain) 	UILoading *loading;
- (id) initWithAgenda: (Agenda *) agenda2;
@end
