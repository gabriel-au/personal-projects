//
//  UILoading.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 20/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "UILoading.h"
#import <QuartzCore/QuartzCore.h>


@implementation UILoading

@synthesize texto;

- (id) initWithView: (UIView *) viw {
	if (self = [self initWithView:viw andText:nil]) {}
	return self;
}

- (id) initWithView: (UIView *) viw andText: (NSString *) text {
	if (self = [super initWithFrame: viw.frame]) {
		UIView *telaPreta = [[[UIView alloc] initWithFrame: viw.frame] autorelease];
		telaPreta.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:.70];

	
		float wQuadrado = 240;
		float hQuadrado = 80;
		UIView *quadrado = [[[UIView alloc] initWithFrame: CGRectMake( 0, 0, wQuadrado, hQuadrado)] autorelease];
		quadrado.backgroundColor = [[UIColor grayColor] colorWithAlphaComponent:0.9];
		quadrado.layer.cornerRadius = 6;
		[quadrado setCenter:CGPointMake(160.0,200.0f)];
		[telaPreta addSubview: quadrado];	
		
		float hInd = 30;
		UIActivityIndicatorView *ind = [[[UIActivityIndicatorView alloc]initWithFrame: CGRectMake(0.0f, 0.0f, 30.0f, hInd)] autorelease];
		[ind setCenter:CGPointMake(160.0f,200.0f - hQuadrado/2 + hInd)]; 
		[ind setActivityIndicatorViewStyle:UIActivityIndicatorViewStyleWhiteLarge];
		//	[self.view addSubview:downloadIndicator]; 
		ind.hidden = FALSE; 
		[ind startAnimating]; 
		
		float hLabel = 20;
		texto = [[[UILabel alloc] initWithFrame: CGRectMake (0,0,300,hLabel)] autorelease];
		texto.backgroundColor = [UIColor clearColor];
		[texto setCenter:CGPointMake(160.0f,200.0 + hQuadrado/2 - hLabel)];
		if (text != nil) {
			texto.text = text;
		} else {
			texto.text = MENSAGEM_LOADING_PADRAO;
		}
		texto.textAlignment = UITextAlignmentCenter;
		
		[self addSubview: telaPreta];
		[telaPreta addSubview: ind];
		[telaPreta addSubview:texto];
		[viw addSubview: self];
		
	}
	return self;
}

- (void) stop {
	[self removeFromSuperview];
}

- (void)dealloc {
    [super dealloc];
}


@end

