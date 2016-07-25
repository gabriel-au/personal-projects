//
//  Loader.m
//  WebView
//
//  Created by Alexandre Da Silva Oliveira on 18/05/11.
//  Copyright 2011 BRQ IT Services. All rights reserved.
//

#import "Loader.h"


@interface Loader (Private)
-(id) getCommandInstance:(NSString*)className;

@end

@implementation Loader

@synthesize webview;

/* 
 Inicializa a view com os parametros especificados.
 */
- (id)initWithView:(UIWebView *)webView usingFile:(NSString *)file inDirectory:(NSString *)directory {
    
	self = [super init];
	
	if (self) {
		
		self.webview = webView;
		
		NSString *path = [[NSBundle mainBundle] pathForResource:file ofType:nil inDirectory:directory]; ;
		NSURL *url = [NSURL fileURLWithPath:path];
		NSURLRequest *request = [NSURLRequest requestWithURL:url];
		[webview loadRequest:request];
		
	}
	
	return self;
}


/*
 Executa o comando desejado de acordo com os parametros passados na classe command.
 */
- (BOOL) execute:(InvokedUrlCommand*)command
{
	
    // se a classe estiver vazia nada é executado.
	if (command.className == nil || command.methodName == nil) {
		return NO;
	}
    
    // obtem uma instancia da classe passada dentro do command.
	id obj = [self getCommandInstance:command.className];
	
    // concatena : ao nome do método.
    NSString* fullMethodName  = [[NSString alloc] initWithFormat:@"%@:", command.methodName];
    
    // verifica se a clase responde ao método desejado.
	if ([obj respondsToSelector:NSSelectorFromString(fullMethodName)]) {
        
        // cria um selector.
        NSString* selector_str = [NSString stringWithFormat:@"%@:",command.methodName];
        SEL selector = NSSelectorFromString(selector_str);
        
        // executa o comando e armazena em uma string a sua resposta.
        NSString* json_string = [obj performSelector:selector withObject:command.url];
		
        // caso o comando possua callback, o mesmo é executado.
        if (command.callbackName != nil) {
            
            // executa a função javascript especificada no parametro callback.
            NSString* json_escaped = [json_string stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];;
            NSString *js = [NSString stringWithFormat:@"%@('%@');",command.callbackName, json_escaped]; 
            [self.webview stringByEvaluatingJavaScriptFromString:js];
        }
        
    } else {
        // Uma excessão é lançada caso a classe não responda ao comando.
		[NSException raise:NSInternalInconsistencyException format:@"Metodo '%@' nao encontrado na classe: '%@'.", fullMethodName, command.className];
	}
    
	[fullMethodName release];
    
	return YES;
}

/*
 Busca na memória uma instäncia da classe passada como parametro.
 Caso não exista a mesma é criada e instänciada.
 */
-(id) getCommandInstance:(NSString*)className
{
	
	className = [className capitalizedString]; 
    id obj = [commandObjects objectForKey:className];
	
    if (!obj) {
        Class myClass = NSClassFromString(className);
        obj = [[[myClass alloc] init] autorelease];
    }
	
    return obj;
}

- (void)dealloc {
	[settings release];
	[commandObjects release];
	[super dealloc];
}



@end
