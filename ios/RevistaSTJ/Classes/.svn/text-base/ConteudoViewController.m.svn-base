//
//  ConteudoViewController.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 26/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "ConteudoViewController.h"
#import <QuartzCore/QuartzCore.h>
@implementation ConteudoViewController
@synthesize topico, 
topicos,
webView,
primeiroTopico,
txtPagina,
index,  offset;


- (id) initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
	if (self = [super initWithNibName: nibNameOrNil bundle: nibBundleOrNil]) {
		self.webView = [[[UIWebView alloc] initWithFrame: CGRectMake(0, 0, 320, 367)] autorelease];
		self.webView.backgroundColor = [UIColor whiteColor];
		self.webView.scalesPageToFit = YES;
		self.webView.autoresizingMask = (UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight);
		self.webView.delegate = self;
		[self.view addSubview: self.webView];
		//
		self.offset  = 0;
		self.primeiroTopico = YES;
	}
	return self;
}

- (void)dealloc {
	if (DEBUG)
		NSLog(@"DEALLOC: %@", [self class]);
	[topico release];
	[topicos release];
	[webView release];
	[txtPagina release];
    [super dealloc];
}


- (void) viewDidAppear:(BOOL)animated {
	[self mudaTopico];
}
- (BOOL)textViewShouldBeginEditing:(UITextView *)textView {
	return YES;
}

// ACOES DA TOOLBAR

- (IBAction) botaoAumentaFonte : (id) sender {
	[self mudarTamanhoDaFonte: 100 + PORCENTAGEM_AUMENTO_TEXTO * ++self.offset];
	
	
}						
- (IBAction) botaoDiminuiFonte : (id) sender {
	[self mudarTamanhoDaFonte: 100 + PORCENTAGEM_AUMENTO_TEXTO * --self.offset];
}	

- (IBAction) botaoProximoTopico : (id) sender {
	if (self.index < [self.topicos count] -1) {
		self.index++;
		[self mudaTopico];
	}
}
- (IBAction) botaoTopicoAnterior : (id) sender {
	if (self.index > 0) {
		self.index--;
		[self mudaTopico];
	}
	
	
}

- (void) mudaTopico {
	self.topico	= [self.topicos objectAtIndex: self.index];
	if (self.topico.texto == nil) {
		[self.topico preencherTexto];
	}
	else {
		if (LOAD != nil) {
			[LOAD stop];
			LOAD = nil;
		}
	}
	[self mostrarNaWebView];
	
	self.primeiroTopico = NO;
	self.navigationItem.title = self.topico.nome;
	
	// MOSTRAR PAGINAS (ex 04/05)
	NSString *at,*tt;
	if (self.index+1 < 10) {
		at = [@"0" stringByAppendingFormat: @"%d", self.index+1];
	} else {
		at= [NSString stringWithFormat: @"%d", self.index+1];
	}
	NSInteger tot = [self.topicos count];
	if (tot < 10) {
		tt = [@"0" stringByAppendingFormat: @"%d", tot];
	} else {
		tt= [NSString stringWithFormat: @"%d", tot];
	}
	self.txtPagina.text = [at stringByAppendingFormat: @"/%@",tt];
	
	
	
}
- (void) mudarTamanhoDaFonte : (NSInteger) tamanho {
	NSString *jsString = [[NSString alloc] initWithFormat:@"document.getElementsByTagName('body')[0].style.webkitTextSizeAdjust= '%d%%'", 
                          tamanho];
    [self.webView stringByEvaluatingJavaScriptFromString:jsString];
    [jsString release];
	
}
- (void) mostrarNaWebView  {
	NSMutableString *cont = [[[NSMutableString alloc] initWithString:  
							  @"<html><head><meta name='viewport' content='width=320, initial-scale=1.0' /></head><body>"] autorelease];
	[cont appendFormat: @"<script type='text/javascript'>\ndocument.getElementsByTagName('body')[0].style.webkitTextSizeAdjust= '%d%%'; </script>", 100+PORCENTAGEM_AUMENTO_TEXTO*self.offset];
	[cont appendFormat:@"<p style='padding-left: %dpx; padding-right: %dpx; text-align: justify; font-size:%dpt'> ",ESPACO_TEXTO, ESPACO_TEXTO, TAMANHO_TEXTO]; 
	
	NSString *contBR = [self.topico.texto
						stringByReplacingOccurrencesOfString: QUEBRA_DE_LINHA withString: @"<br/>"];
	contBR = [contBR stringByReplacingOccurrencesOfString: @"   " withString: @"  "];
	contBR = [contBR stringByReplacingOccurrencesOfString: @"  " withString: @"<span style='color:white;'>_</span>"];
	
	[cont appendString: contBR];
	[cont appendString: @"</p>"];
	if (self.primeiroTopico == YES) {
		[cont appendString: @"<script type='text/javascript'>"];
		[cont appendString: @" if ( document.getElementById('marca') != null ) {"];
		[cont appendString: @"function findPosY(obj) { \n var top = 0; \n if(obj.offsetParent) { \n while(1) { \n  top += obj.offsetTop; \n if(!obj.offsetParent) \n break; \n obj = obj.offsetParent; \n}\n } \n else if(obj.y) { \n top += obj.y; \n } \n return top-50; \n}"];
		[cont appendString: @"\n window.scrollTo(0, findPosY( document.getElementById('marca') ) );  \n"];
		[cont appendString: @" } "];
		[cont appendString:@"</script>"];
	}
    [cont appendString: @"</body></html>"];
	
	[self.webView loadHTMLString: cont baseURL:nil];
}

- (void)didReceiveMemoryWarning {
	NSLog(@"AVISO DE MEMORIA: %@", [self class]);
    [super didReceiveMemoryWarning];
}

@end
