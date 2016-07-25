//
//  VOTopico.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 30/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <sqlite3.h>

@interface VOTopico : NSObject {
	NSInteger id;
	NSInteger processo_id;
	NSString *nome;
	NSString *texto;
	NSInteger indice;
	
	NSMutableString *textoTemp;
}
@property (nonatomic) NSInteger indice;
@property (nonatomic) NSInteger id;
@property (nonatomic)  NSInteger processo_id;
@property (nonatomic,retain)  NSString *texto;
@property (nonatomic,retain)	NSString *nome;
@property (nonatomic,retain) NSMutableString *textoTemp;

- (id) initWithTempString;
+ (NSMutableArray *) listarPorProcessoSemTexto: (NSInteger) processo_id;
- (void) preencherTexto;
- (void) inserirSemAbrirConexao: (FMDatabase *) db;
- (void) marcarTexto: (NSString *) txt;

@end
