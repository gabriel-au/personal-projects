//
//  VisualizadorProcesso.m
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/30/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import "VisualizadorWeb.h"
#import "Mensagens.h";


@implementation VisualizadorWeb

@synthesize wvVisualizador,
			sTitulo,
			sEndereco,
			sRegistroProcesso;

/*
 // The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if ((self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil])) {
        // Custom initialization
    }
    return self;
}
*/

// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
	UIView *vwContainer = [UIView new];
	[vwContainer setFrame:CGRectMake(0, 0, 320, 367)];
	
	if (sTitulo != nil)
		self.title = sTitulo;
	
	wvVisualizador = [UIWebView new];
	[wvVisualizador setFrame:vwContainer.frame];
	[wvVisualizador setOpaque:NO];
	[wvVisualizador setBackgroundColor:[UIColor clearColor]];
	[wvVisualizador setDelegate:self];
	
	[vwContainer addSubview:wvVisualizador];
	
	NSURLRequest *rqVisualizacao;
	if (sRegistroProcesso != nil) {
		sEndereco = [NSString stringWithString:@"http://m.stj.jus.br/SiteIPhone/Pagina?p=Processos&m=detalhar&parametro="];
		rqVisualizacao = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@%@", sEndereco, sRegistroProcesso]]];
	} else {
		rqVisualizacao = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithString:sEndereco]]];
	}
	
	vwCarregando = [[Mensagens alloc] initWithFrame:CGRectMake(0, 0, 200, 130) 
											message:@"Carregando, por favor aguarde..." 
									   messageFrame:CGRectMake(20, 20, 160, 50) 
								  activityIndicator:YES];
	[vwCarregando setHidden:YES];
	
	[vwCarregando setCenter:CGPointMake(vwContainer.frame.size.width/2, vwContainer.frame.size.height/2)];
	
	[vwContainer addSubview:vwCarregando];
	
	[wvVisualizador loadRequest:rqVisualizacao];
	
	self.view = vwContainer;
}

#pragma mark -
#pragma mark Custom

- (void) setBrowserFrame:(CGRect)frame {
	[wvVisualizador setFrame:frame];
}

/*
// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
}
*/

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
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}

#pragma mark -
#pragma mark UIWebViewDelegate

-(void)webViewDidStartLoad:(UIWebView *)webView {
	[wvVisualizador setUserInteractionEnabled:NO];
	[vwCarregando setHidden:NO];
}

- (void)webViewDidFinishLoad:(UIWebView *)webView {
	[wvVisualizador setUserInteractionEnabled:YES];
	[vwCarregando setHidden:YES];
}

- (void)webView:(UIWebView *)webView didFailLoadWithError:(NSError *)error {
	[wvVisualizador setUserInteractionEnabled:NO];
	[vwCarregando setHidden:YES];
}

@end
