//
//  AlbumController.m
//  EasyBand
//
//  Created by Eduardo Carminati on 14/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "AlbumController.h"
#import "PhotoSource.h"
#import "Photo.h"
#import "NavControlFactory.h"
#import "FlickrParser.h";

@implementation AlbumController
@synthesize images;

- (BOOL) shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)toInterfaceOrientation {
	return YES;
}

- (void) viewDidAppear:(BOOL)animated {
	[super viewDidAppear:animated];
	if (self.images == nil || [self.images count] == 0) {
	//	[self updateImages:nil];
	}
}

- (IBAction) updateImages: (id) sender {
//	[Const sharedInstance].loading = [[UILoading alloc] initWithView: self.view andOrientation: UIInterfaceOrientationPortrait];
	[self performSelector: @selector(update) withObject: nil afterDelay: 0.1];
}

- (void) update {
	FlickrParser *tp = [FlickrParser new];
	[tp release];
	
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	self.images = delegate.photos;
	

	
//	[[Const sharedInstance].loading stop];
//	[[Const sharedInstance].loading release];
//	

}

-(void)createPhotos {
	EasyBandAppDelegate *delegae = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	self.images = delegae.photos;
}

- (void) setCustomNavigationBar {
	UIImage *barLogo = [UIImage imageNamed:@"surfsessions.png"];
	UIImageView *barLogoView = [[UIImageView alloc] initWithImage:barLogo];
	self.navigationItem.titleView = barLogoView;
	self.navigationBarTintColor = [UIColor orangeColor];
}   

- (void) loadView {
	[super loadView];
	if (self = [super initWithNibName:nil bundle:nil]) {
		self.hidesBottomBarWhenPushed = NO;
		[self setCustomNavigationBar];
	}
}

- (void)viewDidLoad {
	[self createPhotos];
	self.photoSource = [[PhotoSource alloc]
						initWithType:PhotoSourceNormal
						title:@"Fotos"
						photos:images
						photos2:nil
						];
}

- (void) viewWillAppear:(BOOL)animated {
	[super viewWillAppear: animated];
	[Const sharedInstance].photoActiveTab = YES;
}

- (void) viewWillDisappear: (BOOL) animated {
	[super viewWillDisappear: animated];
	//[Const sharedInstance].photoActiveTab = NO;
}

- (void) updateTableLayout {
	
	self.tableView.contentInset = UIEdgeInsetsMake(5, 0, 0, 0);
	self.tableView.scrollIndicatorInsets = UIEdgeInsetsMake(TTBarsHeight(), 0, 0, 0);

}

@end