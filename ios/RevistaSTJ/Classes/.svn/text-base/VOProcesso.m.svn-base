//
//  VOProcesso.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 29/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "VOProcesso.h"
#import <sqlite3.h>
#import "RevistaSTJAppDelegate.h"


@implementation VOProcesso
@synthesize  listaDeTopicos, turmasecao_id,id,nome;

-(id)init {
    if (self = [super init]) {
		self.listaDeTopicos = [[NSMutableArray alloc] init];
    }
    return self;
}

+ (NSString *) recuperaNomePorId: (NSInteger) pid {
	NSString *ret =@"";
	FMDatabase *db = [TemplateDAO getDataBase];
	FMResultSet *rs = [db executeQuery: @"SELECT nome FROM processo WHERE id = ?",
					   [NSNumber numberWithInt: pid]
					   ];
	if ([rs next]) {
		ret = [rs stringForColumn: @"nome"];
	}
	[rs close];
	[db close];
	return ret;
}

+ (NSMutableArray *) listarPorTurma: (NSInteger) turma_id {
	FMDatabase *db = [TemplateDAO getDataBase];
	FMResultSet *rs = [db executeQuery: @"SELECT id,nome,turmasecao_id FROM processo WHERE turmasecao_id = ?",
					   [NSNumber numberWithInt: turma_id]
					   ];
	NSMutableArray *ret = [[[NSMutableArray alloc] init] autorelease];
	while ([rs next]) {
		VOProcesso *vo = [[[VOProcesso alloc] init] autorelease];
		vo.id = [rs intForColumn: @"id"];
		vo.nome = [rs stringForColumn: @"nome"];
		vo.turmasecao_id = [rs intForColumn: @"turmasecao_id"];
		
		[ret addObject: vo];
	}
	[rs close];
	[db close];
	return ret;
}
- (void) inserirSemAbrirConexao: (FMDatabase *) db {

	[db executeUpdate: @"INSERT INTO processo(nome,turmasecao_id) VALUES(?,?)", 
	 self.nome,
	 [NSNumber numberWithInt: self.turmasecao_id],
	 self.nome];
	
	FMResultSet *rs = [db executeQuery: @"select max(id) as ultimo from processo"];
	if ([rs next]) {
		self.id = [rs intForColumn: @"ultimo"];
	}
	[rs close];
}
- (void) inserirComTopicos {
	FMDatabase *db = [TemplateDAO getDataBase];
	[db beginTransaction];
	[self inserirSemAbrirConexao: db];
	NSLog(@"\n\nPROCESSO\n%d\n\n\n",self.id);
	for (VOTopico *vot in self.listaDeTopicos) {
		vot.processo_id = self.id;
		[vot inserirSemAbrirConexao: db];
		NSLog(@"\n\nTOPICO\n%d\n\n\n",vot.id);
	}
	[db commit];
	[db close];
}

- (void) dealloc {
	[listaDeTopicos release];
	
	[super dealloc];
}
@end
