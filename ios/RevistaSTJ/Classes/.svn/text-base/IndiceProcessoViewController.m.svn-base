//
//  IndiceProcessoViewController.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 30/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "IndiceProcessoViewController.h"
#import "IndiceViewController.h";
#import "DetalhaProcessoViewController.h"

@implementation IndiceProcessoViewController
@synthesize indiceVc,
processos,
lista,
detalheVc;


- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {	
	VOProcesso *vop = [self.processos objectAtIndex: indexPath.row];

	//  CHAMA PROXIMA VIEW
	self.detalheVc = [[[DetalhaProcessoViewController alloc]
						initWithNibName:@"DetalhaProcessoView" bundle:nil] autorelease];
	[self.navigationController pushViewController:self.detalheVc animated: YES];
	
	self.detalheVc.navigationItem.title = @"Processo";
	self.detalheVc.tituloProcesso = vop.nome;
	self.detalheVc.topicos = [VOTopico listarPorProcessoSemTexto: vop.id];
}
- (void) viewDidAppear:(BOOL)animated {
	[super viewDidAppear: YES];
}

- (void)dealloc {
	if (DEBUG)
		NSLog(@"DEALLOC: %@", [self class]);
	[indiceVc release];
	[detalheVc release];
	[lista release];
	[processos release];
    [super dealloc];
}


- (NSInteger) tableView : (UITableView *) tableView numberOfRowsInSection : (NSInteger) section {
	return [self.processos count];
}

- (UITableViewCell *) tableView : (UITableView *) tableView cellForRowAtIndexPath : (NSIndexPath *) indexPath {
    
    static NSString *CellIdentifier = @"Cell";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    if (cell == nil) {
        cell = [self CreateMultilinesCell:CellIdentifier];
    }
	
	
	VOProcesso *sel = [self.processos objectAtIndex: indexPath.row];
	cell.textLabel.text = sel.nome;

	return cell;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}


// MAIS DE UMA LINHA

#define CONST_Cell_height 44.0f
#define CONST_Cell_width 270.0f

#define CONST_textLabelFontSize     17
#define CONST_detailLabelFontSize   15

static UIFont *titleFont;

- (UIFont*) TitleFont;
{
	if (!titleFont) titleFont = [UIFont boldSystemFontOfSize:CONST_textLabelFontSize];
	return titleFont;
}
/*
- (UIFont*) SubFont;
{
	if (!subFont) subFont = [UIFont systemFontOfSize:CONST_detailLabelFontSize];
	return subFont;
}
*/

- (UITableViewCell*) CreateMultilinesCell :(NSString*)cellIdentifier
{
	UITableViewCell *cell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle 
													reuseIdentifier:cellIdentifier] autorelease];
	
	cell.textLabel.numberOfLines = 0;
	cell.textLabel.font = [self TitleFont];
	
	cell.detailTextLabel.numberOfLines = 0;
	
	return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
	VOProcesso *sel = [self.processos objectAtIndex: indexPath.row];
	NSString *title = sel.nome;
	
	int height = 20 + [self heightOfCellWithTitle:title];

	return (height < CONST_Cell_height ? CONST_Cell_height : height);
}

- (int) heightOfCellWithTitle :(NSString*)titleText 
{
	CGSize titleSize = {0, 0};
	//CGSize subtitleSize = {0, 0};
	
	if (titleText && ![titleText isEqualToString:@""]) 
		titleSize = [titleText sizeWithFont:[self TitleFont] 
						  constrainedToSize:CGSizeMake(CONST_Cell_width, 4000) 
							  lineBreakMode:UILineBreakModeWordWrap];
	
	return titleSize.height;
}
- (void)didReceiveMemoryWarning {
	NSLog(@"AVISO DE MEMORIA: %@", [self class]);
    [super didReceiveMemoryWarning];
}




@end
