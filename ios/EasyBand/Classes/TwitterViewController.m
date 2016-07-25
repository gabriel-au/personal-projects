//
//  TwitterViewController.m
//  EasyBand
//
//  Created by Eduardo Carminati on 10/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "TwitterViewController.h"
#import "TwitterParser.h"
#import "TweetTableCell.h"
#import "EasyBandAppDelegate.h"
#import "TwitterSelViewController.h"

@implementation TwitterViewController
@synthesize table,tweets;

- (void)loadView {
	[super loadView];
	
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	self.tweets = delegate.tweets;
	
	[UITitle setTitle: @"TWITTER" onView: self.view];
	
	table = [[UITableView alloc] initWithFrame:CGRectMake(0, [UITitle getHeight], 320,400)];
	[table setDataSource: self];
	[table setDelegate: self];
	[self.view addSubview: table];
	
    UIBarButtonItem *btnCancel = [[UIBarButtonItem alloc] initWithImage: [UIImage imageNamed: @"update.png"]
                                                                  style:UIBarButtonItemStyleBordered 
                                                                 target:self 
                                                                 action:@selector(updateTweets:)];    
	
    self.navigationItem.rightBarButtonItem = btnCancel;
	
    [btnCancel release];
}

- (void) viewDidAppear:(BOOL)animated {
	[super viewDidAppear:animated];
	if (self.tweets == nil || [self.tweets count] == 0) {
		[self updateTweets:nil];
	}
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
	return 60.0;
}


- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
	Tweet *sel = [self.tweets objectAtIndex: indexPath.row];
	static NSString *MyIdentifier = @"Outro";
	TweetTableCell *cell = (TweetTableCell *) [tableView dequeueReusableCellWithIdentifier:MyIdentifier]; 	
	if (cell == nil) {
		cell = [[TweetTableCell alloc] initWithFrame:CGRectZero reuseIdentifier:MyIdentifier];
		cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
	}
	[cell setData:sel];
	return cell;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
	return [self.tweets count];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
	return 1;
}
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	Tweet *sel = [self.tweets objectAtIndex: indexPath.row];
	TwitterSelViewController *s = [[TwitterSelViewController alloc]initWithString: sel.link];
	[self.navigationController pushViewController:s animated: YES];
	[tableView deselectRowAtIndexPath:indexPath animated:YES];
}

- (IBAction) updateTweets: (id) sender {
	[Const sharedInstance].loading = [[UILoading alloc] initWithView: self.view andOrientation: UIInterfaceOrientationPortrait];
	[self performSelector: @selector(update) withObject: nil afterDelay: 0.1];
}

- (void) update {
	TwitterParser *tp = [TwitterParser new];
	[tp release];
	
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	self.tweets = delegate.tweets;
	
	[table reloadData];
	
	[[Const sharedInstance].loading stop];
	[[Const sharedInstance].loading release];
}


- (void) dealloc{
	[table release];
	[tweets release];
	[super dealloc];
}

	 
@end
