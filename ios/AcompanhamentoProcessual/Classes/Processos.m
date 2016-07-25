//
//  Processos.m
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/19/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import "Processos.h"
#import "Mensagens.h"
#import "CadastroOAB.h"
#import "PesquisaProcessos.h"

@implementation Processos

@synthesize ccProvider,
			tvProcessos,
			maProcessosData,
			maOABsCadastradas,
			maProcessosCadastrados,
			aSections,
			vwCarregando,
			vwSemRegistros,
			bbiRefresh,
			bbiAdd,
			bbiEdit;

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
	self.title = @"Acompanhamentos";
	maOABsCadastradas = [NSMutableArray new];
	maProcessosCadastrados = [NSMutableArray new];
	aSections = [[NSArray alloc] initWithObjects:@"Por OAB", @"Por Processo", nil];

	bbiRefresh = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemRefresh 
															   target:self 
															   action:@selector(updateProcessos)];
	
	bbiAdd = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemAdd
														   target:self 
														   action:@selector(cadastrarProcesso)];
	
	bbiEdit = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemEdit
															target:self 
															action:@selector(editaProcessos)];
	
	[bbiRefresh setEnabled:NO];
	[bbiAdd setEnabled:NO];
	[bbiEdit setEnabled:NO];
	
	
	self.navigationItem.leftBarButtonItem = bbiRefresh;
	self.navigationItem.rightBarButtonItem = bbiAdd;
	self.toolbarItems = [[NSArray alloc] initWithObjects:bbiEdit, nil];
	return self;
}

// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
	UIView *vwContainer = [UIView new];
	[vwContainer setFrame:CGRectMake(0, 0, 320, 367)];
	
	tvProcessos = [[UITableView alloc] initWithFrame:CGRectMake(0, 0, 320, 367)
											   style:UITableViewStylePlain];
	tvProcessos.delegate = self;
	tvProcessos.dataSource = self;
	[tvProcessos setUserInteractionEnabled:NO];
	
	[vwContainer addSubview:tvProcessos];
	[vwContainer setBackgroundColor:[UIColor yellowColor]];
	
	vwCarregando = [[Mensagens alloc] initWithFrame:CGRectMake(0, 0, 200, 180) 
											message:@"Carregando acompanhamentos cadastrados, por favor aguarde." 
									   messageFrame:CGRectMake(20, 20, 160, 100) 
								  activityIndicator:YES];
	
	[vwCarregando setCenter:CGPointMake(vwContainer.frame.size.width/2, vwContainer.frame.size.height/2)];
	
	vwSemRegistros = [[Mensagens alloc] initWithFrame:CGRectMake(0, 0, 200, 100) 
											  message:@"Você não possui acompanhamentos cadastrados." 
										 messageFrame:CGRectMake(20, 10, 160, 80) 
									activityIndicator:NO];
	
	[vwSemRegistros setCenter:CGPointMake(vwContainer.frame.size.width/2, vwContainer.frame.size.height/2)];
	
	[vwContainer addSubview:vwCarregando];
	[vwContainer addSubview:vwSemRegistros];
	
	//self.navigationController.toolbarHidden = NO;
	self.view = vwContainer;
}

- (void)viewWillAppear:(BOOL)animated {
	[self updateProcessos];
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
	[bbiAdd setEnabled:YES];
	maProcessosData = [ccProvider retrieveData];
	[vwCarregando setHidden:YES];
	if ([maProcessosData count] == 0) {
		[vwSemRegistros setHidden:NO];
	} else {
		[maOABsCadastradas removeAllObjects];
		[maProcessosCadastrados removeAllObjects];
		for (NSArray *aRegistro in maProcessosData) {
			if ([[aRegistro objectAtIndex:1] isEqual:@"1"]) {
				[maOABsCadastradas addObject:aRegistro];
			} else {
				[maProcessosCadastrados addObject:aRegistro];
			}
		}
		[tvProcessos setUserInteractionEnabled:YES];
		[bbiEdit setEnabled:YES];
	}
	[tvProcessos reloadData];
}

- (void)updateProcessos {
	[bbiRefresh setEnabled:NO];
	[bbiAdd setEnabled:NO];
	[ccProvider setIdResponseClass:self];
	[ccProvider setADidReadSelectors:[[NSArray alloc] initWithObjects:@"updateInterface", nil]];
	[ccProvider setAReconnectSelectors:[[NSArray alloc] initWithObjects:@"updateProcessos", nil]];
	[tvProcessos setUserInteractionEnabled:NO];
	[vwCarregando setHidden:NO];
	[ccProvider sendCommand:@"listaacompanhamentos"];
	[vwSemRegistros setHidden:YES];
}

