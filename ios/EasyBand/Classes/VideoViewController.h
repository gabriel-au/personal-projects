//
//  VideoViewController.h
//  EasyBand
//
//  Created by Eduardo Carminati on 08/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UINavViewController.h"

@interface VideoViewController : UINavViewController <UITableViewDelegate, UITableViewDataSource> {
	UITableView *table;
	NSMutableArray *videos;
}

@property (nonatomic,retain) UITableView *table;
@property (nonatomic,retain) NSMutableArray *videos;

@end
