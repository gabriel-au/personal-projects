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

@synthesize texto,vu;

- (id) initWithView: (UIView *) viw {
	if (self = [self initWithView: viw andOrientation: UIInterfaceOrientationPortrait]) {
		
	}
	return self;
}

- (id) initWithView: (UIView *) viw andOrientation: (UIInterfaceOrientation) io {
	if (self = [super initWithFrame: viw.frame]) {
		vu = viw;
		float xtp = 0;
		float ytp = -20;
		float wtp;
		float htp;
		if (io == UIInterfaceOrientationPortrait || io == UIInterfaceOrientationPortraitUpsideDown) {
			wtp = 320;
			htp = 480;
			wtp = viw.frame.size.width ;
			htp = viw.frame.size.height ;
		}
		if (io == UIInterfaceOrientationLandscapeLeft || io == UIInterfaceOrientationLandscapeRight) {
			wtp = 480;
			htp = 320;
			//wtp = viw.frame.size.height ;
			//htp = viw.frame.size.width ;
		}
		
		UIView *telaPreta = [[[UIView alloc] initWithFrame: CGRectMake(xtp,ytp,wtp+ (xtp * -1),htp+ (ytp * -1))] autorelease];
		telaPreta.backgroundColor = [[UIColor blackColor] colorWithAlphaComponent:.00];
		
		
		float wQuadrado = 240;
		float hQuadrado = 80;



		UIView *quadrado = [[[UIView alloc] initWithFrame: CGRectMake( 0, 0, wQuadrado, hQuadrado)] autorelease];
		quadrado.backgroundColor = [[UIColor grayColor] colorWithAlphaComponent:0.9];
		quadrado.layer.cornerRadius = 6;
		[quadrado setCenter:CGPointMake(wtp/2,htp/2)];
		[telaPreta addSubview: quadrado];	
		
		float hInd = 30;
		UIActivityIndicatorView *ind = [[[UIActivityIndicatorView alloc]initWithFrame: CGRectMake(0.0f, 0.0f, 30.0f, hInd)] autorelease];
		[ind setCenter:CGPointMake(wtp/2, htp/2 - hQuadrado/2 + hInd)]; 
		[ind setActivityIndicatorViewStyle:UIActivityIndicatorViewStyleWhiteLarge];
		//	[self.view addSubview:downloadIndicator]; 
		ind.hidden = FALSE; 
		[ind startAnimating]; 
		
		float hLabel = 20;
		texto = [[[UILabel alloc] initWithFrame: CGRectMake (0,0,300,hLabel)] autorelease];
		texto.backgroundColor = [UIColor clearColor];
		[texto setCenter:CGPointMake(wtp/2, htp/2 + hQuadrado/2 - hLabel)];
			texto.text = @"Carregando";
		
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

