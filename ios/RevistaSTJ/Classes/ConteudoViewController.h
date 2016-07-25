//
//  ConteudoViewController.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 26/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "VOProcesso.h"
#import "VOTopico.h"

@interface ConteudoViewController : UIViewController <UITextViewDelegate, UIWebViewDelegate> {

	NSInteger index;
	NSInteger offset;

	//
	IBOutlet UITextField *txtPagina;
	IBOutlet UIWebView *webView;
	NSMutableArray *topicos;
	VOTopico *topico;
	BOOL primeiroTopico;
}

@property (nonatomic,retain) NSMutableArray *topicos;
@property (nonatomic,retain) VOTopico *topico;

@property (nonatomic) BOOL primeiroTopico;
@property (nonatomic,retain) IBOutlet UIWebView *webView;
@property (nonatomic,retain) IBOutlet UITextField *txtPagina;
@property (nonatomic) NSInteger offset;
@property (nonatomic) NSInteger index;


- (IBAction) botaoAumentaFonte : (id) sender;
- (IBAction) botaoDiminuiFonte : (id) sender;

- (IBAction) botaoProximoTopico : (id) sender;
- (IBAction) botaoTopicoAnterior : (id) sender;

- (void) mudaTopico;
- (void) mostrarNaWebView;
- (void) mudarTamanhoDaFonte : (NSInteger) tamanho;

@end
