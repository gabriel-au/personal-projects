    //
//  TwitterSelViewController.m
//  EasyBand
//
//  Created by André Máximo on 27/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "TwitterSelViewController.h"


@implementation TwitterSelViewController
@synthesize web, loading, urlLocal;

-(id) initWithString: (NSString *)url{
	if(self = [super init]){
		urlLocal = [NSURL URLWithString: url];
	}
	
	return self;
}

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

- (void)webViewDidStartLoad:(UIWebView *)webView{
	loading = [[UILoading alloc] initWithView: self.view];
}

- (void)webViewDidFinishLoad:(UIWebView *)webView{
	[loading stop];
}

// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
	[super loadView];
	[UITitle setTitle:@"TWITTER" onView:self.view ];
	
	web = [[UIWebView alloc] initWithFrame: CGRectMake(0, [UITitle getHeight], 320, 480-[UITitle getHeight]-self.navigationController.navigationBar.frame.size.height - 49)];
	web.delegate = self;
	NSURLRequest *request = [[NSURLRequest alloc] initWithURL:urlLocal];
	[web loadRequest: request];
	[self.view addSubview:web];
	
	[request release];
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
	[loading release];
	[web release];
	[urlLocal release];
	[super dealloc];
}


@end
