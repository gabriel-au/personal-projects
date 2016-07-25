//
//  ControleEdicoes.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 30/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "ControleEdicoes.h"
#import "VOEdicao.h"
#import "VOTurmaSecao.h"
#import "FileUtil.h"
#import "ProcessaArquivo.h"
#import "EdicaoIndiceViewController.h"
@implementation ControleEdicoes

@synthesize numeroArquivo,
arquivos,
edicoes,
numeroEdicao,
dir,
edicaoVc;

- (void) verificarEdicoes {
	// pega edicoes do banco
	NSMutableArray *ed = [VOEdicao listar];
	// verifica qual é a ultima edicao local
	NSInteger ultimaEdicao = 0;
	for (VOEdicao *vo in ed) {
		if (vo.id > ultimaEdicao)
			ultimaEdicao = vo.id;
	}
	
	NSMutableArray *baixar = [[[NSMutableArray alloc] init] autorelease];
	
	NSLog(@"Verificando edicoes no banco.");
	NSString *conteudoArquivo = [NSString stringWithContentsOfURL:[NSURL URLWithString:@"http://androides.com.br/edicoes.txt"] encoding: NSUTF8StringEncoding error:nil];
	NSArray *conteudoArray = [conteudoArquivo componentsSeparatedByString: MENU_SEPARA_ITEM]; 
	for (NSString *linha in conteudoArray) {
		NSArray *linhaArray = [linha componentsSeparatedByString: MENU_SEPARA_NOME];
		NSString *attEdicao = [linhaArray objectAtIndex: 1];
		
		NSInteger iEdicao = [attEdicao intValue];
		
		if ([self verificaExistenciaEdicao: iEdicao noArray: ed] == NO && iEdicao > ultimaEdicao) {
			VOEdicao *edicao = [[[VOEdicao alloc] init] autorelease];
			NSString *ids = [linhaArray objectAtIndex: 1];
			edicao.id = [ids intValue];
			edicao.nome = [linhaArray objectAtIndex: 0];
			[baixar addObject: edicao];
		} else if (DEBUG) {
			NSLog(@"Não vai baixar a edição %@", linha);
		}
		
	}
	
	self.edicoes = baixar;
	self.numeroEdicao = -1;

	[self baixarProximaEdicao];
	
}

- (void) baixarProximaEdicao {
	self.numeroEdicao++;
	if (self.numeroEdicao < [self.edicoes count]) {
		VOEdicao *vo = [self.edicoes objectAtIndex: self.numeroEdicao];
		LOAD.texto.text = [NSString stringWithFormat: @"Baixando %@...", vo.nome];
		FileUtil *util = [[FileUtil alloc] init];
		NSString *dire = [util directoryAsString: NSDocumentDirectory];
				self.dir = dire;
		
		NSLog(@"DIR: %@",self.dir);
		
		[util saveFileFromURL: [NSString stringWithFormat: @"http://www.androides.com.br/%d.zip",vo.id] toDirectory:dir andFileName: [NSString stringWithFormat: @"%d.zip", vo.id]];
		/*NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory , NSUserDomainMask, YES);
		 NSString *documentsDir = [paths objectAtIndex:0];
		 util.savedPath = [documentsDir stringByAppendingPathComponent: [NSString stringWithFormat: @"%d.zip",vo.id]];
		 */
		
		[util unzipSavedFile];

		[self processarEdicao];
	} else {
		[LOAD stop];
		self.edicaoVc.edicoes = [VOEdicao listar];
		[self.edicaoVc.combo reloadAllComponents];
	}
}

- (void) processarEdicao {
	VOEdicao *vo = [self.edicoes objectAtIndex: self.numeroEdicao];
	LOAD.texto.text = [NSString stringWithFormat: @"Processando %@...", vo.nome];
	[vo inserir];
	NSString * fpath = [NSString stringWithFormat: @"%@/%@/%@",self.dir,[NSString stringWithFormat: @"%d",vo.id],MENU_NOME_ARQUIVO];
	if (DEBUG)
		NSLog(@"%@",fpath);
	NSString *arquivosRevista = [NSString stringWithContentsOfFile: fpath encoding: NSUTF8StringEncoding error:nil];
	self.arquivos = [arquivosRevista componentsSeparatedByString: MENU_SEPARA_ITEM];
	self.numeroArquivo = -1;
	
	[self processarProximoArquivo];
	
}
- (void) processarProximoArquivo {
	self.numeroArquivo++;
	if (self.numeroArquivo < [self.arquivos count]) {
		NSString *linha = [self.arquivos objectAtIndex: self.numeroArquivo];
		NSArray *arrayLinha = [linha componentsSeparatedByString: MENU_SEPARA_NOME];
		VOEdicao *voe = [self.edicoes objectAtIndex: self.numeroEdicao];
		VOTurmaSecao *vots = [[[VOTurmaSecao alloc] init] autorelease];
		vots.nome = [arrayLinha objectAtIndex: 0];
		vots.edicao_id= voe.id;
		[vots inserir];
		NSString *arq = [arrayLinha objectAtIndex: 1];
		NSString *path = [NSString stringWithFormat:@"%@/%@/%@",dir,[NSString stringWithFormat: @"%d",voe.id],arq];
		ProcessaArquivo *proc = [[ProcessaArquivo alloc] init];
		proc.voTs = vots;
		proc.coe = self;
		if (DEBUG)
			NSLog(@"\nPROCESSANDO ARQUIVO: %@", path);
		[proc processaArquivo:path];
	} else {
		[self baixarProximaEdicao];
	}
	
}

- (BOOL) verificaExistenciaEdicao: (NSInteger) ed noArray: (NSMutableArray *) array {
	for (VOEdicao *vo in array) {
		if (vo.id == ed)
			return YES;
	}
	return NO;
}


@end
