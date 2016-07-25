//
//  TweetTableCell.m
//  EasyBand
//
//  Created by Eduardo Carminati on 10/12/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "TweetTableCell.h"
#import "Tweet.h"
@implementation TweetTableCell

// we need to synthesize the two labels
@synthesize titleLabel, contentLabel, timeLabel;
@synthesize itemID;
@synthesize imageView;

- (id)initWithFrame:(CGRect)frame reuseIdentifier:(NSString *)reuseIdentifier {
	if (self = [super initWithFrame:frame reuseIdentifier:reuseIdentifier]) {
		self.itemID = 0;

		UIView *myContentView = self.contentView;

		self.titleLabel = [self newLabelWithPrimaryColor:[UIColor blackColor] selectedColor:[UIColor whiteColor] fontSize:13.0 bold:NO]; 
		//	self.titleLabel.textAlignment = UITextAlignmentRight; // default
		[myContentView addSubview:self.titleLabel];
		[self.titleLabel release];
		

		UIImage *noPicImageSmall = [[UIImage imageNamed:@"nopic-small.png"] retain];
		
		self.imageView = [[UIImageView alloc] initWithFrame: CGRectMake(10,5,50,30)];
		imageView.image = noPicImageSmall;
		[myContentView addSubview:self.imageView];
		[self.imageView release];
	}
	
	return self;
}


- (void)setSelected:(BOOL)selected animated:(BOOL)animated {
	[super setSelected:selected animated:animated];
}


- (void) setData: (Tweet *) tweet {
	self.titleLabel.text = tweet.content;
	self.timeLabel.text = tweet.date;
	self.imageView.image = tweet.image;
}


- (void)layoutSubviews {
	[super layoutSubviews];
	
    if (!self.editing) {
        CGFloat boundsX = 10;
		CGRect frame;
		
		float imgWH = 50;
        float lblx = imgWH + 2*boundsX;
		frame = CGRectMake(lblx, 5, 320 - lblx - 30,imgWH);
		self.titleLabel.frame = frame;
		self.titleLabel.numberOfLines = 5;
		frame = CGRectMake(boundsX + 10, 28, 200, 14);

		self.imageView.frame = CGRectMake(boundsX, 5, imgWH,imgWH);
	}
}


- (UILabel *)newLabelWithPrimaryColor:(UIColor *)primaryColor selectedColor:(UIColor *)selectedColor fontSize:(CGFloat)fontSize bold:(BOOL)bold
{
    UIFont *font;
    if (bold) {
        font = [UIFont boldSystemFontOfSize:fontSize];
    } else {
        font = [UIFont systemFontOfSize:fontSize];
    }
    
	UILabel *newLabel = [[UILabel alloc] initWithFrame:CGRectMake(0, 0, 320, 10)];
	newLabel.backgroundColor = [UIColor whiteColor];
	newLabel.opaque = YES;
	newLabel.textColor = primaryColor;
	newLabel.highlightedTextColor = selectedColor;
	newLabel.font = font;
	
	return newLabel;
}


- (void)dealloc {
	// make sure you free the memory
	[titleLabel dealloc];
	//[urlLabel dealloc];
	[imageView dealloc];
	[super dealloc];
}


@end
