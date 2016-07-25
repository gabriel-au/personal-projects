//
//  VOTopico.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 30/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "VOTopico.h"
#import "RevistaSTJAppDelegate.h"

@implementation VOTopico
@synthesize id,processo_id,texto,nome,textoTemp, indice;

- (id) initWithTempString {
	if (self = [super init]) {
		self.textoTemp = [[[NSMutableString alloc] initWithString: @""] autorelease];
	}
	return self;
}


+ (NSMutableArray *) listarPorProcessoSemTexto: (NSInteger) processo_id {
	FMDatabase *db = [TemplateDAO getDataBase];
	FMResultSet *rs = [db executeQuery: @"SELECT id,nome FROM topico WHERE processo_id = ?", 
					   [NSNumber numberWithInt: processo_id]
					   ];
	NSMutableArray *ret = [[[NSMutableArray alloc] init] autorelease];
	NSInteger ind = 0;
	while ([rs next]) {
		VOTopico *vo = [[[VOTopico alloc] init] autorelease];
		vo.id = [rs intForColumn: @"id"];
		vo.nome = [rs stringForColumn: @"nome"];
		vo.processo_id = processo_id;
		vo.indice = ind++;
		[ret addObject: vo];
	}
	[rs close];
	[db close];
	return ret;
}

- (void) preencherTexto {
	if (self.id > 0) {
		FMDatabase *db = [TemplateDAO getDataBase];
		FMResultSet *rs = [db executeQuery: @"SELECT texto FROM topico WHERE id = ?", 
						   [NSNumber numberWithInt: self.id]
						   ];
		while ([rs next]) {
			self.texto = [rs stringForColumn: @"texto"];
		}
		[rs close];
		[db close];
	}
}

- (void) inserirSemAbrirConexao: (FMDatabase *) db {
	if (self.textoTemp != nil) {
		self.texto = self.textoTemp;
	}
	[db executeUpdate: @"INSERT INTO topico(nome,texto,processo_id) VALUES(?,?,?)", 
		self.nome,
		self.texto,
		[NSNumber numberWithInt: self.processo_id],
	 self.nome];
	
	FMResultSet *rs = [db executeQuery: @"select max(id) as ultimo from topico"];
	if ([rs next]) {
		self.id = [rs intForColumn: @"ultimo"];
	}
	[rs close];
}


- (void) marcarTexto: (NSString *) txt {
	NSRange localiza = [self.texto rangeOfString: txt options: NSCaseInsensitiveSearch];
	
	if (localiza.location != NSNotFound) {
		NSString *ocorrenciaCorreta = [self.texto substringWithRange: localiza];
		NSString *ocorrenciaMarcada = [MARCA_TEXTO stringByReplacingOccurrencesOfString:@"texto"
																			 withString: ocorrenciaCorreta];
		NSString *textoMarcado = [self.texto stringByReplacingOccurrencesOfString: ocorrenciaCorreta
																	 withString: ocorrenciaMarcada];
		self.texto = textoMarcado;
	}
	
}

- (void) dealloc {
	[nome release];
	[texto release];
	[super dealloc];
}

@end
