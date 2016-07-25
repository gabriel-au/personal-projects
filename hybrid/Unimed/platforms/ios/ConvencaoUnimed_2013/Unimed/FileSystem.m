//
//  FileSystem.m
//  BSAD
//
//  Created by Alexandre Oliveira on 6/6/11.
//  Copyright 2011 BRQ IT Services. All rights reserved.
//

#import "FileSystem.h"


@implementation FileSystem


/*
    Obtém o diretório Documents, utilizado para gravar dados do usuário.
*/
+ (NSString *) obterPathDocuments
{
    NSArray *path = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES);
    return [path objectAtIndex:0];
}


+ (NSData *) lerArquivo:(NSString *) nomeArquivo comExtensao:(NSString *) extensao {
    
    NSString* pathArquivo = [[NSBundle mainBundle] pathForResource:nomeArquivo ofType:extensao];  
    
    NSData *arquivo = [NSData dataWithContentsOfFile:pathArquivo];  
    
    if (arquivo) 
    {  
        return arquivo;
    } 
	
    return  nil;
}

+ (BOOL) criarArquivo:(NSString *) nomeArquivo comExtensao:(NSString *) extensao {

	return[[NSFileManager defaultManager] createFileAtPath:[[FileSystem obterPathDocuments] stringByAppendingPathComponent:[NSString stringWithFormat:@"%@.%@",nomeArquivo,extensao]] contents:nil attributes:nil];
}	



+ (NSString *) lerArquivoTexto:(NSString *) nomeArquivo comExtensao:(NSString *) extensao {
    
    NSString* pathArquivo = [[NSBundle mainBundle] pathForResource:nomeArquivo ofType:extensao];
    
    return [NSString stringWithContentsOfFile:pathArquivo encoding:NSUTF8StringEncoding error:nil];
}


+ (NSDictionary *) lerPlist:(NSString *) nomeArquivo {
    NSString* pathArquivo = [[NSBundle mainBundle] pathForResource:nomeArquivo ofType:@"plist"];
    
    if ([[NSFileManager defaultManager] fileExistsAtPath:pathArquivo]) {
        return [NSDictionary dictionaryWithContentsOfFile:pathArquivo];;
    }
    
    return nil;
}

+ (BOOL) copiarArquivoDocuments:(NSString *) nomeArquivo comExtensao:(NSString *) extensao {
    
    NSString* pathDocuments = [NSString stringWithFormat:@"%@/%@.%@",[FileSystem obterPathDocuments],nomeArquivo,extensao ];
    NSString* pathArquivoBundle = [[NSBundle mainBundle] pathForResource:nomeArquivo ofType:extensao];
    
    if (![[NSFileManager defaultManager] fileExistsAtPath:pathDocuments]) {
        [[NSFileManager defaultManager] copyItemAtPath:pathArquivoBundle toPath:pathDocuments error:nil];
    }
    
    return YES;
}


+ (void) copyDirectoryToDocumentsFolder:(NSString *) directory overwrite:(BOOL) overwrite {
    
    NSString* source = [[NSBundle mainBundle] pathForResource:directory ofType:nil];
    NSString* destination = [[self obterPathDocuments] stringByAppendingString:[NSString stringWithFormat:@"/%@",directory]];
    NSError* error = nil;
    
    
    
    if ([[NSFileManager defaultManager] fileExistsAtPath:destination] && overwrite) {
        [self deleteDirectory:destination];
    }
    
    [[NSFileManager defaultManager] copyItemAtPath:source toPath:destination error:&error];
    
}

+ (NSArray *) showDirectoryContents:(NSString *) directory {
    
    NSError* error = nil;
    
    return [[NSFileManager defaultManager] contentsOfDirectoryAtPath:directory error:&error];
    
}

+ (void) deleteDirectory:(NSString *) directory {
    
    [[NSFileManager defaultManager] removeItemAtPath:directory error:nil];
}


@end
