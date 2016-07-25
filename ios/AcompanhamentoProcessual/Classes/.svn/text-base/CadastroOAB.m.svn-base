//
//  CadastroOAB.m
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/20/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import "CadastroOAB.h"

@implementation CadastroOAB

@synthesize aForm,
			bbiSave,
			ccProvider;

#pragma mark -
#pragma mark Initialization

- (id)initWithStyle:(UITableViewStyle)style {
    // Override initWithStyle: if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
    if ((self = [super initWithStyle:style])) {
		self.title = @"Cadastrar";
		bbiSave = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemOrganize 
																target:self 
																action:@selector(cadastrarOAB)];
		self.navigationItem.rightBarButtonItem = bbiSave;
		
		UITextField *tfOAB = [UITextField new];
		[tfOAB setAutocorrectionType:UITextAutocorrectionTypeNo];
		[tfOAB setAutocapitalizationType:UITextAutocapitalizationTypeNone];
		[tfOAB setDelegate:self];
		[tfOAB setReturnKeyType:UIReturnKeyDone];
		[tfOAB setAutocapitalizationType:UITextAutocapitalizationTypeAllCharacters];
		[tfOAB setPlaceholder:@"Informe a OAB"];
		[tfOAB setFrame:CGRectMake(10, 10, 280, 25)];
		[tfOAB setClearButtonMode:UITextFieldViewModeWhileEditing];
		
		aForm = [[NSArray alloc] initWithObjects:tfOAB, nil];
	}
    return self;
}


#pragma mark -
#pragma mark Custom

- (void)cadastrarOAB {
	//token#[token]|cadastraacompanhamento|[1 ou 2]|[id da classe / null]|[num processo ou num registro]
	[bbiSave setEnabled:NO];
	UITextField *tfOAB = [aForm objectAtIndex:0];
	if (tfOAB.text.length < 8) {
		UIAlertView *avErro = [[UIAlertView alloc] initWithTitle:@"Erro" 
														 message:@"A inscrição de OAB inválida." 
														delegate:self 
											   cancelButtonTitle:@"Ok" 
											   otherButtonTitles:nil];
		[avErro show];
		[avErro release];
	} else {
		[ccProvider sendCommand:[[NSString alloc] initWithFormat:@"cadastraacompanhamento|1#null#%@", [tfOAB.text uppercaseString]]];
	}
}

- (void)updateInterface {
	if ([[[[ccProvider retrieveData] objectAtIndex:0] objectAtIndex:0] isEqual:@"ok"]) {
		[self.navigationController popToViewController:[self.navigationController.viewControllers objectAtIndex:0] animated:YES];
	} else {
		UIAlertView *avErro = [[UIAlertView alloc] initWithTitle:@"Erro" 
														 message:@"Ocorreu um erro ao cadastrar OAB para acompahamento" 
														delegate:self 
											   cancelButtonTitle:@"Ok" 
											   otherButtonTitles:nil];
		[avErro show];
		[avErro release];
	}
	[bbiSave setEnabled:YES];
} 

#pragma mark -
#pragma mark View lifecycle

/*
- (void)viewDidLoad {
    [super viewDidLoad];

    // Uncomment the following line to preserve selection between presentations.
    self.clearsSelectionOnViewWillAppear = NO;
 
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}
*/

- (void)viewWillAppear:(BOOL)animated {
    [ccProvider setIdResponseClass:self];
	[ccProvider setADidReadSelectors:[[NSArray alloc] initWithObjects:@"updateInterface", nil]];
	[ccProvider setADisconnectedSelectors:nil];
	[ccProvider setAReconnectSelectors:nil];
	[super viewWillAppear:animated];
}

/*
- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
}
*/
/*
- (void)viewWillDisappear:(BOOL)animated {
    [super viewWillDisappear:animated];
}
*/
/*
- (void)viewDidDisappear:(BOOL)animated {
    [super viewDidDisappear:animated];
}
*/
/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/


#pragma mark -
#pragma mark Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    // Return the number of sections.
    return 1;
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
	return @"Cadastrar OAB";
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    // Return the number of rows in the section.
	return [aForm count];
}


// Customize the appearance of table view cells.
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
	static NSString *sCellIdentifier = @"Cell";
    
    UITableViewCell *tvcCell = [tableView dequeueReusableCellWithIdentifier:sCellIdentifier];
    if (tvcCell == nil) {
        tvcCell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:sCellIdentifier] autorelease];
    }
	
    for (UIView *vwExisting in tvcCell.contentView.subviews) {
		[vwExisting removeFromSuperview];
	}
	
	[tvcCell.contentView addSubview:[aForm objectAtIndex:indexPath.row]];
	[tvcCell setSelectionStyle:UITableViewCellSelectionStyleNone];
	
    return tvcCell;
}


/*
// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the specified item to be editable.
    return YES;
}
*/


/*
// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source
        [tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:YES];
    }   
    else if (editingStyle == UITableViewCellEditingStyleInsert) {
        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
    }   
}
*/


/*
// Override to support rearranging the table view.
- (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath {
}
*/


/*
// Override to support conditional rearranging of the table view.
- (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the item to be re-orderable.
    return YES;
}
*/


#pragma mark -
#pragma mark Table view delegate

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    // Navigation logic may go here. Create and push another view controller.
	/*
	 <#DetailViewController#> *detailViewController = [[<#DetailViewController#> alloc] initWithNibName:@"<#Nib name#>" bundle:nil];
     // ...
     // Pass the selected object to the new view controller.
	 [self.navigationController pushViewController:detailViewController animated:YES];
	 [detailViewController release];
	 */
}

#pragma mark -
#pragma mark UITextFieldDelegate

- (BOOL)textField:(UITextField *)textField shouldChangeCharactersInRange:(NSRange)range replacementString:(NSString *)string {
	if (textField.text.length < 8 || [string isEqual:@""]) {
		return YES;
	} else {
		return NO;
	}
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
	[textField resignFirstResponder];
	[self cadastrarOAB];
	return NO;
}

#pragma mark -
#pragma mark Memory management

- (void)didReceiveMemoryWarning {
    // Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
    
    // Relinquish ownership any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
    // Relinquish ownership of anything that can be recreated in viewDidLoad or on demand.
    // For example: self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}


@end

