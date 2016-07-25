//
//  TemplateDAO.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 04/05/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "TemplateDAO.h"
#import "FMDatabase.h"

@implementation TemplateDAO

+ (FMDatabase *) getDataBase {
	NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];
    FMDatabase* db = [FMDatabase databaseWithPath: [self getDBPath]];
    if (![db open]) {
        NSLog(@"Could not open db.");
        [pool release];
		return nil;
    }
	return db;
}

+ (NSString *) getDBPath {
	NSArray *paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory , NSUserDomainMask, YES);
	NSString *documentsDir = [paths objectAtIndex:0];
//	NSLog(@"DATABASE EM:\n%@",[documentsDir stringByAppendingPathComponent:@"revistastj.sqlite"]);
	return [documentsDir stringByAppendingPathComponent:@"revistastj.sqlite"];
}

@end
