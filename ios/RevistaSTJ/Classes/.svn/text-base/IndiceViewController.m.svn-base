//
//  IndiceViewController.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 26/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "IndiceViewController.h"
#import "FileUtil.h"
#import "ProcessaArquivo.h"
#import "IndiceProcessoViewController.h"
#import "StringExtras.h"
#import <QuartzCore/QuartzCore.h>
#import "UILoading.h"
#import "VOProcesso.h"
@implementation IndiceViewController
@synthesize listaTabela,
lblEdicao;
@synthesize nav,
processoVc,
indexSel,
botao,
pv,edicao_id,
tituloTela;

- (void) abreIndiceDeProcessos: (NSMutableArray *) processos comTitulo: (NSString *) titulo {

}

- (IBAction) cliqueBotao : (id) sender {
	VOTurmaSecao *sel = [self.listaTabela objectAtIndex: self.indexSel];
	
	if ([self.processoVc retainCount] > 1) {
		[self.processoVc release];
	}
	
	self.processoVc = [[[IndiceProcessoViewController alloc]
						initWithNibName:@"IndiceProcessoView" bundle:nil] autorelease];
	self.processoVc.navigationItem.title = sel.nome;
	self.processoVc.processos = [VOProcesso listarPorTurma: sel.id];
	[self.navigationController pushViewController:self.processoVc animated: YES];
}


- (NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView;
{
    return 1;
}
- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component
{
	self.indexSel = row;
}

- (NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component;
{
    return [listaTabela count];
}

- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component;
{
	VOTurmaSecao *vo = [self.listaTabela objectAtIndex: row];
	return vo.nome;
}


/*
 - (IBAction) proximaView: (id) sender{
 self.processoVc = [[[IndiceProcessoViewController alloc]
 initWithNibName:@"IndiceProcessoView" bundle:nil] autorelease];
 [self.navigationController pushViewController:self.processoVc animated: YES];
 }*/


- (NSInteger) tableView : (UITableView *) tableView numberOfRowsInSection : (NSInteger) section {
	return [ self.listaTabela count ];
}


- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    return YES;
}

- (void)didReceiveMemoryWarning {
	NSLog(@"Aviso de memoria no INDICE");
    [super didReceiveMemoryWarning];
}
- (void) viewDidLoad {
	self.navigationItem.title = @"Índice";
	
	/*
	FileUtil *util = [[FileUtil alloc] init];
	self.listaTabela = [[NSMutableArray alloc] init];
	NSString *dir = [util filePathAsString: [util directoryAsString: NSDocumentDirectory] andFileName:@"Revista/"];
	NSString *path = [util filePathAsString: dir andFileName: MENU_NOME_ARQUIVO];
	// DOWNLOAD E DESCOMPACTACAO
	
	NSArray *dels = [util listFilesAndFoldersInDirectory: dir];
	
	if (BAIXAR_ARQUIVO) {
		NSLog(@"Deletando");
		for (NSString *ite in dels) {
			[FileUtil deleteFile: [util filePathAsString:dir andFileName: ite]];
		}
	}
	BOOL procede = YES;
	for (NSString *arq in dels) {
		//NSLog(@"ARQUIVO: %@",arq);
		if ([arq containsString: MENU_NOME_ARQUIVO]) {
			procede = NO;
			break;
		}
	}
	if (procede == YES) {

	}
	
	NSString *content = [NSString stringWithContentsOfFile: path encoding:NSUTF8StringEncoding error:nil];
	NSArray *linhas = [content componentsSeparatedByString: MENU_SEPARA_ITEM];
	for (NSString *compl in linhas) {
		[self.listaTabela addObject: compl];
	}
	
	self.indexSel = 0;*
	  */
	
}

- (void)viewDidUnload {
	
}

- (void)dealloc {
	if (DEBUG)
		NSLog(@"DEALLOC: %@", [self class]);
	[nav release];
	[botao release];
    [super dealloc];
}
- (void) viewDidAppear:(BOOL)animated {

	
	
}
- (void) viewWillAppear:(BOOL)animated {
	[super viewWillAppear: YES];
	self.lblEdicao.text = self.tituloTela;
	self.nav.hidden= YES;
	

}

- (void) viewWillDisappear:(BOOL)animated {
	self.nav.hidden = NO;
}

@end
