//
//  ProcessaArquivo.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 29/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "ProcessaArquivo.h"
#import "VOProcesso.h"
#import "VOTurmaSecao.h"
#import "StringExtras.h"
#import "IndiceViewController.h"
#import "PesquisaViewController.h"
#import "Busca.h"
#import "ResultadoTableViewController.h"
#import "VOResultado.h";

@implementation ProcessaArquivo
@synthesize stream,
voIncompleto,
stringIncompleta,
coe,
processos,
numSecao,
vi,
titulo,
busca,
pes,
voTs,voTopico;

- (id) init {
	if (self = [super init]) {
		self.processos = [[[NSMutableArray alloc] init] autorelease];
	}
	return self;
}

- (void) processaBuffer : (NSArray *) buffer {
	NSEnumerator *e = [buffer objectEnumerator];
	
	VOProcesso *processo;
	if (self.voIncompleto == nil) {
		processo = [[[VOProcesso alloc] init] autorelease];
	} else {
		processo = self.voIncompleto;
	}
	
	BOOL first = YES;
	BOOL primeiraLinhaSecao = YES;
	int contador = 0;
	int total = [buffer count];
	NSString *inc=@"";
	NSString *s; // linha atual
	while (s = [e nextObject]) {
		// caso seja a primeira iteração e a string incompleta estiver preenchida.
		if (first && self.stringIncompleta != nil) {
			s = [ self.stringIncompleta stringByAppendingString: s];
			first = NO;
		}
		// caso seja o último registro do array, break
		if (contador == total -  1) {
			inc=s;
			break;
		}
		NSString *ltrim = [s trimWhiteSpaces];
		// SE FOREM TODAS AS LETRAS MAIUSCULAS (PROCESSO OU SEÇÃO)
		if ([[s uppercaseString] isEqualToString:s] 
			&& [s containsString: @"(...)"] == NO
			&& [s containsString: @"[...]"] == NO
			&& [ltrim isEqualToString: @"\r"] == NO
			&& [ltrim isEqualToString: @""] == NO
			) {
			
			NSString *regex = REGEX_PROCESSO;
			NSPredicate *regextest = [NSPredicate
									  predicateWithFormat:@"SELF MATCHES %@", regex];
			
			// SE FOR UM NOVO PROCESSO
			if ([regextest evaluateWithObject: s] == YES) {
				primeiraLinhaSecao = YES;
				// CRIA UM NOVO VO
				processo = [[[VOProcesso alloc] init] autorelease];
				processo.turmasecao_id = self.voTs.id;
				[self.processos addObject: processo];
				processo.nome = s;
				self.numSecao = 0;
				
				self.voTopico = [[[VOTopico alloc] initWithTempString] autorelease];
				[processo.listaDeTopicos addObject: self.voTopico];
				self.voTopico.nome = MENSAGEM_DESCRICAO;
				
				//[processo.secoes setObject: MENSAGEM_DESCRICAO forKey: @"0"];
				//[processo.conteudo setObject: [[[NSMutableString alloc] initWithString: @""] autorelease]  forKey: @"0"];
			} 
			
			// SE FOR UM NOVO TOPICO
			
			else if (ltrim.length < 15 && ltrim.length > 3)
			{
				
				self.voTopico = [[[VOTopico alloc] initWithTempString] autorelease];
				[processo.listaDeTopicos addObject: self.voTopico];
				primeiraLinhaSecao = YES;
				self.numSecao++;
				//[processo.secoes setObject: ltrim forKey: [NSString stringWithFormat: @"%d", self.numSecao]];
				//[processo.conteudo setObject: [[[NSMutableString alloc] initWithString: @""] autorelease] forKey: [NSString stringWithFormat: @"%d", self.numSecao]];
				self.voTopico.nome = ltrim;
			} 
			[regex release];
			
		}
		// CASO SEJA UMA LINHA NORMAL
		else {
			//NSString *numString = [NSString stringWithFormat: @"%d", self.numSecao];
			NSMutableString *tudo = self.voTopico.textoTemp;
			if (primeiraLinhaSecao == NO) {
				[tudo appendString: QUEBRA_DE_LINHA];
			} else {
				primeiraLinhaSecao = NO;
			}
			
			[tudo appendString: s];
		}
		contador++;
		
	}
	
	// SALVA NA INSTANCIA A ULTIMA LINHA E O ULTIMO VO (que ainda não foram finalizados)
	self.stringIncompleta = inc;
	self.voIncompleto = processo;
}

- (void) processaArquivo:(NSString *)path {
	// iStream is NSInputStream instance variable
	self.stream = [[[NSInputStream alloc] initWithFileAtPath:path] autorelease];
	[self.stream setDelegate:self];
	[self.stream scheduleInRunLoop:[NSRunLoop currentRunLoop]
						   forMode:NSDefaultRunLoopMode];
	[self.stream open];
}

- (void)stream:(NSStream *)str handleEvent:(NSStreamEvent)eventCode
{
	switch(eventCode) {
		case NSStreamEventEndEncountered:
		{
			[str close];
			[str removeFromRunLoop:[NSRunLoop currentRunLoop]
						   forMode:NSDefaultRunLoopMode];
			[str release];
			str = nil;
			break;
		}
		case NSStreamEventHasBytesAvailable:
		{
			uint8_t buf[10240];
			unsigned int len = 0;
			len = [(NSInputStream *)stream  read:buf maxLength:sizeof(buf)];
			if(len != 0) {
				NSString* s = [[[NSString alloc] initWithBytes:buf length:len encoding:NSUTF8StringEncoding] autorelease];	 
				NSArray *sa = [s componentsSeparatedByString: QUEBRA_DE_LINHA];
			    [self processaBuffer: sa];
				
			} 
			
			// FINAL DO PROCESSAMENTO
			else {
				if (DEBUG)
					NSLog(@"FINAL");
				[self.processos count];
				
				for (VOProcesso *vop in self.processos) {
					[vop inserirComTopicos];
				}
				
				
				[self.coe processarProximoArquivo];
				
				/*
				// SE FOI CHAMADA PELO INDICE
				if (self.busca == NO) {
					[self.vi abreIndiceDeProcessos: self.processos comTitulo: self.titulo];
					
				}
				// SE FOI CHAMADA PELA PESQUISA
				else {
					[self.pes buscaEMostraResultado: self.processos comTitulo: self.titulo];
				}
				 */
			}
		}
			
	}
}


+ (void) logArray : (NSMutableArray *) array {
	for (NSString *s in array) {
		NSLog (@"%@", s);
	}
}

@end
