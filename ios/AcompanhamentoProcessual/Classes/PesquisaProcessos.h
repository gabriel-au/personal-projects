//
//  PesquisaProcessos.h
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/25/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CommandController.h"

@interface PesquisaProcessos : UITableViewController <UITextFieldDelegate, UIPickerViewDelegate, UIPickerViewDataSource, UIActionSheetDelegate> {
	CommandController	*ccProvider;
	NSArray				*aForm;
	UIPickerView		*pvClassesProcesso;
	UIActionSheet		*asClassesProcesso;
	NSMutableArray		*maClassesProcesso;
	UITextField			*tfCurrent;
	int					iIdClasseProcessoSelecionado;
	UIBarButtonItem		*bbiSearch;
}

@property (nonatomic, retain) CommandController	*ccProvider;
@property (nonatomic, retain) NSArray			*aForm;
@property (nonatomic, retain) UIPickerView		*pvClassesProcesso;
@property (nonatomic, retain) UIActionSheet		*asClassesProcesso;
@property (nonatomic, retain) NSMutableArray	*maClassesProcesso;
@property (nonatomic, retain) UITextField		*tfCurrent;
@property (nonatomic, retain) UIBarButtonItem	*bbiSearch;

- (void)updateClassesProcesso;
- (void)pesquisarProcessos;
- (void)apresentarResultados;

@end
