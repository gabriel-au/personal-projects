//
//  TransactionsController.m
//  FaturaOnLine
//
//  Created by Gabriel Silva on 02/08/10.
//  Copyright 2010 Prime Tecnologia. All rights reserved.
//

#import "TransactionsController.h"
#import "Mensagens.h"


@implementation TransactionsController

@synthesize wvTransactions;

/*- (void)viewDidLoad {
    [super viewDidLoad];
	
	NSURLRequest *rqVisualizacao;
	NSString *sEndereco = [NSString stringWithString:@"http://localhost:8080/FaturaOnLineMobileSite/transactions.jsp"];
	rqVisualizacao = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@", sEndereco]]];
	
	[wvTransactions loadRequest:rqVisualizacao];
}*/

- (void)viewWillAppear:(BOOL)animated {
	[super viewDidLoad];
	
	NSURLRequest *rqVisualizacao;
	
	//NSString *sEndereco = [NSString stringWithString:@"http://localhost:8080/FaturaOnLineMobileSite/transactions.jsp"];
	NSString *sEndereco = [NSString stringWithString:@"http://diamantina.primetecnologia.net:8080/FaturaOnLineMobileSite/transactions.jsp"];
	rqVisualizacao = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@", sEndereco]]];
	
	vwCarregando = [[Mensagens alloc] initWithFrame:CGRectMake(0, 0, 200, 130) 
											message:@"Carregando..." 
									   messageFrame:CGRectMake(20, 20, 160, 50) 
								  activityIndicator:YES];
	[vwCarregando setHidden:YES];
	
	[vwCarregando setCenter:CGPointMake(self.view.frame.size.width/2, self.view.frame.size.height/2)];
	
	[self.view addSubview:vwCarregando];
	
	[wvTransactions loadRequest:rqVisualizacao];
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
	[wvTransactions setUserInteractionEnabled:NO];
	[vwCarregando setHidden:NO];
}

- (void)webViewDidFinishLoad:(UIWebView *)webView {
	[wvTransactions setUserInteractionEnabled:YES];
	[vwCarregando setHidden:YES];
}

- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error {
	[wvTransactions setUserInteractionEnabled:NO];
	[vwCarregando setHidden:YES];
}

@end