- (void)cadastrarProcesso {
	UIActionSheet *asSelecionaTipoCadastro = [[UIActionSheet alloc] initWithTitle:@"Deseja cadastrar o acompanhamento por:" 
																		  delegate:self 
																 cancelButtonTitle:@"Cancelar" 
															destructiveButtonTitle:nil 
																 otherButtonTitles:@"OAB", @"Pesquisar Processos", nil];
	[asSelecionaTipoCadastro showInView:self.parentViewController.view];
	[asSelecionaTipoCadastro release];
}

- (void)editaProcessos {
	[tvProcessos setEditing:YES];
}

#pragma mark -
#pragma mark UITableViewDelegate

- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        NSString *sIdToRemove;
		if (indexPath.section == 0) {
			sIdToRemove = [[NSString alloc] initWithString:[[maOABsCadastradas objectAtIndex:indexPath.row] objectAtIndex:0]];
			[maOABsCadastradas removeObjectAtIndex:indexPath.row];
		} else {
			sIdToRemove = [[NSString alloc] initWithString:[[maProcessosCadastrados objectAtIndex:indexPath.row] objectAtIndex:0]];
			[maProcessosCadastrados removeObjectAtIndex:indexPath.row];
		}
		[ccProvider sendCommand:[[NSString alloc] initWithFormat:@"removeacompanhamento|%@", sIdToRemove]];
        [tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:YES];
		if ([maOABsCadastradas count] == 0 && [maProcessosCadastrados count] == 0) {
			[tvProcessos setUserInteractionEnabled:NO];
			[vwSemRegistros setHidden:NO];
		}
    }   
}

#pragma mark -
#pragma mark UITableViewDataSource

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
	return [aSections count];
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
	return [aSections objectAtIndex:section];
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
	int iRows = 0;
	switch (section) {
		case 0:
			iRows = [maOABsCadastradas count];
			break;
		case 1:
			iRows = [maProcessosCadastrados count];
			break;
	}
	return iRows;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
	static NSString *sCellIdentifier = @"Cell";
    
    UITableViewCell *tvcCell = [tableView dequeueReusableCellWithIdentifier:sCellIdentifier];
    if (tvcCell == nil) {
        if (indexPath.section == 0) {
			tvcCell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:sCellIdentifier] autorelease];
		} else {
			tvcCell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:sCellIdentifier] autorelease];
		}
    } else {
		if (indexPath.section == 0) {
			tvcCell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:sCellIdentifier] autorelease];
		} else {
			tvcCell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle reuseIdentifier:sCellIdentifier] autorelease];
		}
	}
	
    for (UIView *vwExisting in tvcCell.contentView.subviews) {
		[vwExisting removeFromSuperview];
	}
	
	switch (indexPath.section) {
		case 0:
			tvcCell.textLabel.text = [[maOABsCadastradas objectAtIndex:indexPath.row] objectAtIndex:2];
			break;
		case 1:
			tvcCell.textLabel.text = [[maProcessosCadastrados objectAtIndex:indexPath.row] objectAtIndex:2];
			tvcCell.detailTextLabel.text = [NSString stringWithFormat:@"Nº de registro: %@", [[maProcessosCadastrados objectAtIndex:indexPath.row] objectAtIndex:3]];
			break;
	}
	[tvcCell setSelectionStyle:UITableViewCellSelectionStyleNone];
    return tvcCell;
}

#pragma mark -
#pragma mark UIActionSheetDelegate

- (void)actionSheet:(UIActionSheet *)actionSheet didDismissWithButtonIndex:(NSInteger)buttonIndex {
	switch (buttonIndex) {
		case 0:;
			CadastroOAB *vcCadastroOAB = [[CadastroOAB alloc] initWithStyle:UITableViewStyleGrouped];
			[vcCadastroOAB setCcProvider:ccProvider];
			[self.navigationController pushViewController:vcCadastroOAB animated:YES];
			[vcCadastroOAB release];
			break;
		case 1:;
			PesquisaProcessos *vcPesquisaProcessos = [[PesquisaProcessos alloc] initWithStyle:UITableViewStyleGrouped];
			[vcPesquisaProcessos setCcProvider:ccProvider];
			[self.navigationController pushViewController:vcPesquisaProcessos animated:YES];
			[vcPesquisaProcessos release];
			break;
	}
}

@end
