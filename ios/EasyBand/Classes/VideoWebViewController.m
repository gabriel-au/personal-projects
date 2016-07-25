//
//  VideoWebViewController.m
//  EasyBand
//
//  Created by Eduardo Carminati on 20/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "VideoWebViewController.h"


@implementation VideoWebViewController
@synthesize url,tit;

- (void) setTitle: (NSString *) _titlee andUrl: (NSString *) _url {
	self.tit = _titlee;
	self.url = _url;
}

- (void) loadView {
	[super loadView];
	UIWebView *webView = [[UIWebView alloc] initWithFrame: self.view.frame];
	

	NSString *htmlString = @"<html><head><meta name = \"viewport\" content = \"initial-scale = 1.0, user-scalable = no, width = 212\"/></head><body style=\"background:#FFFFFF;margin-top:0px;margin-left:0px\"><center><div><object style=\"border: 1px solid black;\" width=\"212\" height=\"172\"><param name=\"movie\" value=\"@URL@\"></param><param name=\"wmode\" value=\"transparent\"></param><embed src=\"@URL@\" type=\"application/x-shockwave-flash\" wmode=\"transparent\" width=\"212\" height=\"172\"></embed></object></div></body></html>";
	htmlString = [htmlString stringByReplacingOccurrencesOfString: @"@URL@" withString: self.url];
	NSLog(@"%@",htmlString);
	
	
	[webView loadHTMLString:htmlString baseURL:nil];	
	[self.view addSubview: webView];
}     

- (void)didReceiveMemoryWarning {
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Release any cached data, images, etc. that aren't in use.
}

- (void)viewDidUnload {
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}


@end
