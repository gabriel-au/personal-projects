//
//  Network.m
//  Network
//
//  Created by Alexandre Oliveira on 5/12/11.
//  Copyright (c) 2011 BRQ IT Services. All rights reserved.
//

#import "Network.h"

@implementation Network


/*
 
 Executa uma chamada sincrona a url especificada.
 Caso ocorra um erro o mesmo é armazenado na variavel error.
 
 O fragmento de codigo abaixo pode ser utilizado para debug:
 
	    NSHTTPURLResponse* httpResponse;
		httpResponse = (NSHTTPURLResponse *) response;
		int statusCode = [httpResponse statusCode];
 
		NSLog(@"HTTP response Headers %@", [httpResponse allHeaderFields]); // imprime o cabeçalho http
		NSLog(@"HTTP status code %d", statusCode); // codigo de status da requisicao: 200 = OK
 */

- (NSString *) request:(NSURL *)url {
    
    NSError* error = nil;
    NSURLResponse* response = nil;
    
 //   url = [NSURL URLWithString:@"http://portalgovernanca.brq.com/DWGP-PainelAcompanhamento/dataConsOportSit.do?codigoCampanha=5"];
    
	NSMutableURLRequest* request = [NSMutableURLRequest requestWithURL:url];    
    [request setValue:@"gzip" forHTTPHeaderField:@"Accept-Encoding"];
    
	NSData* data = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];
	
	
    NSHTTPURLResponse* httpResponse;
    httpResponse = (NSHTTPURLResponse *) response;
    int statusCode = [httpResponse statusCode];
    
    NSLog(@"HTTP response Headers %@", [httpResponse allHeaderFields]); // imprime o cabeçalho http
    NSLog(@"HTTP status code %d", statusCode); // codigo de status da requisicao: 200 = OK
    
	NSString* json_string = [[[NSString alloc] initWithData:data encoding:NSWindowsCP1252StringEncoding] autorelease];
	
	[error release];
	
    return json_string;    
}

@end
