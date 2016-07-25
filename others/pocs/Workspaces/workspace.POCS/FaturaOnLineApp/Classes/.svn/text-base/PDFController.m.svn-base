//
//  PDFController.m
//  FaturaOnLine
//
//  Created by Gabriel Silva on 02/08/10.
//  Copyright 2010 Prime Mobile Solutions. All rights reserved.
//

#import "PDFController.h"


@implementation PDFController

@synthesize wvPDF;

- (void)viewDidLoad {
    [super viewDidLoad];
	
	NSURLRequest *rqVisualizacao;
	NSString *sEndereco = [NSString stringWithString:@"http://localhost:8080/FaturaOnLineMobileSite/res/teste.pdf"];
	rqVisualizacao = [[NSURLRequest alloc] initWithURL:[NSURL URLWithString:[NSString stringWithFormat:@"%@", sEndereco]]];
	
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


@end
