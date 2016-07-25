//
//  Pushes.m
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/19/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import "Pushes.h"
#import "Mensagens.h"
#import "VisualizadorWeb.h"

@implementation Pushes

@synthesize ccProvider,
			tvPushes,
			maPushesData,
			sNumeroRegistroPush,
			vwCarregando,
			vwSemRegistros,
			bbiRefresh;

/*
 // The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if ((self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil])) {
        // Custom initialization
    }
    return self;
}
*/

- (id)init {
	self.title = @"Push";
	bbiRefresh = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemRefresh 
															   target:self 
															   action:@selector(updatePushes)];
	self.navigationItem.leftBarButtonItem = bbiRefresh;
	[bbiRefresh setEnabled:NO];
	
	
	return self;
}

// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
	UIView *vwContainer = [UIView new];
	[vwContainer setFrame:CGRectMake(0, 0, 320, 367)];
	
	tvPushes = [[UITableView alloc] initWithFrame:CGRectMake(0, 0, 320, 367)
											style:UITableViewStylePlain];
	tvPushes.delegate = self;
	tvPushes.dataSource = self;
	[tvPushes setUserInteractionEnabled:NO];
	
	[vwContainer addSubview:tvPushes];
	[vwContainer setBackgroundColor:[UIColor yellowColor]];
	
	vwCarregando = [[Mensagens alloc] initWithFrame:CGRectMake(0, 0, 200, 130) 
											message:@"Carregando pushs, por favor aguarde." 
									   messageFrame:CGRectMake(20, 20, 160, 50) 
								  activityIndicator:YES];
	
	[vwCarregando setCenter:CGPointMake(vwContainer.frame.size.width/2, vwContainer.frame.size.height/2)];
	
	vwSemRegistros = [[Mensagens alloc] initWithFrame:CGRectMake(0, 0, 200, 100) 
											  message:@"Você não possui pushs." 
										 messageFrame:CGRectMake(20, 10, 160, 80) 
									activityIndicator:NO];
	
	[vwSemRegistros setCenter:CGPointMake(vwContainer.frame.size.width/2, vwContainer.frame.size.height/2)];
	
	[vwContainer addSubview:vwCarregando];
	[vwContainer addSubview:vwSemRegistros];
	
	self.view = vwContainer;
}

- (void)viewWillAppear:(BOOL)animated {
	[self updatePushes];
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
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}

#pragma mark -
#pragma mark Custom

- (void)updateInterface {
	[bbiRefresh setEnabled:YES];
	maPushesData = [[NSMutableArray arrayWithArray:[ccProvider retrieveData]] retain];
	[vwCarregando setHidden:YES];
	if ([maPushesData count] == 0) {
		[vwSemRegistros setHidden:NO];
	} else {
		for (NSArray *aCurrentPush in maPushesData) {
			UITabBarItem *tbiPushes = [self.tabBarController.tabBar.items objectAtIndex:1];
			if ([[aCurrentPush objectAtIndex:1] isEqual:@"0"]) {
				iBadgeValue++;
				tbiPushes.badgeValue = [[NSString alloc] initWithFormat:@"%d", iBadgeValue];
				[UIApplication sharedApplication].applicationIconBadgeNumber = iBadgeValue;
			}
			if (iBadgeValue == 0) {
				tbiPushes.badgeValue = nil;
				[UIApplication sharedApplication].applicationIconBadgeNumber = 0;
			}
		}
		[tvPushes setUserInteractionEnabled:YES];
	}
	[tvPushes reloadData];
	if (sNumeroRegistroPush != nil) {
		VisualizadorWeb * vcVisualizador = [VisualizadorWeb new];
		[vcVisualizador setSTitulo:@"Detalhes do Processo"];
		[vcVisualizador setSRegistroProcesso:sNumeroRegistroPush];
		[self.navigationController pushViewController:vcVisualizador animated:YES];
		sNumeroRegistroPush = nil;
	}
	
}

