//
//  ProcessaArquivo.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 29/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "VOTurmaSecao.h"
#import "VOTopico.h"
#import "VOProcesso.h"
#import "ControleEdicoes.h"
@class IndiceViewController; 
@class PesquisaViewController;

@interface ProcessaArquivo : NSObject {
	NSInputStream *stream;
	NSInteger numSecao; // número da seção do arquivo do processo;
	
	
	NSMutableArray *processos; // array de VOProcesso
	NSString *titulo; // nome do arquivo
	BOOL busca;
	
	// ponteiros das viewcontrollers que chamam o processamento de arquivo
	PesquisaViewController *pes;
	IndiceViewController *vi;
	
	// string e vo que nao terminaram o processamento (fim do buffer)
	VOProcesso *voIncompleto;
	VOTopico *voTopico;
	VOTurmaSecao *voTs;
	NSString *stringIncompleta;
	
	ControleEdicoes *coe;
	
}
@property (nonatomic,retain) ControleEdicoes *coe;
@property (nonatomic) BOOL busca;
@property (nonatomic,retain) PesquisaViewController *pes;
@property (nonatomic,retain) IndiceViewController *vi;
@property (nonatomic) NSInteger numSecao;
@property (nonatomic,retain) NSMutableArray *processos;
@property (nonatomic,retain) NSInputStream *stream;
@property (nonatomic,retain) VOProcesso *voIncompleto;
@property (nonatomic,retain) VOTopico *voTopico;
@property (nonatomic,retain) VOTurmaSecao *voTs;
@property (nonatomic,retain) NSString *stringIncompleta;
@property (nonatomic,retain) NSString *titulo;


+ (void) logArray : (NSMutableArray *) array;
- (void) processaBuffer : (NSArray *) buffer;
- (void) processaArquivo:(NSString *)path;
@end
