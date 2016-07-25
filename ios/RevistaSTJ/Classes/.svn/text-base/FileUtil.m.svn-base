//
//  FileUtil.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 26/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "FileUtil.h"
#import "ZipArchive.h"


@implementation FileUtil
@synthesize savedPath;
@synthesize savedData;

- (NSArray *) fileContentAsStringArray : (NSString *) filePath {
	NSString *s = [self fileContentAsString: filePath];
	return [s componentsSeparatedByString: MARCA_TEXTO];
}
- (NSString *) fileContentAsString : (NSString *) filePath {
	//NSData *data = [NSData dataWithContentsOfFile: filePath];
	NSString *s = [NSString stringWithContentsOfFile: filePath encoding: NSUTF8StringEncoding error:nil];
	//NSArray *array = [s componentsSeparatedByString: @"\n"];
	return s;
}
- (NSArray *) listFilesAndFoldersInDirectory : (NSString *) dir {
	return [[NSFileManager defaultManager] directoryContentsAtPath:dir];
	
}

- (NSArray *) listFilesAndFoldersInDirectory : (NSString *) dir withExtension: (NSString *) ext {
	NSArray *children = [[NSFileManager defaultManager] directoryContentsAtPath:dir];
	NSMutableArray *retorno = [[NSMutableArray alloc] init];
	for (NSString *filename in children) {
		
	}
	return retorno;
	
}
- (BOOL) saveFileFromURL : (NSString *) urlString toDirectory: (NSString *) directory andFileName: (NSString *) fileName {
	NSURL *url = [NSURL URLWithString:
				  [urlString stringByAddingPercentEscapesUsingEncoding:
				   NSASCIIStringEncoding]];
	NSData *data = [NSData dataWithContentsOfURL: url];
	if (data == NULL) { return NO; } 
	NSString *completeFilePath = [self filePathAsString: directory andFileName: fileName];
	
	[data writeToFile:completeFilePath atomically:YES];
	self.savedData = data;
	self.savedPath = completeFilePath;
	if (DEBUG)
		NSLog(@"SAVED FROM: %@\nSAVED TO: %@",urlString, completeFilePath);
	return YES;
}
- (void) unzipSavedFile {
	ZipArchive *za = [[ZipArchive alloc] init];
	if ([za UnzipOpenFile: [self savedPath]]) {
		BOOL ret = [za UnzipFileTo: [self directoryAsString: NSDocumentDirectory] overWrite: YES];
		if (NO == ret){} [za UnzipCloseFile];
	}
	[za release];
}

- (NSString *) filePathAsString : (NSString *) dir andFileName: (NSString *) fileName {
	return [dir stringByAppendingPathComponent:fileName];
}

- (NSString *) directoryAsString : (NSSearchPathDirectory) dir {
	NSArray *array = NSSearchPathForDirectoriesInDomains((NSSearchPathDirectory)dir, NSUserDomainMask, YES);
	return [array objectAtIndex:0];
}

+ (BOOL) deleteFile : (NSString *) filePath {
	NSFileManager *fm = [NSFileManager defaultManager];
	
	if ([fm removeItemAtPath: filePath error: NULL]  == YES)
        return YES;
	else
        return NO;
}

@end