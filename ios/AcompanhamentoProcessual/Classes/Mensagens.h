//
//  Mensagens.h
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/19/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import <UIKit/UIKit.h>


@interface Mensagens : UIView {
	UILabel					*lblMensagem;
	UIActivityIndicatorView *aivAtividade;
}

@property (nonatomic, retain) UILabel					*lblMensagem;
@property (nonatomic, retain) UIActivityIndicatorView	*aivAtividade;

- (id)initWithFrame:(CGRect)frame message:(NSString *)sMessage 
	   messageFrame:(CGRect)cgrMessageFrame 
  activityIndicator:(BOOL)bIndicator;

@end
