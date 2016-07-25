    //
//  VideoViewController.m
//  EasyBand
//
//  Created by Eduardo Carminati on 08/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "VideoViewController.h"
#import "YoutubeParser.h"
#import "VideoTableCell.h"
#import "EasyBandAppDelegate.h"
#import "VideoWebViewController.h"


@implementation VideoViewController
@synthesize table,videos;


- (void)loadView {
	[super loadView];
	
	
	
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	self.videos=delegate.videos;	
	
	[UITitle setTitle: @"VÍDEOS" onView: self.view];
	
	table = [[UITableView alloc] initWithFrame:CGRectMake(0,[UITitle getHeight], 320,420- [UITitle getHeight] - self.navigationController.navigationBar.frame.size.height)];
		[self.view addSubview: table];
	//[self updateVideos:nil];
	[table setDataSource: self];
	[table setDelegate: self];

	
}

- (void) viewDidAppear:(BOOL)animated {
	[super viewDidAppear:animated];
	if (self.videos == nil || [self.videos count] == 0) {
		[self updateVideos:nil];
	}
}

- (IBAction) updateVideos: (id) sender {
	[Const sharedInstance].loading = [[UILoading alloc] initWithView: self.view andOrientation: UIInterfaceOrientationPortrait];
	[self performSelector: @selector(update) withObject: nil afterDelay: 0.1];
}

- (void) update {
	YoutubeParser *tp = [YoutubeParser new];
	[tp release];
	
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	self.videos = delegate.videos;
	
	[table reloadData];
	
	[[Const sharedInstance].loading stop];
	[[Const sharedInstance].loading release];
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
- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
	return 100.0;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
	Video *v = [self.videos objectAtIndex: indexPath.row];
	static NSString *MyIdentifier = @"MyIdentifier";
	
	//UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:MyIdentifier];
	VideoTableCell *cell = (VideoTableCell *)[tableView dequeueReusableCellWithIdentifier:MyIdentifier]; 	
	if (cell == nil) {
		cell = [[[VideoTableCell alloc] initWithFrame:CGRectZero reuseIdentifier:MyIdentifier] autorelease]; // changed this
		cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
	}
	
	// setting the text
	NSMutableDictionary *itemAtIndex = [[NSMutableDictionary alloc] init];
	[itemAtIndex setObject: v.preview forKey:@"img"];
	[itemAtIndex setObject: [NSNumber numberWithInteger:1] forKey:@"id"];
	[itemAtIndex setObject: v.name forKey:@"title"];
	[cell setData:itemAtIndex];
	
	return cell;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
	return [self.videos count];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
	return 1;
}


- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	Video *v = [self.videos objectAtIndex: indexPath.row];
//	NSString *url = [v.url stringByReplacingOccurrencesOfString:@"?f=user_uploads&app=youtube_gdata" withString:@""];
//	NSLog(@"PLAYING: %@",url);
	VideoWebViewController *vweb = [VideoWebViewController new];
	[vweb setTitle: v.name andUrl: v.url];
	[self.navigationController pushViewController:vweb animated:YES];
//	[[UIApplication sharedApplication] openURL: [NSURL URLWithString:url]];
}

@end
