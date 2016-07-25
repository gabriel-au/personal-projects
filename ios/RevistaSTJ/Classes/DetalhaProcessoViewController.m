//
//  DetalhaProcessoViewController.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 30/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "DetalhaProcessoViewController.h"
#import "ProcessaArquivo.h";
#import "ConteudoViewController.h"
#import "VOResultado.h";

@implementation DetalhaProcessoViewController
@synthesize contVc,
tituloProcesso,
navItem,
resultado,
topicos;



// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
	
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {	
	
	VOTopico *vo = [self.topicos objectAtIndex: indexPath.row];
	[vo preencherTexto];
	
	//  CHAMA PROXIMA VIEW
	self.contVc = [[[ConteudoViewController alloc]
					   initWithNibName:@"ConteudoView" bundle:nil] autorelease];
	[self.navigationController pushViewController:self.contVc animated: YES];
	
	//self.contVc.conteudo = [self.processo.conteudo objectForKey: [NSString stringWithFormat: @"%d", indexPath.row]];
	self.contVc.navigationItem.title = vo.nome;
	self.contVc.index = indexPath.row;
	self.contVc.topicos = self.topicos;
	self.contVc.topico = vo;
}

- (void) viewDidAppear:(BOOL)animated {

	UILabel *label = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 500, 200)];
	[label setFont:[UIFont boldSystemFontOfSize:13.0]];
	[label setBackgroundColor:[UIColor clearColor]];
	[label setTextColor:[UIColor whiteColor]];
	[label setText: self.tituloProcesso];
	[label setNumberOfLines: 2];
	[label setTextAlignment: UITextAlignmentCenter];
	[self.navItem setTitleView:label];
	[label release];
	
	if (resultado != nil) {
		self.resultado = nil;
		[self.navigationController pushViewController:self.contVc animated: YES];
		/*
		//  CHAMA PROXIMA VIEW
		self.contVc = [[[ConteudoViewController alloc]
						initWithNibName:@"ConteudoView" bundle:nil] autorelease];
		
		self.contVc.navigationItem.title = self.resultado.topico.nome;

		self.contVc.topicos = self.topicos;
		
		NSInteger x=0;
		for (VOTopico *t in self.topicos) {
			if (t.id == self.resultado.topico.id) {
				self.resultado.topico.indice = t.indice;
				break;
			}
			x++;
		}
		[self.topicos replaceObjectAtIndex: x withObject: self.resultado.topico];
		self.contVc.index = self.resultado.topico.indice;
		[self.resultado.topico marcarTexto: self.resultado.textoBusca];
		self.contVc.topico = self.resultado.topico;
		self.resultado = nil;
		[self.navigationController pushViewController:self.contVc animated: YES];
		 */
	}

}

- (void)dealloc {
	if (DEBUG)
		NSLog(@"DEALLOC: %@", [self class]);
	[self.contVc release];
    [super dealloc];
}


- (NSInteger) tableView : (UITableView *) tableView numberOfRowsInSection : (NSInteger) section {
	return [self.topicos count];
}

- (UITableViewCell *) tableView : (UITableView *) tableView cellForRowAtIndexPath : (NSIndexPath *) indexPath {
    
	UITableViewCell *cell =  [tableView dequeueReusableCellWithIdentifier:@"celula"];
	if (cell == nil) {
		cell = [[[UITableViewCell alloc] initWithStyle: UITableViewCellStyleDefault  reuseIdentifier: @"celula"] autorelease];			
	}
	VOTopico *vosel = [self.topicos objectAtIndex: indexPath.row];
	cell.textLabel.text = vosel.nome;
	return cell;
}

- (void)didReceiveMemoryWarning {
	NSLog(@"AVISO DE MEMORIA: %@", [self class]);
    [super didReceiveMemoryWarning];
}
@end
