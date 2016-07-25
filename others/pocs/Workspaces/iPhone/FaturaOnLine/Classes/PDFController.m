//
//  PDFController.m
//  FaturaOnLine
//
//  Created by Gabriel Silva on 02/08/10.
//  Copyright 2010 Prime Mobile Solutions. All rights reserved.
//

#import "PDFController.h"
#import "Mensagens.h"

@implementation PDFController

@synthesize wvPDF;

- (void)viewDidLoad {
    [super viewDidLoad];
	
	//wvPDF.autoresizingMask = (UIViewAutoresizingFlexibleWidth | UIViewAutoresizingFlexibleHeight);
	wvPDF.scalesPageToFit = YES;
	
	
	NSURLRequest *rqVisualizacao;
	
	//NSString *sEndereco = [NSString stringWithString:@"http://localhost:8080/FaturaOnLineMobileSite/res/teste.pdf"];
	NSString *sEndereco = [NSString stringWithString:@"http://diamantina.primetecnologia.net:8080/FaturaOnLineMobileSite/res/teste.pdf"];
	rqVisualizacao = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@", sEndereco]]];
	
	vwCarregando = [[Mensagens alloc] initWithFrame:CGRectMake(0, 0, 200, 130) 
											message:@"Carregando..." 
									   messageFrame:CGRectMake(20, 20, 160, 50) 
								  activityIndicator:YES];
	[vwCarregando setHidden:YES];
	
	[vwCarregando setCenter:CGPointMake(self.view.frame.size.width/2, self.view.frame.size.height/2)];
	
	[self.view addSubview:vwCarregando];
	
	[wvPDF loadRequest:rqVisualizacao];
}

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}

-(void)webViewDidStartLoad:(UIWebView *)webView {
	[wvPDF setUserInteractionEnabled:NO];
	[vwCarregando setHidden:NO];
}

- (void)webViewDidFinishLoad:(UIWebView *)webView {
	[wvPDF setUserInteractionEnabled:YES];
	[vwCarregando setHidden:YES];
}

- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error {
	[wvPDF setUserInteractionEnabled:NO];
	[vwCarregando setHidden:YES];
}


@end
