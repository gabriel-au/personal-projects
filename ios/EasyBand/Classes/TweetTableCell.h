//
//  TweetTableCell.h
//  EasyBand
//
//  Created by Eduardo Carminati on 10/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

@class Tweet;
@interface TweetTableCell : UITableViewCell {
	UILabel *titleLabel;
	UILabel *timeLabel;
	UILabel *contentLabel;
	//
	NSInteger *itemID; 
	UIImageView *imageView; // added this
}

- (void) setData: (Tweet *) tweet;
-(UILabel *)newLabelWithPrimaryColor:(UIColor *)primaryColor selectedColor:(UIColor *)selectedColor fontSize:(CGFloat)fontSize bold:(BOOL)bold;

@property (nonatomic, retain) UILabel *titleLabel;
@property (nonatomic, retain) UILabel *timeLabel;
@property (nonatomic, retain) UILabel *contentLabel;
@property (nonatomic, assign) NSInteger *itemID;
@property (nonatomic, retain) UIImageView *imageView;  // added this

@end
