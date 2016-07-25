//
//  VOTurmaSecao.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 30/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "VOTurmaSecao.h"
#import <sqlite3.h>
#import "RevistaSTJAppDelegate.h"

@implementation VOTurmaSecao
@synthesize id,edicao_id,nome;

+ (NSMutableArray *) listarPorEdicao: (NSInteger) edicao {
	FMDatabase *db = [TemplateDAO getDataBase];
	FMResultSet *rs = [db executeQuery: @"SELECT id,nome FROM turmasecao WHERE edicao_id = ?", [NSNumber numberWithInt: edicao]];
	NSMutableArray *ret = [[[NSMutableArray alloc] init] autorelease];
	while ([rs next]) {
		VOTurmaSecao *vo = [[[VOTurmaSecao alloc] init] autorelease];
		vo.id = [rs intForColumn: @"id"];
		vo.nome = [rs stringForColumn: @"nome"];
		vo.edicao_id = edicao;
		
		[ret addObject: vo];
	}
	[rs close];
	[db close];
	return ret;
}
- (void) inserir {
	FMDatabase *db = [TemplateDAO getDataBase];
	[db executeUpdate: @"INSERT INTO turmasecao(edicao_id,nome) VALUES(?,?)", 
	 [NSNumber numberWithInt: self.edicao_id],
	 self.nome];
	
	// pegar PK gerada
	FMResultSet *rs = [db executeQuery: @"select max(id) as ultimo from turmasecao"];
	if ([rs next]) {
		self.id = [rs intForColumn: @"ultimo"];
	}
	[rs close];
	[db close];
}


@end
