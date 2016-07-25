//
//  ResultadoPesquisaProcessos.h
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/30/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CommandController.h"


@interface ResultadoPesquisaProcessos : UIViewController <UITableViewDelegate, UITableViewDataSource> {
	UITableView			*tvResultadoPesquisa;
	NSMutableArray		*maResultadoPesquisa;
	CommandController	*ccProvider;
	NSIndexPath			*ipClicked;
}

@property (nonatomic, retain) UITableView		*tvResultadoPesquisa;
@property (nonatomic, retain) NSMutableArray	*maResultadoPesquisa;
@property (nonatomic, retain) CommandController	*ccProvider;
@property (nonatomic, retain) NSIndexPath		*ipClicked;

- (void)cadastraProcesso:(id)sender;
- (void)resultadoCadastro;

@end
