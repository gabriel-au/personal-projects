//
//  PesquisaViewController.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 29/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "PesquisaViewController.h"
#import "FileUtil.h"
#import "StringExtras.h"
#import "Busca.h"
#import "ProcessaArquivo.h"
#import "IndiceViewController.h"
#import "VOResultado.h"
#import "ResultadoTableViewController.h"
@implementation PesquisaViewController
@synthesize parametro,
resultVc,
loading,
lblEfeito;

// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
}

- (void)dealloc {
    [super dealloc];
}



// EVENTOS DA SEARCHBAR

// Mostra botão de cancelar ao começar a digitar e esconde ao terminar.
- (void)searchBarTextDidBeginEditing:(UISearchBar *)searchBar {
	[searchBar becomeFirstResponder];
	[searchBar setShowsCancelButton:YES animated:YES];
	
}
- (void)searchBarTextDidEndEditing:(UISearchBar *)searchBar {
	[searchBar resignFirstResponder];
	[searchBar setShowsCancelButton:NO animated:YES];
}

- (void)searchBarCancelButtonClicked:(UISearchBar *) searchBar {
	[searchBar resignFirstResponder];
	[searchBar setShowsCancelButton:NO animated:YES];
}

// CLIQUE NO BOTAO DE PESQUISA
- (void)searchBarSearchButtonClicked:(UISearchBar *)searchBar {
	LOAD = [[[UILoading alloc] initWithView: self.view] autorelease];
		[searchBar resignFirstResponder];	
	[self performSelector:@selector(fazerPesquisa:)
               withObject:searchBar
               afterDelay:0.001];
	
}

- (void) fazerPesquisa: (UISearchBar *)searchBar{
	if ([self.resultVc.listaResultados retainCount] > 0) {
		[self.resultVc.listaResultados release];
	}
	if ([self.resultVc.detalhaVc retainCount] > 1) {
		[self.resultVc.detalhaVc release];
	}
	NSMutableArray *resultados = [Busca buscar: searchBar.text];

	
	
	//self.resultVc = [[[ResultadoTableViewController alloc] initWithNibName:@"ResultadoTable" bundle:nil] autorelease];
	self.resultVc.listaResultados = resultados;
	
	// PROCESSA LISTA DE RESULTADOS PARA DIVIDIR POR PROCESSO
	NSMutableDictionary *dict = [[[NSMutableDictionary alloc] init] autorelease];
	NSMutableArray *list = [[[NSMutableArray alloc] init] autorelease];
	NSMutableArray *iList = [[[NSMutableArray alloc] init] autorelease];
	for (VOResultado *res in self.resultVc.listaResultados) {
		NSString *pid = [NSString stringWithFormat: @"%d", res.topico.processo_id];
		NSMutableArray *parr = [dict objectForKey: pid];
		if (parr == nil) {
			parr = [[[NSMutableArray alloc] init] autorelease];
			[dict setObject: parr forKey: pid];
			NSString *no = [VOProcesso recuperaNomePorId: res.topico.processo_id];
			[list addObject: no];
			[iList addObject: pid];
		}
		[parr addObject: res];
	}
	
	self.resultVc.dicionarioTabela = dict;
	self.resultVc.sectionList = list;
	self.resultVc.indexList = iList;
	self.resultVc.viewAnterior = self;
	self.resultVc.lblNotFound.hidden = (self.resultVc.listaResultados != nil && [self.resultVc.listaResultados count] != 0);
	
	[self.resultVc.tableView reloadData];
	
	[LOAD stop];
}
- (void) viewWillAppear:(BOOL)animated {
	[super viewWillAppear:YES];
	if ([self.resultVc.detalhaVc retainCount] > 1) {
		[self.resultVc.detalhaVc release];
	}
	/*
	for (UINavigationController *uivc in self.tabBarController.viewControllers) {
		for (UIViewController *uiv in [uivc viewControllers]) {
			if ([uiv class] == [IndiceViewController class]) {
				IndiceViewController *iv = (IndiceViewController *) uiv;
				self.listaCombo = iv.listaTabela;
				self.indexSel = iv.indexSel;
				NSString *linhaCompleta = [self.listaCombo objectAtIndex:self.indexSel];
				NSArray *linhaDividida = [linhaCompleta componentsSeparatedByString: @"#"];
				self.navigationItem.title = [linhaDividida objectAtIndex: 0];
				[self.resultVc setViewAnterior: self];
			}
		}
	}*/
}

- (void)didReceiveMemoryWarning {
	NSLog(@"AVISO DE MEMORIA: %@", [self class]);
    [super didReceiveMemoryWarning];
}
@end
