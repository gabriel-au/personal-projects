//
//  AgendaViewController.h
//  EasyBand
//
//  Created by Eduardo Carminati on 22/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UINavViewController.h"


@interface AgendaViewController : UINavViewController <UITableViewDelegate, UITableViewDataSource> {
	NSMutableArray *agendas;
	UITableView *table;
}

@property (nonatomic,retain) NSMutableArray *agendas;
@property (nonatomic,retain) UITableView *table;

@end
