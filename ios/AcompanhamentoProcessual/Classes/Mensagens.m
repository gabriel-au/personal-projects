//
//  Mensagens.m
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/19/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import "Mensagens.h"
#import <QuartzCore/QuartzCore.h>

@implementation Mensagens

@synthesize lblMensagem,
			aivAtividade;

- (id)initWithFrame:(CGRect)frame message:(NSString *)sMessage messageFrame:(CGRect)cgrMessageFrame activityIndicator:(BOOL)bIndicator {
    if ((self = [super initWithFrame:frame])) {
		[self setBackgroundColor:[[UIColor blackColor] autorelease]];
		[self setAlpha:0.8];
		self.layer.cornerRadius = 5;
		
		lblMensagem = [[UILabel alloc] initWithFrame:cgrMessageFrame];
		[lblMensagem setBackgroundColor:[[UIColor clearColor] autorelease]];
		[lblMensagem setTextAlignment:UITextAlignmentCenter];
		[lblMensagem setText:sMessage];
		[lblMensagem setNumberOfLines:0];
		[lblMensagem setTextColor:[[UIColor whiteColor] autorelease]];
		
		[self addSubview:lblMensagem];
		
		if (bIndicator) {
			aivAtividade = [[UIActivityIndicatorView alloc] initWithActivityIndicatorStyle:UIActivityIndicatorViewStyleWhiteLarge];
			[aivAtividade startAnimating];
			[aivAtividade setFrame:CGRectMake(0, 0, 30, 30)];
			[aivAtividade setCenter:CGPointMake(self.frame.size.width/2, self.frame.size.height - aivAtividade.frame.size.height)];
			[self addSubview:aivAtividade];
		}
    }
    return self;
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect {
    // Drawing code
}
*/

- (void)dealloc {
    [super dealloc];
}


@end
