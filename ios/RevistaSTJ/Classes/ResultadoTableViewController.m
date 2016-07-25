//
//  ResultadoTableViewController.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 09/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "ResultadoTableViewController.h"
#import "VOResultado.h"
#import "StringExtras.h"
#import "DetalhaProcessoViewController.h"
#import "ConteudoViewController.h"
#import "PesquisaViewController.h"
 #import <QuartzCore/QuartzCore.h>
@implementation ResultadoTableViewController
@synthesize listaResultados,
sectionList, indexList,
dicionarioTabela,
detalhaVc,
conteudoVc,
viewAnterior,
lblNotFound;

- (void)viewDidLoad {
    [super viewDidLoad];

	//
	CGRect cr = self.view.frame;
	int espacoLateral = 10;
	int tamanhoVertical = 100;
	self.lblNotFound = [[[UILabel alloc] initWithFrame: CGRectMake( espacoLateral, 
																    (cr.size.height - tamanhoVertical*1.5)/2,
																   cr.size.width - 2*espacoLateral,													   
																   tamanhoVertical)] autorelease];
	self.lblNotFound.text = @"Nenhum resultado encontrado.";
	[self.view addSubview: self.lblNotFound];
	self.lblNotFound.hidden = (self.listaResultados != nil && [self.listaResultados count] != 0);
	self.lblNotFound.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:.10];
	self.lblNotFound.layer.cornerRadius = 6;
	self.lblNotFound.textAlignment = UITextAlignmentCenter;
	
}

#pragma mark Table view methods

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	LOAD = [[[UILoading alloc] initWithView: self.viewAnterior.view] autorelease];
	
	
	NSString *sectionSel = [self.indexList objectAtIndex: indexPath.section];
	NSMutableArray *resultados = [dicionarioTabela objectForKey: sectionSel];
	VOResultado *vo = [resultados objectAtIndex: indexPath.row];	

	self.detalhaVc = [[DetalhaProcessoViewController alloc] initWithNibName: @"DetalhaProcessoView" 
						bundle:nil];
	self.detalhaVc.tituloProcesso = [self.sectionList objectAtIndex: indexPath.section];
	self.detalhaVc.topicos = [VOTopico listarPorProcessoSemTexto: vo.topico.processo_id];
	self.detalhaVc.resultado = vo;

	self.detalhaVc.navigationItem.title = @"Processo";
	
	if (self.detalhaVc.resultado != nil) {
		//  CHAMA PROXIMA VIEW
		self.detalhaVc.contVc = [[[ConteudoViewController alloc]
						initWithNibName:@"ConteudoView" bundle:nil] autorelease];
		
		self.detalhaVc.contVc.navigationItem.title = self.detalhaVc.resultado.topico.nome;
		
		self.detalhaVc.contVc.topicos = self.detalhaVc.topicos;
		
		NSInteger x=0;
		for (VOTopico *t in self.detalhaVc.topicos) {
			if (t.id == self.detalhaVc.resultado.topico.id) {
				self.detalhaVc.resultado.topico.indice = t.indice;
				break;
			}
			x++;
		}
		[self.detalhaVc.topicos replaceObjectAtIndex: x withObject: self.detalhaVc.resultado.topico];
		self.detalhaVc.contVc.index = self.detalhaVc.resultado.topico.indice;
		[self.detalhaVc.resultado.topico marcarTexto: self.detalhaVc.resultado.textoBusca];
		self.detalhaVc.contVc.topico = self.detalhaVc.resultado.topico;

	}
	
		[self.viewAnterior.navigationController pushViewController: self.detalhaVc animated:YES];
}


- (void)dealloc {
	if (DEBUG)
		NSLog(@"DEALLOC: %@", [self class]);
    [super dealloc];
}

// TABLE COM MAIS DE UMA LINHA

#define CONST_Cell_height 44.0f
#define CONST_Cell_width 270.0f

#define CONST_textLabelFontSize     17
#define CONST_detailLabelFontSize   15

static UIFont *subFont;
static UIFont *titleFont;

- (UIFont*) TitleFont;
{
	if (!titleFont) titleFont = [UIFont boldSystemFontOfSize:CONST_textLabelFontSize];
	return titleFont;
}

- (UIFont*) SubFont;
{
	if (!subFont) subFont = [UIFont systemFontOfSize:CONST_detailLabelFontSize];
	return subFont;
}

- (UITableViewCell*) CreateMultilinesCell :(NSString*)cellIdentifier
{
	UITableViewCell *cell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle 
													reuseIdentifier:cellIdentifier] autorelease];
	
	cell.textLabel.numberOfLines = 0;
	cell.textLabel.font = [self TitleFont];
	
	cell.detailTextLabel.numberOfLines = 0;
	cell.detailTextLabel.font = [self SubFont];
	
	return cell;
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return [self.indexList count];
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
	
	NSMutableArray *a = [self.dicionarioTabela objectForKey: [self.indexList objectAtIndex: section]];
	return [a count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView 
		 cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    static NSString *CellIdentifier = @"Cell";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    if (cell == nil) {
        cell = [self CreateMultilinesCell:CellIdentifier];
    }

	NSMutableArray *vosDaSecao = [self.dicionarioTabela objectForKey: [self.indexList objectAtIndex: indexPath.section]];
	VOResultado *vo = [vosDaSecao objectAtIndex: indexPath.row];
	

	NSString *title = vo.topico.nome;
	NSString *subtitle = vo.linhaOcorrencia;
	
	cell.textLabel.text = title;
	cell.detailTextLabel.text = subtitle ;
	 
    return cell;
}

- (CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
	NSMutableArray *vosDaSecao = [self.dicionarioTabela objectForKey: [self.indexList objectAtIndex: indexPath.section]];
	VOResultado *vo = [vosDaSecao objectAtIndex: indexPath.row];
	
	
	NSString *title = vo.topico.nome;
	NSString *subtitle = vo.linhaOcorrencia;
	

	int height = 10 + [self heightOfCellWithTitle:title andSubtitle:subtitle];
	return (height < CONST_Cell_height ? CONST_Cell_height : height);
}

- (int) heightOfCellWithTitle :(NSString*)titleText 
				   andSubtitle:(NSString*)subtitleText
{
	CGSize titleSize = {0, 0};
	CGSize subtitleSize = {0, 0};
	
	if (titleText && ![titleText isEqualToString:@""]) 
		titleSize = [titleText sizeWithFont:[self TitleFont] 
						  constrainedToSize:CGSizeMake(CONST_Cell_width, 4000) 
							  lineBreakMode:UILineBreakModeWordWrap];
	
	if (subtitleText && ![subtitleText isEqualToString:@""]) 
		subtitleSize = [subtitleText sizeWithFont:[self SubFont] 
								constrainedToSize:CGSizeMake(CONST_Cell_width, 4000) 
									lineBreakMode:UILineBreakModeWordWrap];
	
	return titleSize.height + subtitleSize.height;
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
	return [sectionList objectAtIndex: section];
}


- (void)didReceiveMemoryWarning {
	NSLog(@"AVISO DE MEMORIA: %@", [self class]);
    [super didReceiveMemoryWarning];
}
@end

