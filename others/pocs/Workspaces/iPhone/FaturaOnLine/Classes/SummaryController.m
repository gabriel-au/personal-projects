//
//  SummaryController.m
//  FaturaOnLine
//
//  Created by Gabriel Silva on 15/07/10.
//  Copyright 2010 Prime Mobile Solutions. All rights reserved.
//

#import "SummaryController.h"
#import "Mensagens.h"

@implementation SummaryController

@synthesize wvSummary;


/*
// The designated initializer. Override to perform setup that is required before the view is loaded.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if ((self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil])) {
        // Custom initialization
    }
    return self;
}
*/

/*
// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
}
*/

- (NSString *) getPath {
	return [[[NSBundle mainBundle] resourcePath] stringByAppendingPathComponent: @"summary.html"];
}

// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
/*- (void)viewDidLoad {
    [super viewDidLoad];

	NSURLRequest *rqVisualizacao;
	NSString *sEndereco = [NSString stringWithString:@"http://localhost:8080/FaturaOnLineMobileSite/summary.jsp"];
	rqVisualizacao = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@", sEndereco]]];
	
	//rqVisualizacao = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@", [self getPath]]]];
	//[wvSummary loadRequest:[NSURLRequest requestWithURL:[NSURL fileURLWithPath:[[NSBundle mainBundle] pathForResource:@"summary" ofType:@"html"]isDirectory:NO]]];
	
	[wvSummary loadRequest:rqVisualizacao];
}*/

- (void)viewWillAppear:(BOOL)animated {
	[super viewDidLoad];
	
	NSURLRequest *rqVisualizacao;
	
	//NSString *sEndereco = [NSString stringWithString:@"http://localhost:8080/FaturaOnLineMobileSite/summary.jsp"];
	NSString *sEndereco = [NSString stringWithString:@"http://diamantina.primetecnologia.net:8080/FaturaOnLineMobileSite/summary.jsp"];
	rqVisualizacao = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@", sEndereco]]];
	
	//rqVisualizacao = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@", [self getPath]]]];
	//[wvSummary loadRequest:[NSURLRequest requestWithURL:[NSURL fileURLWithPath:[[NSBundle mainBundle] pathForResource:@"summary" ofType:@"html"]isDirectory:NO]]];
	
	vwCarregando = [[Mensagens alloc] initWithFrame:CGRectMake(0, 0, 200, 130) 
											message:@"Carregando..." 
									   messageFrame:CGRectMake(20, 20, 160, 50) 
								  activityIndicator:YES];
	[vwCarregando setHidden:YES];
	
	[vwCarregando setCenter:CGPointMake(self.view.frame.size.width/2, self.view.frame.size.height/2)];
	
	[self.view addSubview:vwCarregando];
	
	[wvSummary loadRequest:rqVisualizacao];
}

/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

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
	[wvSummary setUserInteractionEnabled:NO];
	[vwCarregando setHidden:NO];
}

- (void)webViewDidFinishLoad:(UIWebView *)webView {
	[wvSummary setUserInteractionEnabled:YES];
	[vwCarregando setHidden:YES];
}

- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error {
	[wvSummary setUserInteractionEnabled:NO];
	[vwCarregando setHidden:YES];
}

@end
