    //
//  BioViewController.m
//  EasyBand
//
//  Created by André Máximo on 23/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "BioViewController.h"

@implementation BioViewController
@synthesize l,wv;

// The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
/*
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization.
    }
    return self;
}
*/


- (void)loadView {
	[super loadView];

	[UITitle setTitle:@"BIOGRAFIA" onView:self.view];
	wv = [[UIWebView alloc] initWithFrame: CGRectMake(0, [UITitle getHeight], 320, 480-[UITitle getHeight]-self.navigationController.navigationBar.frame.size.height - 49)];
		  [wv setDelegate:self];
		  [self.view addSubview:wv];
		  
		  
		NSURL * url = [NSURL fileURLWithPath:[[NSBundle mainBundle]resourcePath]];
	NSString *path = [NSString stringWithFormat:@"%@%@",[[NSBundle mainBundle]resourcePath],@"/biografia.html" ];

	NSData *data = [NSData dataWithContentsOfFile:path];
		  [wv loadData:data MIMEType:@"text/html" textEncodingName:@"utf-8" baseURL: url];
		  
}
		  
			- (void)webViewDidStartLoad:(UIWebView *)webView{
				
				l = [[UILoading alloc] initWithView:self.view];
			
				
			}
		  
		  - (void)webViewDidFinishLoad:(UIWebView *)webView{
			  [l stop];  
		  }


/*
// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
}
*/

/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations.
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

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
	[wv release];
	[l release];
    [super dealloc];
}


@end
