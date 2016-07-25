//
//  ResultadoTableViewController.h
//  RevistaSTJ
//
//  Created by Eduardo Carminati C Ribeiro on 09/04/10.
//  Copyright 2010 primetecnologia. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "DetalhaProcessoViewController.h"
@class ConteudoViewController;
@class PesquisaViewController;
 
@interface ResultadoTableViewController : UITableViewController <UITableViewDelegate, UITableViewDataSource> {
	UILabel *lblNotFound;
	
	
	NSMutableArray *listaResultados;
	
	//NSMutableDictionary< String , NSMutableArray<VOResultado> >
	NSMutableDictionary *dicionarioTabela;
	NSMutableArray *sectionList;
	NSMutableArray *indexList;
	
	DetalhaProcessoViewController *detalhaVc;
	ConteudoViewController *conteudoVc;
	PesquisaViewController *viewAnterior;
}
@property (nonatomic,retain) NSMutableArray *indexList;
@property (nonatomic,retain) UILabel *lblNotFound;
@property (nonatomic,retain) PesquisaViewController *viewAnterior;
@property (nonatomic,retain) ConteudoViewController *conteudoVc;
@property (nonatomic,retain) DetalhaProcessoViewController *detalhaVc;
@property (nonatomic,retain) NSMutableDictionary *dicionarioTabela;
@property (nonatomic,retain) NSMutableArray *sectionList;
@property (nonatomic,retain) NSMutableArray *listaResultados;

- (UITableViewCell*) CreateMultilinesCell :(NSString*)cellIdentifier;
- (int) heightOfCellWithTitle :(NSString*)titleText  andSubtitle:(NSString*)subtitleText;

@end
