//
//  VOResultado.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 08/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "VOResultado.h"


@implementation VOResultado
@synthesize topico,
topicos,
textoBusca,
linhaOcorrencia;

- (id) init {
	if (self = [super init]) {
		self.topico = [[VOTopico alloc] init] ;
	}
	return self;
}

- (void) preencheOcorrencia: (NSString *) txt {
	NSRange localiza = [self.topico.texto rangeOfString: txt options: NSCaseInsensitiveSearch];
	NSRange newRange;
	if (localiza.location + localiza.length + 50 > [self.topico.texto length] -1) {
		newRange = NSMakeRange(localiza.location, [self.topico.texto length] - localiza.location -1);
	} else {
		newRange = NSMakeRange(localiza.location, localiza.length + 50);
	}
	self.linhaOcorrencia  = [self.topico.texto substringWithRange: newRange];
	self.linhaOcorrencia = [self.linhaOcorrencia stringByReplacingOccurrencesOfString: QUEBRA_DE_LINHA withString: @""];
	self.linhaOcorrencia = [NSString stringWithFormat: @"%@%@%@", @"...", self.linhaOcorrencia, @"..."];
}

- (void) dealloc {
	[self.topico release];
	if ([self.topico retainCount] > 0) {
		[self.topico release];
	}

	
	[super dealloc];
}

@end
