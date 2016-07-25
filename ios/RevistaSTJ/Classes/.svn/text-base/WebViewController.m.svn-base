//
//  WebViewController.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 26/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "WebViewController.h"
#import "FileUtil.h"

@implementation WebViewController
@synthesize wv;

/*
 // The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if (self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
        // Custom initialization
    }
    return self;
}
*/


// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
	FileUtil *util = [[FileUtil alloc] init];
	//[util saveFileFromURL: @"http://www.androides.com.br/SEGUNDA%20TURMA.rtf" toDirectory:[util directoryAsString:NSDocumentDirectory] andFileName: @"SEGUNDA TURMA.pdf"];
	NSString *c = [util filePathAsString: [util directoryAsString: NSDocumentDirectory] andFileName: @"Revista/SEGUNDA TURMA.rtf"];
		NSLog(@"%@",c);
	[wv loadRequest:	 [NSURLRequest requestWithURL:	  [NSURL fileURLWithPath: c isDirectory: NO]	  ]];
	wv.scalesPageToFit =YES;
	NSString *myText = [wv stringByEvaluatingJavaScriptFromString:@"document.documentElement.textContent"];
	NSLog(@"aeeee %@", myText);

}


/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}


@end
