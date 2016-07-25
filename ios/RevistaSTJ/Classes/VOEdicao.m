//
//  VOEdicao.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 30/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "VOEdicao.h"
#import <sqlite3.h>
#import "RevistaSTJAppDelegate.h"

static sqlite3 *database = nil;
static sqlite3_stmt *deleteStmt = nil;
static sqlite3_stmt *addStmt = nil;

@implementation VOEdicao
@synthesize id,nome;



/*
+ (NSMutableArray *) listar {
	NSMutableArray * retorno = [[[NSMutableArray alloc] init] autorelease];
	RevistaSTJAppDelegate *appDelegate = (RevistaSTJAppDelegate *)[[UIApplication sharedApplication] delegate];
	
	if (sqlite3_open([[appDelegate getDBPath] UTF8String], &database) == SQLITE_OK) {
		
		const char *sql = "select id,nome from edicao";
		sqlite3_stmt *selectstmt;
		if(sqlite3_prepare_v2(database, sql, -1, &selectstmt, NULL) == SQLITE_OK) {
			
			while(sqlite3_step(selectstmt) == SQLITE_ROW) {
				
				NSInteger primaryKey = sqlite3_column_int(selectstmt, 0);
				VOEdicao *vo = [[[VOEdicao alloc] init] autorelease];
				vo.id = primaryKey;
				vo.nome = [NSString stringWithUTF8String:(char *)sqlite3_column_text(selectstmt, 1)];
				
				[retorno addObject: vo];
			}
		}
	}
	else
		sqlite3_close(database); //Even though the open call failed, close the database connection to release all the memory.

	return retorno;
}
*/
/*
- (void) inserir {
	RevistaSTJAppDelegate *appDelegate = (RevistaSTJAppDelegate *)[[UIApplication sharedApplication] delegate];
	
	if (sqlite3_open([[appDelegate getDBPath] UTF8String], &database) == SQLITE_OK) {
	if(addStmt == nil) {
		const char *sql = "insert into edicao(id,nome) Values(?,?)";
		if(sqlite3_prepare_v2(database, sql, -1, &addStmt, NULL) != SQLITE_OK)
			NSAssert1(0, @"Error while creating add statement. '%s'", sqlite3_errmsg(database));
	}
	sqlite3_bind_int(addStmt, 1, self.id);
	sqlite3_bind_text(addStmt, 2, [self.nome UTF8String], -1, SQLITE_TRANSIENT);
	
	if(SQLITE_DONE != sqlite3_step(addStmt))
		NSAssert1(0, @"Error while inserting data. '%s'", sqlite3_errmsg(database));
	//Reset the add statement.
	sqlite3_reset(addStmt);
	}
}*/
+ (NSMutableArray *) listar {
	FMDatabase *db = [TemplateDAO getDataBase];
	FMResultSet *rs = [db executeQuery: @"SELECT id,nome FROM edicao"];
	NSMutableArray *ret = [[[NSMutableArray alloc] init] autorelease];
	while ([rs next]) {
		VOEdicao *vo = [[[VOEdicao alloc] init] autorelease];
		vo.id = [rs intForColumn: @"id"];
		vo.nome = [rs stringForColumn: @"nome"];
		
		[ret addObject: vo];
	}
	[rs close];
	[db close];
	return ret;
}
- (void) inserir {
	FMDatabase *db = [TemplateDAO getDataBase];
	[db executeUpdate: @"INSERT INTO edicao(id,nome) VALUES(?,?)", 
	 [NSNumber numberWithInt: self.id],
	 self.nome];
	
	[db close];
}

- (void) remover {
		if(deleteStmt == nil) {
			const char *sql = "delete from edicao where id = ?";
			if(sqlite3_prepare_v2(database, sql, -1, &deleteStmt, NULL) != SQLITE_OK)
				NSAssert1(0, @"Error while creating delete statement. '%s'", sqlite3_errmsg(database));
		}
		
		//When binding parameters, index starts from 1 and not zero.
		sqlite3_bind_int(deleteStmt, 1, self.id);
		
		if (SQLITE_DONE != sqlite3_step(deleteStmt))
			NSAssert1(0, @"Error while deleting. '%s'", sqlite3_errmsg(database));
		
		sqlite3_reset(deleteStmt);
}


+ (void) finalizeStatements {
	
	if(database) sqlite3_close(database);
	if(deleteStmt) sqlite3_finalize(deleteStmt);
	if(addStmt) sqlite3_finalize(addStmt);
}


@end
