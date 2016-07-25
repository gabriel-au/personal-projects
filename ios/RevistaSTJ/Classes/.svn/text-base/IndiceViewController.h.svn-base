//
//  IndiceViewController.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 26/03/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "FileUtil.h"
#import "ProcessaArquivo.h"
#import "IndiceProcessoViewController.h"
#import "UILoading.h"
@interface IndiceViewController : UIViewController < UIPickerViewDelegate, UIPickerViewDataSource > {

	IBOutlet UINavigationBar *nav;
	IBOutlet UIButton *botao;
	IBOutlet UIPickerView *pv;
	IBOutlet UILabel *lblEdicao;
	IBOutlet IndiceProcessoViewController *processoVc;

	NSMutableArray *listaTabela;
	NSInteger indexSel;
	NSInteger edicao_id;
	NSString *tituloTela;

}
@property (nonatomic,retain) NSString *tituloTela;
@property (nonatomic,retain) IBOutlet UILabel *lblEdicao;
@property (nonatomic,retain) 	IBOutlet UIPickerView *pv;
@property (nonatomic) 	NSInteger edicao_id;
@property (nonatomic, retain) IBOutlet UIButton *botao;
@property (nonatomic) NSInteger indexSel;
@property (nonatomic,retain) NSMutableArray *listaTabela;
@property (nonatomic,retain) IBOutlet UINavigationBar *nav;
@property (nonatomic,retain) IBOutlet IndiceProcessoViewController *processoVc;


- (IBAction) cliqueBotao : (id) sender ;
- (void) abreIndiceDeProcessos: (NSMutableArray *) processos comTitulo: (NSString *) titulo;


@end

