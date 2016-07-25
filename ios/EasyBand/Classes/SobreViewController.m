//
//  SobreViewController.m
//  EasyBand
//
//  Created by André Máximo on 27/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "SobreViewController.h"


@implementation SobreViewController
@synthesize web,load;



-(void) loadView {
	[super loadView];
	
	[UITitle setTitle:@"SOBRE" onView:self.view];
	web = [[UIWebView alloc] initWithFrame: CGRectMake(0, [UITitle getHeight], 320, 365-[UITitle getHeight]-self.navigationController.navigationBar.frame.size.height - 49)];
	[web setDelegate:self];
	[self.view addSubview:web];
	
	
	NSURL *url = [NSURL fileURLWithPath:[[NSBundle mainBundle]resourcePath]];
	NSString *path = [NSString stringWithFormat:@"%@%@",[[NSBundle mainBundle]resourcePath],@"/sobre.html" ];
	
	NSData *data = [NSData dataWithContentsOfFile:path];
	[web loadData:data MIMEType:@"text/html" textEncodingName:@"utf-8" baseURL: url];
	
	UIButton *button = [UIButton buttonWithType:UIButtonTypeCustom];
	[button setImage:[UIImage imageNamed:@"prime.png"] forState:UIControlStateNormal];
	[button addTarget:self action:@selector(loadUrl:) forControlEvents:UIControlEventTouchUpInside];
	button.frame = CGRectMake(0, [UITitle getHeight] + web.frame.size.height , 320, 80);
	
	[self.view addSubview:button];
	
	//[button release];
	
	//UIImageView *uiImage = [[UIImageView alloc] initWithImage: [UIImage imageNamed: @"prime.png"]];
	//uiImage.frame = CGRectMake(0, [UITitle getHeight] + web.frame.size.height , 320, 80);
	
	//[self.view addSubview:uiImage];
	
	//[uiImage release];
}
	 
- (void)loadUrl:(id)param{
	NSURL *urlPrime = [ [ NSURL alloc ] initWithString: @"http://www.primems.com.br" ];
	[[UIApplication sharedApplication] openURL:urlPrime];
	[urlPrime release];
}

//
- (void)webViewDidStartLoad:(UIWebView *)webView{
	load = [[UILoading alloc] initWithView:self.view];
}

//
- (void)webViewDidFinishLoad:(UIWebView *)webView{
	[load stop];  
}

-(void) dealloc{
	[web release];
	[load release];
	[super dealloc];
}

@end

