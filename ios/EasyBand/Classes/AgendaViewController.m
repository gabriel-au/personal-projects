//
//  AgendaViewController.m
//  EasyBand
//
//  Created by Eduardo Carminati on 22/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "AgendaViewController.h"
#import "Agenda.h"
#import "AgendaTableCell.h"
#import "AgendaDetailViewController.h"
#import "AgendaParser.h"

@implementation AgendaViewController
@synthesize agendas,table;


- (void)loadView {
	[super loadView];
	
	[UITitle setTitle: @"AGENDA" onView: self.view];
	
	table = [[UITableView alloc] initWithFrame:CGRectMake(0, [UITitle getHeight], 320,400)];;
	[table setDelegate: self];
	[table setDataSource: self];
	
	[self.view addSubview: table];
	
	[table release];
}

- (void) viewDidAppear:(BOOL)animated {
	[super viewDidAppear:animated];
	if (self.agendas == nil || [self.agendas count] == 0) {
		[self updateAgendas:nil];
	}
}

- (IBAction) updateAgendas: (id) sender {
	[Const sharedInstance].loading = [[UILoading alloc] initWithView: self.view andOrientation: UIInterfaceOrientationPortrait];
	[self performSelector: @selector(update) withObject: nil afterDelay: 0.1];
}

- (void) update {
	AgendaParser *tp = [AgendaParser new];
	[tp release];
	
	EasyBandAppDelegate *delegate = (EasyBandAppDelegate *) [[UIApplication sharedApplication] delegate];
	self.agendas = delegate.agendas;
	
	[table reloadData];
	
	[[Const sharedInstance].loading stop];
	[[Const sharedInstance].loading release];
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
	[self.agendas release];
	
    [super dealloc];
}




- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath {
	return 50.0;
}
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
	Agenda *sel = [self.agendas objectAtIndex: indexPath.row];
	static NSString *MyIdentifier = @"Agendas";
	AgendaTableCell	*cell = (AgendaTableCell *) [tableView dequeueReusableCellWithIdentifier:MyIdentifier]; 
	if (cell == nil) {
		cell = [[AgendaTableCell alloc] initWithFrame:CGRectZero reuseIdentifier:MyIdentifier];
		cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
	}
	[cell setData:sel];
	return cell;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
	return [self.agendas count];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
	return 1;
}
- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	Agenda *sel = [self.agendas objectAtIndex: indexPath.row];
	AgendaDetailViewController *next = [[AgendaDetailViewController alloc] initWithAgenda: sel];
	[self.navigationController pushViewController: next animated:YES];
	sel.isAccessibilityElement;
		[tableView deselectRowAtIndexPath:indexPath animated:YES];
}



@end
