//
//  VOProcesso.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 29/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface VOProcesso : NSObject {
	NSInteger id;
	NSInteger turmasecao_id;
	NSString *nome;
	
	
	NSMutableArray *listaDeTopicos;
	
}
@property (nonatomic) NSInteger id;
@property (nonatomic) NSInteger turmasecao_id;
@property (nonatomic,retain) NSString *nome;
@property (nonatomic,retain) NSMutableArray *listaDeTopicos;


- (void) inserirSemAbrirConexao: (FMDatabase *) db;
- (void) inserirComTopicos;
+ (NSString *) recuperaNomePorId: (NSInteger) pid;
+ (NSMutableArray *) listarPorTurma: (NSInteger) turma_id;

@end
