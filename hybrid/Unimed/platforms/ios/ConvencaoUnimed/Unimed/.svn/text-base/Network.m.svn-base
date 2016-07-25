//
//  Network.h
//  Network
//
//  Created by Alexandre Oliveira on 5/12/11.
//  Copyright (c) 2011 BRQ IT Services. All rights reserved.
//

#import "Network.h"
#import "Reachability.h"


@interface Network (Privado)
- (BOOL) isConexaoAtiva;
- (NSError *) erroConexao;
- (void) log:(NSURLResponse *) resposta;
- (void) iniciarNetworkIndicator;
- (void) pararNetworkIndicator;
- (void) liberarConexao;
-(NSString *) montarQueryString:(NSDictionary *) parametros;
@end


@implementation NSString (Escaping)
- (NSString*)stringWithPercentEscape {            
    return [(NSString *) CFURLCreateStringByAddingPercentEscapes(NULL, (CFStringRef)[[self mutableCopy] autorelease], NULL, CFSTR("￼=,!$&'()*+;@?\n\"<>#\t :/"),kCFStringEncodingUTF8) autorelease];
}
@end


@implementation Network;

@synthesize delegate;



-(NSString *) montarQueryString:(NSDictionary *) parametros {
   
    NSMutableArray* componentes = [NSMutableArray array];
    
    for (NSString* chave in parametros) {
        
        id valor = [parametros objectForKey:chave];
        
        if ([valor isKindOfClass:[NSString class]]) {
            valor = [valor stringWithPercentEscape];
        }
        
        [componentes addObject:[NSString stringWithFormat: @"%@=%@", chave, valor]];
    }
    
    return [componentes componentsJoinedByString:@"&"];
}



- (void) requestSincrono:(NSURL *)url {
    
    if ([self isConexaoAtiva]) {
    
        [self iniciarNetworkIndicator];
        
        NSError* error = nil;
        NSURLResponse* response = nil;
        NSMutableURLRequest* request = [NSMutableURLRequest requestWithURL:url];    
        [request setValue:@"gzip" forHTTPHeaderField:@"Accept-Encoding"];
        NSData* data = [NSURLConnection sendSynchronousRequest:request returningResponse:&response error:&error];

        [self log:response];
        
        if (response == nil) {
            
            [delegate falhou:error];
             
        } else {
            
           [self pararNetworkIndicator];
            [delegate executou:data];
        }

    } else {
        
        [delegate falhou:[self erroConexao]];
    }
}

- (void) requestAssincrono:(NSURL *)url {
    
    if ([self isConexaoAtiva]) {
    
        [self iniciarNetworkIndicator];
        
        NSMutableURLRequest* request = [NSMutableURLRequest requestWithURL:url];
        [request setValue:@"gzip" forHTTPHeaderField:@"Accept-Encoding"];
        conexao = [NSURLConnection connectionWithRequest:request delegate:self];
        
    } else {
        
        [delegate falhou:[self erroConexao]];
        
    }
}


#pragma mark
#pragma mark Tratamento da Conexao

- (BOOL) isConexaoAtiva {
    
    return ([[Reachability reachabilityForInternetConnection] currentReachabilityStatus] != NotReachable);
}

- (NSError *) erroConexao {
    
    NSMutableDictionary* detalhesErro = [NSMutableDictionary dictionary];
    [detalhesErro setValue:@"Dispositivo sem conexão" forKey:NSLocalizedDescriptionKey];
    return [NSError errorWithDomain:@"Conexão" code:100 userInfo:detalhesErro];
}

- (void) log:(NSURLResponse *) resposta {
    
//#if (defined DEBUG && defined CLASS_DEBUG)
  
#if (TARGET_IPHONE_SIMULATOR)
    
    NSHTTPURLResponse* httpResponse;
    httpResponse = (NSHTTPURLResponse *) resposta;
    int statusCode = [httpResponse statusCode];
    
    NSLog(@"HTTP response Headers %@", [httpResponse allHeaderFields]); // imprime o cabeçalho http
    NSLog(@"HTTP status code %d", statusCode); // codigo de status da requisicao: 200 = OK
    
#endif
    
}

- (void) iniciarNetworkIndicator {
    [UIApplication sharedApplication].networkActivityIndicatorVisible = YES;
}

- (void) pararNetworkIndicator {
    [UIApplication sharedApplication].networkActivityIndicatorVisible = NO;
}


#pragma mark -
#pragma mark NSURLConnection delegate methods

- (void)connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response
{
    
    [self log:response];
    dadosRecebidos = [[NSMutableData data] retain];
    [dadosRecebidos setLength:0];
}

- (void)connection:(NSURLConnection *)connection didReceiveData:(NSData *)data
{
    [dadosRecebidos appendData:data];  
}

- (void)connection:(NSURLConnection *)connection didFailWithError:(NSError *)error
{ 
    [self pararNetworkIndicator];
    [delegate falhou:error];

    [self liberarConexao];
    
}

- (void)connectionDidFinishLoading:(NSURLConnection *)connection {
    
    [self pararNetworkIndicator];
    [delegate executou:dadosRecebidos];
    
    [self liberarConexao];
 
}

#pragma mark
#pragma mark Gerenciamento de memoria

- (void) liberarConexao {
    conexao = nil;
    [conexao release];
}
@end
