//
//  Processos.h
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/19/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CommandController.h"

@interface Processos : UIViewController <UITableViewDelegate, UITableViewDataSource, UIActionSheetDelegate> {
	CommandController	*ccProvider;
	UITableView			*tvProcessos;
	NSMutableArray		*maProcessosData;
	NSMutableArray		*maOABsCadastradas;
	NSMutableArray		*maProcessosCadastrados;
	NSArray				*aSections;
	UIView				*vwCarregando;
	UIView				*vwSemRegistros;
	UIBarButtonItem		*bbiRefresh;
	UIBarButtonItem		*bbiAdd;
	UIBarButtonItem		*bbiEdit;
}

@property (nonatomic, retain) CommandController	*ccProvider;
@property (nonatomic, retain) UITableView		*tvProcessos;
@property (nonatomic, retain) NSMutableArray	*maProcessosData;
@property (nonatomic, retain) NSMutableArray	*maOABsCadastradas;
@property (nonatomic, retain) NSMutableArray	*maProcessosCadastrados;
@property (nonatomic, retain) NSArray			*aSections;
@property (nonatomic, retain) UIView			*vwCarregando;
@property (nonatomic, retain) UIView			*vwSemRegistros;
@property (nonatomic, retain) UIBarButtonItem	*bbiRefresh;
@property (nonatomic, retain) UIBarButtonItem	*bbiAdd;
@property (nonatomic, retain) UIBarButtonItem	*bbiEdit;

- (void)updateInterface;
- (void)updateProcessos;
- (void)cadastrarProcesso;
- (void)editaProcessos;

@end
