//
//  InvokedUrlCommand.m
//  InvokedUrlCommand
//
//  Created by Alexandre Oliveira on 5/12/11.
//  Copyright (c) 2011 BRQ IT Services. All rights reserved.
//

#import "InvokedUrlCommand.h"

@implementation InvokedUrlCommand

@synthesize className;
@synthesize methodName;
@synthesize callbackName;
@synthesize url;

/*
 Cria um objeto de acordo com o comando passado contendo:
	className = nome da classe.
	methodName = nome do método.
	callbackName = nome do método a ser executado no cliente.
	url = url do serviço.
*/
+ (InvokedUrlCommand*) newFromUrl:(NSURL*)url
{
	InvokedUrlCommand* iuc = [[InvokedUrlCommand alloc] init];
	
    NSString* command = [url host]; // comando a ser executado (formato: classe / método)
    
    NSArray* components = [command componentsSeparatedByString:@"."];
    iuc.className = [components objectAtIndex:0]; // classe
    iuc.methodName = [components objectAtIndex:1]; // metodo
    
	// Separa a url da classe, metodo e callback
	NSString * fullUrl = [url description];
	int prefixLength = [[url scheme] length] + [@"://" length] + [command length] + 1;
	int pathLength = [fullUrl length] - prefixLength;

    NSString* urlPath = [fullUrl substringWithRange:NSMakeRange(prefixLength, pathLength)];
	
	// obtem o nome do método de callback.
	iuc.callbackName  = [[NSMutableArray arrayWithArray:[urlPath componentsSeparatedByString:@"/"]]objectAtIndex:0];
    urlPath = [urlPath stringByReplacingOccurrencesOfString:[NSString stringWithFormat:@"%@/",iuc.callbackName]  withString:@""];
   
	// defin e a url que contem o serviço a ser acessado.
	iuc.url = [NSURL URLWithString:urlPath];
    
	return iuc;
}

// liberação de memnória.
- (void) dealloc
{
	[className release];
	[methodName release];
	[callbackName release];
    [url release];
	[super dealloc];
}

@end
