    //
//  UINavViewController.m
//  EasyBand
//
//  Created by Eduardo Carminati on 08/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "UINavViewController.h"
#import "NavControlFactory.h"


@implementation UINavViewController
@synthesize navControl;


- (void)loadView {
	[super loadView];
	[self.view setAutoresizingMask: UIViewAutoresizingFlexibleWidth];
	navControl = [NavControlFactory createNavControl: self.navigationController];
	self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemPlay target:self action:@selector(handlePlay)];
	self.navigationItem.backBarButtonItem = [[[UIBarButtonItem alloc] initWithTitle:@"Voltar" style:UIBarButtonItemStyleDone target:nil action:nil] autorelease];
}

- (void) handlePlay {
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	UIViewController *player = delegate.playerController;
	[self.navigationController pushViewController: player animated: YES];
}

- (void) viewWillAppear:(BOOL)animated {	
	[super viewWillAppear:animated];
	[self.navigationController.navigationBar addSubview: [Const sharedInstance].logo];
	[Const sharedInstance].logo.hidden = NO;
	[Const sharedInstance].photoActiveTab = NO;
}

- (void) viewWillDisappear:(BOOL)animated {
	[super viewWillDisappear:animated];
	[Const sharedInstance].logo.hidden=YES;
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (void)viewDidUnload {
    [super viewDidUnload];
}


- (void)dealloc {
    [super dealloc];
}


@end
