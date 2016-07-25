//
//  SplashView.m
//  EasyBand
//
//  Created by Eduardo Carminati on 09/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "SplashView.h"


@implementation SplashView

@synthesize modelView;

- (void) didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
	
}

- (void) viewDidUnload {
}


- (void) dealloc {
    [super dealloc];
}

-(void) showSplash
{
    UIViewController *modalViewController = [[UIViewController alloc] init];
    modalViewController.view = self.modelView;
    [self presentModalViewController:modalViewController animated:NO];
    [self performSelector:@selector(hideSplash) withObject:nil afterDelay:5.0];
}

- (void) hideSplash {
    [[self modalViewController] dismissModalViewControllerAnimated:YES];
}
@end
