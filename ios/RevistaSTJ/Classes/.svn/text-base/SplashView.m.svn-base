//
//  SplashView.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 22/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "SplashView.h"

@implementation SplashView
@synthesize modelView;

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

-(void)showSplash
{
    UIViewController *modalViewController = [[UIViewController alloc] init];
    modalViewController.view = self.modelView;
    [self presentModalViewController:modalViewController animated:NO];
    [self performSelector:@selector(hideSplash) withObject:nil afterDelay:5.0];
}

//hide splash screen
- (void)hideSplash{
    [[self modalViewController] dismissModalViewControllerAnimated:YES];
}


@end
