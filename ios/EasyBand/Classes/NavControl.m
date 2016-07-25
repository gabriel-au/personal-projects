    //
//  NavControl.m
//  EasyBand
//
//  Created by Eduardo Carminati on 08/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "NavControl.h"


@implementation NavControl

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


// Implement loadView to create a view hierarchy programmatically, without using a nib.
//- (void)loadView {
//	[super loadView];
//	UIView *logo = [[UIView alloc] initWithFrame: CGRectMake(0, 0, 20, 20)];
//	logo.backgroundColor = [UIColor whiteColor];
//	[self.view addSubview: logo ];
//}

- (void) viewWillAppear: (BOOL) animated {
	[super viewWillAppear: YES];
	UIView *logo = [[UIView alloc] initWithFrame: CGRectMake(0, 0, 20, 20)];
	logo.backgroundColor = [UIColor whiteColor];
	[self.view addSubview: logo ];
	
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
    [super dealloc];
}


@end
