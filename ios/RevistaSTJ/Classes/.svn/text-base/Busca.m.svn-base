//
//  Busca.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 06/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "Busca.h"
#import "VOResultado.h"
#import "StringExtras.h"
@implementation Busca

+ (NSMutableArray *) buscar: (NSString *) texto {
	NSMutableArray *results = [[NSMutableArray alloc] init] ;
		FMDatabase *db = [TemplateDAO getDataBase];
		FMResultSet *rs = [db executeQuery: @"SELECT id,nome,texto,processo_id FROM topico WHERE texto LIKE ?", 
						   [NSString stringWithFormat: @"%@%@%@",@"%%",texto,@"%%"]
						   ];
	NSLog(@"\n\nPESQUISANDO POR: %@\n\n", [NSString stringWithFormat: @"%@%@%@",@"%",texto,@"%"]);
		while ([rs next]) {
			VOResultado *res = [[[VOResultado alloc] init] autorelease];
				res.topico.id = [rs intForColumn: @"id"];
				res.topico.processo_id = [rs intForColumn: @"processo_id"];
				res.topico.nome = [rs stringForColumn: @"nome"];
				res.topico.texto = [rs stringForColumn: @"texto"];
			[res preencheOcorrencia: texto];
			res.textoBusca = texto;
			[results addObject: res];
		}
		[rs close];
		[db close];
	return results;
}

- (void) dealloc {
	[super dealloc];
}
@end
