    //
//  PlaylistVideoController.m
//  EasyBand
//
//  Created by Eduardo Carminati on 17/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "PlaylistVideoController.h"
#import "Song.h"
#import "SongParser.h"

@implementation PlaylistVideoController
@synthesize songs;
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
- (void)loadView {
	[super loadView];
	
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	self.songs = delegate.songs;
	

	
	[UITitle setTitle: @"MÚSICAS" onView: self.view];
	UIImageView *imgVu = [[UIImageView alloc] initWithImage: [UIImage imageNamed: @"cena.jpg"]];
	[self.view addSubview: imgVu];
	imgVu.frame = CGRectMake(0, [UITitle getHeight], 320, 150 );
	UITableView *tableVu = [[UITableView alloc] initWithFrame: CGRectMake(0, [UITitle getHeight] +150 , self.view.frame.size.width, self.view.frame.size.height - [UITitle getHeight] - imgVu.frame.size.height - 90 ) style: UITableViewStylePlain];
	tableVu.delegate = self;
	tableVu.dataSource = self;
	[self.view addSubview: tableVu];
	[imgVu release];
	[tableVu release];
	
	table = tableVu;
	
}




- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
	return 40;
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
	[table release];
	[songs release];
    [super dealloc];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
	Song *sel = [self.songs objectAtIndex: indexPath.row];
	static NSString *MyIdentifier = @"Songs";
	UITableViewCell	*cell = (UITableViewCell *) [tableView dequeueReusableCellWithIdentifier:MyIdentifier]; 	
	if (cell == nil) {
		cell = [[UITableViewCell alloc] initWithFrame:CGRectZero reuseIdentifier:MyIdentifier];
		cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
	}
//	[cell setData:sel];
	cell.textLabel.text = sel.name;
	return cell;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
	return [self.songs count];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
	return 1;
}
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	Song *sel = [self.songs objectAtIndex: indexPath.row];
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];

	[self.navigationController pushViewController: delegate.playerController animated:YES];
	[delegate.playerController setData:sel forSongs:self.songs];
	[delegate.playerController play: nil];
	
	[tableView deselectRowAtIndexPath:indexPath animated:YES];
}


@end
