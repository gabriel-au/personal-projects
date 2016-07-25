//
//  Inicio.m
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/26/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import "Inicio.h"
#import "VisualizadorWeb.h"

@implementation Inicio

@synthesize ccProvider;

- (id)init {
	self.title = @"Início";
	return self;
}

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
	UIView *vwInicio = [[UIView alloc] initWithFrame:CGRectMake(0, 0, 320, 480)];
	VisualizadorWeb *vcVisualizador = [VisualizadorWeb new];
	vcVisualizador.sEndereco = @"http://m.stj.jus.br/SiteIPhone/";
	
	[vwInicio addSubview:vcVisualizador.view];
	self.view =  vwInicio;
}

/*
// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
}
*/

- (void)viewWillAppear:(BOOL)animated {
	[ccProvider setIdResponseClass:self];
	[ccProvider setADidReadSelectors:nil];
	[ccProvider setADisconnectedSelectors:nil];
	[ccProvider setAReconnectSelectors:nil];
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
    [super viewDidUnload];
    // Release any retained subviews of the main view.
    // e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}


@end
