//
//  PlaylistVideoController.h
//  EasyBand
//
//  Created by Eduardo Carminati on 17/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "UINavViewController.h"

@interface PlaylistVideoController : UINavViewController <UITableViewDelegate, UITableViewDataSource> {
	NSMutableArray *songs;
	UITableView *table;
}

@property (nonatomic,retain) NSMutableArray *songs;
@property (nonatomic,retain) UITableView *table;

@end
