//
//  Pushes.h
//  AcompanhamentoProcessual
//
//  Created by Paulo Ferreira on 5/19/10.
//  Copyright 2010 MobileLifeUtils.com. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "CommandController.h"

@interface Pushes : UIViewController <UITableViewDelegate, UITableViewDataSource> {
	CommandController	*ccProvider;
	UITableView			*tvPushes;
	NSMutableArray		*maPushesData;
	NSString			*sNumeroRegistroPush;
	UIView				*vwCarregando;
	UIView				*vwSemRegistros;
	UIBarButtonItem		*bbiRefresh;
	int					iBadgeValue;
}

@property (nonatomic, retain) CommandController	*ccProvider;
@property (nonatomic, retain) UITableView		*tvPushes;
@property (nonatomic, retain) NSMutableArray	*maPushesData;
@property (nonatomic, retain) NSString			*sNumeroRegistroPush;
@property (nonatomic, retain) UIView			*vwCarregando;
@property (nonatomic, retain) UIView			*vwSemRegistros;
@property (nonatomic, retain) UIBarButtonItem	*bbiRefresh;

- (void)updateInterface;
- (void)updatePushes;

@end
