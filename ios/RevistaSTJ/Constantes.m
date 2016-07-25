//
//  Constantes.m
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 22/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import "Constantes.h"
#import "UILoading.h"

@implementation Constantes
	UILoading * LOAD;
	BOOL const DEBUG = YES;
	BOOL const BAIXAR_ARQUIVO = NO;
	NSString * const MARCA_TEXTO = @"<span id='marca' style='background-color: yellow;'>texto</span>";
	float const TEMPO_SPLASH = 2.6; //em segundos
	// processamento
	NSString * const QUEBRA_DE_LINHA = @"\n";
	// conteúdo (zoom)
	int const TAMANHO_TEXTO = 15;
	int const ESPACO_TEXTO = 5;
	int const PORCENTAGEM_AUMENTO_TEXTO = 20;
	// regex para o número do proceso
	NSString * const REGEX_PROCESSO = @".*[(][0-9]{4}[/][0-9]{7}[-][0-9]{1}[)].*";
	// separadores do menu
	NSString * const MENU_SEPARA_ITEM = @"$";
	NSString * const MENU_SEPARA_NOME = @"#";
	NSString * const MENU_NOME_ARQUIVO = @"indice.txt";
	// mensagens
	NSString * const MENSAGEM_LOADING_PADRAO = @"Carregando...";
	NSString * const MENSAGEM_DESCRICAO = @"DESCRIÇÃO";
	
@end
