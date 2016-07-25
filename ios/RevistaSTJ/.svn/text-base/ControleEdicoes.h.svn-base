//
//  ControleEdicoes.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 30/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "VOEdicao.h";

@class EdicaoIndiceViewController;

@interface ControleEdicoes : NSObject {
	NSInteger numeroArquivo;
	NSArray *arquivos;
	NSInteger numeroEdicao;
	NSMutableArray *edicoes;
	
	NSString *dir;
	
	EdicaoIndiceViewController *edicaoVc;
}
@property (nonatomic,retain) EdicaoIndiceViewController *edicaoVc;
@property (nonatomic,retain) NSString *dir;
@property (nonatomic) NSInteger numeroEdicao;
@property (nonatomic,retain) NSMutableArray *edicoes;
@property (nonatomic) NSInteger numeroArquivo;
@property (nonatomic,retain) NSArray *arquivos;
- (void) baixarProximaEdicao;
- (void) verificarEdicoes;
- (BOOL) verificaExistenciaEdicao: (NSInteger) arquivo noArray: (NSMutableArray *) array;
- (void) processarEdicao;

- (void) processarProximoArquivo;
@end
