    //
//  ResultadoPesquisaProcessos.m
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/30/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import "ResultadoPesquisaProcessos.h"
#import "VisualizadorWeb.h";

@implementation ResultadoPesquisaProcessos

@synthesize tvResultadoPesquisa,
			maResultadoPesquisa,
			ccProvider,
			ipClicked;

- (id)init {
	self.title = @"Resultados";
	return self;
}

/*
 // The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if ((self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil])) {
        // Custom initialization
    }
    return self;
}
*/

// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
	UIView *vwContainer = [UIView new];
	[vwContainer setFrame:CGRectMake(0, 0, 320, 367)];
	
	tvResultadoPesquisa = [[UITableView alloc] initWithFrame:CGRectMake(0, 0, 320, 367)
													   style:UITableViewStylePlain];
	tvResultadoPesquisa.delegate = self;
	tvResultadoPesquisa.dataSource = self;
	
	[vwContainer addSubview:tvResultadoPesquisa];
	[vwContainer setBackgroundColor:[UIColor yellowColor]];
	self.view = vwContainer;
}

- (void)viewWillAppear:(BOOL)animated {
	[ccProvider setIdResponseClass:self];
	[ccProvider setADidReadSelectors:[[NSArray alloc] initWithObjects:@"resultadoCadastro", nil]];
	[ccProvider setADisconnectedSelectors:nil];
	[ccProvider setAReconnectSelectors:nil];
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

- (void)cadastraProcesso:(id)sender {
	ipClicked = [tvResultadoPesquisa indexPathForCell:(UITableViewCell *)[sender superview]];
	[ccProvider sendCommand:[[NSString alloc] initWithFormat:@"cadastraacompanhamento|2#null#%@", [[maResultadoPesquisa objectAtIndex:ipClicked.row] objectAtIndex:3]]];
}

- (void)resultadoCadastro {
	if ([[[[ccProvider retrieveData] objectAtIndex:0] objectAtIndex:0] isEqual:@"ok"]) {
		[maResultadoPesquisa removeObjectAtIndex:ipClicked.row];
		[tvResultadoPesquisa deleteRowsAtIndexPaths:[NSArray arrayWithObject:ipClicked] withRowAnimation:YES];
		if ([maResultadoPesquisa count] == 0) {
			[self.navigationController popToViewController:[self.navigationController.viewControllers objectAtIndex:1] animated:YES];
		}
	} else {
		UIAlertView *avErro = [[UIAlertView alloc] initWithTitle:@"Erro" 
														 message:@"Ocorreu um erro ao cadastrar o processo selecionado." 
														delegate:self 
											   cancelButtonTitle:@"Ok" 
											   otherButtonTitles:nil];
		[avErro show];
		[avErro release];
	}
}

#pragma mark -
#pragma mark UITableViewDataSource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
	return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
	return [maResultadoPesquisa count];
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
	
	//UILabel *lblCell = [[UILabel alloc] initWithFrame:CGRectMake(10, 5, 280, 30)];
	
	tvcCell.textLabel.text = [NSString stringWithFormat:@"%@ %@", [[maResultadoPesquisa objectAtIndex:indexPath.row] objectAtIndex:1], [[maResultadoPesquisa objectAtIndex:indexPath.row] objectAtIndex:2]];
	tvcCell.detailTextLabel.text = [NSString stringWithFormat:@"Nº Registro:%@", [[maResultadoPesquisa objectAtIndex:indexPath.row] objectAtIndex:3]];
	
	UIButton *btAdd = [UIButton buttonWithType:UIButtonTypeCustom];
	[btAdd setFrame:CGRectMake(0, 0, 50, 50)];
	[btAdd setImage:[[UIImage imageNamed:@"addprocesso.png"] autorelease] forState:UIControlStateNormal];
	[btAdd addTarget:self action:@selector(cadastraProcesso:) forControlEvents:UIControlEventTouchUpInside];
	
	tvcCell.accessoryView = btAdd;
	
	//[tvcCell.contentView addSubview:lblCell];
	[tvcCell setSelectionStyle:UITableViewCellSelectionStyleNone];
	
    return tvcCell;
}

#pragma mark -
#pragma mark UITableViewDelegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	VisualizadorWeb * vcVisualizador = [VisualizadorWeb new];
	[vcVisualizador setSTitulo:@"Pré Visualização"];
	[vcVisualizador setSRegistroProcesso:[[maResultadoPesquisa objectAtIndex:indexPath.row] objectAtIndex:3]];
	[self.navigationController pushViewController:vcVisualizador animated:YES];
	//[vcVisualizador release];
}

@end
