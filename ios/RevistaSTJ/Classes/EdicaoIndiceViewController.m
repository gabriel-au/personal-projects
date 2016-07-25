//
//  EdicaoIndice.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 28/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "EdicaoIndiceViewController.h"
#import "VOEdicao.h"
#import "UILoading.h"
#import "ControleEdicoes.h"
#import "IndiceViewController.h"
#import "VOTurmaSecao.h"

@implementation EdicaoIndiceViewController

@synthesize edicoes,indexSel,indiceVc,combo;


- (IBAction) lerEdicao: (id) sender {
	VOEdicao *vo = [self.edicoes objectAtIndex: self.indexSel];
	NSInteger edicao = vo.id;



	NSMutableArray *lista = [VOTurmaSecao listarPorEdicao: edicao];

	if (self.indiceVc != nil) {
		self.indiceVc.listaTabela = lista;
		[self.indiceVc.pv reloadAllComponents];
	}
	self.indiceVc.tituloTela = vo.nome;	
	[self.tabBarController setSelectedIndex: 1];
	
	[self.indiceVc.navigationController popViewControllerAnimated: NO];
	[self.indiceVc.navigationController popViewControllerAnimated: NO];
	[self.indiceVc.navigationController popViewControllerAnimated: NO];
	[self.indiceVc.navigationController popViewControllerAnimated: NO];
	
	
	
}
- (IBAction) atualizar: (id) sender {
	LOAD = [[[UILoading alloc] initWithView: self.view] autorelease];
	
	[self performSelector:@selector(tAtualizar:)
               withObject:@""
               afterDelay:0.001];
	
	
    // removeSpinner: will run in at least one second, but will wait if
    // another event (like the doDelete: one) is in the middle of running.
	

}

- (void) tAtualizar: (id)b {
	ControleEdicoes *c = [[[ControleEdicoes alloc] init] autorelease];
	c.edicaoVc = self;
	[c verificarEdicoes];
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
    return [self.edicoes count];
}

- (NSString *)pickerView:(UIPickerView *)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component;
{
	VOEdicao *vo = [self.edicoes objectAtIndex: row];
	return vo.nome;
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}

- (void)viewDidUnload {
}

- (void) viewDidLoad {
	self.edicoes = [VOEdicao listar];
	if ([self.edicoes count] != 0) {
	VOEdicao *vo = [self.edicoes objectAtIndex: self.indexSel];
	NSInteger edicao = vo.id;
	
	
	
	NSMutableArray *lista = [VOTurmaSecao listarPorEdicao: edicao];
	
	if (self.indiceVc != nil) {
		self.indiceVc.listaTabela = lista;
		[self.indiceVc.pv reloadAllComponents];
	}
	self.indiceVc.tituloTela = vo.nome;	
	}
}

- (void)dealloc {
    [super dealloc];
}


@end
