//
//  TwitterViewController.h
//  EasyBand
//
//  Created by Eduardo Carminati on 10/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "UINavViewController.h"

@interface TwitterViewController : UINavViewController <UITableViewDelegate, UITableViewDataSource> {
	UITableView *table;
	NSMutableArray *tweets;
}

@property (nonatomic,retain) UITableView *table;
@property (nonatomic,retain) NSMutableArray *tweets;

- (IBAction) updateTweets: (id) sender;

@end
