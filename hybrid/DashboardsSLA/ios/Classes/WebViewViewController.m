//
//  WebViewViewController.m
//  WebView
//
//  Created by Alexandre Oliveira on 5/10/11.
//  Copyright (c) 2011 BRQ IT Services. All rights reserved.
//

#import "WebViewViewController.h"


@implementation WebViewViewController

@synthesize webView,loader;

/* 
	Responde a todas as requisições feitas pelo cliente. (javascript)
	Caso a requisição utilize o schema "brq", a mesma é tratada diferenciada.
	Caso seja uma requisição normal, o método ignora a requisição e o fluxo é seguido normalmente.
*/
- (BOOL)webView:(UIWebView *)webView shouldStartLoadWithRequest:(NSURLRequest *)request navigationType:(UIWebViewNavigationType)navigationType {
    
    NSURL *url = [request URL];
    
	// verifica se a requisição responde ao schema desejado.
    if ([[url scheme] isEqualToString:@"brq"]) { 
        
        InvokedUrlCommand* iuc = [[InvokedUrlCommand newFromUrl:url] autorelease];

		[loader execute:iuc];
        
        return NO;        

    }
                
    return YES;
    
}    



/*
 Carrega a página principal do sistema buscando no mainBundle.
*/
- (void)viewDidLoad {
    [super viewDidLoad];
	
	self.loader = [[Loader alloc] initWithView:self.webView usingFile:@"index.html" inDirectory:@"poccentral"];
	
	[webView setDelegate:self];
    
    for (id subview in webView.subviews)
        if ([[subview class] isSubclassOfClass: [UIScrollView class]])
            ((UIScrollView *)subview).bounces = NO;

    

}

// Ensure that the view controller supports rotation and that the split view can therefore show in both portrait and landscape.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    return (interfaceOrientation == UIInterfaceOrientationLandscapeLeft);
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
    webView.delegate = nil; // 
    [webView release];
	[loader release];
    [super dealloc];
}

@end
