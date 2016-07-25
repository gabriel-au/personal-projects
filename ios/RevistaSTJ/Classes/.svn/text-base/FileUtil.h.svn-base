//
//  FileUtil.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 26/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "ZipArchive.h"
#import "ZipArchive.h"


@interface FileUtil : NSObject {
	NSString *savedPath;
	NSData *savedData;
}

@property (nonatomic,retain) NSString *savedPath;
@property (nonatomic,retain) NSData *savedData;
//
- (NSArray *) fileContentAsStringArray : (NSString *) filePath;
- (NSString *) fileContentAsString : (NSString *) filePath;
- (NSString *) directoryAsString : (NSSearchPathDirectory) dir;
- (NSString *) filePathAsString : (NSString *) dir andFileName: (NSString *) fileName;
- (void) unzipSavedFile;
- (NSArray *) listFilesAndFoldersInDirectory : (NSString *) dir;
- (BOOL) saveFileFromURL : (NSString *) urlString toDirectory: (NSString *) directory andFileName: (NSString *) fileName;
+ (BOOL) deleteFile : (NSString *) filePath;

@end