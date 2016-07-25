//
//  PesquisaProcessos.m
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/25/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import "PesquisaProcessos.h"
#import "ResultadoPesquisaProcessos.h"


@implementation PesquisaProcessos

@synthesize ccProvider,
			aForm,
			pvClassesProcesso,
			asClassesProcesso,
			maClassesProcesso,
			tfCurrent,
			bbiSearch;

#pragma mark -
#pragma mark Initialization


- (id)initWithStyle:(UITableViewStyle)style {
    // Override initWithStyle: if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
    if ((self = [super initWithStyle:style])) {
		self.title = @"Pesquisar";
		bbiSearch = [[UIBarButtonItem alloc] initWithBarButtonSystemItem:UIBarButtonSystemItemSearch 
																  target:self 
																  action:@selector(pesquisarProcessos)];
		self.navigationItem.rightBarButtonItem = bbiSearch;
		
		maClassesProcesso = [[NSMutableArray alloc] init];
		
		pvClassesProcesso = [[UIPickerView alloc] initWithFrame:CGRectMake(0, 480, 320, 216)];
		pvClassesProcesso.dataSource = self;
		pvClassesProcesso.delegate = self;
		pvClassesProcesso.showsSelectionIndicator = YES;
		
		UITextField *tfClasseProcesso = [UITextField new];
		[tfClasseProcesso setDelegate:self];
		[tfClasseProcesso setFrame:CGRectMake(10, 10, 280, 25)];
		[tfClasseProcesso setPlaceholder:@"Classe do Processo"];
		[tfClasseProcesso setTag:1];
		
		UITextField *tfNumeroProcesso = [UITextField new];
		[tfNumeroProcesso setDelegate:self];
		[tfNumeroProcesso setKeyboardType:UIKeyboardTypeNumberPad];
		[tfNumeroProcesso setReturnKeyType:UIReturnKeyDone];
		[tfNumeroProcesso setFrame:CGRectMake(10, 10, 280, 25)];
		[tfNumeroProcesso setPlaceholder:@"Número do Processo"];
		[tfNumeroProcesso setTag:2];
		
		UITextField *tfRegistroProcesso = [UITextField new];
		[tfRegistroProcesso setDelegate:self];
		[tfRegistroProcesso setKeyboardType:UIKeyboardTypeNumberPad];
		[tfRegistroProcesso setReturnKeyType:UIReturnKeyDone];
		[tfRegistroProcesso setFrame:CGRectMake(10, 10, 280, 25)];
		[tfRegistroProcesso setPlaceholder:@"Número do Registro"];
		[tfRegistroProcesso setTag:3];
		
		aForm = [[NSArray alloc] initWithObjects:tfClasseProcesso, tfNumeroProcesso, tfRegistroProcesso, nil];
		
		/*
		asClassesProcesso = [[UIActionSheet alloc] initWithTitle:@"Selecione a classe do processo" 
														delegate:self 
											   cancelButtonTitle:nil 
										  destructiveButtonTitle:nil 
											   otherButtonTitles:nil];
		*/
		[self.view addSubview:pvClassesProcesso];
	}
    return self;
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
    [self updateClassesProcesso];
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
#pragma mark Custom

- (void)mostraPicker {
	self.tableView.scrollEnabled = NO;
	[UIView beginAnimations:nil context:nil];
	[UIView setAnimationDuration:0.5];
	[UIView setAnimationCurve:UIViewAnimationCurveEaseInOut];
	[pvClassesProcesso setFrame:CGRectMake(0, 200, 320, 216)];
	[UIView commitAnimations];
}

- (void)ocultaPicker {
	self.tableView.scrollEnabled = YES;
	[UIView beginAnimations:nil context:nil];
	[UIView setAnimationDuration:0.5];
	[UIView setAnimationCurve:UIViewAnimationCurveEaseInOut];
	[pvClassesProcesso setFrame:CGRectMake(0, 480, 320, 216)];
	[UIView commitAnimations];
}

- (void)updateInterface {
	maClassesProcesso = [[[NSMutableArray alloc] init] retain];
	[maClassesProcesso addObject:[NSArray arrayWithObjects:@"", @"", nil]];
	for (NSArray *aCurrent in [ccProvider retrieveData]) {
		[maClassesProcesso addObject:aCurrent];
	}
	[pvClassesProcesso reloadAllComponents];
} 

- (void)updateClassesProcesso {
	[ccProvider setIdResponseClass:self];
	[ccProvider setADidReadSelectors:[[NSArray alloc] initWithObjects:@"updateInterface", nil]];
	[ccProvider setADisconnectedSelectors:nil];
	[ccProvider setAReconnectSelectors:nil];
	[ccProvider sendCommand:@"listaclassesprocesso"];
}

- (void)pesquisarProcessos {
	UITextField *tfClasseProcesso = [aForm objectAtIndex:0];
	UITextField *tfNumeroProcesso = [aForm objectAtIndex:1];
	UITextField *tfRegistroProcesso = [aForm objectAtIndex:2];
	
	UIAlertView *avErro = nil;
	
	if (tfCurrent == tfClasseProcesso || tfCurrent == tfNumeroProcesso) {
		if ((tfClasseProcesso.text == nil || [tfClasseProcesso.text isEqual:@""]) || (tfNumeroProcesso.text == nil || [tfNumeroProcesso.text isEqual:@""])) {
			avErro = [[UIAlertView alloc] initWithTitle:@"Erro" 
												message:@"É necessário informar a classe e o número do processo para a realização da pesquisa." 
											   delegate:self 
									  cancelButtonTitle:@"Ok" 
									  otherButtonTitles:nil];
		}
	} else if (tfCurrent == tfRegistroProcesso) {
		if (tfRegistroProcesso.text == nil || [tfRegistroProcesso.text isEqual:@""]) {
			avErro = [[UIAlertView alloc] initWithTitle:@"Erro" 
												message:@"É necessário informar o número do registro do processo para a realização da pesquisa." 
											   delegate:self 
									  cancelButtonTitle:@"Ok" 
									  otherButtonTitles:nil];
		}
	} else {
		if ((tfClasseProcesso.text == nil || [tfClasseProcesso.text isEqual:@""]) && (tfNumeroProcesso.text == nil || [tfNumeroProcesso.text isEqual:@""]) && (tfRegistroProcesso.text == nil || [tfRegistroProcesso.text isEqual:@""])) {
			avErro = [[UIAlertView alloc] initWithTitle:@"Erro" 
												message:@"O preenchimento de classe e número do processo (ambos) ou número do registro do processo é necessário para a realização da pesquisa." 
											   delegate:self 
									  cancelButtonTitle:@"Ok" 
									  otherButtonTitles:nil];
		}
	}
	
	if (avErro != nil) {
		[avErro show];
		[avErro release];
	} else {
		[bbiSearch setEnabled:NO];
		[ccProvider setADidReadSelectors:[[NSArray alloc] initWithObjects:@"apresentarResultados", nil]];
		if (tfCurrent == tfClasseProcesso || tfCurrent == tfNumeroProcesso) { 
			[ccProvider sendCommand:[NSString stringWithFormat:@"pesquisaprocessos|%d#%@#null", iIdClasseProcessoSelecionado, tfNumeroProcesso.text]];
		} else {
			[ccProvider sendCommand:[NSString stringWithFormat:@"pesquisaprocessos|null#null#%@", tfRegistroProcesso.text]];
		}
	}
}

- (void)apresentarResultados {
	if ([[ccProvider retrieveData] count] > 0) {
		ResultadoPesquisaProcessos *vcResultadoPesquisa = [ResultadoPesquisaProcessos new];
		[vcResultadoPesquisa setMaResultadoPesquisa:[[NSMutableArray arrayWithArray:[ccProvider retrieveData]] retain]];
		[vcResultadoPesquisa setCcProvider:ccProvider];
		[self.navigationController pushViewController:vcResultadoPesquisa animated:YES];
		[vcResultadoPesquisa release];
	} else {
		UIAlertView *avSemRegistros = [[UIAlertView alloc] initWithTitle:@"Sem ocorrências" 
																 message:@"Sua pesquisa não retornou nenhum resultado." 
																delegate:self
													   cancelButtonTitle:@"Ok" 
													   otherButtonTitles:nil];
		[avSemRegistros show];
		[avSemRegistros release];
	}
	[bbiSearch setEnabled:YES];
}

#pragma mark -
#pragma mark Picker view data source

- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView {
    return 1;
}

- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component {
    return [maClassesProcesso count];
}


#pragma mark -
#pragma mark UIPickerViewDelegate

- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component {
	return [[maClassesProcesso objectAtIndex:row] objectAtIndex:1];
}

- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component {
	UITextField *tfClasseProcesso = [aForm objectAtIndex:0];
	tfClasseProcesso.text = [[maClassesProcesso objectAtIndex:row] objectAtIndex:1];
	iIdClasseProcessoSelecionado = [[[maClassesProcesso objectAtIndex:row] objectAtIndex:0] intValue];
	NSLog(@"%d", iIdClasseProcessoSelecionado);
}

#pragma mark -
#pragma mark UITextFieldDelegate 

- (BOOL)textFieldShouldBeginEditing:(UITextField *)textField {
	tfCurrent = textField;
	UITextField *tfClasseProcesso = [aForm objectAtIndex:0];
	UITextField *tfNumeroProcesso = [aForm objectAtIndex:1];
	UITextField *tfRegistroProcesso = [aForm objectAtIndex:2];
	
	if ([textField tag] == 1 || [textField tag] == 2) {
		tfRegistroProcesso.text = nil;
	} else {
		tfClasseProcesso.text = nil;
		tfNumeroProcesso.text = nil;
	}

	
	if ([textField tag] == 1) {
		for (UITextField *tfCurrentToResign in aForm) {
			if ([tfCurrentToResign isFirstResponder]) {
				[tfCurrentToResign resignFirstResponder];
			}
		}
		[self mostraPicker];
		return NO;
	} else {
		[self ocultaPicker];
		return YES;
	}
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
	[tfCurrent resignFirstResponder];
	return NO;
}

#pragma mark -
#pragma mark Table view data source

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    // Return the number of sections.
    return 2;
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
	NSString *sSectionTitle;
	switch (section) {
		case 0:
			sSectionTitle = [[NSString alloc] initWithString:@"Pesquisar por"];
			break;
		case 1:
			sSectionTitle = [[NSString alloc] initWithString:@"ou"];
			return @"ou";
			break;
	}
	return sSectionTitle;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    // Return the number of rows in the section.
    int iRows = 0;
	switch (section) {
		case 0:
			iRows = 2;
			break;
		case 1:
			iRows = 1;
			break;
	}
	return iRows;
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
	
	switch (indexPath.section) {
		case 0:
			[tvcCell.contentView addSubview:[aForm objectAtIndex:indexPath.row]];
			break;
		case 1:
			[tvcCell.contentView addSubview:[aForm objectAtIndex:indexPath.row + 2]];
			break;
		default:
			break;
	}
	
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
    if (indexPath.row == 0) {
		//[asClassesProcesso showInView:[[[UIApplication sharedApplication] delegate] window]];
	}
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

