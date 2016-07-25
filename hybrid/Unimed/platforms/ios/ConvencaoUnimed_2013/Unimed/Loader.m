//
//  Loader.m
//  WebView
//
//  Created by Alexandre Da Silva Oliveira on 18/05/11.
//  Copyright 2011 BRQ IT Services. All rights reserved.
//

#import "Loader.h"
#import "FileSystem.h"

@interface Loader (Private)
-(id) getCommandInstance:(NSString*)className;

@end

@implementation Loader

@synthesize webview,sharedCommand;

/* 
 Inicializa a view com os parametros especificados.
 */
- (id)initWithView:(UIWebView *)webView2 usingFile:(NSString *)file inDirectory:(NSString *)directory {
    
	self = [super init];
	
	if (self) {
		
		self.webview = [webView2 retain];    
        NSString* fullPath = [FileSystem obterPathDocuments];
        fullPath = [fullPath stringByAppendingString:[NSString stringWithFormat:@"/%@/%@",directory,file]];
        NSURL *url = [NSURL fileURLWithPath:fullPath];
		NSURLRequest *request = [NSURLRequest requestWithURL:url];
		[webview loadRequest:request];
    }
	
	return self;
}


/*
 Executa o comando desejado de acordo com os parametros passados na classe command.
 */
- (void) execute:(InvokedUrlCommand*)command usingView:(UIView*)view
{
    
    self.sharedCommand = command;
    self.webview = (UIWebView*) view;
    
    // se a classe estiver vazia nada é executado.
	if (command.className == nil || command.methodName == nil) {
		//return NO;
	}
    
    // obtem uma instancia da classe passada dentro do command.
	id obj = [self getCommandInstance:command.className];
    
    
    // concatena : ao nome do método.
    NSString* fullMethodName  = [[NSString alloc] initWithFormat:@"%@:", command.methodName];
    
    // cria um selector.
    NSString* selector_str = [NSString stringWithFormat:@"%@:",command.methodName];
    SEL selector = NSSelectorFromString(selector_str);
    
    // verifica se a clase responde ao método desejado.
	if ([obj respondsToSelector:NSSelectorFromString(fullMethodName)]) {
        
        if ([obj isKindOfClass:[Network class]]) {
            network = obj;
            network.delegate = self;
            [network performSelector:selector withObject:command.url];
        } else {
            // executa o comando e armazena em uma string a sua resposta.
            [obj performSelector:selector withObject:command.url];
        }
        
    } else {
        // Uma excessão é lançada caso a classe não responda ao comando.
		[NSException raise:NSInternalInconsistencyException format:@"Metodo '%@' nao encontrado na classe: '%@'.", fullMethodName, command.className];
	}
    
	[fullMethodName release];
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

-(void) executeJavascript:(id) javascript {
    
}

#pragma mark
#pragma mark NetworkRequestDelegate métodos

- (void)executou: (NSData *) dados {
    
    json_string = [[NSString alloc] initWithData:dados encoding:NSUTF8StringEncoding];
    
    // caso o comando possua callback, o mesmo é executado.
    if (sharedCommand.callbackName != nil) {
        
        // executa a função javascript especificada no parametro callback.
        NSString* json_escaped = [json_string stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        NSString *js = [NSString stringWithFormat:@"%@('%@');",sharedCommand.callbackName, json_escaped]; 
        [self.webview stringByEvaluatingJavaScriptFromString:js];
    }
    
    NSString* retorno = [[NSString alloc] initWithData:dados encoding:NSASCIIStringEncoding];
    
    [retorno release];
}
- (void)falhou: (NSError *) erro { 
    
    UIAlertView* alertView = [[UIAlertView alloc] initWithTitle:@"Aviso" message:[erro localizedDescription] delegate:nil cancelButtonTitle:@"OK" otherButtonTitles:nil, nil];
    [alertView show];
    [alertView release];
    
}


- (void)dealloc {
	[settings release];
	[commandObjects release];
	[super dealloc];
}



@end