- (void)updatePushes {
	iBadgeValue = 0;
	[bbiRefresh setEnabled:NO];
	[ccProvider setIdResponseClass:self];
	[ccProvider setADidReadSelectors:[[NSArray alloc] initWithObjects:@"updateInterface", nil]];
	[ccProvider setAReconnectSelectors:[[NSArray alloc] initWithObjects:@"updatePushes", nil]];
	[tvPushes setUserInteractionEnabled:NO];
	[vwCarregando setHidden:NO];
	[ccProvider sendCommand:@"listapushes"];
	[vwSemRegistros setHidden:YES];
}

#pragma mark -
#pragma mark UITableViewDataSource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
	return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
	return [maPushesData count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
	static NSString *sCellIdentifier = @"Cell";
    
    UITableViewCell *tvcCell = [tableView dequeueReusableCellWithIdentifier:sCellIdentifier];
    if (tvcCell == nil) {
        tvcCell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:sCellIdentifier] autorelease];
    }
	
    for (UIView *vwExisting in tvcCell.contentView.subviews) {
		[vwExisting removeFromSuperview];
	}
	
	/*
	UILabel *lblCell = [[UILabel alloc] initWithFrame:CGRectMake(10, 5, 280, 30)];
	*/
	if ([[[maPushesData objectAtIndex:indexPath.row] objectAtIndex:1] isEqual:@"1"]) {
		tvcCell.textLabel.font = [UIFont systemFontOfSize:16];
	} else {
		tvcCell.textLabel.font = [UIFont boldSystemFontOfSize:16];
	}
	tvcCell.detailTextLabel.adjustsFontSizeToFitWidth = YES;
	
	
	//[lblCell setText:[[maPushesData objectAtIndex:indexPath.row] objectAtIndex:2]];
	
	tvcCell.textLabel.text = [[maPushesData objectAtIndex:indexPath.row] objectAtIndex:6];
	tvcCell.detailTextLabel.text = [NSString stringWithFormat:@"Nº Proc.:%@ Nº Reg.:%@ Data:%@ %@", [[maPushesData objectAtIndex:indexPath.row] objectAtIndex:2], [[maPushesData objectAtIndex:indexPath.row] objectAtIndex:3], [[maPushesData objectAtIndex:indexPath.row] objectAtIndex:4], [[maPushesData objectAtIndex:indexPath.row] objectAtIndex:5]];
	
	//[tvcCell.contentView addSubview:lblCell];
	[tvcCell setSelectionStyle:UITableViewCellSelectionStyleNone];
	
    return tvcCell;
}

#pragma mark -
#pragma mark UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	//token#[token]|li|[ID PROCESSO]
	[ccProvider sendCommand:[[NSString alloc] initWithFormat:@"li|%@", [[maPushesData objectAtIndex:indexPath.row] objectAtIndex:0]]];
	VisualizadorWeb * vcVisualizador = [VisualizadorWeb new];
	[vcVisualizador setSTitulo:@"Detalhes do Processo"];
	[vcVisualizador setSRegistroProcesso:[[maPushesData objectAtIndex:indexPath.row] objectAtIndex:3]];
	[self.navigationController pushViewController:vcVisualizador animated:YES];
	//[vcVisualizador release];
}

- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if (editingStyle == UITableViewCellEditingStyleDelete) {
		NSString *sIdToRemove = [[NSString alloc] initWithString:[[maPushesData objectAtIndex:indexPath.row] objectAtIndex:0]];
		[maPushesData removeObjectAtIndex:indexPath.row];
		[ccProvider sendCommand:[[NSString alloc] initWithFormat:@"removepush|%@", sIdToRemove]];
        [tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:YES];
		if ([maPushesData count] == 0) {
			[tvPushes setUserInteractionEnabled:NO];
			[vwSemRegistros setHidden:NO];
		}
    }   
}

@end
