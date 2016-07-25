//
//  FileSystem.h
//  BSAD
//
//  Created by Alexandre Oliveira on 6/6/11.
//  Copyright 2011 BRQ IT Services. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface FileSystem : NSObject {
    
}

+ (NSString *) obterPathDocuments;
+ (NSData *) lerArquivo:(NSString *) nomeArquivo comExtensao:(NSString *) extensao;
+ (NSString *) lerArquivoTexto:(NSString *) nomeArquivo comExtensao:(NSString *) extensao;
+ (BOOL) criarArquivo:(NSString *) nomeArquivo comExtensao:(NSString *) extensao;
+ (NSDictionary *) lerPlist:(NSString *) nomeArquivo;
+ (BOOL) copiarArquivoDocuments:(NSString *) nomeArquivo comExtensao:(NSString *) extensao;
+ (void) copyDirectoryToDocumentsFolder:(NSString *) directory overwrite:(BOOL) overwrite;
+ (NSArray *) showDirectoryContents:(NSString *) directory; 
+ (void) deleteDirectory:(NSString *) directory;
@end
