//
//  VisualizadorProcesso.h
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/30/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface VisualizadorWeb : UIViewController <UIWebViewDelegate> {
	UIWebView	*wvVisualizador;
	NSString	*sTitulo;
	NSString	*sEndereco;
	NSString	*sRegistroProcesso;
	UIView		*vwCarregando;
}

@property (nonatomic, retain) UIWebView *wvVisualizador;
@property (nonatomic, retain) NSString	*sTitulo;
@property (nonatomic, retain) NSString	*sEndereco;
@property (nonatomic, retain) NSString	*sRegistroProcesso;

- (void) setBrowserFrame:(CGRect)frame;

@end
